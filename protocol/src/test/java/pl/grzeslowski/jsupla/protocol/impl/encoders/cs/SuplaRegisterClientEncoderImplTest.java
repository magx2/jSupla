package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientEncoderImplTest extends EncoderTest<SuplaRegisterClient> {
    @InjectMocks
    SuplaRegisterClientEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterClient proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.accessId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.accessIdPwd, bytesToWriteInto(), offset);
        offset += SUPLA_ACCESSID_PWD_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.guid, bytesToWriteInto(), offset);
        offset += SUPLA_GUID_SIZE;
        verify(primitiveEncoder).writeBytes(proto.name, bytesToWriteInto(), offset);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;
        verify(primitiveEncoder).writeBytes(proto.softVer, bytesToWriteInto(), offset);
        offset += SUPLA_SOFTVER_MAXSIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaRegisterClient> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterClient getProto() {
        return new SuplaRegisterClient(
            1,
            new byte[SUPLA_ACCESSID_PWD_MAXSIZE],
            new byte[SUPLA_GUID_SIZE],
            new byte[SUPLA_CLIENT_NAME_MAXSIZE],
            new byte[SUPLA_SOFTVER_MAXSIZE]);
    }
}