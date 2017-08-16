package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.ds.FirmwareUpdateParamsDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.FirmwareUpdateParams;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class FirmwareUpdateParamsDecoderImpl implements FirmwareUpdateParamsDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public FirmwareUpdateParamsDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public FirmwareUpdateParams decode(byte[] bytes, int offset) {
        final byte platform = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int param1 = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int param2 = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int param3 = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int param4 = primitiveDecoder.parseInt(bytes, offset);

        return new FirmwareUpdateParams(platform, param1, param2, param3, param4);
    }
}
