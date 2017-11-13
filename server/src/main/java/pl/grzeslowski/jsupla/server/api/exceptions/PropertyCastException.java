package pl.grzeslowski.jsupla.server.api.exceptions;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public final class PropertyCastException extends IllegalArgumentException {
    private final Object objectToCast;
    private final Class<?> classToCast;

    public PropertyCastException(final Object objectToCast, final Class<?> classToCast) {
        super(format("Can't cast object \"%s\" to class \"%s\"!",
                objectToCast, classToCast.getName()));
        this.objectToCast = requireNonNull(objectToCast);
        this.classToCast = requireNonNull(classToCast);
    }

    public Object getObjectToCast() {
        return objectToCast;
    }

    public Class<?> getClassToCast() {
        return classToCast;
    }

    @Override
    public String toString() {
        return "PropertyCastException{" +
                       "objectToCast=" + objectToCast +
                       ", classToCast=" + classToCast +
                       "} " + super.toString();
    }
}
