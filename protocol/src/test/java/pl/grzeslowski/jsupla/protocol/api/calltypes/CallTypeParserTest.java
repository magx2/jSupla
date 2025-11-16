package pl.grzeslowski.jsupla.protocol.api.calltypes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.Test;

public class CallTypeParserTest {
    private final CallTypeParser parser = CallTypeParser.INSTANCE;

    @Test
    public void shouldParseEveryKnownCallTypeValue() {
        Stream.of(
                        ServerDeviceClientCallType.values(),
                        ServerDeviceCallType.values(),
                        ServerClientCallType.values(),
                        DeviceServerCallType.values(),
                        DeviceClientServerCallType.values(),
                        ClientServerCallType.values(),
                        ServerClientDeviceCallType.values(),
                        ClientServerDeviceCallType.values(),
                        DeviceServerClientCallType.values())
                .flatMap(Stream::of)
                .forEach(
                        callType ->
                                assertThat(parser.parse(callType.getValue())).contains(callType));
    }

    @Test
    public void shouldReturnEmptyOptionalForUnknownValue() {
        assertThat(parser.parse(-1)).isEmpty();
    }
}
