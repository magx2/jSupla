package pl.grzeslowski.jsupla.protocol.api.serialization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

class ProtocolPacketSerializationTest {
    private final ProtocolPacketSerializer serializer = ProtocolPacketSerializer.INSTANCE;
    private final ProtocolPacketDeserializer deserializer = ProtocolPacketDeserializer.INSTANCE;

    @Test
    void suplaDataPacketShouldRoundTrip() {
        byte[] data = new byte[] {1, 2, 3, 4};
        SuplaDataPacket packet =
                new SuplaDataPacket(
                        (short) 28,
                        0x01020304L,
                        SUPLA_DS_CALL_DEVICE_CALCFG_RESULT.getValue(),
                        data.length,
                        data);

        SuplaDataPacket decoded = deserializer.deserialize(serializer.serialize(packet));

        assertThat(decoded.version()).isEqualTo(packet.version());
        assertThat(decoded.rrId()).isEqualTo(packet.rrId());
        assertThat(decoded.callId()).isEqualTo(packet.callId());
        assertThat(decoded.dataSize()).isEqualTo(packet.dataSize());
        assertThat(decoded.data()).containsExactly(packet.data());
    }

    @Test
    void suplaDataPacketShouldUseLittleEndianHeaderFields() {
        SuplaDataPacket packet =
                new SuplaDataPacket(
                        (short) 0x28, 0x01020304L, 0x05060708L, 3, new byte[] {0x11, 0x22, 0x33});

        byte[] encoded = serializer.serialize(packet);

        assertThat(Arrays.copyOfRange(encoded, 0, SUPLA_TAG.length)).containsExactly(SUPLA_TAG);
        assertThat(encoded[SUPLA_TAG.length]).isEqualTo((byte) 0x28);
        assertThat(Arrays.copyOfRange(encoded, SUPLA_TAG.length + 1, SUPLA_TAG.length + 5))
                .containsExactly((byte) 0x04, (byte) 0x03, (byte) 0x02, (byte) 0x01);
        assertThat(Arrays.copyOfRange(encoded, SUPLA_TAG.length + 5, SUPLA_TAG.length + 9))
                .containsExactly((byte) 0x08, (byte) 0x07, (byte) 0x06, (byte) 0x05);
        assertThat(Arrays.copyOfRange(encoded, SUPLA_TAG.length + 9, SUPLA_TAG.length + 13))
                .containsExactly((byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00);
    }

    @Test
    void malformedPacketShouldRejectBadTag() {
        SuplaDataPacket packet = new SuplaDataPacket((short) 28, 1, 2, 0, new byte[0]);
        byte[] encoded = serializer.serialize(packet);
        encoded[0] = 'X';

        assertThatThrownBy(() -> deserializer.deserialize(encoded))
                .isInstanceOf(ProtocolCodecException.class)
                .hasMessageContaining("Invalid SUPLA start tag");
    }

    @Test
    void malformedPacketShouldRejectTruncatedHeader() {
        byte[] truncated = Arrays.copyOf(SUPLA_TAG, SUPLA_TAG.length + 2);

        assertThatThrownBy(() -> deserializer.deserialize(truncated))
                .isInstanceOf(ProtocolCodecException.class)
                .hasMessageContaining("Truncated TSuplaDataPacket header");
    }

    @Test
    void malformedPacketShouldRejectTruncatedPayload() {
        SuplaDataPacket packet = new SuplaDataPacket((short) 28, 1, 2, 4, new byte[] {1, 2, 3, 4});
        byte[] encoded = serializer.serialize(packet);
        byte[] truncated = Arrays.copyOf(encoded, encoded.length - 2);

        assertThatThrownBy(() -> deserializer.deserialize(truncated))
                .isInstanceOf(ProtocolCodecException.class)
                .hasMessageContaining("Truncated TSuplaDataPacket payload");
    }
}
