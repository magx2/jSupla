package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaChannelGroupRelationEncoderImplTest extends EncoderTest<SuplaChannelGroupRelation> {
    @InjectMocks
    SuplaChannelGroupRelationEncoderImpl encoder;

    SuplaChannelGroupRelation proto;

    @Before
    public void setUp() {
        proto = new SuplaChannelGroupRelation(
            RANDOM_SUPLA.nextByte(),
            RANDOM_SUPLA.nextInt(),
            RANDOM_SUPLA.nextInt()
        );
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaChannelGroupRelation proto) {
        int offset = 0;
        verify(primitiveEncoder).writeByte(proto.eol, bytesToWriteInto(), offset);
        offset += BYTE_SIZE;
        verify(primitiveEncoder).writeInteger(proto.channelGroupId, bytesToWriteInto(), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.channelId, bytesToWriteInto(), offset);
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaChannelGroupRelationEncoderImpl(null);
    }

    @Override
    public Encoder<SuplaChannelGroupRelation> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaChannelGroupRelation getProto() {
        return proto;
    }
}