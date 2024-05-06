package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaEventEncoderImplTest extends EncoderTest<SuplaEvent> {
    @InjectMocks
    SuplaEventEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaEvent proto) {

        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.event, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.channelId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.durationMs, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.senderId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.senderNameSize, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.senderName, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaEventEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaEvent> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaEvent getProto() {
        return new SuplaEvent(
            1,
            2,
            3,
            4,
            5,
            new byte[5]
        );
    }
}