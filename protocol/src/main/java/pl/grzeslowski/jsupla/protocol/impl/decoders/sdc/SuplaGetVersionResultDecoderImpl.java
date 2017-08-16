package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaGetVersionResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class SuplaGetVersionResultDecoderImpl implements SuplaGetVersionResultDecoder {
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

        final byte[] softVer = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);

        return new SuplaGetVersionResult(protoVersionMin, protoVersion, softVer);
    }
}
