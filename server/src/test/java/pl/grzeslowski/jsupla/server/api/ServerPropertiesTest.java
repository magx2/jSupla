package pl.grzeslowski.jsupla.server.api;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import pl.grzeslowski.jsupla.server.api.exceptions.PropertyCastException;
import pl.grzeslowski.jsupla.server.api.exceptions.PropertyNotExistsException;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

public class ServerPropertiesTest {

    @Test
    public void shouldReturnPreviouslyGivenProperty() throws Exception {

        // given
        final String keyName = EnhancedRandom.random(String.class);
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
    public void shouldReturnPropertyWithProperType() throws Exception {

        // given
        final String keyName = EnhancedRandom.random(String.class);
        final Integer obj = EnhancedRandom.random(Integer.class);

        final Map<String, Integer> properties = new HashMap<>();
        properties.put(keyName, obj);
        final ServerProperties serverProperties = new ServerProperties(properties);

        // when
        final Integer property = serverProperties.getProperty(keyName, Integer.class);

        // then
        assertThat(property).isEqualTo(obj);
    }

    @Test
    public void shouldThrowPropertyCastExceptionWhenCannotCastObject() throws Exception {

        // given
        final String keyName = EnhancedRandom.random(String.class);
        final Integer obj = EnhancedRandom.random(Integer.class);

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
    public void shouldThrowPropertyNotExistsExceptionWhenCantFindGivenProperty() throws Exception {

        // given
        final String keyName = EnhancedRandom.random(String.class);

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