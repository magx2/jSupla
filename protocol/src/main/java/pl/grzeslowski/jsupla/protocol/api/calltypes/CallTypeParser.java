package pl.grzeslowski.jsupla.protocol.api.calltypes;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class CallTypeParser {
    public static final CallTypeParser INSTANCE = new CallTypeParser();
    private final List<CallType> callTypes;

    private CallTypeParser() {
        callTypes = Stream.of(ServerDeviceClientCallType.values(),
                ServerDeviceCallType.values(),
                ServerClientCallType.values(),
                DeviceServerCallType.values(),
                DeviceClientServerCallType.values(),
                ClientServerCallType.values(),
                ServerClientDeviceCallType.values(),
                ClientServerDeviceCallType.values(),
                DeviceServerClientCallType.values(),
                ServerDeviceClientCallType.values())
            .flatMap(Stream::of)
            .map(v -> (CallType) v)
            .collect(Collectors.toList());

    }

    public Optional<CallType> parse(final long callType) {
        Optional<CallType> any = callTypes.stream().filter(v -> v.getValue() == callType).findAny();
        if (!any.isPresent()) {
            log.debug("Cannot find call type for {}", callType);
        }
        return any;
    }
}
