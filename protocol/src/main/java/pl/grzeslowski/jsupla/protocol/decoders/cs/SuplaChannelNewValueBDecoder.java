package pl.grzeslowski.jsupla.protocol.decoders.cs;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.cs.SuplaChannelNewValueB;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class SuplaChannelNewValueBDecoder implements ClientServerDecoder<SuplaChannelNewValueB> {
    @Override
    public SuplaChannelNewValueB decode(byte[] bytes, int offset) {
        final int channelId = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValueB(channelId, value);
    }
}
