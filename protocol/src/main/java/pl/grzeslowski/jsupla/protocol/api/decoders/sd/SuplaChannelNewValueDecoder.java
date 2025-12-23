package pl.grzeslowski.jsupla.protocol.api.decoders.sd;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;

@javax.annotation.Generated(
        value = "Struct original name: TSD_SuplaChannelNewValue",
        date = "2025-12-23T10:16:20.828157300+01:00[Europe/Warsaw]")
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaChannelNewValueDecoder implements ServerDeviceDecoder<SuplaChannelNewValue> {
    public static final SuplaChannelNewValueDecoder INSTANCE = new SuplaChannelNewValueDecoder();

    @Override
    public SuplaChannelNewValue decode(byte[] bytes, int offset) {
        int senderId = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        short channelNumber = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += CHAR_SIZE;

        // union start
        Long durationMs = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        Long durationSec = null;
        offset += INT_SIZE;
        // union end

        byte[] value =
                PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                        bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += value.length;

        return new SuplaChannelNewValue(senderId, channelNumber, durationMs, durationSec, value);
    }
}
