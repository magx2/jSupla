package pl.grzeslowski.jsupla.protocol.api.channeltype;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFunction.SUPLA_CHANNELFNC_CONTROLLINGTHEGATE;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFunction.SUPLA_CHANNELFNC_NONE;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.ChannelFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;

class ChannelDescriptionTest {
    @Test
    void shouldCreateDescriptionFromRawValues() {
        var funcList = SUPLA_CHANNELFNC_CONTROLLINGTHEGATE.getValue();
        var description =
                ChannelDescription.fromValues(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        funcList,
                        SUPLA_CHANNELFNC_CONTROLLINGTHEGATE.getValue());

        assertThat(description.functions())
                .containsExactlyElementsOf(ChannelFunction.findByMask(funcList));
        assertThat(description.selectedFunction()).contains(SUPLA_CHANNELFNC_CONTROLLINGTHEGATE);
    }

    @Test
    void shouldCreateDescriptionWithoutFunctionsForEmptyFuncList() {
        assertThat(
                        ChannelDescription.fromValues(
                                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                                        Set.of(),
                                        null,
                                        SUPLA_CHANNELFNC_NONE.getValue())
                                .functions())
                .isEmpty();
        assertThat(
                        ChannelDescription.fromValues(
                                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                                        Set.of(),
                                        0,
                                        SUPLA_CHANNELFNC_NONE.getValue())
                                .functions())
                .isEmpty();
    }

    @Test
    void shouldTreatNoneDefaultValueAsEmptySelectedFunction() {
        assertThat(
                        ChannelDescription.fromValues(
                                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                                        Set.of(),
                                        0,
                                        SUPLA_CHANNELFNC_NONE.getValue())
                                .selectedFunction())
                .isEmpty();
    }

    @Test
    void shouldTreatUnknownDefaultValueAsEmptySelectedFunction() {
        assertThat(
                        ChannelDescription.fromValues(
                                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                                        Set.of(),
                                        0,
                                        Integer.MIN_VALUE)
                                .selectedFunction())
                .isEmpty();
    }

    @Test
    void shouldCopyFunctionList() {
        var description =
                new ChannelDescription(
                        ChannelType.SUPLA_CHANNELTYPE_RELAY,
                        Set.of(),
                        List.of(SUPLA_CHANNELFNC_CONTROLLINGTHEGATE),
                        Optional.empty());

        assertThat(description.functions()).containsExactly(SUPLA_CHANNELFNC_CONTROLLINGTHEGATE);
    }
}
