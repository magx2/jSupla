package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OpenClose;

public class OpenCloseTypeEncoderImplTest {
    private final OpenCloseTypeEncoderImpl encoder = new OpenCloseTypeEncoderImpl();

    @Test
    public void shouldEncodeOpenCloseStates() {
        assertThat(encoder.encode(OpenClose.OPEN)[0]).isEqualTo((byte) 1);
        assertThat(encoder.encode(OpenClose.CLOSE)[0]).isEqualTo((byte) 0);
    }
}
