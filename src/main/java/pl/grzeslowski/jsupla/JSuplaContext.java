package pl.grzeslowski.jsupla;

import pl.grzeslowski.jsupla.exceptions.ServiceNotFoundException;

public interface JSuplaContext {
    /**
     * Finds service with given class.
     *
     * @throws ServiceNotFoundException when cannot find any service matching given class
     */
    <T> T getService(Class<T> serviceClass) throws ServiceNotFoundException;
}
