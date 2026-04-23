package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.util.Optional.empty;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ELECTRICITY_METER;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.Phase.EMPTY;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_VALUE_FLAG_PHASE1_ON;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_VALUE_FLAG_PHASE2_ON;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_VALUE_FLAG_PHASE3_ON;

import java.util.Optional;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.BigDecimalDivider;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.ElectricityMeterValueDecoder;

public class ElectricityMeterSimpleDecoder implements ChannelValueDecoder<ElectricityMeterValue> {
    private static final int DIVIDER_PRECISION = 64;
    private static final BigDecimalDivider ENERGY_DIVIDER =
            new BigDecimalDivider(100, DIVIDER_PRECISION);

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_ELECTRICITY_METER);
    }

    @Override
    public Class<ElectricityMeterValue> getChannelValueType() {
        return ElectricityMeterValue.class;
    }

    @Override
    public ElectricityMeterValue decode(byte[] bytes, int offset) {
        var decode = ElectricityMeterValueDecoder.INSTANCE.decode(bytes, offset);
        var totalForwardActiveEnergy = ENERGY_DIVIDER.divide(decode.totalForwardActiveEnergy());
        var flags = decode.flags();
        return new ElectricityMeterValue(
                Optional.of(totalForwardActiveEnergy),
                empty(),
                empty(),
                empty(),
                empty(),
                0,
                empty(),
                empty(),
                empty(),
                empty(),
                phaseIfEnabled(flags, EM_VALUE_FLAG_PHASE1_ON),
                phaseIfEnabled(flags, EM_VALUE_FLAG_PHASE2_ON),
                phaseIfEnabled(flags, EM_VALUE_FLAG_PHASE3_ON));
    }

    private Optional<ElectricityMeterValue.Phase> phaseIfEnabled(byte flags, int phaseFlag) {
        return hasFlag(flags, phaseFlag) ? Optional.of(EMPTY) : empty();
    }

    private boolean hasFlag(byte flags, int phaseFlag) {
        return (flags & phaseFlag) != 0;
    }
}
