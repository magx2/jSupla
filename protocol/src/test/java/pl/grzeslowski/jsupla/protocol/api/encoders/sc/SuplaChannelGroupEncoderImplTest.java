package pl.grzeslowski.jsupla.protocol.api.encoders.sc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderTest;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelGroupEncoderImplTest extends EncoderTest<SuplaChannelGroup> {
    @InjectMocks
    SuplaChannelGroupEncoderImpl encoder;

    SuplaChannelGroup proto;

    @Before
    public void setUp() {
        final long captionSize = RANDOM_SUPLA.nextUnsignedInt(100);
        proto = new SuplaChannelGroup(
            RANDOM_SUPLA.nextByte(),
            RANDOM_SUPLA.nextPositiveInt(),
            RANDOM_SUPLA.nextPositiveInt(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextUnsignedInt(),
            captionSize,
            new byte[(int) captionSize]
        );
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelGroup proto) {
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
        verify(primitiveEncoder).writeUnsignedInteger(proto.captionSize, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeBytes(proto.caption, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelGroupEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaChannelGroup> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelGroup getProto() {
        return proto;
    }
}