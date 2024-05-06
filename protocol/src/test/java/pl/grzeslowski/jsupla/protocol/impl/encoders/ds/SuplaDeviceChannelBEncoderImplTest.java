package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaDeviceChannelBEncoderImplTest extends EncoderTest<SuplaDeviceChannelB> {
    @InjectMocks
    SuplaDeviceChannelBEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaDeviceChannelB proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.number, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.type, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.funcList, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.defaultValue, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDeviceChannelBEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaDeviceChannelB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaDeviceChannelB getProto() {
        return new SuplaDeviceChannelB((short) 1, 3, 5, 67, new byte[SUPLA_CHANNELVALUE_SIZE]);
    }
}