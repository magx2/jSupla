package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelDecoderImplTest extends ProperDecoderTest<SuplaChannel> {
    @InjectMocks SuplaChannelDecoderImpl decoder;
    @Mock SuplaChannelValueDecoder channelValueDecoder;
    private byte eol;
    private int id;
    private int locationId;
    private int func;
    private byte online;
    private byte[] caption;
    private SuplaChannelValue value;

    @Override
    public SuplaChannelDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    public void givenParseEntity(final byte[] bytes, int offset) {
        eol = RANDOM_SUPLA.nextByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(eol, bytes, offset);

        id = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(id, bytes, offset);

        locationId = RANDOM_SUPLA.nextInt(1000);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(locationId, bytes, offset);

        func = RANDOM_SUPLA.nextInt(1000);
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(func, bytes, offset);

        online = (byte) (RANDOM_SUPLA.nextBoolean() ? 1 : 0);
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(online, bytes, offset);

        value = RANDOM_SUPLA.nextObject(SuplaChannelValue.class);
        given(channelValueDecoder.decode(eq(bytes), anyInt())).willReturn(value);
        offset += SuplaChannelValue.SIZE;

        caption = RANDOM_SUPLA.nextString(SUPLA_CHANNEL_CAPTION_MAXSIZE).getBytes(UTF_8);
        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedInteger(caption.length, bytes, offset);

        PrimitiveEncoderImpl.INSTANCE.writeBytes(caption, bytes, offset);
    }

    @Override
    protected void verifyParseEntity(final SuplaChannel entity) {
        assertThat(entity.eol).isEqualTo(eol);
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.locationId).isEqualTo(locationId);
        assertThat(entity.func).isEqualTo(func);
        assertThat(entity.online).isEqualTo(online);
        assertThat(entity.value).isEqualTo(value);
        assertThat(entity.captionSize).isEqualTo(caption.length);
        assertThat(entity.caption).isEqualTo(caption);
    }

    @Override
    public int entitySize() {
        return SuplaChannel.MIN_SIZE;
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenChannelValueDecoderIsNull() throws Exception {
        new SuplaChannelDecoderImpl(null);
    }
}