package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientBDecoderImplTest extends DecoderTest<SuplaRegisterClientBDecoderImpl> {
    @InjectMocks
    SuplaRegisterClientBDecoderImpl decoder;

    @Override
    public SuplaRegisterClientBDecoderImpl getDecoder() {
        return decoder;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void verifyParseEntity(final byte[] bytes, final int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE,
            offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE,
            offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE
                + SUPLA_GUID_SIZE,
            offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE +
                SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE,
            offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                + SUPLA_SOFTVER_MAXSIZE);
        verify(primitiveDecoder).copyOfRange(bytes, offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE
                + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                + SUPLA_SOFTVER_MAXSIZE,
            offset + INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                + SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public int entitySize() {
        return SuplaRegisterClientB.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientBDecoderImpl(null);
    }
}