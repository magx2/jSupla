package pl.grzeslowski.jsupla.protocol.impl.decoders.sd;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sd.ServerDeviceDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaChannelNewValue;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelNewValueDecoderImpl implements ServerDeviceDecoder<SuplaChannelNewValue> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaChannelNewValueDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaChannelNewValue decode(byte[] bytes, int offset) {
        final int senderId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final short channelNumber = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final long durationMs = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelNewValue(senderId, channelNumber, durationMs, value);
    }
}
