package pl.grzeslowski.jsupla.protocoljava.common;

import java.lang.reflect.Field;

public class TestUtil {
    public static Field getDeclaredField(Class<?> clazz, String field) throws NoSuchFieldException {
        final Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return declaredField;
    }

    public static String lowerFirstLetter(String string) {
        return Character.toLowerCase(string.charAt(0)) +
            (string.length() > 1 ? string.substring(1) : "");
    }

}
