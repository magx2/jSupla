package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Test;

public class PreconditionsUnionTest {
    @Test
    public void properUnion() {
        // given
        String obj1 = null;
        String obj2 = "obj2";
        String obj3 = null;

        // when
        Preconditions.unionCheck(obj1, obj2, obj3);

        // then
        // should not throw exception
    }

    @Test
    public void shouldThrowExWhenMoreThan1ObjectsAreNotNull() {
        // given
        String obj1 = null;
        String obj2 = "obj2";
        String obj3 = "obj3";

        // when
        ThrowingCallable when = () -> Preconditions.unionCheck(obj1, obj2, obj3);

        // then
        assertThatThrownBy(when).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowExWhenAllObjectsAreNull() {
        // given
        String obj1 = null;
        String obj2 = null;
        String obj3 = null;

        // when
        ThrowingCallable when = () -> Preconditions.unionCheck(obj1, obj2, obj3);

        // then
        assertThatThrownBy(when).isInstanceOf(IllegalArgumentException.class);
    }
}
