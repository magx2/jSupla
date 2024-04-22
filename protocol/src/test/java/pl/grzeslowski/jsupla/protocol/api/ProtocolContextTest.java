package pl.grzeslowski.jsupla.protocol.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolContext.PROTOCOL_CONTEXT;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ProtocolContextTest {

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] params() throws Exception {
        return new Object[][]{
            {PrimitiveDecoder.class},
            {PrimitiveEncoder.class},
            {DecoderFactory.class},
            {EncoderFactory.class}
        };
    }

    final Class<?> serviceClass;

    public ProtocolContextTest(final Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Test
    public void shouldFindServiceInContext() throws Exception {

        // given

        // when
        Object service = PROTOCOL_CONTEXT.getService(serviceClass);

        // then
        assertThat(service).isNotNull();
    }
}