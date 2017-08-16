package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sdc.ServerClientDeviceDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaGetVersionResult;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class SuplaGetVersionResultDecoderImpl implements ServerClientDeviceDecoder<SuplaGetVersionResult> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaGetVersionResultDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaGetVersionResult decode(byte[] bytes, int offset) {
        final short protoVersionMin = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short protoVersion = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);

        return new SuplaGetVersionResult(protoVersionMin, protoVersion, softVer);
    }
}
