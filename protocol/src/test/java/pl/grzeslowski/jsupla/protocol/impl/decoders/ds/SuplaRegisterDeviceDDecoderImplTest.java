package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterDeviceDDecoderImplTest extends ProperDecoderTest<SuplaRegisterDeviceD> {
    @InjectMocks
    SuplaRegisterDeviceDDecoderImpl decoder;
    @Mock
    SuplaDeviceChannelBDecoder suplaDeviceChannelBDecoder;

    byte[] email;
    byte[] authKey;
    byte[] guid;
    byte[] name;
    byte[] softVer;
    byte[] serverName;
    short channelCount;
    SuplaDeviceChannelB[] channels;

    SuplaRegisterDeviceD entity;

    @Before
    public void setUp() {
        email = RANDOM_SUPLA.nextByteArray(SUPLA_EMAIL_MAXSIZE);
        authKey = RANDOM_SUPLA.nextByteArray(SUPLA_AUTHKEY_SIZE);
        guid = RANDOM_SUPLA.nextByteArray(SUPLA_GUID_SIZE);
        name = RANDOM_SUPLA.nextByteArray(SUPLA_DEVICE_NAME_MAXSIZE);
        softVer = RANDOM_SUPLA.nextByteArray(SUPLA_SOFTVER_MAXSIZE);
        serverName = RANDOM_SUPLA.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE);
        channelCount = RANDOM_SUPLA.nextPositiveByte((byte) 100);
        channels = Stream.generate(() -> RANDOM_SUPLA.nextObject(SuplaDeviceChannelB.class))
            .limit(channelCount)
            .toArray(SuplaDeviceChannelB[]::new);
        entity = new SuplaRegisterDeviceD(
            email,
            authKey,
            guid,
            name,
            softVer,
            serverName,
            channelCount,
            channels
        );
    }

    @Override
    public ProtoWithSizeDecoder<SuplaRegisterDeviceD> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return entity.size();
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        given(suplaDeviceChannelBDecoder.decode(any(), anyInt()))
            .willReturn(RANDOM_SUPLA.nextObject(SuplaDeviceChannelB.class));
        final byte[] bytes = new byte[entitySize() + offset];

        offset += INSTANCE.writeBytes(email, bytes, offset);
        offset += INSTANCE.writeBytes(authKey, bytes, offset);
        offset += INSTANCE.writeBytes(guid, bytes, offset);
        offset += INSTANCE.writeBytes(name, bytes, offset);
        offset += INSTANCE.writeBytes(softVer, bytes, offset);
        offset += INSTANCE.writeBytes(serverName, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(channelCount, bytes, offset);
        for (SuplaDeviceChannelB channel : channels) {
            final byte[] channelBytes = RANDOM_SUPLA.nextByteArray(channel.size());
            given(suplaDeviceChannelBDecoder.decode(bytes, offset)).willReturn(channel);
            offset += PrimitiveEncoderImpl.INSTANCE.writeBytes(channelBytes, bytes, offset);
        }

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaRegisterDeviceD entity) {
        assertThat(entity).isEqualTo(this.entity);
    }
}
