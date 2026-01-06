package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

@Slf4j
@RequiredArgsConstructor(access = PRIVATE)
public final class ChannelTypeDecoder {
    public static final ChannelTypeDecoder INSTANCE = new ChannelTypeDecoder();

    private final Set<ChannelValueDecoder<?>> decoders;

    private ChannelTypeDecoder() {
        this(
                Set.of(
                        new ColorTypeDecoder(),
                        new RelayTypeDecoder(),
                        new ThermometerTypeDecoder(),
                        new ThermometerDoubleTypeDecoder(),
                        new ElectricityMeterDecoder(),
                        new ElectricityMeterV2Decoder(),
                        new HvacTypeDecoder(),
                        new TimerSecDecoder(),
                        new TimerMsecDecoder(),
                        new PercentageTypeDecoder(),
                        new HumidityTypeDecoder(),
                        new ActionTriggerDecoder()));
    }

    public ChannelValue decode(int type, byte[] value) {
        return ChannelType.findByValue(type)
                .map(t -> decode(t, value))
                .orElseGet(
                        () ->
                                new UnknownValue(
                                        new byte[0],
                                        format(
                                                "Don't know how to map device channel type %s to"
                                                        + " channel value",
                                                type)));
    }

    public ChannelValue decode(final ChannelType channelType, final byte[] value) {
        if (channelType == null) {
            return new UnknownValue(value, "Channel type is null");
        }
        return findChannelTypeDecoder(channelType)
                .map(decoder -> decoder.decode(value))
                .map(ChannelValue.class::cast)
                .orElseGet(
                        () -> {
                            val message =
                                    format(
                                            "Don't know how to map channel type %s to channel"
                                                    + " value!",
                                            channelType);
                            if (log.isWarnEnabled()) {
                                log.warn(message + " value={}", Arrays.toString(value));
                            }
                            return new UnknownValue(value, message);
                        });
    }

    public Class<? extends ChannelValue> findClass(int type) {
        var maybe = ChannelType.findByValue(type).map(this::findClass);
        if (maybe.isEmpty()) {
            log.warn("Don't know how to map device channel type {} to channel value!", type);
            return UnknownValue.class;
        }
        return maybe.get();
    }

    public Class<? extends ChannelValue> findClass(final ChannelType channelType) {
        if (channelType == null) {
            return UnknownValue.class;
        }
        var maybe =
                findChannelTypeDecoder(channelType).map(ChannelValueDecoder::getChannelValueType);
        if (maybe.isEmpty()) {
            log.warn("Don't know how to map channel type {} to channel value!", channelType);
            return UnknownValue.class;
        }
        return maybe.get();
    }

    private Optional<ChannelValueDecoder<?>> findChannelTypeDecoder(ChannelType channelType) {
        return decoders.stream()
                .filter(decoder -> decoder.supportedChannelValueTypes().contains(channelType))
                .findAny();
    }
}
