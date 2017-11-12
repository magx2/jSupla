package pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.PingServerResultClient;

public interface PingServerResultClientSerializer
        extends ServerDeviceClientSerializer<PingServerResultClient, SuplaPingServerResultClient> {
}
