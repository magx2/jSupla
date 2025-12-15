package pl.grzeslowski.jsupla.protocol;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.reflect.ClassPath;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;

@SuppressWarnings("WeakerAccess")
class CallTypeIntegrationTest {
    @SuppressWarnings("UnstableApiUsage")
    static Stream<Arguments> data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getTopLevelClassesRecursive(CallType.class.getPackage().getName())
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(CallType.class::isAssignableFrom)
                .map(Class::getEnumConstants)
                .flatMap(Arrays::stream)
                .map(Arguments::of);
    }

    @ParameterizedTest(name = "class = {0}")
    @MethodSource("data")
    void shouldFindCallType(CallType callType) {
        Optional<CallType> parse = CallTypeParser.INSTANCE.parse(callType.getValue());
        assertThat(parse).contains(callType);
    }
}
