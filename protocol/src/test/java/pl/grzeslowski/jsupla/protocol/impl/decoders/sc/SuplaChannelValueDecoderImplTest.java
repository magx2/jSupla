package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValueDecoderImplTest extends DecoderTest<SuplaChannelValueDecoderImpl> {
    @InjectMocks SuplaChannelValueDecoderImpl decoder;
    @Mock SuplaChannelValueDecoder channelValueDecoder;

    @Override
    public SuplaChannelValueDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        // @formatter:off
        given(channelValueDecoder.decode(eq(bytes), anyInt())).willReturn(
                new pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue(
                                                                                 new byte[SUPLA_CHANNELVALUE_SIZE],
                                                                                 new byte[SUPLA_CHANNELVALUE_SIZE]));
        // @formatter:on
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(channelValueDecoder).decode(bytes, offset);
    }

    @Override
    public int entitySize() {
        return SuplaChannelValue.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelValueDecoderImpl(null, channelValueDecoder);
    }

    public void shouldThrowNpeWhenChannelValueDecoderIsNull() throws Exception {
        new SuplaChannelValueDecoderImpl(primitiveDecoder, null);
    }
}