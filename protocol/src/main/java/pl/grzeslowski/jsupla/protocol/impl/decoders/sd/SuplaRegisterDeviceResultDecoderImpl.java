package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sd.SuplaRegisterDeviceResultDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterDeviceResultDecoderImpl implements SuplaRegisterDeviceResultDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaRegisterDeviceResultDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaRegisterDeviceResult decode(byte[] bytes, int offset) {
        final int resultCode = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte activityTimeout = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte version = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte versionMin = primitiveDecoder.parseByte(bytes, offset);

        return new SuplaRegisterDeviceResult(resultCode, activityTimeout, version, versionMin);
    }
}
