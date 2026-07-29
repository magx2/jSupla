package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
        if (description == null || description.type() == null) {
            return new UnknownValue(value, "Channel description or channel type is null");
        }
        return findChannelTypeDecoder(description.type())
                .map(decoder -> decoder.decode(value, description))
                .map(ChannelValue.class::cast)
                .orElseGet(
                        () -> {
                            val message =
                                    format(
                                            "Don't know how to map channel type %s to channel"
                                                    + " value!",
                                            description.type());
                            if (log.isWarnEnabled()) {
                                log.warn(message + " value={}", Arrays.toString(value));
                            }
                            return new UnknownValue(value, message);
                        });
    }

    public Class<? extends ChannelValue> findClass(final ChannelDescription description) {
        if (description == null || description.type() == null) {
            return UnknownValue.class;
        }
        var maybe =
                findChannelTypeDecoder(description.type())
                        .map(decoder -> decoder.getChannelValueType(description));
        if (maybe.isEmpty()) {
            log.warn("Don't know how to map channel type {} to channel value!", description.type());
            return UnknownValue.class;
        }
        return maybe.get();
    }

    private Optional<ChannelValueDecoder<?>> findChannelTypeDecoder(ChannelType channelType) {
        return streamOfDecoders(channelType).findAny();
    }

    /** VisibleForTesting */
    Stream<ChannelValueDecoder<?>> streamOfDecoders(ChannelType channelType) {
        return decoders.stream()
                .filter(decoder -> decoder.supportedChannelValueTypes().contains(channelType));
    }
}
