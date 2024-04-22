package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_CAPTION_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaLocationDecoderImplTest extends ProperDecoderTest<SuplaLocation> {
    @InjectMocks
    SuplaLocationDecoderImpl decoder;
    private byte eol;
    private int id;
    private byte[] caption;

    @Override
    public SuplaLocationDecoderImpl getDecoder() {
        return decoder;
    }

    @Override
    protected byte[] givenParseEntity(int offset) {
        caption = RANDOM_SUPLA.nextString(SUPLA_LOCATION_CAPTION_MAXSIZE).getBytes(UTF_8);
        final byte[] bytes = new byte[offset + entitySize() + caption.length];

        eol = RANDOM_SUPLA.nextByte();
        offset += PrimitiveEncoderImpl.INSTANCE.writeByte(eol, bytes, offset);

        id = RANDOM_SUPLA.nextPositiveInt();
        offset += PrimitiveEncoderImpl.INSTANCE.writeInteger(id, bytes, offset);

        offset += PrimitiveEncoderImpl.INSTANCE.writeUnsignedInteger(caption.length, bytes, offset);
        PrimitiveEncoderImpl.INSTANCE.writeBytes(caption, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaLocation entity) {
        assertThat(entity.eol).isEqualTo(eol);
        assertThat(entity.id).isEqualTo(id);
        assertThat(entity.captionSize).isEqualTo(caption.length);
        assertThat(entity.caption).isEqualTo(caption);
    }

    @Override
    public int entitySize() {
        return SuplaLocation.MIN_SIZE;
    }

    // @formatter:off
    @Test(expected = IllegalArgumentException.class)
    public void
        shouldShouldThrowIllegalArgumentExceptionIfBytesLengthIsBiggerThanMinSizeButSmallerThanMinSizeWIthCaption() {
        // @formatter:on

        // given
        final int offset = 5;
        final byte[] bytes = givenParseEntity(offset);
        final byte[] tooSmallByte = Arrays.copyOfRange(bytes, 0, bytes.length - 1);

        // when
        decoder.decode(tooSmallByte, offset);
    }
}