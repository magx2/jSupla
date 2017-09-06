package pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.PingServer;

public interface PingServerSerializer extends DeviceClientServerSerializer<PingServer, SuplaPingServer> {
}
