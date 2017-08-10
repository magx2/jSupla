package pl.grzeslowski.jsupla.protocol.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaChannelNewValue;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelNewValueDecoder implements ServerDeviceDecoder<SuplaChannelNewValue> {
    @Override
    public SuplaChannelNewValue decode(byte[] bytes, int offset) {
        final int senderId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final short channelNumber = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final long durationMs = PrimitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValue(senderId, channelNumber, durationMs, value);
    }
}
