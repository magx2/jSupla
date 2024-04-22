package pl.grzeslowski.jsupla.protocoljava;

import com.google.common.reflect.ClassPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.JSuplaContext;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.*;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;
import pl.grzeslowski.jsupla.protocoljava.api.ProtocolJavaContext;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType.SUPLA_CHANNELTYPE_RELAY;

@SuppressWarnings("WeakerAccess")
@RunWith(Parameterized.class)
public class IntegrationTest {
    private static final Collection<Class<? extends Proto>> TEMPORARY_UNSUPORTED_PROTOS = Arrays.asList(
        pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class,
        pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class,
        SuplaChannelB.class,
        SuplaChannelValuePack.class,
        SuplaChannelPackB.class,
        SuplaChannelPack.class,
        SuplaChannel.class
    );

    Proto proto;
    JSuplaContext context = new ProtocolJavaContext(channelNumber -> SUPLA_CHANNELTYPE_RELAY);

    @SuppressWarnings({"UnstableApiUsage", "SuspiciousMethodCalls"})
    @Parameterized.Parameters(name = "class = {0}")
    public static Object[][] data() throws Exception {
        return ClassPath.from(Thread.currentThread().getContextClassLoader())
            .getTopLevelClassesRecursive(SuplaTimeval.class.getPackage().getName())
            .stream()
            .map(ClassPath.ClassInfo::load)
            .filter(clazz -> !clazz.isInterface())
            .filter(Proto.class::isAssignableFrom)
            .filter(clazz -> !SuplaDataPacket.class.isAssignableFrom(clazz))
            // FIXME fix problem with SuplaChannelValueToChannelTypeImpl
            .filter(clazz -> !TEMPORARY_UNSUPORTED_PROTOS.contains(clazz))
            .map(clazz -> RandomSupla.RANDOM_SUPLA.nextObject(clazz))
            .map(obj -> new Object[]{obj})
            .toArray(Object[][]::new);
    }

    public IntegrationTest(Proto proto) {
        this.proto = proto;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldFindSerializerAndParserFor() {
        Entity entity1 = context.getService(Parser.class).parse(proto);
        assertThat(entity1).isNotNull();
        Proto serialize = context.getService(Serializer.class).serialize(entity1);
        assertThat(serialize).isNotNull();
        assertThat(serialize).isEqualTo(proto);
        assertThat(proto).isEqualTo(serialize);
        Entity entity2 = context.getService(Parser.class).parse(serialize);
        assertThat(entity2).isEqualTo(entity1);
        assertThat(entity1).isEqualTo(entity2);
    }
}
