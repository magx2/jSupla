package pl.grzeslowski.jsupla.protocoljava.api.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;

public interface ChannelNewValueResultSerializer
    extends DeviceServerSerializer<ChannelNewValueResult, SuplaChannelNewValueResult> {
}
