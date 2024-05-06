package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaLocationEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class SuplaLocationPackEncoderImplTest extends EncoderTest<SuplaLocationPack> {
    @InjectMocks
    SuplaLocationPackEncoderImpl encoder;
    @Mock
    SuplaLocationEncoder locationEncoder;

    @Override
    protected void givenEncodeEntity() {
        super.givenEncodeEntity();
        // @formatter:off
        given(locationEncoder.encode(any(SuplaLocation.class))).willAnswer(
            invocation -> new byte[invocation.getArgumentAt(0, SuplaLocation.class).size()]
        );
        // @formatter:on
    }

    @Override
    protected void verifyEncodeEntity(final byte[] encode, final SuplaLocationPack proto) {
        int offset = 0;

        verify(primitiveEncoder).writeInteger(proto.count, bytesToWriteInto(proto), offset);
        offset += INT_SIZE;
        verify(primitiveEncoder).writeInteger(proto.totalLeft, bytesToWriteInto(proto), offset);
        offset += INT_SIZE;
        for (SuplaLocation location : proto.locations) {
            verify(locationEncoder).encode(location);
            verify(primitiveEncoder).writeBytes(new byte[location.size()], bytesToWriteInto(proto), offset);
            offset += location.size();
        }
    }

    @Override
    public void shouldThrowNpeWhenPrimitiveParserIsNull() throws Exception {
        new SuplaLocationPackEncoderImpl(null, locationEncoder);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenSuplaLocationEncoderIsNull() throws Exception {
        new SuplaLocationPackEncoderImpl(primitiveEncoder, null);
    }

    @Override
    public Encoder<SuplaLocationPack> getEncoder() {
        return encoder;
    }

    @Override
    public SuplaLocationPack getProto() {
        return RANDOM_SUPLA.nextObject(SuplaLocationPack.class);
    }
}