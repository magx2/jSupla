package pl.grzeslowski.jsupla.protocol;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.EncoderFactoryImpl;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class IntegrationTest {
    Class<ProtoWithSize> structClass;

    @SuppressWarnings("UnstableApiUsage")
    @Parameterized.Parameters(name = "class = {0}")
    public static Object[][] data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
            .getTopLevelClassesRecursive(SuplaTimeval.class.getPackage().getName())
            .stream()
            .map(ClassPath.ClassInfo::load)
            .filter(clazz -> !clazz.isInterface())
            .filter(ProtoWithSize.class::isAssignableFrom)
            .map(clazz -> new Object[]{clazz})
            .toArray(Object[][]::new);
    }

    public IntegrationTest(Class<ProtoWithSize> structClass) {
        this.structClass = structClass;
    }

    @Test
    public void shouldFindEncoderFor() {
        EncoderFactoryImpl.INSTANCE.getEncoder(structClass);
    }

    @Test
    public void shouldFindDecoderFor() {
        DecoderFactoryImpl.INSTANCE.getDecoder(structClass);
    }
}
