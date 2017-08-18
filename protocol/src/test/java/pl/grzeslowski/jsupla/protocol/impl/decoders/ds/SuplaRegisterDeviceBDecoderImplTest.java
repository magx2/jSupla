package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceBDecoderImplTest extends DecoderTest<SuplaRegisterDeviceBDecoderImpl> {
    private static final short CHANNELS_COUNT = (short) 5;

    @InjectMocks SuplaRegisterDeviceBDecoderImpl decoder;
    @Mock SuplaDeviceChannelBDecoder channelDecoder;

    @Override
    public SuplaRegisterDeviceBDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(primitiveDecoder.parseUnsignedByte(eq(bytes), anyInt())).willReturn(CHANNELS_COUNT);
        given(channelDecoder.decode(eq(bytes), anyInt())).willReturn(
                new SuplaDeviceChannelB((short) 1, 2, 3, 4, new byte[SUPLA_CHANNELVALUE_SIZE]));
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
        offset += BYTE_SIZE;

        for (int i = 0; i < CHANNELS_COUNT; i++) {
            verify(channelDecoder).decode(bytes, offset);
            offset += SuplaDeviceChannelB.SIZE;
        }

    }

    @Override
    public int entitySize() {
        return SuplaRegisterDeviceB.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceBDecoderImpl(null, channelDecoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenChannelDecoderIsNull() throws Exception {
        new SuplaRegisterDeviceBDecoderImpl(primitiveDecoder, null);
    }
}