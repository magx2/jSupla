package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static java.util.Arrays.copyOfRange;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

public abstract class ProperDecoderTest<T extends ProtoWithSize> {
    public abstract ProtoWithSizeDecoder<T> getDecoder();

    public abstract int entitySize();

    @Test
    public void shouldParseEntity() throws Exception {

        // given
        int offset = RANDOM_SUPLA.nextInt(100);
        byte[] bytes = givenParseEntity(offset);

        // when
        final T entity = getDecoder().decode(bytes, offset);

        // then
        verifyParseEntity(entity);
    }

    protected abstract byte[] givenParseEntity(final int offset);

    protected abstract void verifyParseEntity(final T entity);

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmall() throws Exception {

        // given
        final byte[] original = givenParseEntity(0);
        final byte[] bytes = copyOfRange(original, 0, original.length - 1);

        // when
        getDecoder().decode(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmallAfterAddingOffset() throws Exception {

        // given
        final byte[] original = givenParseEntity(1);
        final byte[] bytes = copyOfRange(original, 0, original.length - 1);

        // when
        getDecoder().decode(bytes, 1);
    }
}
