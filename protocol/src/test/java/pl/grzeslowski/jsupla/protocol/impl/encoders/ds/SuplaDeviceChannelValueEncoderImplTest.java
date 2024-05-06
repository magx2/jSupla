package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaDeviceChannelValueEncoderImplTest extends EncoderTest<SuplaDeviceChannelValue> {
    @InjectMocks
    SuplaDeviceChannelValueEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaDeviceChannelValue proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.channelNumber, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDeviceChannelValueEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaDeviceChannelValue> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaDeviceChannelValue getProto() {
        return new SuplaDeviceChannelValue((short) 5, new byte[SUPLA_CHANNELVALUE_SIZE]);
    }
}