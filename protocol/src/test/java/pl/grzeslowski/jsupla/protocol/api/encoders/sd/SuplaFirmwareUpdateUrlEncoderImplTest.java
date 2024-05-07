package pl.grzeslowski.jsupla.protocol.api.encoders.sd;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaFirmwareUpdateUrlEncoderImplTest extends EncoderTest<SuplaFirmwareUpdateUrl> {
    @InjectMocks
    FirmwareUpdateUrlEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaFirmwareUpdateUrl proto) {
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
    public Encoder<SuplaFirmwareUpdateUrl> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaFirmwareUpdateUrl getProto() {
        return new SuplaFirmwareUpdateUrl(
            (byte) 1,
            new byte[SUPLA_URL_HOST_MAXSIZE],
            9090,
            new byte[SUPLA_URL_PATH_MAXSIZE]
        );
    }
}