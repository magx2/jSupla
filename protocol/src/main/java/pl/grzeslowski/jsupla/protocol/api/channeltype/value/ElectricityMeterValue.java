package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.List;

@Value
@Builder
public class ElectricityMeterValue implements ChannelValue {
    /**
     * kWh Vector phase-to-phase balancing
     */
    BigInteger totalForwardActiveEnergyBalanced;
    /**
     * kWh Vector phase-to-phase balancing The price per unit, total cost and currency is overwritten by the server
     */
    BigInteger totalReverseActiveEnergyBalanced;
    /**
     * total_cost == SUM(total_forward_active_energy[n] * price_per_unit
     */
    BigDecimal totalCost;
    BigDecimal pricePerUnit;
    Currency currency;
    int measuredValues;
    /**
     * Approximate period between measurements in seconds
     */
    int period;


    List<Phase> phases;

    @Builder
    @Value
    public static class Phase {
        BigInteger totalForwardActiveEnergy;
        BigInteger totalReverseActiveEnergy;
        BigInteger totalForwardReactiveEnergy;
        BigInteger totalReverseReactiveEnergy;

        /**
         * V
         */
        double voltage;
        /**
         * A (0.01A WHEN EM_VAR_CURRENT_OVER_65A)
         * <p>
         * unsigned short
         */
        double current;
        /**
         * W (0.01kW WHEN EM_VAR_POWER_ACTIVE_KW)
         */
        double powerActive;
        /**
         * var (0.01kvar WHEN EM_VAR_POWER_REACTIVE_KVAR)
         */
        double powerReactive;
        /**
         * VA (0.01kVA WHEN EM_VAR_POWER_APPARENT_KVA)
         */
        double powerApparent;
        double powerFactor;
        /**
         * degree
         */
        double phaseAngle;

        /**
         * Hz
         */
        int frequency;
    }
}
