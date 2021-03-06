package pl.grzeslowski.jsupla.protocol.impl.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientDecoderImplTest extends DecoderTest<SuplaRegisterClientDecoderImpl> {
    @InjectMocks SuplaRegisterClientDecoderImpl decoder;

    @Override
    public SuplaRegisterClientDecoderImpl getDecoder() {
        return decoder;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_ACCESSID_PWD_MAXSIZE);
        offset += SUPLA_ACCESSID_PWD_MAXSIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_CLIENT_NAME_MAXSIZE);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;

        verify(primitiveDecoder).copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
    }

    @Override
    public int entitySize() {
        return SuplaRegisterClient.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaRegisterClientDecoderImpl(null);
    }
}