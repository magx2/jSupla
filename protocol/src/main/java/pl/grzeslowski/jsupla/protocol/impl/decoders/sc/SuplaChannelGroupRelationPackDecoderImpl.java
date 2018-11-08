package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupRelationDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupRelationPackDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelGroupRelationPackDecoderImpl implements SuplaChannelGroupRelationPackDecoder {
    private final SuplaChannelGroupRelationDecoder suplaChannelGroupRelationDecoder;

    public SuplaChannelGroupRelationPackDecoderImpl(SuplaChannelGroupRelationDecoder suplaChannelGroupRelationDecoder) {
        this.suplaChannelGroupRelationDecoder = requireNonNull(suplaChannelGroupRelationDecoder);
    }

    @Override
    public SuplaChannelGroupRelationPack decode(byte[] bytes, int offset) {
        final int count = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final SuplaChannelGroupRelation[] items = new SuplaChannelGroupRelation[count];
        for (int i = 0; i < count; i++) {
            SuplaChannelGroupRelation item = suplaChannelGroupRelationDecoder.decode(bytes, offset);
            items[i] = item;
            offset += item.size();
        }

        return new SuplaChannelGroupRelationPack(
                count,
                totalLeft,
                items
        );
    }
}
