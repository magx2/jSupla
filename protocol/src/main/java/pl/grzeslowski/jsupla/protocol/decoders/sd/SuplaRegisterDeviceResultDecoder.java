package pl.grzeslowski.jsupla.protocol.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public class SuplaRegisterDeviceResultDecoder implements ServerDeviceDecoder<SuplaRegisterDeviceResult> {
    @Override
    public SuplaRegisterDeviceResult decode(byte[] bytes, int offset) {
        final int resultCode = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte activityTimeout = PrimitiveParser.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte version = PrimitiveParser.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte versionMin = PrimitiveParser.parseByte(bytes, offset);

        return new SuplaRegisterDeviceResult(resultCode, activityTimeout, version, versionMin);
    }
}
