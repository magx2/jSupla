package pl.grzeslowski.jsupla.protocol.api.encoders.ds;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaDeviceChannelEncoderImplTest extends EncoderTest<SuplaDeviceChannel> {
    @InjectMocks
    SuplaDeviceChannelEncoderImpl encoder;

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaDeviceChannel proto) {
        int offset = 0;

        verify(primitiveEncoder).writeUnsignedByte(proto.number, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.type, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.value, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaDeviceChannelEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaDeviceChannel> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaDeviceChannel getProto() {
        return new SuplaDeviceChannel((short) 3, 5, new byte[SUPLA_CHANNELVALUE_SIZE]);
    }
}