package pl.grzeslowski.jsupla.generator.generator;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class NameMapperTest {
    NameMapper nameMapper = NameMapper.INSTANCE;

    @ParameterizedTest(name = "{index}: should map {0} to {1}")
    @MethodSource
    void mapper(String name, String expected) {
        // when
        var actual = nameMapper.mapRecordName(name);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> mapper() {
        return Stream.of(
            Arguments.of("TAction_ShadingSystem_Parameters", "ActionShadingSystemParameters"),
            Arguments.of("TCalCfg_LightSourceLifespan", "CalCfgLightSourceLifespan"),
            Arguments.of("TCS_ActionWithAuth", "ActionWithAuth"),
            Arguments.of("TCSD_ChannelStateRequest", "ChannelStateRequest"),
            Arguments.of("TDS_ActionTrigger", "ActionTrigger"),
            Arguments.of("THVACTemperatureCfg", "HVACTemperatureCfg"),
            Arguments.of("TRGBW_Value", "RGBWValue"),
            Arguments.of("TSC_ChannelConfigUpdateOrResult", "ChannelConfigUpdateOrResult"),
            Arguments.of("TSCD_SetCaptionResult", "SetCaptionResult"),
            Arguments.of("TSCS_ChannelConfig", "ChannelConfig"),
            Arguments.of("TSD_ChannelConfigFinished", "ChannelConfigFinished"),
            Arguments.of("TSDC_UserLocalTimeResult", "UserLocalTimeResult"),
            Arguments.of("TSDC_SuplaPingServerResult", "SuplaPingServerResult"),
            Arguments.of("TValve_Value", "ValveValue")
        );
    }


    @ParameterizedTest
    @MethodSource("fieldNames")
    void shouldMapFieldName(String input, String expected) {
        // when
        var actual = nameMapper.mapFieldName(input);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> fieldNames() {
        return Stream.of(
            Arguments.of("default", "defaultField"),
            Arguments.of("SuperUserAuthorized", "superUserAuthorized"),
            Arguments.of("Some_field", "someField"),
            Arguments.of("some_field", "someField"),
            Arguments.of("another_example_name", "anotherExampleName"),
            Arguments.of("single", "single"),
            Arguments.of("CAPS_LOCK", "capsLock"),
            Arguments.of("one_two_three_four", "oneTwoThreeFour"),
            Arguments.of("e_enabled", "eEnabled"),
            Arguments.of("n_value_sec", "nValueSec"),
            Arguments.of("value1_value2", "value1Value2"),
            Arguments.of("A_B_C", "aBC"),
            Arguments.of("field_with__double_underscore", "fieldWithDoubleUnderscore")
        );
    }
}