package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaDataPacketDecoderImplTest extends DecoderTest<SuplaDataPacketDecoderImpl> {
    private static final long DATA_SIZE = 5L;
    @InjectMocks SuplaDataPacketDecoderImpl decoder;

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

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + (int) DATA_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaDataPacket.MIN_SIZE + ProtoConsts.SUPLA_TAG_SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDataPacketDecoderImpl(null);
    }
}