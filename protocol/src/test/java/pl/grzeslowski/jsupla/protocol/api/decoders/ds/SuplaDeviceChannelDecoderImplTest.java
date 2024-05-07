package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaDeviceChannelDecoderImplTest extends DecoderTest<SuplaDeviceChannelDecoderImpl> {
    @InjectMocks
    SuplaDeviceChannelDecoderImpl decoder;

    @Override
    public SuplaDeviceChannelDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public int entitySize() {
        return SuplaDeviceChannel.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDeviceChannelDecoderImpl(null);
    }
}