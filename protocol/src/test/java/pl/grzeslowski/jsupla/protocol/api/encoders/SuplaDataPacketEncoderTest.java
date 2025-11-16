package pl.grzeslowski.jsupla.protocol.api.encoders;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SuplaDataPacketEncoderTest {
    private final SuplaDataPacketEncoder encoder = SuplaDataPacketEncoder.INSTANCE;

    @Test
    public void shouldEncodePacketFieldsInOrder() {
        // given
        byte[] payload = new byte[]{0x12, 0x34, 0x56};
        SuplaDataPacket packet = new SuplaDataPacket(
            (short) 0xAB,
            0x01020304L,
            0x05060708L,
            payload.length,
            payload);

        // when
        byte[] encoded = encoder.encode(packet);

        // then
        assertThat(encoded).hasSize(packet.size());
        assertThat(PrimitiveDecoder.INSTANCE.parseUnsignedByte(encoded, 0)).isEqualTo((short) 0xAB);
        assertThat(PrimitiveDecoder.INSTANCE.parseUnsignedInt(encoded, 1)).isEqualTo(0x01020304L);
        assertThat(PrimitiveDecoder.INSTANCE.parseUnsignedInt(encoded, 5)).isEqualTo(0x05060708L);
        assertThat(PrimitiveDecoder.INSTANCE.parseUnsignedInt(encoded, 9)).isEqualTo(payload.length);
        assertThat(Arrays.copyOfRange(encoded, 13, encoded.length)).containsExactly(payload);
    }
}
