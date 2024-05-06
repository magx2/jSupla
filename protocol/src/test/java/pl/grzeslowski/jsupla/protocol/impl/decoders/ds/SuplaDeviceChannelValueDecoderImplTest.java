package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaDeviceChannelValueDecoderImplTest extends DecoderTest {
    @InjectMocks
    SuplaDeviceChannelValueDecoderImpl decoder;

    @Override
    public ProtoWithSizeDecoder<?> getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaDeviceChannelValue.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDeviceChannelValueDecoderImpl(null);
    }
}