package pl.grzeslowski.jsupla.protocol.api.encoders.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceEncoderImplTest extends EncoderTest<SuplaRegisterDevice> {
    @InjectMocks
    SuplaRegisterDeviceEncoderImpl encoder;
    @Mock
    SuplaDeviceChannelEncoder deviceChannelEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        // @formatter:off
        given(deviceChannelEncoder.encode(any(SuplaDeviceChannel.class))).willAnswer(
            invocationOnMock -> new byte[invocationOnMock.getArgumentAt(0, SuplaDeviceChannel.class).size()]
        );
        // @formatter:on
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterDevice proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.locationId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.locationPwd, bytesToWriteInto(), offset);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.guid, bytesToWriteInto(), offset);
        offset += SUPLA_GUID_SIZE;
        verify(primitiveEncoder).writeBytes(proto.name, bytesToWriteInto(), offset);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.softVer, bytesToWriteInto(), offset);
        offset += SUPLA_SOFTVER_MAXSIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.channelCount, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;

        for (SuplaDeviceChannel channel : proto.channels) {
            verify(deviceChannelEncoder).encode(channel);
            verify(primitiveEncoder).writeBytes(any(byte[].class), any(byte[].class), eq(offset));
            offset += SuplaDeviceChannel.SIZE;
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceEncoderImpl(null, deviceChannelEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenDeviceChannelEncoderIsNull() throws Exception {
        new SuplaRegisterDeviceEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaRegisterDevice> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterDevice getProto() {
        short channelCount = (short) 10;
        final SuplaDeviceChannel[] channels = new SuplaDeviceChannel[channelCount];
        for (int i = 0; i < channelCount; i++) {
            channels[i] = new SuplaDeviceChannel((short) (i * 8), (short) (i * 4), new byte[SUPLA_CHANNELVALUE_SIZE]);
        }
        return new SuplaRegisterDevice(
            1,
            new byte[SUPLA_LOCATION_PWD_MAXSIZE],
            new byte[SUPLA_GUID_SIZE],
            new byte[SUPLA_DEVICE_NAME_MAXSIZE],
            new byte[SUPLA_SOFTVER_MAXSIZE],
            channelCount,
            channels
        );
    }
}