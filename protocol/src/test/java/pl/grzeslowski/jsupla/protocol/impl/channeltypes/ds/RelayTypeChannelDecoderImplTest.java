package pl.grzeslowski.jsupla.protocol.impl.channeltypes.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.channelvalues.OnOff;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.channelvalues.OnOff.OFF;
import static pl.grzeslowski.jsupla.protocol.api.channelvalues.OnOff.ON;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class RelayTypeChannelDecoderImplTest {
    @InjectMocks RelayTypeChannelDecoderImpl decoder;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsTooSmall() throws Exception {

        // given
        final int offset = 20;
        final byte[] bytes = new byte[offset - 1];

        // when
        decoder.decode(bytes, offset);
    }

    @Test
    public void shouldReturnOnIfByteIsOne() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE];
        INSTANCE.writeUnsignedByte((short) 1, bytes, 0);

        // when
        final OnOff decode = decoder.decode(bytes);

        // then
        assertThat(decode).isEqualTo(ON);
    }

    @Test
    public void shouldReturnOnIfByteIsOneWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[offset + BYTE_SIZE];
        INSTANCE.writeUnsignedByte((short) 1, bytes, offset);

        // when
        final OnOff decode = decoder.decode(bytes, offset);

        // then
        assertThat(decode).isEqualTo(ON);
    }

    @Test
    public void shouldReturnOffIfByteIsZero() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE];
        INSTANCE.writeUnsignedByte((short) 0, bytes, 0);

        // when
        final OnOff decode = decoder.decode(bytes);

        // then
        assertThat(decode).isEqualTo(OFF);
    }

    @Test
    public void shouldReturnOffIfByteIsZeroWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[offset + BYTE_SIZE];
        INSTANCE.writeUnsignedByte((short) 0, bytes, offset);

        // when
        final OnOff decode = decoder.decode(bytes, offset);

        // then
        assertThat(decode).isEqualTo(OFF);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfValueIsNotOneNorZero() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE];
        INSTANCE.writeUnsignedByte((short) 3, bytes, 0);

        // when
        decoder.decode(bytes);
    }
}