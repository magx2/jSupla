package pl.grzeslowski.jsupla.protocol.api.structs;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.types.Proto;

import static pl.grzeslowski.jsupla.protocol.common.RandomBean.RANDOM_BEAN;

public abstract class StructTest<T extends Proto> {
    public abstract Class<T> getTestClass();

    @Test
    public void shouldCreateObject() throws Exception {
        RANDOM_BEAN.nextObject(getTestClass());
    }
}
