package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.toUnsignedByte;

import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue.Command;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue.Subject;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.RGBWValueDecoder;

@Slf4j
class ColorTypeChannelDecoderImpl implements Decoder<RgbValue> {
    @SuppressWarnings("UnqualifiedInnerClassAccess")
    @Override
    public RgbValue decode(final byte[] bytes, int offset) {
        var rgbw = RGBWValueDecoder.INSTANCE.decode(bytes, offset);
        var rgbValue =
                new RgbValue(
                        toUnsignedByte(rgbw.brightness()),
                        toUnsignedByte(rgbw.colorBrightness()),
                        rgbw.r(),
                        rgbw.g(),
                        rgbw.b(),
                        rgbw.dimmerCct(),
                        Command.parse(rgbw.command()),
                        Subject.parse(rgbw.onOff()));
        log.debug("Decoded {} from {}", rgbValue, rgbw);
        return rgbValue;
    }
}
