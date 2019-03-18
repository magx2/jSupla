package pl.grzeslowski.jsupla.protocoljava.api.entities;

import com.google.common.reflect.ClassPath;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import static nl.jqno.equalsverifier.Warning.NULL_FIELDS;
import static nl.jqno.equalsverifier.Warning.STRICT_HASHCODE;
import static pl.grzeslowski.jsupla.protocoljava.api.entities.RedefinedEqualsVerifierTest.ALL_REDEFINED_TYPES;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class EqualsVerifierTest {
    final Class<?> clazz;

    public EqualsVerifierTest(final Class<?> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("UnstableApiUsage")
    @Parameterized.Parameters(name = "{0}")
    public static Object[][] params() throws Exception {
        return ClassPath.from(pl.grzeslowski.jsupla.protocol.api.structs.EqualsVerifierTest.class.getClassLoader())
                   .getTopLevelClassesRecursive(ChannelValue.class.getPackage().getName())
                   .asList()
                   .stream()
                   .map(ClassPath.ClassInfo::load)
                   .filter(clazz -> !clazz.isInterface())
                   .filter(Entity.class::isAssignableFrom)
                   .filter(clazz -> !ALL_REDEFINED_TYPES.contains(clazz))
                   .map(clazz -> new Object[]{clazz})
                   .toArray(Object[][]::new);
    }

    @Test
    public void shouldVerifyEqualsAndHashCode() {
        EqualsVerifier.forClass(clazz)
                .suppress(NULL_FIELDS, STRICT_HASHCODE)
                .verify();
    }
}
