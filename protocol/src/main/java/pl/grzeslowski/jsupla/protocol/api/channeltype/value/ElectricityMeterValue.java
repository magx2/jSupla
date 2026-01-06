package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.List;

/**
 * @param totalForwardActiveEnergyBalanced kWh Vector phase-to-phase balancing
 * @param totalReverseActiveEnergyBalanced kWh Vector phase-to-phase balancing The price per unit, total cost and currency is overwritten by the server
 * @param totalCost                        total_cost == SUM(total_forward_active_energy[n] * price_per_unit
 * @param pricePerUnit
 * @param currency
 * @param measuredValues
 * @param period                           Approximate period between measurements in seconds
 * @param phases
 */
public record ElectricityMeterValue(
      @NonNull   BigInteger totalForwardActiveEnergyBalanced,
      @NonNull   BigInteger totalReverseActiveEnergyBalanced,
      @NonNull   BigDecimal totalCost,
      @NonNull   BigDecimal pricePerUnit,
      @NonNull   Currency currency,
        int measuredValues,
        int period,
      @NonNull List<Phase> phases)
        implements ChannelValue {

    /**
     * @param totalForwardActiveEnergy
     * @param totalReverseActiveEnergy
     * @param totalForwardReactiveEnergy
     * @param totalReverseReactiveEnergy
     * @param voltage                    V
     * @param current                    A (0.01A WHEN EM_VAR_CURRENT_OVER_65A) <p> unsigned short
     * @param powerActive                W (0.01kW WHEN EM_VAR_POWER_ACTIVE_KW)
     * @param powerReactive              var (0.01kvar WHEN EM_VAR_POWER_REACTIVE_KVAR)
     * @param powerApparent              VA (0.01kVA WHEN EM_VAR_POWER_APPARENT_KVA)
     * @param powerFactor
     * @param phaseAngle                 degree
     * @param frequency                  Hz
     */
    public record Phase(
            BigInteger totalForwardActiveEnergy,
            BigInteger totalReverseActiveEnergy,
            BigInteger totalForwardReactiveEnergy,
            BigInteger totalReverseReactiveEnergy,
            double voltage,
            double current,
            double powerActive,
            double powerReactive,
            double powerApparent,
            double powerFactor,
            double phaseAngle,
            int frequency) {}
}
