package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

@SuppressWarnings({"WeakerAccess", "unchecked", "unused"})
@RunWith(Parameterized.class)
public class SerializerFactoryNpeTest {

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() throws IOException {
        return ClassPath.from(SerializerImpl.class.getClassLoader())
            .getTopLevelClassesRecursive(SerializerImpl.class.getPackage().getName())
            .asList()
            .stream()
            .map(ClassPath.ClassInfo::load)
            .filter(clazz -> !clazz.isInterface())
            .filter(Serializer.class::isAssignableFrom)
            .map(clazz -> (Class<Serializer>) clazz)
            .map(clazz -> new Object[]{clazz})
            .collect(Collectors.toList());
    }

    final Class<? extends Serializer<?, ?>> serializerFactoryClass;

    public SerializerFactoryNpeTest(final Class<? extends Serializer<?, ?>> serializerFactoryClass) {
        this.serializerFactoryClass = serializerFactoryClass;
    }

    @Test
    public void should() throws Exception {
        final Constructor<?>[] constructors = serializerFactoryClass.getDeclaredConstructors();
        assertThat(constructors).hasSize(1);
        final Constructor<?> constructor = constructors[0];

        final List<?> parameters = Arrays.stream(constructor.getParameterTypes())
            .map(Mockito::mock)
            .collect(Collectors.toList());

        for (int i = 0; i < parameters.size(); i++) {
            try {
                final Object[] parametersWithNull = parameters.toArray();
                parametersWithNull[i] = null;
                constructor.newInstance(parametersWithNull);
                fail(format("Parameter #%s can not be null and constructor should throw an exception!", i));
            } catch (InvocationTargetException ex) {
                assertThat(ex).hasRootCauseExactlyInstanceOf(NullPointerException.class);
            }
        }
    }
}
