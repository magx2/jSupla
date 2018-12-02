package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupRelationDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Arrays.stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelGroupRelationPackDecoderImplTest extends ProperDecoderTest<SuplaChannelGroupRelationPack> {
    @InjectMocks
    SuplaChannelGroupRelationPackDecoderImpl decoder;
    @Mock
    SuplaChannelGroupRelationDecoder suplaChannelGroupRelationDecoder;

    int count;
    int totalLeft;
    SuplaChannelGroupRelation[] items;
    byte[][] rawItems;

    @Before
    public void setUp() {
        count = RANDOM_SUPLA.nextPositiveInt(20);
        totalLeft = RANDOM_SUPLA.nextPositiveInt();
        items = RANDOM_SUPLA.objects(SuplaChannelGroupRelation.class, count).toArray(SuplaChannelGroupRelation[]::new);
    }

    @Override
    public ProtoWithSizeDecoder<SuplaChannelGroupRelationPack> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaChannelGroupRelationPack.MIN_SIZE + stream(items).mapToInt(SuplaChannelGroupRelation::size).sum();
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        final byte[] bytes = new byte[entitySize() + offset];
        given(suplaChannelGroupRelationDecoder.decode(any(), anyInt()))
                .willReturn(RANDOM_SUPLA.nextObject(SuplaChannelGroupRelation.class));

        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(count, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(totalLeft, bytes, offset);

        rawItems = new byte[items.length][];
        for (int idx = 0; idx < items.length; idx++) {
            final SuplaChannelGroupRelation item = items[idx];
            int size = item.size();
            byte[] itemBytes = new byte[size];
            for (int i = 0; i < size; i++) {
                itemBytes[i] = RANDOM_SUPLA.nextByte();
            }
            rawItems[idx] = itemBytes;
            given(suplaChannelGroupRelationDecoder.decode(bytes, offset)).willReturn(item);
            offset += PrimitiveEncoderImpl.INSTANCE.writeBytes(itemBytes, bytes, offset);
        }

        return bytes;
    }

    @Override
    protected void verifyParseEntity(SuplaChannelGroupRelationPack entity) {
        assertThat(entity.count).isEqualTo(count);
        assertThat(entity.totalLeft).isEqualTo(totalLeft);
        assertThat(entity.items).isEqualTo(items);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfParameterIsNull() {
        new SuplaChannelGroupRelationPackDecoderImpl(null);
    }
}
