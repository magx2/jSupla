package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceDEncoderImplTest extends EncoderTest<SuplaRegisterDeviceD> {
    @InjectMocks SuplaRegisterDeviceDEncoderImpl encoder;
    @Mock SuplaDeviceChannelBEncoder suplaDeviceChannelBEncoder;

    SuplaRegisterDeviceD proto;

    @Before
    public void setUp() {
        given(suplaDeviceChannelBEncoder.encode(any()))
            .willAnswer(invocationOnMock -> {
                final SuplaDeviceChannelB channel = invocationOnMock.getArgumentAt(0, SuplaDeviceChannelB.class);
                return new byte[channel.size()];
            });
        final short channelCount = (short) (RANDOM_SUPLA.nextUnsignedByte((short) 100) + 1);
        proto = new SuplaRegisterDeviceD(
            RANDOM_SUPLA.nextByteArray(SUPLA_EMAIL_MAXSIZE),
            RANDOM_SUPLA.nextByteArray(SUPLA_AUTHKEY_SIZE),
            RANDOM_SUPLA.nextByteArray(SUPLA_GUID_SIZE),
            RANDOM_SUPLA.nextByteArray(SUPLA_DEVICE_NAME_MAXSIZE),
            RANDOM_SUPLA.nextByteArray(SUPLA_SOFTVER_MAXSIZE),
            RANDOM_SUPLA.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE),
            channelCount,
            Stream.generate(() -> RANDOM_SUPLA.nextObject(SuplaDeviceChannelB.class))
                .limit(channelCount)
                .toArray(SuplaDeviceChannelB[]::new)
        );
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterDeviceD proto) {
        int offset = 0;
        verify(primitiveEncoder).writeBytes(proto.email, bytesToWriteInto(), offset);
        offset += SUPLA_EMAIL_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.authKey, bytesToWriteInto(), offset);
        offset += SUPLA_AUTHKEY_SIZE;
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
            verify(suplaDeviceChannelBEncoder).encode(channel);
            verify(primitiveEncoder).writeBytes(new byte[channel.size()], bytesToWriteInto(), offset);
            offset += channel.size();
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterDeviceDEncoderImpl(null, suplaDeviceChannelBEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenEncoderIsNull() {
        new SuplaRegisterDeviceDEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaRegisterDeviceD> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterDeviceD getProto() {
        return proto;
    }
}
