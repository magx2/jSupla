package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class FirmwareUpdateUrlEncoderImplTest extends EncoderTest<FirmwareUpdateUrl> {
    @InjectMocks FirmwareUpdateUrlEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final FirmwareUpdateUrl proto) {
        int offset = 0;

        verify(primitiveEncoder).writeByte(proto.availableProtocols, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeBytes(proto.host, bytesToWriteInto(), offset);
        offset += proto.host.length;
        verify(primitiveEncoder).writeInteger(proto.port, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.path, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new FirmwareUpdateUrlEncoderImpl(null);
    }

    @Override
    public Encoder<FirmwareUpdateUrl> getEncoder() {
        return encoder;
    }

    @Override
    public FirmwareUpdateUrl getProto() {
        return new FirmwareUpdateUrl(
                                            (byte) 1,
                                            new byte[SUPLA_URL_HOST_MAXSIZE],
                                            9090,
                                            new byte[SUPLA_URL_PATH_MAXSIZE]
        );
    }
}