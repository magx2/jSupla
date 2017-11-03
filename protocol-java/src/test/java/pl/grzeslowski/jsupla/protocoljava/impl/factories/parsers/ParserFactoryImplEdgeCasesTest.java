package pl.grzeslowski.jsupla.protocoljava.impl.factories.parsers;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.cs.ClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.dcs.DeviceClientServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.ds.DeviceServerParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sc.ServerClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sd.ServerDeviceParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.factories.parsers.sdc.ServerDeviceClientParserFactory;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.fail;
import static pl.grzeslowski.jsupla.protocol.common.RandomSupla.RANDOM_SUPLA;

@SuppressWarnings({"unused", "WeakerAccess"})
@RunWith(MockitoJUnitRunner.class)
public class ParserFactoryImplEdgeCasesTest {
    @InjectMocks ParserFactoryImpl factory;

    @Mock ClientServerParserFactory clientServerParserFactory;
    @Mock DeviceClientServerParserFactory deviceClientServerParserFactory;
    @Mock DeviceServerParserFactory deviceServerParserFactory;
    @Mock ServerClientParserFactory serverClientParserFactory;
    @Mock ServerDeviceParserFactory serverDeviceParserFactory;
    @Mock ServerDeviceClientParserFactory serverDeviceClientParserFactory;

    @Mock DeviceChannelParser deviceChannelParser;
    @Mock DeviceChannelBParser deviceChannelBParser;
    @Mock FirmwareUpdateUrlParser firmwareUpdateUrlParser;
    @Mock ChannelValueParser channelValueParser;
    @Mock TimevalParser timevalParser;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCannotFindProtoToParser() throws Exception {

        // given
        final @NotNull Proto proto = new Proto() {
        };

        // when
        factory.getParser(proto);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenProtoIsNullIsNull() {
        factory.getParser(null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldWorkForAllSuplaStructs() throws Exception {
        ClassPath.from(Proto.class.getClassLoader())
                .getTopLevelClassesRecursive(SuplaDataPacket.class.getPackage().getName())
                .asList()
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(Proto.class::isAssignableFrom)
                .filter(clazz -> !SuplaDataPacket.class.equals(clazz))
                .map(clazz -> (Class<Proto>) clazz)
                .map(clazz -> RANDOM_SUPLA.nextObject(clazz))
                .forEach(this::shouldWorkForSuplaProtoSubClass);
    }

    private void shouldWorkForSuplaProtoSubClass(Proto proto) {
        factory.getParser(proto);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldThrowNullPointerExceptionIfOneOfArgsIsNull() throws Exception {
        ClassPath.from(ParserFactoryImpl.class.getClassLoader())
                .getTopLevelClassesRecursive(ParserFactoryImpl.class.getPackage().getName())
                .asList()
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(ParserFactory.class::isAssignableFrom)
                .map(clazz -> (Class<ParserFactory>) clazz)
                .forEach(this::passNullForEachClass);
    }

    private void passNullForEachClass(final Class<ParserFactory> factoryClass) {
        Arrays.stream(factoryClass.getConstructors()).forEach(this::passNullForEachConstructor);
    }

    private void passNullForEachConstructor(final Constructor<?> constructor) {
        final Object[] constructorParameters = mockParameterTypes(constructor.getParameterTypes());
        for (int i = 0; i < constructorParameters.length; i++) {
            final Object[] params = Arrays.copyOf(constructorParameters, constructorParameters.length);
            params[i] = null;
            try {
                constructor.newInstance(params);
                fail("Should throw NPE when invoking constructor %s with param %d", constructor, i);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                if (e.getCause().getClass() == NullPointerException.class) {
                    // OK - we expected this
                    return;
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Object[] mockParameterTypes(final Class<?>[] parameterTypes) {
        return Arrays.stream(parameterTypes)
                       .map(Mockito::mock)
                       .toArray(Object[]::new);
    }
}