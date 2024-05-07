package pl.grzeslowski.jsupla.protocol.api.decoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelGroupRelationDecoderImplTest extends ProperDecoderTest<SuplaChannelGroupRelation> {
    @InjectMocks
    SuplaChannelGroupRelationDecoderImpl decoder;
    byte eol;
    int channelGroupId;
    int channelId;

    @Override
    public SuplaChannelGroupRelationDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaChannelGroupRelation.SIZE;
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];

        eol = RANDOM_SUPLA.nextByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(eol, bytes, offset);

        channelGroupId = RANDOM_SUPLA.nextInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(channelGroupId, bytes, offset);

        channelId = RANDOM_SUPLA.nextInt();
        PrimitiveEncoderImpl.INSTANCE.writeInteger(channelId, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaChannelGroupRelation entity) {
        assertThat(entity.eol).isEqualTo(eol);
        assertThat(entity.channelGroupId).isEqualTo(channelGroupId);
        assertThat(entity.channelId).isEqualTo(channelId);
    }
}