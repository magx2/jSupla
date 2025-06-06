package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ElectricityMeterExtendedValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterExtendedValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterMeasurement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.List;
import java.util.stream.IntStream;

import static java.math.MathContext.UNLIMITED;
import static java.util.stream.Collectors.toList;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.parseString;

@Slf4j
public class ElectricityMeterDecoderImpl implements Decoder<ElectricityMeterValue> {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    public static final BigDecimal ONE_THOUSAND = new BigDecimal(1_000);
    public static final int NUMBER_OF_PHASES = 3;
    private static final BigInteger DIVIDER = new BigInteger("100" + "000");

    @Override
    public ElectricityMeterValue decode(byte[] bytes, int offset) {
        val value = ElectricityMeterExtendedValueDecoder.INSTANCE.decode(bytes, offset);
        val phases = buildPhases(value);
        return ElectricityMeterValue.builder()
            .totalForwardActiveEnergyBalanced(computeTotalForwardActiveEnergyBalanced(phases))// only V2 need to compute manually
            .totalReverseActiveEnergyBalanced(computeTotalReverseActiveEnergyBalanced(phases))// only V2 need to compute manually
            .totalCost(new BigDecimal(value.totalCost).divide(ONE_HUNDRED, UNLIMITED))
            .pricePerUnit(new BigDecimal(value.pricePerUnit).divide(ONE_THOUSAND, UNLIMITED))
            .currency(parseCurrency(value.currency))
            .measuredValues(value.measuredValues)
            .period(value.period)
            .phases(phases)
            .build();
    }

    private BigInteger computeTotalForwardActiveEnergyBalanced(List<ElectricityMeterValue.Phase> phases) {
        return phases.stream()
            .map(phase -> phase.getTotalForwardActiveEnergy().subtract(phase.getTotalForwardReactiveEnergy()))
            .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private BigInteger computeTotalReverseActiveEnergyBalanced(List<ElectricityMeterValue.Phase> phases) {
        return phases.stream()
            .map(phase -> phase.getTotalReverseActiveEnergy().subtract(phase.getTotalReverseReactiveEnergy()))
            .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private List<ElectricityMeterValue.Phase> buildPhases(ElectricityMeterExtendedValue value) {
        return IntStream.range(0, NUMBER_OF_PHASES)
            .mapToObj(idx -> mapPhase(idx, value, value.m[0]))
            .collect(toList());
    }

    private ElectricityMeterValue.Phase mapPhase(int idx, ElectricityMeterExtendedValue parent, ElectricityMeterMeasurement value) {
        log.debug("Mapping phase {} from parent {} and value {}", idx, parent, value);
        val multiplier = 0.00001;
        return ElectricityMeterValue.Phase.builder()
            // from parent
            .totalForwardActiveEnergy(parent.totalForwardActiveEnergy[idx].divide(DIVIDER))
            .totalReverseActiveEnergy(parent.totalReverseActiveEnergy[idx].divide(DIVIDER))
            .totalForwardReactiveEnergy(parent.totalForwardReactiveEnergy[idx].divide(DIVIDER))
            .totalReverseReactiveEnergy(parent.totalReverseReactiveEnergy[idx].divide(DIVIDER))
            // from value
            .voltage(value.voltage[idx] * 0.01)
            .current(value.current[idx] * 0.001)
            .powerActive(value.powerActive[idx] * multiplier)
            .powerReactive(value.powerReactive[idx] * multiplier)
            .powerApparent(value.powerApparent[idx] * multiplier)
            .powerFactor(value.powerFactor[idx] * 0.001)
            .phaseAngle(value.phaseAngle[idx] * 0.1)
            .frequency(value.freq)
            .build();
    }

    private Currency parseCurrency(byte[] currency) {
        try {
            val currencyCode = parseString(currency);
            return Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            log.debug("Can't parse currency: {}", currency);
            return null;
        }
    }
}
