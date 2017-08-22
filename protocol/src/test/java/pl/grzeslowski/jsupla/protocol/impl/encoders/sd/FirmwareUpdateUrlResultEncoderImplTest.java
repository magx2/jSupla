package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.FirmwareUpdateUrlEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class FirmwareUpdateUrlResultEncoderImplTest extends EncoderTest<FirmwareUpdateUrlResult> {
    @InjectMocks FirmwareUpdateUrlResultEncoderImpl encoder;
    @Mock FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        // @formatter:off
        given(firmwareUpdateUrlEncoder.encode(any(FirmwareUpdateUrl.class))).willAnswer(
            __ -> new byte[FirmwareUpdateUrl.SIZE]
        );
        // @formatter:on
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final FirmwareUpdateUrlResult proto) {
        int offset = 0;

        verify(primitiveEncoder).writeByte(proto.exists, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(firmwareUpdateUrlEncoder).encode(proto.url);
        verify(primitiveEncoder).writeBytes(new byte[proto.url.size()], bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new FirmwareUpdateUrlResultEncoderImpl(null, firmwareUpdateUrlEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenFirmwareUpdateUrlEncoderIsNull() throws Exception {
        new FirmwareUpdateUrlResultEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<FirmwareUpdateUrlResult> getEncoder() {
        return encoder;
    }

    @Override
    public FirmwareUpdateUrlResult getProto() {
        return new FirmwareUpdateUrlResult(
                                                  (byte) 1,
                                                  new FirmwareUpdateUrl(
                                                                               (byte) 1,
                                                                               new byte[SUPLA_URL_HOST_MAXSIZE],
                                                                               9090,
                                                                               new byte[SUPLA_URL_PATH_MAXSIZE]
                                                  )
        );
    }
}