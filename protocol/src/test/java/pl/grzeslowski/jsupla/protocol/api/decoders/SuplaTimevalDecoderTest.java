package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SuplaTimevalDecoderTest {

    @Test
    public void zamel() {
        // given
        var bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        var tvSec = 67305985L; // PrimitiveDecoder.INSTANCE.parseLong(new byte[]{1, 2, 3, 4, 0, 0, 0, 0}, 0)
        var tvUsec = 134678021L; // PrimitiveDecoder.INSTANCE.parseLong(new byte[]{5, 6, 7, 8, 0, 0, 0, 0}, 0)

        // when
        var timeval = SuplaTimevalDecoder.INSTANCE.decode(bytes);

        // then
        assertThat(timeval.tvSec).isEqualTo(tvSec);
        assertThat(timeval.tvUsec).isEqualTo(tvUsec);
    }

    @Test
    public void auraton() {
        // given
        var bytes = new byte[]{110, 50, 46, 103, 0, 0, 0, 0, -119, -51, 13, 0, 0, 0, 0, 0};
        var tvSec = 1731080814L; // PrimitiveDecoder.INSTANCE.parseLong(new byte[]{110,50,46,103,0,0,0,0}, 0)
        var tvUsec = 904585L; // PrimitiveDecoder.INSTANCE.parseLong(new byte[]{-119,-51,13,0,0,0,0,0}, 0)

        // when
        var timeval = SuplaTimevalDecoder.INSTANCE.decode(bytes);

        // then
        assertThat(timeval.tvSec).isEqualTo(tvSec);
        assertThat(timeval.tvUsec).isEqualTo(tvUsec);
    }
}