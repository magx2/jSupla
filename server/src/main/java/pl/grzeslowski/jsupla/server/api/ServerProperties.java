package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.server.api.exceptions.PropertyCastException;
import pl.grzeslowski.jsupla.server.api.exceptions.PropertyNotExistsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class ServerProperties {
    private final Map<String, ?> properties;

    public static ServerProperties fromList(final List<Object> properties) {
        if (properties.size() % 2 != 0) {
            throw new IllegalArgumentException("Size should be even! Actual size is " + properties.size());
        }
        final Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < properties.size() - 1; i += 2) {
            final Object key = properties.get(i);
            final Object value = properties.get(i + 1);
            if (!(key instanceof String)) {
                throw new IllegalArgumentException(
                        format("All keys must be Strings! Key at index %s was not String! key = %s, class = %s",
                                i, key, key.getClass().getSimpleName())
                );
            }
            map.put((String) key, value);
        }
        return new ServerProperties(map);
    }

    public ServerProperties(final Map<String, ?> properties) {
        this.properties = requireNonNull(properties);
    }

    public Object getProperty(String name) {
        return getProperty(name, Object.class);
    }

    /**
     * Finds property in map with given type.
     *
     * @param name property name
     * @param clazz property class
     * @param <T> type of property
     * @return Property of given name with given class
     * @throws PropertyCastException      when cannot cast property to given class
     * @throws PropertyNotExistsException when there is no property with given name
     */
    public <T> T getProperty(String name, Class<T> clazz) throws PropertyCastException, PropertyNotExistsException {
        Object o = properties.get(name);
        if (o == null) {
            throw new PropertyNotExistsException(name);
        }
        if (clazz.isAssignableFrom(o.getClass())) {
            return clazz.cast(o);
        } else {
            throw new PropertyCastException(o, clazz);
        }
    }

    @Override
    public String toString() {
        return "ServerProperties{" +
                       "properties=" + properties +
                       '}';
    }
}
