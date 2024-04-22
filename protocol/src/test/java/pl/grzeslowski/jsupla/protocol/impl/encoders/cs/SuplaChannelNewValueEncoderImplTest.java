package pl.grzeslowski.jsupla.protocol.impl.encoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueEncoderImplTest extends EncoderTest<SuplaChannelNewValue> {
    @InjectMocks
    private SuplaChannelNewValueEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelNewValue proto) {
        verify(primitiveEncoder).writeByte(proto.channelId, bytesToWriteInto(), 0);
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), BYTE_SIZE);
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
        return new SuplaChannelNewValue((byte) 2, new byte[SUPLA_CHANNELVALUE_SIZE]);
    }
}