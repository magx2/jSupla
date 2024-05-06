package pl.grzeslowski.jsupla.protocol.impl.encoders;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;

@SuppressWarnings("WeakerAccess")
public abstract class EncoderTest<ProtoT extends ProtoWithSize> {
    @Mock
    protected PrimitiveEncoder primitiveEncoder;

    protected void givenEncodeEntity() {
        given(primitiveEncoder.writeByte(anyByte(), any(), anyInt())).willReturn(BYTE_SIZE);
        given(primitiveEncoder.writeUnsignedByte(anyByte(), any(), anyInt())).willReturn(BYTE_SIZE);
        given(primitiveEncoder.writeInteger(anyInt(), any(), anyInt())).willReturn(INT_SIZE);
        given(primitiveEncoder.writeLong(anyLong(), any(), anyInt())).willReturn(LONG_SIZE);
        given(primitiveEncoder.writeUnsignedInteger(anyInt(), any(), anyInt())).willReturn(INT_SIZE);
        given(primitiveEncoder.writeBytes(any(byte[].class), any(), anyInt())).willAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(final InvocationOnMock invocationOnMock) throws Throwable {
                return invocationOnMock.getArgumentAt(0, byte[].class).length;
            }
        });
    }

    @Test
    public final void shouldEncodeEntity() throws Exception {

        // given
        givenEncodeEntity();
        final ProtoT proto = getProto();

        // when
        final byte[] encode = getEncoder().encode(proto);

        // then
        verifyEncodeEntity(encode, proto);
        verifyNoMoreInteractions(primitiveEncoder);
    }

    protected abstract void verifyEncodeEntity(final byte[] encode, final ProtoT proto);

    @Test(expected = NullPointerException.class)
    public abstract void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception;

    public abstract Encoder<ProtoT> getEncoder();

    public abstract ProtoT getProto();

    protected final byte[] bytesToWriteInto() {
        return bytesToWriteInto(getProto());
    }

    protected final byte[] bytesToWriteInto(ProtoT proto) {
        return new byte[proto.size()];
    }
}
