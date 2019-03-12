package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.*;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class ServerDeviceClientParserImpl implements ServerDeviceClientParser<ServerDeviceClientEntity,
                                                                                           ServerDeviceClient> {
    private final VersionErrorParser versionErrorParser;
    private final GetVersionResultParser getVersionResultParser;
    private final SetActivityTimeoutResultParser setActivityTimeoutResultParser;
    private final PingServerResultClientParser pingServerResultClientParser;

    public ServerDeviceClientParserImpl(final VersionErrorParser versionErrorParser,
                                        final GetVersionResultParser getVersionResultParser,
                                        final SetActivityTimeoutResultParser setActivityTimeoutResultParser,
                                        final PingServerResultClientParser pingServerResultClientParser) {
        this.versionErrorParser = requireNonNull(versionErrorParser);
        this.getVersionResultParser = requireNonNull(getVersionResultParser);
        this.setActivityTimeoutResultParser = requireNonNull(setActivityTimeoutResultParser);
        this.pingServerResultClientParser = requireNonNull(pingServerResultClientParser);
    }

    @Override
    public ServerDeviceClientEntity parse(@NotNull final ServerDeviceClient proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaGetVersionResult) {
            return getVersionResultParser.parse((SuplaGetVersionResult) proto);
        } else if (proto instanceof SuplaPingServerResultClient) {
            return pingServerResultClientParser.parse((SuplaPingServerResultClient) proto);
        } else if (proto instanceof SuplaSetActivityTimeoutResult) {
            return setActivityTimeoutResultParser.parse((SuplaSetActivityTimeoutResult) proto);
        } else if (proto instanceof SuplaVersionError) {
            return versionErrorParser.parse((SuplaVersionError) proto);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to parser! %s",
                proto.getClass(), proto));
    }
}
