package pl.grzeslowski.jsupla.generator.generator;

import java.util.Map;
import java.util.Objects;

public class JavaFileGenerator {
    public static JavaFileGenerator INSTANCE = new JavaFileGenerator();
    private final JavaRecordGenerator javaRecordGenerator;
    private final JavaInterfaceGenerator javaInterfaceGenerator;

    public JavaFileGenerator() {
        this(JavaRecordGenerator.INSTANCE, JavaInterfaceGenerator.INSTANCE);
    }

    private JavaFileGenerator(JavaRecordGenerator javaRecordGenerator, JavaInterfaceGenerator javaInterfaceGenerator) {
        this.javaRecordGenerator = javaRecordGenerator;
        this.javaInterfaceGenerator = javaInterfaceGenerator;
    }

    public String generate(JavaFile javaFile, Map<String, String> classToJavaClass) {
        if (Objects.requireNonNull(javaFile) instanceof JavaFile.JavaRecord javaRecord) {
            return javaRecordGenerator.generate(javaRecord, classToJavaClass);
        }
        if (javaFile instanceof JavaFile.JavaInterface javaInterface) {
            return javaInterfaceGenerator.generate(javaInterface, classToJavaClass);
        }
        throw new IllegalArgumentException();
    }
}
