package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaRegisterClientResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaRegisterClientResultDecoderImpl implements SuplaRegisterClientResultDecoder {
    @Override
    public SuplaRegisterClientResult decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaRegisterClientResult.SIZE);

        final int resultCode = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int clientId = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationCount = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelCount = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final short activityTimeout = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short version = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short versionMin = INSTANCE.parseUnsignedByte(bytes, offset);

        return new SuplaRegisterClientResult(
                resultCode,
                clientId,
                locationCount,
                channelCount,
                activityTimeout,
                version,
                versionMin);
    }
}
