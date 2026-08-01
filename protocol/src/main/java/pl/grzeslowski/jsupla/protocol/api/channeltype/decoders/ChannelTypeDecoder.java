package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

@Slf4j
@RequiredArgsConstructor(access = PRIVATE)
public final class ChannelTypeDecoder {
    public static final ChannelTypeDecoder INSTANCE = new ChannelTypeDecoder();

    private final Set<ChannelValueDecoder<?>> decoders;

    private ChannelTypeDecoder() {
        this(
                Set.of(
                        new RgbTypeDecoder(),
                        new RelayTypeDecoder(),
                        new ThermometerTypeDecoder(),
                        new ThermometerDoubleTypeDecoder(),
                        new ElectricityMeterDecoder(),
                        new ElectricityMeterSimpleDecoder(),
                        new ElectricityMeterV2Decoder(),
                        new ElectricityMeterV3Decoder(),
                        HvacTypeDecoder.INSTANCE,
                        new TimerSecDecoder(),
                        new TimerMsecDecoder(),
                        new PercentageTypeDecoder(),
                        new HumidityTypeDecoder(),
                        new PressureTypeDecoder(),
                        new RainTypeDecoder(),
                        new WeightTypeDecoder(),
                        new WindTypeDecoder(),
                        new HeatpolThermostatTypeDecoder()));
    }

    public ChannelValue decode(final ChannelDescription description, final byte[] value) {
        if (description == null) {
            return new UnknownValue(value, "Channel description is null");
        }
        return findDecoder(description)
                .map(decoder -> decoder.decode(value, description))
                .map(ChannelValue.class::cast)
                .orElseGet(
                        () -> {
                            val message =
                                    format(
                                            "Don't know how to map channel description %s to"
                                                    + " channel value!",
                                            description);
                            if (log.isWarnEnabled()) {
                                log.warn(message + " value={}", Arrays.toString(value));
                            }
                            return new UnknownValue(value, message);
                        });
    }

    public Class<? extends ChannelValue> findClass(final ChannelDescription description) {
        if (description == null) {
            return UnknownValue.class;
        }
        var maybe =
                findDecoder(description).map(decoder -> decoder.getChannelValueType(description));
        if (maybe.isEmpty()) {
            log.warn("Don't know how to map channel description {} to channel value!", description);
            return UnknownValue.class;
        }
        return maybe.get();
    }

    private Optional<ChannelValueDecoder<?>> findDecoder(ChannelDescription description) {
        return description
                .selectedFunction()
                .flatMap(this::findChannelFunctionDecoder)
                .or(() -> findSingleChannelFunctionDecoder(description.functions()))
                .or(() -> findChannelTypeDecoder(description.type()));
    }

    private Optional<ChannelValueDecoder<?>> findSingleChannelFunctionDecoder(
            List<ChannelFunction> functions) {
        var functionDecoders =
                functions.stream()
                        .flatMap(function -> streamOfFunctionDecoders(function))
                        .distinct()
                        .toList();
        return functionDecoders.size() == 1
                ? Optional.of(functionDecoders.getFirst())
                : Optional.empty();
    }

    private Optional<ChannelValueDecoder<?>> findChannelFunctionDecoder(
            ChannelFunction channelFunction) {
        return streamOfFunctionDecoders(channelFunction).findAny();
    }

    private Optional<ChannelValueDecoder<?>> findChannelTypeDecoder(ChannelType channelType) {
        if (channelType == null) {
            return Optional.empty();
        }
        return streamOfDecoders(channelType).findAny();
    }

    /** VisibleForTesting */
    Stream<ChannelValueDecoder<?>> streamOfFunctionDecoders(ChannelFunction channelFunction) {
        return decoders.stream()
                .filter(decoder -> decoder.supportedChannelFunctions().contains(channelFunction));
    }

    /** VisibleForTesting */
    Stream<ChannelValueDecoder<?>> streamOfDecoders(ChannelType channelType) {
        return decoders.stream()
                .filter(decoder -> decoder.supportedChannelValueTypes().contains(channelType));
    }
}
