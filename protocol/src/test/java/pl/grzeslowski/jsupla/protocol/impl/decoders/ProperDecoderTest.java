package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public abstract class ProperDecoderTest<T extends ProtoWithSize> {
    private static final int RANDOM_INT = 123;
    @Mock protected PrimitiveDecoder primitiveDecoder;

    @Before
    public void init() {
        given(primitiveDecoder.parseUnsignedInt(any(), anyInt())).willAnswer(
                args -> INSTANCE.parseUnsignedInt(
                        args.getArgumentAt(0, byte[].class),
                        args.getArgumentAt(1, Integer.class)
                ));
        given(primitiveDecoder.parseInt(any(), anyInt())).willAnswer(
                args -> INSTANCE.parseInt(
                        args.getArgumentAt(0, byte[].class),
                        args.getArgumentAt(1, Integer.class)
                ));
        given(primitiveDecoder.parseLong(any(), anyInt())).willAnswer(
                args -> INSTANCE.parseLong(
                        args.getArgumentAt(0, byte[].class),
                        args.getArgumentAt(1, Integer.class)
                ));
        given(primitiveDecoder.parseUnsignedByte(any(), anyInt())).willAnswer(
                args -> INSTANCE.parseUnsignedByte(
                        args.getArgumentAt(0, byte[].class),
                        args.getArgumentAt(1, Integer.class)
                ));
        given(primitiveDecoder.parseByte(any(), anyInt())).willAnswer(
                args -> INSTANCE.parseByte(
                        args.getArgumentAt(0, byte[].class),
                        args.getArgumentAt(1, Integer.class)
                ));
        given(primitiveDecoder.copyOfRange(any(), anyInt(), anyInt())).willAnswer(
                args -> INSTANCE.copyOfRange(
                        args.getArgumentAt(0, byte[].class),
                        args.getArgumentAt(1, Integer.class),
                        args.getArgumentAt(2, Integer.class)
                ));
    }

    public abstract ProtoWithSizeDecoder<T> getDecoder();

    public abstract int entitySize();

    @Test
    public void shouldParseEntity() throws Exception {

        // given
        int offset = RANDOM_SUPLA.nextInt(100);
        byte[] bytes = new byte[entitySize() + offset + RANDOM_INT];
        givenParseEntity(bytes, offset);

        // when
        final T entity = getDecoder().decode(bytes, offset);

        // then
        verifyParseEntity(entity);
    }

    protected abstract void givenParseEntity(final byte[] bytes, final int offset);

    protected abstract void verifyParseEntity(final T entity);

    @Test(expected = NullPointerException.class)
    public abstract void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmall() throws Exception {

        // given
        final byte[] bytes = new byte[entitySize() - 1];

        // when
        getDecoder().decode(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenBytesAreTooSmallAfterAddingOffset() throws Exception {

        // given
        final byte[] bytes = new byte[entitySize()];

        // when
        getDecoder().decode(bytes, 1);
    }
}
