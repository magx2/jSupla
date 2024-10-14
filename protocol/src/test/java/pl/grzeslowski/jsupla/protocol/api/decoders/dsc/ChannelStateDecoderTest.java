package pl.grzeslowski.jsupla.protocol.api.decoders.dsc;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.dsc.ChannelState;

public class ChannelStateDecoderTest {
    ChannelStateDecoder decoder = ChannelStateDecoder.INSTANCE;

    @Test
    public void decodeExampleFromAuraton() {
        // given
        byte[] data = new byte[]{
            0, 0, 0, 0, 11, 0, 0, 0, -1, 9, 0, 0, 0, 0, 0, 0, -64, -88, 1, 41, -128, 101, -103, -121, 36, -104, 100, 1, -61, 78, 0, 100, -5, 6, 0, 0, 7, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0
        };

        // when
        ChannelState decode = decoder.decode(data);

        // then
        System.out.println(decode);
    }
}