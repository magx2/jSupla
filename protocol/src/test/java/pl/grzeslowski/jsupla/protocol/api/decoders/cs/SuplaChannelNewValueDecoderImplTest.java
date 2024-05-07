package pl.grzeslowski.jsupla.protocol.api.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue.SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueDecoderImplTest extends DecoderTest<SuplaChannelNewValueDecoderImpl> {
    @InjectMocks
    SuplaChannelNewValueDecoderImpl decoder;

    @Override
    public SuplaChannelNewValueDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, final int offset) {
        verify(primitiveDecoder).parseByte(bytes, offset);
        verify(primitiveDecoder).copyOfRangeByte(bytes, offset + BYTE_SIZE, offset + BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int entitySize() {
        return SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueDecoderImpl(null);
    }
}