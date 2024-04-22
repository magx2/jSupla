package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueEncoderImplTest extends EncoderTest<SuplaChannelNewValue> {
    @InjectMocks
    SuplaChannelNewValueEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelNewValue proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.senderId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.channelNumber, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.durationMs, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaChannelNewValue> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelNewValue getProto() {
        return new SuplaChannelNewValue(1, (short) 2, 3, new byte[SUPLA_CHANNELVALUE_SIZE]);
    }
}