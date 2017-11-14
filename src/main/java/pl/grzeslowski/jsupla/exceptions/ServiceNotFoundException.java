package pl.grzeslowski.jsupla.exceptions;

import static java.lang.String.format;

public final class ServiceNotFoundException extends IllegalArgumentException {
    private final Class<?> serviceClass;

    public ServiceNotFoundException(final Class<?> serviceClass) {
        super(format("Cant find service with class \"%s\"!", serviceClass.getName()));
        this.serviceClass = serviceClass;
    }

    public Class<?> getServiceClass() {
        return serviceClass;
    }
}
