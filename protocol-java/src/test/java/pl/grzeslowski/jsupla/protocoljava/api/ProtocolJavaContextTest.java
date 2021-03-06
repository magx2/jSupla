package pl.grzeslowski.jsupla.protocoljava.api;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.JSuplaContext;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class ProtocolJavaContextTest {
    DeviceChannelValueParser.TypeMapper typeMapper = mock(DeviceChannelValueParser.TypeMapper.class);
    
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        return Stream.concat(createStream(Parser.class), createStream(Serializer.class))
                       .map(entity -> new Object[]{entity})
                       .collect(Collectors.toList());
    }

    private static Stream<? extends Class<?>> createStream(Class<?> mainClass) throws IOException {
        return ClassPath.from(mainClass.getClassLoader())
                       .getTopLevelClassesRecursive(mainClass.getPackage().getName())
                       .asList()
                       .stream()
                       .map(ClassPath.ClassInfo::load)
                       .filter(Class::isInterface)
                       .filter(mainClass::isAssignableFrom);
    }

    final Class<?> serviceClass;

    public ProtocolJavaContextTest(final Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Test
    public void shouldFindService() {

        // given
        final JSuplaContext context = new ProtocolJavaContext(typeMapper);
        
        // when
        Object service = context.getService(serviceClass);

        // then
        assertThat(service).isNotNull();
    }
}