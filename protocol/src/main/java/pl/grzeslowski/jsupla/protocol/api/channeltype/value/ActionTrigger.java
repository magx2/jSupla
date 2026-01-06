package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.util.Arrays.stream;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import java.util.EnumSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.val;

public record ActionTrigger(Set<Capabilities> capabilities) implements ChannelValue {
    public ActionTrigger(int bitmask) {
        this(Capabilities.from(bitmask));
    }

    @RequiredArgsConstructor
    public enum Capabilities {
        // Recommended for bistable buttons
        TURN_ON(SUPLA_ACTION_CAP_TURN_ON),
        TURN_OFF(SUPLA_ACTION_CAP_TURN_OFF),
        TOGGLE_x1(SUPLA_ACTION_CAP_TOGGLE_x1),
        TOGGLE_x2(SUPLA_ACTION_CAP_TOGGLE_x2),
        TOGGLE_x3(SUPLA_ACTION_CAP_TOGGLE_x3),
        TOGGLE_x4(SUPLA_ACTION_CAP_TOGGLE_x4),
        TOGGLE_x5(SUPLA_ACTION_CAP_TOGGLE_x5),

        // Recommended for monostable buttons
        HOLD(SUPLA_ACTION_CAP_HOLD),
        SHORT_PRESS_x1(SUPLA_ACTION_CAP_SHORT_PRESS_x1),
        SHORT_PRESS_x2(SUPLA_ACTION_CAP_SHORT_PRESS_x2),
        SHORT_PRESS_x3(SUPLA_ACTION_CAP_SHORT_PRESS_x3),
        SHORT_PRESS_x4(SUPLA_ACTION_CAP_SHORT_PRESS_x4),
        SHORT_PRESS_x5(SUPLA_ACTION_CAP_SHORT_PRESS_x5);

        private final int value;

        public static Set<Capabilities> from(int mask) {
            val result = EnumSet.noneOf(Capabilities.class);
            stream(values()).filter(c -> (mask & c.value) == c.value).forEach(result::add);
            return result;
        }

        public static Capabilities fromSingle(int mask) {
            val set = from(mask);
            if (set.size() != 1) {
                throw new IllegalArgumentException(
                        "Expected exactly 1 capability, but got: " + set);
            }
            return set.iterator().next();
        }

        public static int toMask(Set<Capabilities> set) {
            return set.stream().mapToInt(c -> c.value).reduce(0, (a, b) -> a | b);
        }

        public int toMask() {
            return toMask(Set.of(this));
        }
    }
}
