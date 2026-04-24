package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ELECTRICITY_METER;
import static pl.grzeslowski.jsupla.protocol.api.ElectricMeterPhaseFlag.*;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.BigDecimalDivider;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterSimpleValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.ElectricityMeterValueDecoder;

public class ElectricityMeterSimpleDecoder
        implements ChannelValueDecoder<ElectricityMeterSimpleValue> {
    private static final int DIVIDER_PRECISION = 64;
    private static final BigDecimalDivider ENERGY_DIVIDER =
            new BigDecimalDivider(100, DIVIDER_PRECISION);

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_ELECTRICITY_METER);
    }

    @Override
    public Class<ElectricityMeterSimpleValue> getChannelValueType() {
        return ElectricityMeterSimpleValue.class;
    }

    @Override
    public ElectricityMeterSimpleValue decode(byte[] bytes, int offset) {
        var decode = ElectricityMeterValueDecoder.INSTANCE.decode(bytes, offset);
        var totalForwardActiveEnergy = ENERGY_DIVIDER.divide(decode.totalForwardActiveEnergy());
        var flags = decode.flags();
        var emFlags = findByMask(flags);
        return new ElectricityMeterSimpleValue(
                totalForwardActiveEnergy,
                emFlags.contains(EM_VALUE_FLAG_PHASE1_ON),
                emFlags.contains(EM_VALUE_FLAG_PHASE2_ON),
                emFlags.contains(EM_VALUE_FLAG_PHASE3_ON));
    }
}
