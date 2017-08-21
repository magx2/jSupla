package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceCEncoderImplTest extends EncoderTest<SuplaRegisterDeviceC> {
    @InjectMocks SuplaRegisterDeviceCEncoderImpl encoder;
    @Mock SuplaDeviceChannelBEncoder deviceChannelBEncoder;

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
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterDeviceC proto) {
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
        verify(primitiveEncoder).writeBytes(proto.serverName, bytesToWriteInto(), offset);
        offset += SUPLA_SERVER_NAME_MAXSIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.channelCount, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        for (SuplaDeviceChannelB channel : proto.channels) {
            verify(deviceChannelBEncoder).encode(channel);
            verify(primitiveEncoder).writeBytes(new byte[SuplaDeviceChannelB.SIZE], bytesToWriteInto(), offset);
            offset += SuplaDeviceChannelB.SIZE;
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceCEncoderImpl(null, deviceChannelBEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaDeviceChannelBEncoderIsNull() throws Exception {
        new SuplaRegisterDeviceCEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaRegisterDeviceC> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterDeviceC getProto() {
        final short channelCount = (short) 10;
        final SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[channelCount];
        for (int i = 0; i < channelCount; i++) {
            // @formatter:off
            channels[i] = new SuplaDeviceChannelB(
                    (short) (i * 7), i * 8, i * 5, i * 6, new byte[SUPLA_CHANNELVALUE_SIZE]);
            // @formatter:on
        }
        return new SuplaRegisterDeviceC(
                                               1,
                                               new byte[SUPLA_LOCATION_PWD_MAXSIZE],
                                               new byte[SUPLA_GUID_SIZE],
                                               new byte[SUPLA_DEVICE_NAME_MAXSIZE],
                                               new byte[SUPLA_SOFTVER_MAXSIZE],
                                               new byte[SUPLA_SERVER_NAME_MAXSIZE],
                                               channelCount,
                                               channels
        );
    }
}