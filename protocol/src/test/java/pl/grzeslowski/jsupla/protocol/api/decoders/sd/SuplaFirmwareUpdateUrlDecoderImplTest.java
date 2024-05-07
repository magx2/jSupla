package pl.grzeslowski.jsupla.protocol.api.decoders.sd;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaFirmwareUpdateUrlDecoderImplTest extends DecoderTest<FirmwareUpdateUrlDecoderImpl> {
    @InjectMocks
    FirmwareUpdateUrlDecoderImpl decoder;

    @Override
    public FirmwareUpdateUrlDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_URL_HOST_MAXSIZE);
        offset += SUPLA_URL_HOST_MAXSIZE;

        verify(primitiveDecoder).parseInt(bytes, offset);
        offset += INT_SIZE;

        verify(primitiveDecoder).copyOfRangeByte(bytes, offset, offset + SUPLA_URL_PATH_MAXSIZE);
    }

    @Override
    public int entitySize() {
        return SuplaFirmwareUpdateUrl.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new FirmwareUpdateUrlDecoderImpl(null);
    }
}