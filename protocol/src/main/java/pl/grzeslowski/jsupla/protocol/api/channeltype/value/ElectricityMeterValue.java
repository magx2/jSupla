package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_PHASE_SEQUENCE_CURRENT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_PHASE_SEQUENCE_VOLTAGE;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;
import lombok.NonNull;

/**
 * @param totalForwardActiveEnergyBalanced kWh Vector phase-to-phase balancing
 * @param totalReverseActiveEnergyBalanced kWh Vector phase-to-phase balancing The price per unit,
 *                                         total cost and currency is overwritten by the server
 * @param totalCost                        total_cost == SUM(total_forward_active_energy[n] * price_per_unit
 * @param pricePerUnit
 * @param currency
 * @param measuredValues
 * @param period                           Approximate period between measurements in seconds
 * @param voltagePhaseAngle12              Voltage phase angle between phase 1 and 2, in degrees
 * @param voltagePhaseAngle13              Voltage phase angle between phase 1 and 3, in degrees
 * @param phaseSequence                    voltage and current phase sequence
 * @param phase1
 * @param phase2
 * @param phase3
 */
public record ElectricityMeterValue(
        // active
        @NonNull BigDecimal totalForwardActiveEnergy,
        @NonNull BigDecimal totalReverseActiveEnergy,
        // reactive
        @NonNull BigDecimal totalForwardReactiveEnergy,
        @NonNull BigDecimal totalReverseReactiveEnergy,
        // balanced
        @NonNull BigDecimal totalForwardActiveEnergyBalanced,
        @NonNull BigDecimal totalReverseActiveEnergyBalanced,
        // rest
        @NonNull BigDecimal totalCost,
        @NonNull BigDecimal pricePerUnit,
        @NonNull Optional<Currency> currency,
        int measuredValues,
        int period,
        @NonNull BigDecimal voltagePhaseAngle12,
        @NonNull BigDecimal voltagePhaseAngle13,
        @NonNull Optional<PhaseSequence> phaseSequence,
        @NonNull Optional<Phase> phase1,
        @NonNull Optional<Phase> phase2,
        @NonNull Optional<Phase> phase3)
        implements ChannelValue {
    public record PhaseSequence(Sequence voltage, Sequence current) {
        public static PhaseSequence fromBitmask(short bitmask) {
            return new PhaseSequence(
                    parse(bitmask, EM_PHASE_SEQUENCE_VOLTAGE),
                    parse(bitmask, EM_PHASE_SEQUENCE_CURRENT));
        }

        private static Sequence parse(short bitmask, int flag) {
            return (bitmask & flag) == 0 ? Sequence.CLOCKWISE_123 : Sequence.COUNTER_CLOCKWISE_132;
        }
    }

    public enum Sequence {
        CLOCKWISE_123,
        COUNTER_CLOCKWISE_132
    }

    /**
     * @param totalForwardActiveEnergy   kWh
     * @param totalReverseActiveEnergy   kWh
     * @param totalForwardReactiveEnergy kvarh
     * @param totalReverseReactiveEnergy kvarh
     * @param voltage                    V
     * @param current                    A
     * @param powerActive                W
     * @param powerReactive              var
     * @param powerApparent              VA
     * @param powerFactor
     * @param phaseAngle                 degree
     * @param frequency                  Hz
     */
    public record Phase(
            BigDecimal totalForwardActiveEnergy,
            BigDecimal totalReverseActiveEnergy,
            BigDecimal totalForwardReactiveEnergy,
            BigDecimal totalReverseReactiveEnergy,
            BigDecimal voltage,
            BigDecimal current,
            BigDecimal powerActive,
            BigDecimal powerReactive,
            BigDecimal powerApparent,
            BigDecimal powerFactor,
            BigDecimal phaseAngle,
            BigDecimal frequency) {}
}
