package pl.grzeslowski.jsupla.protocol.impl.calltypes;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CallTypeParserImpl implements CallTypeParser {
    private final List<CallType> callTypes;

    public CallTypeParserImpl() {
        callTypes = Stream.of(ServerDeviceClientCallType.values(),
                ServerDeviceCallType.values(),
                ServerClientCallType.values(),
                DeviceServerCallType.values(),
                DeviceClientServerCallType.values(),
                ClientServerCallType.values())
                            .flatMap(Stream::of)
                            .map(v -> (CallType) v)
                            .collect(Collectors.toList());

    }

    @Override
    public Optional<CallType> parse(final long callType) {
        return callTypes.stream().filter(v -> v.getValue() == callType).findAny();
    }
}
