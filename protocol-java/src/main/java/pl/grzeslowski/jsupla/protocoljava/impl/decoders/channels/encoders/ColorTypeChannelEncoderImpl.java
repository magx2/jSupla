package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ColorTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;

import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

public class ColorTypeChannelEncoderImpl implements ColorTypeChannelEncoder {
    @Override
    public byte[] encode(final RgbValue rgbValue) {
        return new byte[]{
                toByte(rgbValue.brightness),
                toByte(rgbValue.colorBrightness),
                toByte(rgbValue.red),
                toByte(rgbValue.green),
                toByte(rgbValue.blue),
                0,
                0,
                0
        };
    }

    private static byte toByte(int unsignedByte) {
        final byte[] bytes = new byte[1];
        INSTANCE.writeUnsignedByte((short) unsignedByte, bytes, 0);
        return bytes[0];
    }
}
