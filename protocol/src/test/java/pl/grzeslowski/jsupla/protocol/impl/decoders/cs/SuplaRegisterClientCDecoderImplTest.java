package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("WeakerAccess")
public class SuplaRegisterClientCDecoderImplTest extends ProperDecoderTest<SuplaRegisterClientC> {
    @InjectMocks SuplaRegisterClientCDecoderImpl decoder;

    byte[] email;
    byte[] authKey;
    byte[] guid;
    byte[] name;
    byte[] softVer;
    byte[] serverName;

    SuplaRegisterClientC entity;

    @Before
    public void setUp() {
        email = RANDOM_SUPLA.nextByteArray(SUPLA_EMAIL_MAXSIZE);
        authKey = RANDOM_SUPLA.nextByteArray(SUPLA_AUTHKEY_SIZE);
        guid = RANDOM_SUPLA.nextByteArray(SUPLA_GUID_SIZE);
        name = RANDOM_SUPLA.nextByteArray(SUPLA_CLIENT_NAME_MAXSIZE);
        softVer = RANDOM_SUPLA.nextByteArray(SUPLA_SOFTVER_MAXSIZE);
        serverName = RANDOM_SUPLA.nextByteArray(SUPLA_SERVER_NAME_MAXSIZE);

        entity = new SuplaRegisterClientC(
                email,
                authKey,
                guid,
                name,
                softVer,
                serverName
        );
    }

    @Override
    public ProtoWithSizeDecoder<SuplaRegisterClientC> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaRegisterClientC.SIZE;
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];

        offset += INSTANCE.writeBytes(email, bytes, offset);
        offset += INSTANCE.writeBytes(authKey, bytes, offset);
        offset += INSTANCE.writeBytes(guid, bytes, offset);
        offset += INSTANCE.writeBytes(name, bytes, offset);
        offset += INSTANCE.writeBytes(softVer, bytes, offset);
        offset += INSTANCE.writeBytes(serverName, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaRegisterClientC entity) {
        assertThat(entity).isEqualTo(this.entity);
    }
}