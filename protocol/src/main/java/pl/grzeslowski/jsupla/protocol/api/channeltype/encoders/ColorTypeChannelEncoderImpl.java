package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.RGBWValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.RGBWValue;

@Slf4j
public class ColorTypeChannelEncoderImpl {
    public byte[] encode(final RgbValue rgbValue) {
        var proto =
                new RGBWValue(
                        (byte) rgbValue.brightness(),
                        (byte) rgbValue.colorBrightness(),
                        (byte) rgbValue.blue(),
                        (byte) rgbValue.green(),
                        (byte) rgbValue.red(),
                        (byte) rgbValue.subject().getValue(),
                        (byte) rgbValue.command().ordinal());
        log.debug("Encoding {} into {}", rgbValue, proto);
        // The size of a channel value is fixed, so we use SUPLA_CHANNELVALUE_SIZE constant
        var bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        RGBWValueEncoder.INSTANCE.encode(proto, bytes, 0);
        return bytes;
    }
}
