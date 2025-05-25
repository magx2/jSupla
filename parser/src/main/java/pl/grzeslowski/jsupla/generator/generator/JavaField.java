package pl.grzeslowski.jsupla.generator.generator;

import javax.annotation.Nullable;

public record JavaField(JavaType type, String name, @Nullable String value,
                        @Nullable String byteSize, @Nullable String comment) {
    public JavaField(JavaType type, String name, String value, @Nullable String byteSize) {
        this(type, name, value, byteSize, null);
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
