package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientCEncoderImplTest extends EncoderTest<SuplaRegisterClientC> {
    @InjectMocks SuplaRegisterClientCEncoderImpl encoder;
    final SuplaRegisterClientC proto = RANDOM_SUPLA.nextObject(SuplaRegisterClientC.class);

    @SuppressWarnings("UnusedAssignment")
    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterClientC proto) {
        int offset = 0;
        verify(primitiveEncoder).writeBytes(proto.email, bytesToWriteInto(), offset);
        offset += SUPLA_EMAIL_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.authKey, bytesToWriteInto(), offset);
        offset += SUPLA_AUTHKEY_SIZE;
        verify(primitiveEncoder).writeBytes(proto.guid, bytesToWriteInto(), offset);
        offset += SUPLA_GUID_SIZE;
        verify(primitiveEncoder).writeBytes(proto.name, bytesToWriteInto(), offset);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.softVer, bytesToWriteInto(), offset);
        offset += SUPLA_SOFTVER_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.serverName, bytesToWriteInto(), offset);
        offset += SUPLA_SERVER_NAME_MAXSIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientCEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaRegisterClientC> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterClientC getProto() {
        return proto;
    }
}