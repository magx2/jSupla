package pl.grzeslowski.jsupla.protocol.impl.decoders.dcs;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ProperDecoderTest;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaSetActivityTimeoutDecoderImplTest extends ProperDecoderTest<SuplaSetActivityTimeout> {
    @InjectMocks SuplaSetActivityTimeoutDecoderImpl decoder;
    @Mock PrimitiveDecoder primitiveDecoder;

    final SuplaSetActivityTimeout proto = RANDOM_SUPLA.nextObject(SuplaSetActivityTimeout.class);

    @Before
    public void setUp() {
        given(primitiveDecoder.parseUnsignedByte(any(), anyInt())).will(invocation -> {
            byte[] bytes = invocation.getArgumentAt(0, byte[].class);
            Integer offset = invocation.getArgumentAt(1, int.class);
            return PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        });
    }

    @Override
    public ProtoWithSizeDecoder<SuplaSetActivityTimeout> getDecoder() {
        return decoder;
    }

    @Override
    public int entitySize() {
        return SuplaSetActivityTimeout.SIZE;
    }

    @Override
    protected byte[] givenParseEntity(final int offset) {
        byte[] bytes = new byte[entitySize() + offset];

        PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte(proto.activityTimeout, bytes, offset);

        return bytes;
    }

    @Override
    protected void verifyParseEntity(final SuplaSetActivityTimeout entity) {
        assertThat(entity.activityTimeout).isEqualTo(proto.activityTimeout);
    }
}
