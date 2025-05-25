package pl.grzeslowski.jsupla.generator.generator;

import javax.annotation.Nullable;

public record JavaField(JavaType type, String name, @Nullable String value, @Nullable String comment) {
    public JavaField(JavaType type, String name, String value) {
        this(type, name, value, null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        JavaField javaField = (JavaField) o;
        return name.equals(javaField.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
