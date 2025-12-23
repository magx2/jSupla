package pl.grzeslowski.jsupla.protocol.api.structs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ElectricityMeterMeasurementTest {

    @Test
    void shouldReturnCorrectToString() {
        // given
        final int freq = 50;
        final int[] voltage = new int[] {230, 231, 229};
        final int[] current = new int[] {10, 11, 12};
        final int[] powerActive = new int[] {100, 101, 102};
        final int[] powerReactive = new int[] {200, 201, 202};
        final int[] powerApparent = new int[] {300, 301, 302};
        final short[] powerFactor = new short[] {1, 2, 3};
        final short[] phaseAngle = new short[] {4, 5, 6};

        final ElectricityMeterMeasurement measurement =
                new ElectricityMeterMeasurement(
                        freq,
                        voltage,
                        current,
                        powerActive,
                        powerReactive,
                        powerApparent,
                        powerFactor,
                        phaseAngle);

        // when
        final String toString = measurement.toString();

        // then
        assertThat(toString)
                .isEqualTo(
                        "ElectricityMeterMeasurement[freq="
                                + freq
                                + ", voltage="
                                + Arrays.toString(voltage)
                                + ", current="
                                + Arrays.toString(current)
                                + ", powerActive="
                                + Arrays.toString(powerActive)
                                + ", powerReactive="
                                + Arrays.toString(powerReactive)
                                + ", powerApparent="
                                + Arrays.toString(powerApparent)
                                + ", powerFactor="
                                + Arrays.toString(powerFactor)
                                + ", phaseAngle="
                                + Arrays.toString(phaseAngle)
                                + "]");
    }
}
