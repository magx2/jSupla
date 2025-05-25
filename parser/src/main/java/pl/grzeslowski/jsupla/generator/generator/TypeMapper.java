package pl.grzeslowski.jsupla.generator.generator;

import java.util.Map;

import static java.util.Objects.requireNonNull;

class TypeMapper {
    public static final TypeMapper INSTANCE = new TypeMapper();

    private TypeMapper() {
    }

    String mapType(JavaType type, Map<String, String> classToJavaClass) {
        requireNonNull(type);
        if (type instanceof JavaType.PrimitiveType pt) {
            return pt.getJavaName();
        }
        if (type instanceof JavaType.ArrayType at) {
            return mapType(at.elementType(), classToJavaClass) + "[]";
        }
        if (type instanceof JavaType.ReferenceType ref) {
            return classToJavaClass.get(ref.name());
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }
}
