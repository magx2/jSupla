package pl.grzeslowski.jsupla.protocoljava.api.entities;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.RegisterClientB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannels;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.RegisterClientResultB;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.jqno.equalsverifier.Warning.NULL_FIELDS;
import static nl.jqno.equalsverifier.Warning.STRICT_HASHCODE;

@RunWith(Parameterized.class)
@SuppressWarnings({"unchecked", "WeakerAccess"})
public class RedefinedEqualsVerifierTest<T> {
    static final Set<Hierarchy> REDEFINED_TYPES = new HashSet<>();
    static final Set<Class<?>> ALL_REDEFINED_TYPES;

    static {
        REDEFINED_TYPES.add(new Hierarchy(ChannelNewValue.class, ChannelNewValueB.class));
        REDEFINED_TYPES.add(new Hierarchy(RegisterClient.class, RegisterClientB.class));
        REDEFINED_TYPES.add(new Hierarchy(DeviceChannel.class, DeviceChannelB.class));
        REDEFINED_TYPES.add(new Hierarchy(DeviceChannels.class, DeviceChannelsB.class));
        REDEFINED_TYPES.add(new Hierarchy(RegisterDevice.class, RegisterDeviceB.class));
        REDEFINED_TYPES.add(new Hierarchy(RegisterDeviceB.class, RegisterDeviceC.class, RegisterDevice.class));
        REDEFINED_TYPES.add(new Hierarchy(RegisterDeviceC.class, RegisterDeviceD.class, RegisterDeviceB.class));
        REDEFINED_TYPES.add(new Hierarchy(
            RegisterClientResult.class,
            RegisterClientResultB.class,
            RegisterDevice.class));
        REDEFINED_TYPES.add(new Hierarchy(Channel.class, ChannelB.class));

        // there is some shitty error with javac
        final Stream<Class<?>> classStream = REDEFINED_TYPES.stream().flatMap(Hierarchy::toStream);
        ALL_REDEFINED_TYPES = classStream.collect(Collectors.toSet());
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] params() throws Exception {
        return REDEFINED_TYPES
                       .stream()
                       .map(hierarchy -> new Object[]{hierarchy})
                       .toArray(Object[][]::new);

    }

    final Hierarchy hierarchy;

    public RedefinedEqualsVerifierTest(final Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Test
    public void shouldVerifyEqualsAndHashCode() throws Exception {
        if (hierarchy.superClass != null) {
            EqualsVerifier.forClass(hierarchy.clazz)
                    .withRedefinedSubclass(hierarchy.subClass)
                    .withRedefinedSuperclass()
                    .suppress(NULL_FIELDS, STRICT_HASHCODE)
                    .verify();
        } else {
            EqualsVerifier.forClass(hierarchy.clazz)
                    .withRedefinedSubclass(hierarchy.subClass)
                    .suppress(NULL_FIELDS, STRICT_HASHCODE)
                    .verify();
        }
    }

    private static class Hierarchy<T> {
        final Class<T> clazz;
        final Class<? extends T> subClass;
        final Class<? super T> superClass;

        private Hierarchy(final Class<T> clazz, final Class<? extends T> subClass, final Class<? super T> superClass) {
            this.clazz = clazz;
            this.subClass = subClass;
            this.superClass = superClass;
        }

        public Hierarchy(final Class<T> clazz, final Class<? extends T> subClass) {
            this(clazz, subClass, null);
        }

        Stream<Class<?>> toStream() {
            return Stream.of(clazz, subClass, superClass);
        }

        @Override
        public String toString() {
            return "Hierarchy{" +
                           "clazz=" + clazz +
                           ", subClass=" + subClass +
                           ", superClass=" + superClass +
                           '}';
        }
    }
}
