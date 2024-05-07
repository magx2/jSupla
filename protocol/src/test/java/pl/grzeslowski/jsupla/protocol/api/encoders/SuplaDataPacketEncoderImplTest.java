package pl.grzeslowski.jsupla.protocol.api.encoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaDataPacketEncoderImplTest extends EncoderTest<SuplaDataPacket> {
    @InjectMocks
    SuplaDataPacketEncoderImpl mockedEncoder;
    private final SuplaDataPacketEncoderImpl encoder = new SuplaDataPacketEncoderImpl(PrimitiveEncoderImpl.INSTANCE);

    @Test
    public void shouldParseEntityIntoArray() {

        // given
        byte version = (byte) (170);
        int rrId = 15; // 00000000 00000000 00000000 00001111
        int callType = 100; // 00000000 00000000 00000000 01100100
        int dataSize = 10222; // 00000000 00000000 00100111 11101110
        byte[] data = randomBytes(dataSize);
        final SuplaDataPacket packet = new SuplaDataPacket(version, rrId, callType, dataSize, data);

        // when
        final byte[] bytes = encoder.encode(packet);

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
        byte[] newData = new byte[dataSize];
        System.arraycopy(bytes, BYTE_SIZE + INT_SIZE * 3, newData, 0, newData.length);
        assertThat(newData).isEqualTo(data);
    }

    private byte[] randomBytes(int size) {
        final Random random = new Random(1337);
        final byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaDataPacket proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.version, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.rrId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.callType, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.dataSize, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.data, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDataPacketEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaDataPacket> getEncoder() {
        return mockedEncoder;
    }

    @Override
    public SuplaDataPacket getProto() {
        return new SuplaDataPacket((short) 1, 2, 3, 4, new byte[4]);
    }
}
