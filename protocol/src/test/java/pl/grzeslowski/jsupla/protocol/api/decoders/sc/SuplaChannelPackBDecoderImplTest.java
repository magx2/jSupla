package pl.grzeslowski.jsupla.protocol.api.decoders.sc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATIONPACK_MAXCOUNT;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelPackBDecoderImplTest extends ProperDecoderTest<SuplaChannelPackB> {
    @InjectMocks
    SuplaChannelPackBDecoderImpl decoder;
    @Mock
    SuplaChannelBDecoder suplaChannelBDecoder;

    int count;
    int totalLeft;
    SuplaChannelB[] channels;
    SuplaChannelPackB entity;

    @Before
    public void setUp() {
        count = RANDOM_SUPLA.nextPositiveInt(SUPLA_LOCATIONPACK_MAXCOUNT);
        totalLeft = RANDOM_SUPLA.nextPositiveInt();
        channels = Stream.generate(this::minSuplaChannelB)
            .limit(count)
            .toArray(SuplaChannelB[]::new);
        entity = new SuplaChannelPackB(count, totalLeft, channels);
    }

    @Override
    public ProtoWithSizeDecoder<SuplaChannelPackB> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return entity.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected byte[] givenParseEntity(int offset) {
        given(suplaChannelBDecoder.decode(any(), anyInt())).willReturn(RANDOM_SUPLA.nextObject(SuplaChannelB.class));
        final byte[] bytes = new byte[entitySize() + offset];

        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(count, bytes, offset);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(totalLeft, bytes, offset);
        for (SuplaChannelB channel : channels) {
            final byte[] channelBytes = RANDOM_SUPLA.nextByteArray(channel.size());
            given(suplaChannelBDecoder.decode(bytes, offset)).willReturn(channel);
            offset += PrimitiveEncoderImpl.INSTANCE.writeBytes(channelBytes, bytes, offset);
        }

        return bytes;
    }

    @Override
    protected void verifyParseEntity(SuplaChannelPackB entity) {
        assertThat(entity.count).isEqualTo(count);
        assertThat(entity.totalLeft).isEqualTo(totalLeft);
        assertThat(entity.channels).isEqualTo(channels);
    }

    private SuplaChannelB minSuplaChannelB() {
        return new SuplaChannelB(
            RANDOM_SUPLA.nextByte(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextUnsignedInt(),
            RANDOM_SUPLA.nextUnsignedByte(),
            RANDOM_SUPLA.nextByte(),
            RANDOM_SUPLA.nextObject(SuplaChannelValue.class),
            0,
            new byte[0]
        );
    }
}
