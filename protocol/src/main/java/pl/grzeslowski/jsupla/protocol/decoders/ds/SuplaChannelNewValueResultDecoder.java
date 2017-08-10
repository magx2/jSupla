package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaChannelNewValueResult;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelNewValueResultDecoder implements DeviceServerDecoder<SuplaChannelNewValueResult> {
    @Override
    public SuplaChannelNewValueResult decode(byte[] bytes, int offset) {
        final short channelNumber = PrimitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int senderId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte success = PrimitiveDecoder.parseByte(bytes, offset);

        return new SuplaChannelNewValueResult(channelNumber, senderId, success);
    }
}
