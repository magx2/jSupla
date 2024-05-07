package pl.grzeslowski.jsupla.protocol.api.encoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueResultEncoderImplTest extends EncoderTest<SuplaChannelNewValueResult> {
    @InjectMocks
    SuplaChannelNewValueResultEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelNewValueResult proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.channelNumber, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.senderId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeByte(proto.success, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueResultEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaChannelNewValueResult> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelNewValueResult getProto() {
        return new SuplaChannelNewValueResult((short) 2, 3, (byte) 1);
    }
}