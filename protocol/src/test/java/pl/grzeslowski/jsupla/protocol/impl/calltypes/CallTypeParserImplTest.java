package pl.grzeslowski.jsupla.protocol.impl.calltypes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class CallTypeParserImplTest {
    private final CallTypeParserImpl callTypeParser = new CallTypeParserImpl();

    private final CallType callType;

    public CallTypeParserImplTest(CallType callType) {
        this.callType = callType;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Stream.of(ServerDeviceClientCallType.values(),
                ServerDeviceCallType.values(),
                ServerClientCallType.values(),
                DeviceServerCallType.values(),
                DeviceClientServerCallType.values(),
                ClientServerCallType.values())
                       .flatMap(Stream::of)
                       .map(v -> (CallType) v)
                       .map(callType -> new Object[]{callType})
                       .collect(Collectors.toList());
    }

    @Test
    public void shouldFindProperCallType() throws Exception {
        // when
        final Optional<CallType> parseCallTypeOptional = callTypeParser.parse(callType.getValue());

        // then
        assertThat(parseCallTypeOptional).contains(callType);
    }
}