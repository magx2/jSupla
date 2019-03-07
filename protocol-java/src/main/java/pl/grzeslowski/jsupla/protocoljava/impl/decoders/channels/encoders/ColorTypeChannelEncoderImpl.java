package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ColorTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueBSerializerImpl;

import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

public class ColorTypeChannelEncoderImpl implements ColorTypeChannelEncoder {
    public static final ColorTypeChannelEncoderImpl INSTANCE = new ColorTypeChannelEncoderImpl();
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
        PrimitiveEncoderImpl.INSTANCE.writeUnsignedByte((short) unsignedByte, bytes, 0);
        return bytes[0];
    }
}
