package pl.grzeslowski.jsupla.protocol;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.reflect.ClassPath;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class CallTypeIntegrationTest {
    CallType callType;

    @SuppressWarnings("UnstableApiUsage")
    @Parameterized.Parameters(name = "class = {0}")
    public static Object[][] data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getTopLevelClassesRecursive(CallType.class.getPackage().getName())
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(CallType.class::isAssignableFrom)
                .map(Class::getEnumConstants)
                .flatMap(Arrays::stream)
                .map(enumConst -> new Object[] {enumConst})
                .toArray(Object[][]::new);
    }

    public CallTypeIntegrationTest(CallType callType) {
        this.callType = callType;
    }

    @Test
    public void shouldFindCallType() {
        Optional<CallType> parse = CallTypeParser.INSTANCE.parse(callType.getValue());
        assertThat(parse).contains(callType);
    }
}
