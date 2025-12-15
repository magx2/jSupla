package pl.grzeslowski.jsupla.protocol.api.decoders.dcs;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SetCaption;

class SetCaptionDecoderTest {
    private final SetCaptionDecoder decoder = SetCaptionDecoder.INSTANCE;

    @Test
    void shouldDecodeCaptionBytes() {
        int id = 1234;
        byte[] caption = "name\0".getBytes();

        byte[] payload =
                ByteBuffer.allocate(4 + 4 + caption.length)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .putInt(id)
                        .putInt(caption.length)
                        .put(caption)
                        .array();

        SetCaption result = decoder.decode(payload, 0);

        assertThat(result.id()).isEqualTo(id);
        assertThat(result.channelNumber()).isNull();
        assertThat(result.captionSize()).isEqualTo(caption.length);
        assertThat(result.caption()).containsExactly(caption);
    }
}
