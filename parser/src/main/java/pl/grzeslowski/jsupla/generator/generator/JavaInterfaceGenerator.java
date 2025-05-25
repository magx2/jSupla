package pl.grzeslowski.jsupla.generator.generator;


import java.util.Map;

public class JavaInterfaceGenerator {
    public static final JavaInterfaceGenerator INSTANCE = new JavaInterfaceGenerator();
    private final TypeMapper typeMapper;

    private JavaInterfaceGenerator() {
        this(TypeMapper.INSTANCE);
    }

    private JavaInterfaceGenerator(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    String generate(JavaFile.JavaInterface javaFile, Map<String, String> classToJavaClass) {
        var sb = new StringBuilder();

        sb.append("package ")
                .append(javaFile.javaPackage())
                .append(";\n\n")
                .append("public interface ")
                .append(javaFile.name())
                .append(" {\n");
        javaFile.consts()
                .stream()
                .map(javaField -> mapField(javaField, classToJavaClass))
                .forEach(sb::append);
        sb.append("}\n");

        return sb.toString();
    }

    private String mapField(JavaField javaField, Map<String, String> classToJavaClass) {
        var sb = new StringBuilder();
        if (javaField.comment() != null) {
            sb.append("\t/**\n\t * ")
                    .append(javaField.comment().replaceAll("\n", "\n\t * "))
                    .append("\n\t */\n");
        }
        var value = javaField.value();
        sb.append("\t")
                .append(typeMapper.mapType(javaField.type(), classToJavaClass))
                .append(" ")
                .append(javaField.name())
                .append(" = ")
                .append(value)
                .append(";\n");
        return sb.toString();
    }
}
