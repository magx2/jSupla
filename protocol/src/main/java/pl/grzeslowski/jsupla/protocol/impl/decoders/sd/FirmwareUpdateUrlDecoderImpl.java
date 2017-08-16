package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sd.FirmwareUpdateUrlDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrl;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public final class FirmwareUpdateUrlDecoderImpl implements FirmwareUpdateUrlDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public FirmwareUpdateUrlDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public FirmwareUpdateUrl decode(byte[] bytes, int offset) {
        final byte availableProtocols = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] host = Arrays.copyOfRange(bytes, offset, offset + SUPLA_URL_HOST_MAXSIZE);
        offset += SUPLA_URL_HOST_MAXSIZE;

        final int port = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] path = Arrays.copyOfRange(bytes, offset, offset + SUPLA_URL_PATH_MAXSIZE);

        return new FirmwareUpdateUrl(availableProtocols, host, port, path);
    }
}
