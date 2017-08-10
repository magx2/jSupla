package pl.grzeslowski.jsupla.protocol.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrlResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public class FirmwareUpdateUrlResultDecoder implements ServerDeviceDecoder<FirmwareUpdateUrlResult> {
    private final FirmwareUpdateUrlDecoder firmwareUpdateUrlDecoder;

    public FirmwareUpdateUrlResultDecoder(FirmwareUpdateUrlDecoder firmwareUpdateUrlDecoder) {
        this.firmwareUpdateUrlDecoder = requireNonNull(firmwareUpdateUrlDecoder);
    }

    @Override
    public FirmwareUpdateUrlResult decode(byte[] bytes, int offset) {
        final byte exists = PrimitiveParser.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final FirmwareUpdateUrl url = firmwareUpdateUrlDecoder.decode(bytes, offset);

        return new FirmwareUpdateUrlResult(exists, url);
    }
}
