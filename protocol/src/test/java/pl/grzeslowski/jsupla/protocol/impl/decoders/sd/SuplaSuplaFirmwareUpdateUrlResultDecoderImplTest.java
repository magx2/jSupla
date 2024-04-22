package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaSuplaFirmwareUpdateUrlResultDecoderImplTest extends DecoderTest<FirmwareUpdateUrlResultDecoderImpl> {
    @InjectMocks
    FirmwareUpdateUrlResultDecoderImpl decoder;
    @Mock
    FirmwareUpdateUrlDecoder firmwareUpdateUrlDecoder;

    @Override
    public FirmwareUpdateUrlResultDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, final int offset) {
        given(firmwareUpdateUrlDecoder.decode(eq(bytes), anyInt())).willReturn(
            new SuplaFirmwareUpdateUrl((byte) 1,
                new byte[SUPLA_URL_HOST_MAXSIZE],
                9090,
                new byte[SUPLA_URL_PATH_MAXSIZE]));
    }

    @Override
    public void verifyParseEntity(final byte[] bytes, int offset) {
        verify(primitiveDecoder).parseByte(bytes, offset);
        offset += BYTE_SIZE;

        verify(firmwareUpdateUrlDecoder).decode(bytes, offset);
    }

    @Override
    public int entitySize() {
        return SuplaFirmwareUpdateUrlResult.SIZE;
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new FirmwareUpdateUrlResultDecoderImpl(null, firmwareUpdateUrlDecoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenFirmwareUpdateUrlDecoderIsNull() throws Exception {
        new FirmwareUpdateUrlResultDecoderImpl(primitiveDecoder, null);
    }
}