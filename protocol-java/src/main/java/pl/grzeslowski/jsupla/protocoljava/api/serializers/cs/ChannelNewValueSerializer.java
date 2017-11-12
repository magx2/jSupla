package pl.grzeslowski.jsupla.protocoljava.api.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;

@Deprecated
public interface ChannelNewValueSerializer extends ClientServerSerializer<ChannelNewValue, SuplaChannelNewValue> {
}
