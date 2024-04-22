package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static org.junit.Assert.fail;

@SuppressWarnings({"WeakerAccess", "unused"})
@RunWith(MockitoJUnitRunner.class)
public class DecoderFactoryImplExceptionTest {
    @InjectMocks
    DecoderFactoryImpl decoderFactory;
    @Mock
    PrimitiveDecoder primitiveDecoder;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfDoNotKnowType() throws Exception {

        // given
        ProtoWithSize proto = () -> 101;

        // when
        decoderFactory.getDecoder(proto);

        // then
        fail("Should throw exception");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenCallTypeIsNull() throws Exception {
        decoderFactory.getDecoder((CallType) null);
    }
}