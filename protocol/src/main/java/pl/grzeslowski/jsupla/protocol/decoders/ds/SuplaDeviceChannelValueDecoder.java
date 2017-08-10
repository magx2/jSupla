package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelValue;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaDeviceChannelValueDecoder implements DeviceServerDecoder<SuplaDeviceChannelValue> {
    @Override
    public SuplaDeviceChannelValue decode(byte[] bytes, int offset) {
        final short channelNumber = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaDeviceChannelValue(channelNumber, value);
    }
}
