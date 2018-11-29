package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelBEncoderImplTest extends EncoderTest<SuplaChannelB> {
    @InjectMocks SuplaChannelBEncoderImpl encoder;
    @Mock SuplaChannelValueEncoder suplaChannelValueEncoder;

    SuplaChannelB proto;
    byte[] valueBytes;

    @Before
    public void setUp() {
        final long captionSize = RANDOM_SUPLA.nextUnsignedInt(100);
        proto = new SuplaChannelB(
                RANDOM_SUPLA.nextByte(),
                RANDOM_SUPLA.nextPositiveInt(),
                RANDOM_SUPLA.nextPositiveInt(),
                RANDOM_SUPLA.nextInt(),
                RANDOM_SUPLA.nextInt(),
                RANDOM_SUPLA.nextUnsignedInt(),
                RANDOM_SUPLA.nextUnsignedByte(),
                RANDOM_SUPLA.nextBoolByte(),
                RANDOM_SUPLA.nextObject(SuplaChannelValue.class),
                captionSize,
                RANDOM_SUPLA.nextByteArray((int) captionSize));
        valueBytes = RANDOM_SUPLA.nextByteArray(proto.value.size());
        given(suplaChannelValueEncoder.encode(proto.value)).willReturn(valueBytes);
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelB proto) {
        int offset = 0;
        verify(primitiveEncoder).writeByte(proto.eol, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.id, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.locationId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.func, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.altIcon, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedInteger(proto.flags, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeUnsignedByte(proto.protocolVersion, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeByte(proto.online, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(suplaChannelValueEncoder).encode(proto.value);
        verify(primitiveEncoder).writeBytes(valueBytes, bytesToWriteInto(), offset);
        offset += proto.value.size();
        verify(primitiveEncoder).writeUnsignedInteger(proto.captionSize, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.caption, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelBEncoderImpl(null, suplaChannelValueEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaChannelEncoderIsNull() {
        new SuplaChannelBEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaChannelB> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelB getProto() {
        return proto;
    }
}