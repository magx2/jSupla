package pl.grzeslowski.jsupla.protocoljava.api.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public interface ChannelParser extends ServerClientParser<Channel, SuplaChannel> {
}
