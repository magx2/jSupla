package pl.grzeslowski.jsupla.generator.generator;

import javax.annotation.Nullable;

public sealed interface JavaType {

    public enum PrimitiveType implements JavaType {
        BYTE("byte"),
        UBYTE("short"),
        SHORT("short"),
        USHORT("int"),
        INT("int"),
        UINT("long"),
        LONG("long"),
        ULONG("long"),
        DOUBLE("double"),
        BOOLEAN("boolean");
        private final String javaName;

        PrimitiveType(String javaName) {
            this.javaName = javaName;
        }

        public String getJavaName() {
            return javaName;
        }
    }

    public record ArrayType(String javaName, JavaType elementType, @Nullable String length) implements JavaType {
    }

    public record ReferenceType(String name) implements JavaType {
    }
}
