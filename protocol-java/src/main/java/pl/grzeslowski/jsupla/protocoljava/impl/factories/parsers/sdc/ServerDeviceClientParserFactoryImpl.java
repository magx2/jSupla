package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sdc.ServerDeviceClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.GetVersionResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.PingServerResultClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.SetActivityTimeoutResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.VersionErrorParser;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceClientParserFactoryImpl implements ServerDeviceClientParserFactory {
    private final VersionErrorParser versionErrorParser;
    private final GetVersionResultParser getVersionResultParser;
    private final SetActivityTimeoutResultParser setActivityTimeoutResultParser;
    private final PingServerResultClientParser pingServerResultClientParser;

    public ServerDeviceClientParserFactoryImpl(final VersionErrorParser versionErrorParser,
                                               final GetVersionResultParser getVersionResultParser,
                                               final SetActivityTimeoutResultParser setActivityTimeoutResultParser,
                                               final PingServerResultClientParser pingServerResultClientParser) {
        this.versionErrorParser = requireNonNull(versionErrorParser);
        this.getVersionResultParser = requireNonNull(getVersionResultParser);
        this.setActivityTimeoutResultParser = requireNonNull(setActivityTimeoutResultParser);
        this.pingServerResultClientParser = requireNonNull(pingServerResultClientParser);
    }

    @Override
    public Parser<? extends ServerDeviceClientEntity,
                         ? extends ServerDeviceClient> getParser(@NotNull final ServerDeviceClient proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaGetVersionResult) {
            return getVersionResultParser;
        } else if (proto instanceof SuplaPingServerResultClient) {
            return pingServerResultClientParser;
        } else if (proto instanceof SuplaSetActivityTimeoutResult) {
            return setActivityTimeoutResultParser;
        } else if (proto instanceof SuplaVersionError) {
            return versionErrorParser;
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
