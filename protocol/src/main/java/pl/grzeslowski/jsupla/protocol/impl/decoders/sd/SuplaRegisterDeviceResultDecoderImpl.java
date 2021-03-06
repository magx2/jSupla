package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaRegisterDeviceResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaEventDecoderImpl;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaRegisterDeviceResultDecoderImpl implements SuplaRegisterDeviceResultDecoder {
    public static final SuplaRegisterDeviceResultDecoderImpl INSTANCE = new SuplaRegisterDeviceResultDecoderImpl(
            PrimitiveDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaRegisterDeviceResultDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaRegisterDeviceResult decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaRegisterDeviceResult.SIZE);

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
