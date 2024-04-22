package pl.grzeslowski.jsupla.protocol.api.structs;

import com.google.common.reflect.ClassPath;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;

import static nl.jqno.equalsverifier.Warning.NULL_FIELDS;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public final class EqualsVerifierTest {
    final Class<?> clazz;

    public EqualsVerifierTest(final Class<?> clazz) {
        this.clazz = clazz;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] params() throws Exception {
        return ClassPath.from(EqualsVerifierTest.class.getClassLoader())
            .getTopLevelClassesRecursive(SuplaDataPacket.class.getPackage().getName())
            .asList()
            .stream()
            .map(ClassPath.ClassInfo::load)
            .filter(clazz -> !clazz.isInterface())
            .filter(Proto.class::isAssignableFrom)
            .map(clazz -> new Object[]{clazz})
            .toArray(Object[][]::new);
    }

    @Test
    public void shouldVerifyEqualsAndHashCode() throws Exception {
        EqualsVerifier.forClass(clazz)
            .suppress(NULL_FIELDS)
            .verify();
    }
}
