package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public final class FirmwareUpdateUrlResultDecoderImpl implements FirmwareUpdateUrlResultDecoder {
    private final PrimitiveDecoder primitiveDecoder;
    private final FirmwareUpdateUrlDecoder firmwareUpdateUrlDecoder;

    public FirmwareUpdateUrlResultDecoderImpl(PrimitiveDecoder primitiveDecoder,
                                              FirmwareUpdateUrlDecoder firmwareUpdateUrlDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.firmwareUpdateUrlDecoder = requireNonNull(firmwareUpdateUrlDecoder);
    }

    @Override
    public FirmwareUpdateUrlResult decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + FirmwareUpdateUrlResult.SIZE);

        final byte exists = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final FirmwareUpdateUrl url = firmwareUpdateUrlDecoder.decode(bytes, offset);

        return new FirmwareUpdateUrlResult(exists, url);
    }
}
