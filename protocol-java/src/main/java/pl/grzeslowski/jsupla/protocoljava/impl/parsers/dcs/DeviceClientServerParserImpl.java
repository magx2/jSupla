package pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceClientServerParserImpl implements DeviceClientServerParser<DeviceClientServerEntity,
                                                                                           DeviceClientServer> {

    private final PingServerParser pingServerParser;
    private final SetActivityTimeoutParser setActivityTimeoutParser;

    public DeviceClientServerParserImpl(final PingServerParser pingServerParser,
                                        final SetActivityTimeoutParser setActivityTimeoutParser) {
        this.pingServerParser = requireNonNull(pingServerParser);
        this.setActivityTimeoutParser = requireNonNull(setActivityTimeoutParser);
    }

    @Override
    public DeviceClientServerEntity parse(@NotNull final DeviceClientServer proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaPingServer) {
            return pingServerParser.parse((SuplaPingServer) proto);
        } else if (proto instanceof SuplaSetActivityTimeout) {
            return setActivityTimeoutParser.parse((SuplaSetActivityTimeout) proto);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to parser! %s",
                proto.getClass(), proto));
    }
}
