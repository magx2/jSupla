package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupRelationDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupRelationPackDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelGroupRelationPackDecoderImpl implements SuplaChannelGroupRelationPackDecoder {
    public static final SuplaChannelGroupRelationPackDecoderImpl INSTANCE = 
            new SuplaChannelGroupRelationPackDecoderImpl(SuplaChannelGroupRelationDecoderImpl.INSTANCE);
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
            if (bytes.length - offset < SuplaChannelGroupRelation.SIZE) {
                throw new IllegalArgumentException(format(
                        "Can't parse SuplaChannelGroupRelation from byte array of length %s with offset %s, " +
                                "because length is %s!", bytes.length, offset, SuplaChannelGroupRelation.SIZE));
            }
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
