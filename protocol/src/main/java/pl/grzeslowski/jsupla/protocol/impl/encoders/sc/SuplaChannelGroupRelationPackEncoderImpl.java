package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupRelationEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupRelationPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelGroupRelationPackEncoderImpl implements SuplaChannelGroupRelationPackEncoder {
    public static final SuplaChannelGroupRelationPackEncoderImpl INSTANCE =
            new SuplaChannelGroupRelationPackEncoderImpl(PrimitiveEncoderImpl.INSTANCE,
                    SuplaChannelGroupRelationEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelGroupRelationEncoder suplaChannelGroupRelationEncoder;

    SuplaChannelGroupRelationPackEncoderImpl(final PrimitiveEncoder primitiveEncoder, 
                                             final SuplaChannelGroupRelationEncoder suplaChannelGroupRelationEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.suplaChannelGroupRelationEncoder = requireNonNull(suplaChannelGroupRelationEncoder);
    }

    @Override
    public byte[] encode(final SuplaChannelGroupRelationPack proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.count, bytes, offset);
        offset += primitiveEncoder.writeInteger(proto.totalLeft, bytes, offset);
        for (SuplaChannelGroupRelation item : proto.items) {
            final byte[] itemBytes = suplaChannelGroupRelationEncoder.encode(item);
            offset += primitiveEncoder.writeBytes(itemBytes, bytes, offset);
        }

        return bytes;
    }
}
