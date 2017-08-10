package pl.grzeslowski.jsupla.protocol.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValue;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public class SuplaChannelNewValueDecoder implements ClientServerDecoder<SuplaChannelNewValue> {
    @Override
    public SuplaChannelNewValue decode(byte[] bytes, int offset) {
        final byte channelId = PrimitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValue(channelId, value);
    }
}
