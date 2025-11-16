package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

public class SuplaDataPacketDecoderTest {
    private final SuplaDataPacketDecoder decoder = SuplaDataPacketDecoder.INSTANCE;

    @Test
    public void shouldDecodePacketPayload() {
        short version = 12;
        long rrId = 345L;
        long callId = 678L;
        byte[] data = new byte[] {1, 2, 3, 4};

        byte[] payload =
                ByteBuffer.allocate(1 + 4 + 4 + 4 + data.length)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .put((byte) version)
                        .putInt((int) rrId)
                        .putInt((int) callId)
                        .putInt(data.length)
                        .put(data)
                        .array();

        SuplaDataPacket packet = decoder.decode(payload, 0);

        assertThat(packet.version()).isEqualTo(version);
        assertThat(packet.rrId()).isEqualTo(rrId);
        assertThat(packet.callId()).isEqualTo(callId);
        assertThat(packet.dataSize()).isEqualTo(data.length);
        assertThat(packet.data()).containsExactly(data);
    }
}
