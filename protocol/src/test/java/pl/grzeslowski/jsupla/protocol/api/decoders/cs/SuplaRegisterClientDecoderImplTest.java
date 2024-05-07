package pl.grzeslowski.jsupla.protocol.api.decoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaRegisterClientDecoderImplTest extends DecoderTest<SuplaRegisterClientDecoderImpl> {
    @InjectMocks
    SuplaRegisterClientDecoderImpl decoder;

    @Override
    public SuplaRegisterClientDecoderImpl getDecoder() {
        return decoder;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_ACCESSID_PWD_MAXSIZE);
        offset += SUPLA_ACCESSID_PWD_MAXSIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_CLIENT_NAME_MAXSIZE);
        offset += SUPLA_CLIENT_NAME_MAXSIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
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