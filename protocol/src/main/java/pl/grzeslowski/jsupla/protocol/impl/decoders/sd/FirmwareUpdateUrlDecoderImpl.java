package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public final class FirmwareUpdateUrlDecoderImpl implements FirmwareUpdateUrlDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public FirmwareUpdateUrlDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public FirmwareUpdateUrl decode(byte[] bytes, int offset) {
        final byte availableProtocols = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] host = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_URL_HOST_MAXSIZE);
        offset += SUPLA_URL_HOST_MAXSIZE;

        final int port = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] path = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_URL_PATH_MAXSIZE);

        return new FirmwareUpdateUrl(availableProtocols, host, port, path);
    }
}
