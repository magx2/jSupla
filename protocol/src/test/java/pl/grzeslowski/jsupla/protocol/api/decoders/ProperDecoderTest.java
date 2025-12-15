package pl.grzeslowski.jsupla.protocol.api.decoders;

import static java.util.Arrays.copyOfRange;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

abstract class ProperDecoderTest<T extends ProtoWithSize> {
    abstract ProtoWithSizeDecoder<T> getDecoder();

    abstract int entitySize();

    @Test
    void shouldParseEntity() {

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

    @Test
    void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmall() {

        // given
        final byte[] original = givenParseEntity(0);
        final byte[] bytes = copyOfRange(original, 0, original.length - 1);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> getDecoder().decode(bytes, 0));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmallAfterAddingOffset() {

        // given
        final byte[] original = givenParseEntity(1);
        final byte[] bytes = copyOfRange(original, 0, original.length - 1);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> getDecoder().decode(bytes, 1));
    }
}
