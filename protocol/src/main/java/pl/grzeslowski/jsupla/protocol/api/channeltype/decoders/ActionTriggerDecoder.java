package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_ACTIONTRIGGER;

import java.util.Arrays;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.UnknownValue;

@Slf4j
class ActionTriggerDecoder implements ChannelValueDecoder<UnknownValue> {
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_ACTIONTRIGGER);
    }

    @Override
    public Class<UnknownValue> getChannelValueType() {
        return UnknownValue.class;
    }

    @Override
    public UnknownValue decode(byte[] bytes, int offset) {
        log.error(
                "Can not decode SUPLA_CHANNELTYPE_ACTIONTRIGGER, value={}", Arrays.toString(bytes));
        return new UnknownValue(bytes, "Can not decode SUPLA_CHANNELTYPE_ACTIONTRIGGER");
    }
}
