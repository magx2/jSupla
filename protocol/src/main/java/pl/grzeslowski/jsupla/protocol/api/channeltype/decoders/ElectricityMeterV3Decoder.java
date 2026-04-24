package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.util.stream.Collectors.toList;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V3;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.BigDecimalDivider;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.PhaseSequence;
import pl.grzeslowski.jsupla.protocol.api.decoders.ElectricityMeterExtendedValueV3Decoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterExtendedValueV3;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterMeasurement;

@Slf4j
class ElectricityMeterV3Decoder implements ChannelValueDecoder<ElectricityMeterValue> {
    public static final int NUMBER_OF_PHASES = 3;
    private static final int DIVIDER_PRECISION = 64;
    private static final BigDecimalDivider ENERGY_DIVIDER =
            new BigDecimalDivider(100_000, DIVIDER_PRECISION);
    private static final BigDecimalDivider TOTAL_COST_DIVIDER =
            new BigDecimalDivider(100, DIVIDER_PRECISION);
    private static final BigDecimalDivider PRICE_PER_UNIT_DIVIDER =
            new BigDecimalDivider(10_000, DIVIDER_PRECISION);
    private static final BigDecimalDivider FREQUENCY_DIVIDER =
            new BigDecimalDivider(100, DIVIDER_PRECISION);
    private static final BigDecimalDivider VOLTAGE_DIVIDER =
            new BigDecimalDivider(100, DIVIDER_PRECISION);
    private static final BigDecimalDivider CURRENT_DIVIDER =
            new BigDecimalDivider(1_000, DIVIDER_PRECISION);
    private static final BigDecimalDivider POWER_DIVIDER =
            new BigDecimalDivider(100_000, DIVIDER_PRECISION);
    private static final BigDecimalDivider POWER_FACTOR_DIVIDER =
            new BigDecimalDivider(1_000, DIVIDER_PRECISION);
    private static final BigDecimalDivider PHASE_ANGLE_DIVIDER =
            new BigDecimalDivider(10, DIVIDER_PRECISION);
    private static final BigDecimalDivider VOLTAGE_PHASE_ANGLE_DIVIDER =
            new BigDecimalDivider(10, DIVIDER_PRECISION);

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(EV_TYPE_ELECTRICITY_METER_MEASUREMENT_V3);
    }

    @Override
    public Class<ElectricityMeterValue> getChannelValueType() {
        return ElectricityMeterValue.class;
    }

    @Override
    public ElectricityMeterValue decode(byte[] bytes, int offset) {
        val value = ElectricityMeterExtendedValueV3Decoder.INSTANCE.decode(bytes, offset);
        var phases = buildPhases(value);
        return new ElectricityMeterValue(
                sumEnergy(value.totalForwardActiveEnergy()),
                sumEnergy(value.totalReverseActiveEnergy()),
                sumEnergy(value.totalForwardReactiveEnergy()),
                sumEnergy(value.totalReverseReactiveEnergy()),
                ENERGY_DIVIDER.divide(value.totalForwardActiveEnergyBalanced()),
                ENERGY_DIVIDER.divide(value.totalReverseActiveEnergyBalanced()),
                TOTAL_COST_DIVIDER.divide(BigInteger.valueOf(value.totalCost())),
                PRICE_PER_UNIT_DIVIDER.divide(BigInteger.valueOf(value.pricePerUnit())),
                parseCurrency(value.currency()),
                value.measuredValues(),
                value.period(),
                VOLTAGE_PHASE_ANGLE_DIVIDER.divide(BigInteger.valueOf(value.voltagePhaseAngle12())),
                VOLTAGE_PHASE_ANGLE_DIVIDER.divide(BigInteger.valueOf(value.voltagePhaseAngle13())),
                Optional.of(PhaseSequence.fromBitmask(value.phaseSequence())),
                Optional.of(phases.get(0)),
                Optional.of(phases.get(1)),
                Optional.of(phases.get(2)));
    }

    private BigDecimal sumEnergy(BigInteger[] energies) {
        return Arrays.stream(energies)
                .map(ENERGY_DIVIDER::divide)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ElectricityMeterValue.Phase> buildPhases(ElectricityMeterExtendedValueV3 value) {
        return IntStream.range(0, NUMBER_OF_PHASES)
                .mapToObj(idx -> mapPhase(idx, value, value.m()[0]))
                .collect(toList());
    }

    private ElectricityMeterValue.Phase mapPhase(
            int idx, ElectricityMeterExtendedValueV3 parent, ElectricityMeterMeasurement value) {
        log.debug("Mapping phase {} from parent {} and value {}", idx, parent, value);
        return new ElectricityMeterValue.Phase(
                // from parent
                ENERGY_DIVIDER.divide(parent.totalForwardActiveEnergy()[idx]),
                ENERGY_DIVIDER.divide(parent.totalReverseActiveEnergy()[idx]),
                ENERGY_DIVIDER.divide(parent.totalForwardReactiveEnergy()[idx]),
                ENERGY_DIVIDER.divide(parent.totalReverseReactiveEnergy()[idx]),
                // from value
                VOLTAGE_DIVIDER.divide(BigInteger.valueOf(value.voltage()[idx])),
                CURRENT_DIVIDER.divide(BigInteger.valueOf(value.current()[idx])),
                POWER_DIVIDER.divide(BigInteger.valueOf(value.powerActive()[idx])),
                POWER_DIVIDER.divide(BigInteger.valueOf(value.powerReactive()[idx])),
                POWER_DIVIDER.divide(BigInteger.valueOf(value.powerApparent()[idx])),
                POWER_FACTOR_DIVIDER.divide(BigInteger.valueOf(value.powerFactor()[idx])),
                PHASE_ANGLE_DIVIDER.divide(BigInteger.valueOf(value.phaseAngle()[idx])),
                FREQUENCY_DIVIDER.divide(BigInteger.valueOf(value.freq())));
    }

    private Optional<Currency> parseCurrency(byte[] currency) {
        try {
            val currencyCode = parseString(currency);
            return Optional.of(Currency.getInstance(currencyCode));
        } catch (IllegalArgumentException e) {
            log.debug("Can't parse currency: {}", currency);
            return Optional.empty();
        }
    }
}
