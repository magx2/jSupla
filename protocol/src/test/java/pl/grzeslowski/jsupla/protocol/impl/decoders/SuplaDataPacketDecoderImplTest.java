package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_TAG_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaDataPacketDecoderImplTest extends DecoderTest<SuplaDataPacketDecoderImpl> {
    private static final long DATA_SIZE = 5L;
    @InjectMocks
    SuplaDataPacketDecoderImpl decoder;

    @Override
    public SuplaDataPacketDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseUnsignedInt(eq(bytes), anyInt())).willReturn(DATA_SIZE);
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        offset += ProtoConsts.SUPLA_TAG_SIZE;

        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + (int) DATA_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaDataPacket.MIN_SIZE + ProtoConsts.SUPLA_TAG_SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDataPacketDecoderImpl(null);
    }


    @Test
    public void shouldParseTSuplaDataPacketFromArrayWithSuplaTag() {

        // given
        final SuplaDataPacketDecoderImpl decoder = new SuplaDataPacketDecoderImpl(PrimitiveDecoderImpl.INSTANCE);

        byte[] bytes = new byte[SUPLA_TAG_SIZE + BYTE_SIZE + INT_SIZE * 3 + 297];
        int i = 0;

        // supla tag
        bytes[i++] = (byte) 83;
        bytes[i++] = (byte) 85;
        bytes[i++] = (byte) 80;
        bytes[i++] = (byte) 76;
        bytes[i++] = (byte) 65;

        // version
        bytes[i++] = (byte) 5;

        // rrId
        bytes[i++] = (byte) 1;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;

        // callType
        bytes[i++] = (byte) 65;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;

        // dataSize
        bytes[i++] = (byte) 41;
        bytes[i++] = (byte) 1;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;

        // data
        bytes[i++] = (byte) -71;
        bytes[i++] = (byte) 1;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 0;
        bytes[i++] = (byte) 120;
        bytes[i++] = (byte) 120;
        bytes[i++] = (byte) 120;

        for (; i < bytes.length; i++) {
            bytes[i] = 0;
        }

        // when
        final SuplaDataPacket packet = decoder.decode(bytes, 0);

        // then
        assertThat(packet.version).isEqualTo((byte) 5);
        assertThat(packet.rrId).isEqualTo(1);
        assertThat(packet.callType).isEqualTo(65);
        assertThat(packet.dataSize).isEqualTo(297);
        assertThat(packet.data[0]).isEqualTo((byte) -71);
        assertThat(packet.data[1]).isEqualTo((byte) 1);
        assertThat(packet.data[2]).isEqualTo((byte) 0);
        assertThat(packet.data[3]).isEqualTo((byte) 0);
        assertThat(packet.data[4]).isEqualTo((byte) 120);
        assertThat(packet.data[5]).isEqualTo((byte) 120);
        assertThat(packet.data[6]).isEqualTo((byte) 120);
    }
}