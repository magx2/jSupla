package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;

public class ThermometerTypeDoubleChannelDecoderImplTest {
    ThermometerTypeDoubleChannelDecoderImpl decoder = new ThermometerTypeDoubleChannelDecoderImpl();

    @Test
    public void auratonTestCase() {
        // given
        byte[] data = new byte[]{0, 0, 0, 0, 0, 48, 113, -64};

        // when
        ChannelValue decode = decoder.decode(data);

        // then
        System.out.println(decode);
    }

    @Test
    public void auratonTestCase2() {
        // given
        byte[] data = new byte[]{102, 102, 102, 102, 102, 102, 57, 64};

        // when
        ChannelValue decode = decoder.decode(data);

        // then
        System.out.println(decode);
    }
}