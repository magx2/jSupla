package pl.grzeslowski.jsupla.protocol.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public class SuplaRegisterDeviceResultDecoder implements ServerDeviceDecoder<SuplaRegisterDeviceResult> {
    @Override
    public SuplaRegisterDeviceResult decode(byte[] bytes, int offset) {
        final int resultCode = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte activityTimeout = PrimitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte version = PrimitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte versionMin = PrimitiveDecoder.parseByte(bytes, offset);

        return new SuplaRegisterDeviceResult(resultCode, activityTimeout, version, versionMin);
    }
}
