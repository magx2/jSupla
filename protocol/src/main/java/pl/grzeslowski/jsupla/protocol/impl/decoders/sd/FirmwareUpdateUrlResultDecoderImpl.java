package pl.grzeslowski.jsupla.protocol.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrlResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class FirmwareUpdateUrlResultDecoderImpl implements ServerDeviceDecoder<FirmwareUpdateUrlResult> {
    private final PrimitiveDecoder primitiveDecoder;
    private final FirmwareUpdateUrlDecoderImpl firmwareUpdateUrlDecoder;

    public FirmwareUpdateUrlResultDecoderImpl(PrimitiveDecoder primitiveDecoder,
                                              FirmwareUpdateUrlDecoderImpl firmwareUpdateUrlDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.firmwareUpdateUrlDecoder = requireNonNull(firmwareUpdateUrlDecoder);
    }

    @Override
    public FirmwareUpdateUrlResult decode(byte[] bytes, int offset) {
        final byte exists = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final FirmwareUpdateUrl url = firmwareUpdateUrlDecoder.decode(bytes, offset);

        return new FirmwareUpdateUrlResult(exists, url);
    }
}
