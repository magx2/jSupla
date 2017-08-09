package pl.grzeslowski.jsupla.protocol.serializers;

import org.junit.Ignore;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

import java.util.Random;

import static java.lang.Byte.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public class SuplaDataPacketCoderTest {

    @Ignore
    @Test
    public void shouldParseEntityIntoArray() {

        // given
        byte version = (byte) (170 + MIN_VALUE);
        int rrId = 15 + Integer.MIN_VALUE; // 00000000 00000000 00000000  00001111
        int callType = 100 + Integer.MIN_VALUE; // 00000000 00000000 00000000 01100100
        int dataSize = 10222 + Integer.MIN_VALUE; // 00000000 00000000 00100111 11101110
        byte[] data = randomBytes(dataSize - Integer.MIN_VALUE);
        final SuplaDataPacket packet = new SuplaDataPacket(version, rrId, callType, dataSize, data);

        // when
        final byte[] bytes = new byte[0];//serializer.serialise(packet);

        // then
        assertThat(bytes).hasSize(packet.size());

        // version
        assertThat(bytes[0]).isEqualTo(version);

        // rrId
        assertThat(bytes[BYTE_SIZE + 3]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + 2]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + 1]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE]).isEqualTo((byte) 15);

        // callType
        assertThat(bytes[BYTE_SIZE + INT_SIZE + 3]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + INT_SIZE + 2]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + INT_SIZE + 1]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + INT_SIZE]).isEqualTo((byte) 100);

        // dataSize
        assertThat(bytes[BYTE_SIZE + INT_SIZE * 2 + 3]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + INT_SIZE * 2 + 2]).isEqualTo((byte) 0);
        assertThat(bytes[BYTE_SIZE + INT_SIZE * 2 + 1]).isEqualTo((byte) 39);
        assertThat(bytes[BYTE_SIZE + INT_SIZE * 2]).isEqualTo((byte) -18);

        // data
        byte[] newData = new byte[dataSize - Integer.MIN_VALUE];
        System.arraycopy(bytes, BYTE_SIZE + INT_SIZE * 3, newData, 0, newData.length);
        assertThat(newData).isEqualTo(data);
    }

    private byte[] randomBytes(int size) {
        final Random random = new Random(1337);
        final byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }
}
