package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceDecoderImplTest extends DecoderTest<SuplaRegisterDeviceDecoderImpl> {
    public static final short NUMBER_OF_CHANNELS = (short) 3;
    @InjectMocks
    SuplaRegisterDeviceDecoderImpl decoder;
    @Mock
    SuplaDeviceChannelDecoder channelDecoder;

    @Override
    public SuplaRegisterDeviceDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseUnsignedByte(any(byte[].class), anyInt())).willReturn(NUMBER_OF_CHANNELS);
        given(channelDecoder.decode(any(byte[].class), anyInt())).willReturn(
            new SuplaDeviceChannel((short) 1, 2, new byte[SUPLA_CHANNELVALUE_SIZE]));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_LOCATION_PWD_MAXSIZE);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        verify(primitiveDecoder).parseUnsignedByte(bytes, offset);
        offset += INT_SIZE;

        for (int i = 0; i < NUMBER_OF_CHANNELS; i++) {
            verify(channelDecoder).decode(bytes, offset);
            offset += SuplaDeviceChannel.SIZE;
        }
    }

    @Override
    public int entitySize() {
        return SuplaRegisterDevice.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceDecoderImpl(null, channelDecoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenChannelDecoderIsNull() throws Exception {
        new SuplaRegisterDeviceDecoderImpl(primitiveDecoder, null);
    }
}