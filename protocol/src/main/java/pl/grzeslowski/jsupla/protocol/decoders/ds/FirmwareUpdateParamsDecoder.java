package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.FirmwareUpdateParams;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class FirmwareUpdateParamsDecoder implements DeviceServerDecoder<FirmwareUpdateParams> {
    @Override
    public FirmwareUpdateParams decode(byte[] bytes, int offset) {
        final byte platform = PrimitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int param1 = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int param2 = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int param3 = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int param4 = PrimitiveDecoder.parseInt(bytes, offset);

        return new FirmwareUpdateParams(platform, param1, param2, param3, param4);
    }
}
