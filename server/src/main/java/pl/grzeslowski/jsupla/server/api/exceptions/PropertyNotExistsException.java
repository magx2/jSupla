package pl.grzeslowski.jsupla.server.api.exceptions;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class PropertyNotExistsException extends IllegalArgumentException {
    private final String propertyName;

    public PropertyNotExistsException(final String propertyName) {
        super(format("Can't find property \"%s\" in properties map!", propertyName));
        this.propertyName = requireNonNull(propertyName);
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public String toString() {
        return "PropertyNotExistsException{"
                + "propertyName='"
                + propertyName
                + '\''
                + "} "
                + super.toString();
    }
}
