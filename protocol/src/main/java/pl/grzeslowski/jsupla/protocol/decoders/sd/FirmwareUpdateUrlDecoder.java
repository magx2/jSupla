package pl.grzeslowski.jsupla.protocol.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrl;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public class FirmwareUpdateUrlDecoder implements ServerDeviceDecoder<FirmwareUpdateUrl> {
    @Override
    public FirmwareUpdateUrl decode(byte[] bytes, int offset) {
        final byte availableProtocols = PrimitiveParser.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] host = Arrays.copyOfRange(bytes, offset, offset + SUPLA_URL_HOST_MAXSIZE);
        offset += SUPLA_URL_HOST_MAXSIZE;

        final int port = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] path = Arrays.copyOfRange(bytes, offset, offset + SUPLA_URL_PATH_MAXSIZE);

        return new FirmwareUpdateUrl(availableProtocols, host, port, path);
    }
}
