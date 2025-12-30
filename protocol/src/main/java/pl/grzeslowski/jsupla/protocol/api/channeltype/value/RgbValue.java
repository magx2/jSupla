package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue.Command.NOT_SET;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue.Subject.UNKNOWN;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @param brightness {@code [0-100]}
 * @param colorBrightness {@code [0-100]}
 * @param red {@code [0-255]}
 * @param green {@code [0-255]}
 * @param blue {@code [0-255]}
 * @param command requires {@code SUPLA_CHANNEL_FLAG_RGBW_COMMANDS_SUPPORTED}
 * @param subject
 */
public record RgbValue(
        int brightness,
        int colorBrightness,
        int red,
        int green,
        int blue,
        Command command,
        Subject subject)
        implements ChannelValue {
    public RgbValue(int brightness, int colorBrightness, int red, int green, int blue) {
        this(brightness, colorBrightness, red, green, blue, NOT_SET, UNKNOWN);
    }

    public RgbValue(
            int brightness, int colorBrightness, int red, int green, int blue, Command command) {
        this(brightness, colorBrightness, red, green, blue, command, UNKNOWN);
    }

    public RgbValue {
        size(brightness, 0, 100);
        size(colorBrightness, 0, 100);
        unsignedByteSize(red);
        unsignedByteSize(green);
        unsignedByteSize(blue);
        requireNonNull(command);
        requireNonNull(subject);
    }

    public enum Command {
        /** Values from other fields are applied in a standard way */
        NOT_SET,
        /** Ignores all other bytes and turns on the dimmer */
        TURN_ON_DIMMER,
        /** Ignores all other bytes and turns off the dimmer */
        TURN_OFF_DIMMER,
        /** Ignores all other bytes and toggles the dimmer */
        TOGGLE_DIMMER,
        /** Ignores all other bytes and turns on the RGB */
        TURN_ON_RGB,
        /** Ignores all other bytes and turns off the RGB */
        TURN_OFF_RGB,
        /** Ignores all other bytes and toggles the RGB */
        TOGGLE_RGB,
        /** Ignores all other bytes and turns on the RGB and Dimmer */
        TURN_ON_ALL,
        /** Ignores all other bytes and turns off the RGB and Dimmer */
        TURN_OFF_ALL,
        /** Ignores all other bytes and toggles the RGB and Dimmer (with sync, so both will be off or both will be on) */
        TOGGLE_ALL,
        /** Stores brightness value and ignores all other bytes, if dimmer is off, it stays off */
        SET_BRIGHTNESS_WITHOUT_TURN_ON,
        /** Stores colorBrightness value and ignores all other bytes.
         * <p>If RGB is off, it stays off */
        SET_COLOR_BRIGHTNESS_WITHOUT_TURN_ON,
        /** Stores color value (R, G, B) and ignores all other bytes.
         * <p>If RGB is off, it stays off */
        SET_RGB_WITHOUT_TURN_ON,
        /** Start brightness dimmer iteration */
        START_ITERATE_DIMMER,
        /** Start color brightness iteration */
        START_ITERATE_RGB,
        /** Start dimmer and rgb brightness iteration */
        START_ITERATE_ALL,
        /** Stop brightness dimmer iteration */
        STOP_ITERATE_DIMMER,
        /** Stop color brightness iteration */
        STOP_ITERATE_RGB,
        /** Stop dimmer and rgb brightness iteration */
        STOP_ITERATE_ALL;

        public static Command parse(int ordinal) {
            return Arrays.stream(values())
                    .filter(v -> v.ordinal() == ordinal)
                    .findAny()
                    .orElse(NOT_SET);
        }
    }

    @RequiredArgsConstructor
    public enum Subject {
        BRIGHTNESS_ONOFF(0x1),
        COLOR_ONOFF(0x2),
        BOTH_ON_OFF(0x3),
        UNKNOWN(0x0);

        @Getter private final int value;

        public static Subject parse(int value) {
            return Arrays.stream(values()).filter(v -> v.value == value).findAny().orElse(UNKNOWN);
        }
    }
}
