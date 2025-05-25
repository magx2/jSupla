package pl.grzeslowski.jsupla.server.api;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.server.api.exceptions.PropertyCastException;
import pl.grzeslowski.jsupla.server.api.exceptions.PropertyNotExistsException;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

public class ServerPropertiesTest {
    EnhancedRandom random = new EnhancedRandomBuilder().build();

    @Test
    public void shouldReturnPreviouslyGivenProperty() {

        // given
        final String keyName = random.nextObject(String.class);
        final Object obj = new Object();

        final Map<String, Object> properties = new HashMap<>();
        properties.put(keyName, obj);
        final ServerProperties serverProperties = new ServerProperties(properties);

        // when
        final Object property = serverProperties.getProperty(keyName);

        // then
        assertThat(property).isEqualTo(obj);
    }

    @Test
    public void shouldReturnPropertyWithProperType() {

        // given
        final String keyName = random.nextObject(String.class);
        final Integer obj = random.nextObject(Integer.class);

        final Map<String, Integer> properties = new HashMap<>();
        properties.put(keyName, obj);
        final ServerProperties serverProperties = new ServerProperties(properties);

        // when
        final Integer property = serverProperties.getProperty(keyName, Integer.class);

        // then
        assertThat(property).isEqualTo(obj);
    }

    @Test
    public void shouldThrowPropertyCastExceptionWhenCannotCastObject() {

        // given
        final String keyName = random.nextObject(String.class);
        final Integer obj = random.nextObject(Integer.class);

        final Map<String, Integer> properties = new HashMap<>();
        properties.put(keyName, obj);
        final ServerProperties serverProperties = new ServerProperties(properties);

        // when
        try {
            serverProperties.getProperty(keyName, Character.class);
            fail("Should throw PropertyCastException!");
        } catch (PropertyCastException ex) {
            assertThat(ex.getObjectToCast()).isEqualTo(obj);
            assertThat(ex.getClassToCast()).isEqualTo(Character.class);
        }
    }

    @Test
    public void shouldThrowPropertyNotExistsExceptionWhenCantFindGivenProperty() {

        // given
        final String keyName = random.nextObject(String.class);

        final Map<String, Integer> properties = new HashMap<>();
        final ServerProperties serverProperties = new ServerProperties(properties);

        // when
        try {
            serverProperties.getProperty(keyName, Character.class);
            fail("Should throw PropertyCastException!");
        } catch (PropertyNotExistsException ex) {
            assertThat(ex.getPropertyName()).isEqualTo(keyName);
        }
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenIsNull() {
        new ServerProperties(null);
    }
}