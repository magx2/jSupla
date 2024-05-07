package pl.grzeslowski.jsupla.protocol.api.decoders.sc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_PACK_MAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValuePackDecoderImplTest extends ProperDecoderTest<SuplaChannelValuePack> {
    @InjectMocks
    SuplaChannelValuePackDecoderImpl decoder;
    @Mock
    SuplaChannelValueDecoder suplaChannelValueDecoder;

    int count;
    int totalLeft;
    SuplaChannelValue[] items;

    SuplaChannelValuePack entity;

    @Before
    public void setUp() {
        count = RANDOM_SUPLA.nextPositiveInt(SUPLA_CHANNELVALUE_PACK_MAXCOUNT);
        totalLeft = RANDOM_SUPLA.nextPositiveInt();
        items = Stream.generate(() -> RANDOM_SUPLA.nextObject(SuplaChannelValue.class))
            .limit(count)
            .toArray(SuplaChannelValue[]::new);

        entity = new SuplaChannelValuePack(
            count,
            totalLeft,
            items
        );
    }

    @Override
    public ProtoWithSizeDecoder<SuplaChannelValuePack> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return entity.size();
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        given(suplaChannelValueDecoder.decode(any(), anyInt()))
            .willReturn(RANDOM_SUPLA.nextObject(SuplaChannelValue.class));
        final byte[] bytes = new byte[entitySize() + offset];

        offset += INSTANCE.writeInteger(count, bytes, offset);
        offset += INSTANCE.writeInteger(totalLeft, bytes, offset);
        for (SuplaChannelValue item : items) {
            final byte[] itemBytes = RANDOM_SUPLA.nextBytesWithSize(item.size());
            given(suplaChannelValueDecoder.decode(bytes, offset)).willReturn(item);
            offset += INSTANCE.writeBytes(itemBytes, bytes, offset);
        }

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaChannelValuePack entity) {
        assertThat(entity).isEqualTo(this.entity);
    }
}
