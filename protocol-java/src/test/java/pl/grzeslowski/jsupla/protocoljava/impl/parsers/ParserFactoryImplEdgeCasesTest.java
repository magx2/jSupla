package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ServerClientEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceEntityParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.ServerDeviceClientEntityParser;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.fail;

@SuppressWarnings({"unused", "WeakerAccess"})
@RunWith(MockitoJUnitRunner.class)
public class ParserFactoryImplEdgeCasesTest {
    @InjectMocks ParserImpl factory;

    @Mock ClientServerEntityParser clientServerParserFactory;
    @Mock DeviceClientServerEntityParser deviceClientServerParserFactory;
    @Mock DeviceServerEntityParser deviceServerParserFactory;
    @Mock ServerClientEntityParser serverClientParserFactory;
    @Mock ServerDeviceEntityParser serverDeviceParserFactory;
    @Mock ServerDeviceClientEntityParser serverDeviceClientParserFactory;

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
        factory.parse(proto);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenProtoIsNullIsNull() {
        factory.parse(null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldThrowNullPointerExceptionIfOneOfArgsIsNull() throws Exception {
        ClassPath.from(ParserImpl.class.getClassLoader())
                .getTopLevelClassesRecursive(ParserImpl.class.getPackage().getName())
                .asList()
                .stream()
                .map(ClassPath.ClassInfo::load)
                .filter(clazz -> !clazz.isInterface())
                .filter(Parser.class::isAssignableFrom)
                .map(clazz -> (Class<Parser>) clazz)
                .forEach(this::passNullForEachClass);
    }

    private void passNullForEachClass(final Class<Parser> parserClass) {
        Arrays.stream(parserClass.getConstructors()).forEach(this::passNullForEachConstructor);
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