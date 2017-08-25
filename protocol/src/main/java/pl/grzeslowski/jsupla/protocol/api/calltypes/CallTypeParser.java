package pl.grzeslowski.jsupla.protocol.api.calltypes;

import java.util.Optional;

public interface CallTypeParser {
    Optional<CallType> parse(long callType);
}
