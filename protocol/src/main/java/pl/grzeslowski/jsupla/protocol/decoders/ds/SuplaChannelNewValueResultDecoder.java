package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaChannelNewValueResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public class SuplaChannelNewValueResultDecoder implements DeviceServerDecoder<SuplaChannelNewValueResult> {
    @Override
    public SuplaChannelNewValueResult decode(byte[] bytes, int offset) {
        final short channelNumber = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int senderId = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte success = PrimitiveParser.parseByte(bytes, offset);

        return new SuplaChannelNewValueResult(channelNumber, senderId, success);
    }
}
