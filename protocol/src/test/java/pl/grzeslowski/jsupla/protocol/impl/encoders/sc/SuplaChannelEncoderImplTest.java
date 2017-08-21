package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelEncoderImplTest extends EncoderTest<SuplaChannel> {
    @InjectMocks SuplaChannelEncoderImpl encoder;
    @Mock SuplaChannelValueEncoder channelValueEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        given(channelValueEncoder.encode(any(SuplaChannelValue.class))).willAnswer(
                __ -> new byte[SuplaChannelValue.SIZE]
        );
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannel proto) {
        int offset = 0;

        verify(primitiveEncoder).writeByte(proto.eol, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.id, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.locationId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.func, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.online, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.captionSize, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(channelValueEncoder).encode(proto.value);
        verify(primitiveEncoder).writeBytes(new byte[SuplaChannelValue.SIZE], bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelEncoderImpl(null, channelValueEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaChannelValueEncoderIsNull() throws Exception {
        new SuplaChannelEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannel> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannel getProto() {
        final SuplaChannelValue value = new SuplaChannelValue(
                                                                     new byte[SUPLA_CHANNELVALUE_SIZE],
                                                                     new byte[SUPLA_CHANNELVALUE_SIZE]);
        final int captionSize = 9;
        return new SuplaChannel((byte) 1,
                                       3,
                                       4,
                                       3,
                                       (byte) 1,
                                       value,
                                       captionSize,
                                       new byte[captionSize]);
    }
}