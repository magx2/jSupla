package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupRelationDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaChannelGroupRelationDecoderImpl implements SuplaChannelGroupRelationDecoder {
    public static final SuplaChannelGroupRelationDecoderImpl INSTANCE = new SuplaChannelGroupRelationDecoderImpl();
    @Override
    public SuplaChannelGroupRelation decode(byte[] bytes, int offset) {
        final byte eol = PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int channelGroupId = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int channelId = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);

        return new SuplaChannelGroupRelation(eol, channelGroupId, channelId);
    }
}
