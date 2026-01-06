package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.toUnsignedByte;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue.Command;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue.Subject;
import pl.grzeslowski.jsupla.protocol.api.decoders.RGBWValueDecoder;

@Slf4j
class ColorTypeDecoder implements ChannelValueDecoder<RgbValue> {
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(
                SUPLA_CHANNELTYPE_RGBLEDCONTROLLER,
                SUPLA_CHANNELTYPE_DIMMERANDRGBLED,
                SUPLA_CHANNELTYPE_DISTANCESENSOR);
    }

    @Override
    public Class<RgbValue> getChannelValueType() {
        return RgbValue.class;
    }

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
