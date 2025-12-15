package pl.grzeslowski.jsupla.protocol;

import com.google.common.reflect.ClassPath;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

@SuppressWarnings("WeakerAccess")
class IntegrationTest {

    @SuppressWarnings("UnstableApiUsage")
    static Stream<Arguments> data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
                .getTopLevelClassesRecursive(SuplaTimeval.class.getPackage().getName())
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(ProtoWithSize.class::isAssignableFrom)
                .map(Arguments::of);
    }

    @ParameterizedTest(name = "class = {0}")
    @MethodSource("data")
    void shouldFindEncoderFor(Class<ProtoWithSize> structClass) {
        EncoderFactoryImpl.INSTANCE.getEncoder(structClass);
    }

    @ParameterizedTest(name = "class = {0}")
    @MethodSource("data")
    void shouldFindDecoderFor(Class<ProtoWithSize> structClass) {
        DecoderFactoryImpl.INSTANCE.getDecoder(structClass);
    }
}
