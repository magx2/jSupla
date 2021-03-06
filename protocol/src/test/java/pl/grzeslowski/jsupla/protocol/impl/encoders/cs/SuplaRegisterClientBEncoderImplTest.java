package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientBEncoderImplTest extends EncoderTest<SuplaRegisterClientB> {
    @InjectMocks private SuplaRegisterClientBEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaRegisterClientB proto) {
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
        verify(primitiveEncoder).writeBytes(proto.serverName, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientBEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaRegisterClientB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaRegisterClientB getProto() {
        return new SuplaRegisterClientB(
                                               1,
                                               new byte[SUPLA_ACCESSID_PWD_MAXSIZE],
                                               new byte[SUPLA_GUID_SIZE],
                                               new byte[SUPLA_CLIENT_NAME_MAXSIZE],
                                               new byte[SUPLA_SOFTVER_MAXSIZE],
                                               new byte[SUPLA_SERVER_NAME_MAXSIZE]);
    }
}