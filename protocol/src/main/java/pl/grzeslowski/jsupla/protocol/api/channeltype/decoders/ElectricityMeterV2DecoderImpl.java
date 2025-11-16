package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ElectricityMeterExtendedValueV2Decoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterExtendedValueV2;
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
public class ElectricityMeterV2DecoderImpl implements Decoder<ElectricityMeterValue> {
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    public static final BigDecimal ONE_THOUSAND = new BigDecimal(1_000);
    public static final int NUMBER_OF_PHASES = 3;
    private static final BigInteger DIVIDER = new BigInteger("100" + "000");

    @Override
    public ElectricityMeterValue decode(byte[] bytes, int offset) {
        val value = ElectricityMeterExtendedValueV2Decoder.INSTANCE.decode(bytes, offset);
        return new ElectricityMeterValue(
            value.totalForwardActiveEnergyBalanced(),
            value.totalReverseActiveEnergyBalanced(),
            new BigDecimal(value.totalCost()).divide(ONE_HUNDRED, UNLIMITED),
            new BigDecimal(value.pricePerUnit()).divide(ONE_THOUSAND, UNLIMITED),
            parseCurrency(value.currency()),
            value.measuredValues(),
            value.period(),
            buildPhases(value)
        );
    }


    private List<ElectricityMeterValue.Phase> buildPhases(ElectricityMeterExtendedValueV2 value) {
        return IntStream.range(0, NUMBER_OF_PHASES)
            .mapToObj(idx -> mapPhase(idx, value, value.m()[0]))
            .collect(toList());
    }

    private ElectricityMeterValue.Phase mapPhase(int idx, ElectricityMeterExtendedValueV2 parent, ElectricityMeterMeasurement value) {
        log.debug("Mapping phase {} from parent {} and value {}", idx, parent, value);
        return new ElectricityMeterValue.Phase(
            // from parent
            parent.totalForwardActiveEnergy()[idx].divide(DIVIDER),
            parent.totalReverseActiveEnergy()[idx].divide(DIVIDER),
            parent.totalForwardReactiveEnergy()[idx].divide(DIVIDER),
            parent.totalReverseReactiveEnergy()[idx].divide(DIVIDER),
            // from value
            value.voltage()[idx] * 0.01,
            value.current()[idx] * 0.001,
            value.powerActive()[idx] * 0.00001,
            value.powerReactive()[idx] * 0.00001,
            value.powerApparent()[idx] * 0.00001,
            value.powerFactor()[idx] * 0.001,
            value.phaseAngle()[idx] * 0.1,
            value.freq()
        );
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
