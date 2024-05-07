package pl.grzeslowski.jsupla.protocol.api.encoders.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceBEncoderImplTest extends EncoderTest<SuplaRegisterDeviceB> {
    @InjectMocks
    SuplaRegisterDeviceBEncoderImpl encoder;
    @Mock
    SuplaDeviceChannelBEncoder deviceChannelBEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        // @formatter:off
        given(deviceChannelBEncoder.encode(any(SuplaDeviceChannelB.class))).willAnswer(
            __ -> new byte[SuplaDeviceChannelB.SIZE]
        );
        // @formatter:on
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterDeviceB proto) {
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
        for (SuplaDeviceChannelB channel : proto.channels) {
            final byte[] channelBytes = deviceChannelBEncoder.encode(channel);
            verify(primitiveEncoder).writeBytes(channelBytes, bytesToWriteInto(), offset);
            offset += SuplaDeviceChannelB.SIZE;
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceBEncoderImpl(null, deviceChannelBEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaDeviceChannelBEncoderIsNull() throws Exception {
        new SuplaRegisterDeviceBEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaRegisterDeviceB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterDeviceB getProto() {
        final short channelCount = 100;
        final SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[channelCount];
        for (int i = 0; i < channelCount; i++) {
            channels[i] = RANDOM_SUPLA.nextObject(SuplaDeviceChannelB.class);
        }
        return new SuplaRegisterDeviceB(2,
            new byte[SUPLA_LOCATION_PWD_MAXSIZE],
            new byte[SUPLA_GUID_SIZE],
            new byte[SUPLA_DEVICE_NAME_MAXSIZE],
            new byte[SUPLA_SOFTVER_MAXSIZE],
            channelCount,
            channels);
    }
}