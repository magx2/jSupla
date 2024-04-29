package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;


import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

public class ColorTypeChannelEncoderImpl {
    public byte[] encode(final RgbValue rgbValue) {
        return new byte[]{
            toByte(rgbValue.getBrightness()),
            toByte(rgbValue.getColorBrightness()),
            toByte(rgbValue.getRed()),
            toByte(rgbValue.getGreen()),
            toByte(rgbValue.getBlue()),
            0,
            0,
            0
        };
    }

    private static byte toByte(int unsignedByte) {
        final byte[] bytes = new byte[1];
        PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte((short) unsignedByte, bytes, 0);
        return bytes[0];
    }
}
