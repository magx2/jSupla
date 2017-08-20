package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValueDecoderImplTest extends DecoderTest<SuplaChannelValueDecoderImpl> {
    @InjectMocks SuplaChannelValueDecoderImpl decoder;

    @Override
    public SuplaChannelValueDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaChannelValue.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelValueDecoderImpl(null);
    }
}