package pl.grzeslowski.jsupla.protocoljava.api.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public interface ChannelSerializer extends ServerClientSerializer<Channel, SuplaChannel> {
}
