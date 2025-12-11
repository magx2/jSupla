package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.ActionTrigger.Capabilities.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import java.util.EnumSet;
import org.junit.Test;

public class ActionTriggerTest {
    @Test
    public void shouldParseMaskInConstructor() {
        // given
        final int mask = SUPLA_ACTION_CAP_TURN_ON | SUPLA_ACTION_CAP_HOLD;

        // when
        final ActionTrigger actionTrigger = new ActionTrigger(mask);

        // then
        assertThat(actionTrigger.capabilities()).containsOnly(TURN_ON, HOLD);
    }

    @Test
    public void shouldReturnEmptySetFor0FromInt() {
        assertThat(ActionTrigger.Capabilities.from(0)).isEmpty();
    }

    @Test
    public void shouldReturnOneElementSetFromInt() {
        assertThat(ActionTrigger.Capabilities.from(SUPLA_ACTION_CAP_TURN_ON)).containsOnly(TURN_ON);
    }

    @Test
    public void shouldReturnTwoElementSetFromInt() {
        final int mask = SUPLA_ACTION_CAP_TURN_ON | SUPLA_ACTION_CAP_HOLD;
        assertThat(ActionTrigger.Capabilities.from(mask)).containsOnly(TURN_ON, HOLD);
    }

    @Test
    public void shouldReturnAllElementSetFromInt() {
        final int mask =
                SUPLA_ACTION_CAP_TURN_ON
                        | SUPLA_ACTION_CAP_TURN_OFF
                        | SUPLA_ACTION_CAP_TOGGLE_x1
                        | SUPLA_ACTION_CAP_TOGGLE_x2
                        | SUPLA_ACTION_CAP_TOGGLE_x3
                        | SUPLA_ACTION_CAP_TOGGLE_x4
                        | SUPLA_ACTION_CAP_TOGGLE_x5
                        | SUPLA_ACTION_CAP_HOLD
                        | SUPLA_ACTION_CAP_SHORT_PRESS_x1
                        | SUPLA_ACTION_CAP_SHORT_PRESS_x2
                        | SUPLA_ACTION_CAP_SHORT_PRESS_x3
                        | SUPLA_ACTION_CAP_SHORT_PRESS_x4
                        | SUPLA_ACTION_CAP_SHORT_PRESS_x5;
        assertThat(ActionTrigger.Capabilities.from(mask))
                .containsOnly(
                        TURN_ON,
                        TURN_OFF,
                        TOGGLE_x1,
                        TOGGLE_x2,
                        TOGGLE_x3,
                        TOGGLE_x4,
                        TOGGLE_x5,
                        HOLD,
                        SHORT_PRESS_x1,
                        SHORT_PRESS_x2,
                        SHORT_PRESS_x3,
                        SHORT_PRESS_x4,
                        SHORT_PRESS_x5);
    }

    @Test
    public void shouldThrowExceptionFor0FromSingleInt() {
        assertThatThrownBy(() -> ActionTrigger.Capabilities.fromSingle(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReturnOneElementFromSingleInt() {
        assertThat(ActionTrigger.Capabilities.fromSingle(SUPLA_ACTION_CAP_TURN_ON))
                .isEqualTo(TURN_ON);
    }

    @Test
    public void shouldThrowExceptionForTwoElementsFromSingleInt() {
        final int mask = SUPLA_ACTION_CAP_TURN_ON | SUPLA_ACTION_CAP_HOLD;
        assertThatThrownBy(() -> ActionTrigger.Capabilities.fromSingle(mask))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReturn0ForEmptySetToMaskSet() {
        assertThat(
                        ActionTrigger.Capabilities.toMask(
                                EnumSet.noneOf(ActionTrigger.Capabilities.class)))
                .isEqualTo(0);
    }

    @Test
    public void shouldReturnMaskForOneElementToMaskSet() {
        assertThat(ActionTrigger.Capabilities.toMask(EnumSet.of(TURN_ON)))
                .isEqualTo(SUPLA_ACTION_CAP_TURN_ON);
    }

    @Test
    public void shouldReturnMaskForTwoElementsToMaskSet() {
        final int expectedMask = SUPLA_ACTION_CAP_TURN_ON | SUPLA_ACTION_CAP_HOLD;
        assertThat(ActionTrigger.Capabilities.toMask(EnumSet.of(TURN_ON, HOLD)))
                .isEqualTo(expectedMask);
    }
}
