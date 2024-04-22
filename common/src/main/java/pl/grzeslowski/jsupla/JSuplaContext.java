package pl.grzeslowski.jsupla;

import pl.grzeslowski.jsupla.exceptions.ServiceNotFoundException;

public interface JSuplaContext {
    /**
     * Finds service with given class.
     *
     * @param serviceClass Class of service to return.
     * @param <T>          Type of service to return
     * @return Service of type T
     * @throws ServiceNotFoundException when cannot find any service matching given class
     */
    <T> T getService(Class<T> serviceClass) throws ServiceNotFoundException;
}
