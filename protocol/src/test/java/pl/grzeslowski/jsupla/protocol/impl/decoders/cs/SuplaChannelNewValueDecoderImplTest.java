package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueDecoderImplTest extends DecoderTest {
    @InjectMocks SuplaChannelNewValueDecoderImpl decoder;

    @Override
    public void shouldParseEntity() throws Exception {

        // given
        final byte[] bytes = new byte[SuplaChannelNewValue.SIZE];
        final int offset = 5;

        // when
        decoder.decode(bytes, offset);

        // then
        verify(primitiveDecoder).parseByte(bytes, offset);
        verify(primitiveDecoder).copyOfRange(bytes, offset + BYTE_SIZE, offset + BYTE_SIZE + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueDecoderImpl(null);
    }
}