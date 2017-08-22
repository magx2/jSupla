package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelValueEncoderImplTest extends EncoderTest<SuplaChannelValue> {
    @InjectMocks SuplaChannelValueEncoderImpl encoder;
    @Mock pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder channelValueEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        given(channelValueEncoder.encode(any(pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class)))
                .willAnswer(__ -> new byte[pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.SIZE]);
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelValue proto) {
        int offset = 0;

        verify(primitiveEncoder).writeByte(proto.eol, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.id, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeByte(proto.online, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(channelValueEncoder).encode(proto.value);
        final byte[] bytes = new byte[pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.SIZE];
        verify(primitiveEncoder).writeBytes(bytes, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelValueEncoderImpl(null, channelValueEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenchannelValueEncoderIsNull() throws Exception {
        new SuplaChannelValueEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannelValue> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelValue getProto() {
        return new SuplaChannelValue(
                                            (byte) 1,
                                            2,
                                            (byte) 3,
                                            new pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue(
                                                                                                                    new byte[SUPLA_CHANNELVALUE_SIZE],
                                                                                                                    new byte[SUPLA_CHANNELVALUE_SIZE]
                                            )
        );
    }
}