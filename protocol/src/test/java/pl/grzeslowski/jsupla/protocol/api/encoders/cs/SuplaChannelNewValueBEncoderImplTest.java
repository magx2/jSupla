package pl.grzeslowski.jsupla.protocol.api.encoders.cs;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelNewValueBEncoderImplTest extends EncoderTest<SuplaChannelNewValueB> {
    @InjectMocks
    SuplaChannelNewValueBEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelNewValueB proto) {
        int offset = 0;
        verify(primitiveEncoder).writeInteger(proto.channelId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelNewValueBEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaChannelNewValueB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelNewValueB getProto() {
        final int channelId = 1;
        final byte[] value = new byte[SUPLA_CHANNELVALUE_SIZE];
        return new SuplaChannelNewValueB(channelId, value);
    }
}