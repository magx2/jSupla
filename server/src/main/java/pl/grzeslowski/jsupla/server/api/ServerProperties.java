package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.server.api.exceptions.PropertyCastException;
import pl.grzeslowski.jsupla.server.api.exceptions.PropertyNotExistsException;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public final class ServerProperties {
    private final Map<String, ?> properties;

    public ServerProperties(final Map<String, ?> properties) {
        this.properties = requireNonNull(properties);
    }

    public Object getProperty(String name) {
        return getProperty(name, Object.class);
    }

    /**
     * Finds property in map with given type.
     *
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
