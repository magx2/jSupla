package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.dcs.DeviceClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceClientServerParserFactoryImpl implements DeviceClientServerParserFactory {

    private final PingServerParser pingServerParser;
    private final SetActivityTimeoutParser setActivityTimeoutParser;

    public DeviceClientServerParserFactoryImpl(final PingServerParser pingServerParser,
                                               final SetActivityTimeoutParser setActivityTimeoutParser) {
        this.pingServerParser = requireNonNull(pingServerParser);
        this.setActivityTimeoutParser = requireNonNull(setActivityTimeoutParser);
    }

    @Override
    public Parser<? extends DeviceClientServerEntity,
                         ? extends DeviceClientServer> getParser(@NotNull final DeviceClientServer proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaPingServer) {
            return pingServerParser;
        } else if (proto instanceof SuplaSetActivityTimeout) {
            return setActivityTimeoutParser;
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
