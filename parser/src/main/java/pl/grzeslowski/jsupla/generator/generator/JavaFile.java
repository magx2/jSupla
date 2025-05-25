package pl.grzeslowski.jsupla.generator.generator;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

public sealed interface JavaFile extends Comparable<JavaFile> {
    String javaPackage();

    String name();

    @Override
    default int compareTo(@NotNull JavaFile o) {
        return Comparator.comparing(JavaFile::javaPackage)
                .thenComparing(JavaFile::name)
                .compare(this, o);
    }

    public record JavaRecord(String javaPackage, String name, List<JavaField> fields,
                             @Nullable String implementInterface,
                             String originalCode,
                             @Nullable String comment) implements JavaFile {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            JavaRecord javaRecord = (JavaRecord) o;
            return name.equals(javaRecord.name) && javaPackage.equals(javaRecord.javaPackage);
        }

        @Override
        public int hashCode() {
            int result = javaPackage.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    public record JavaInterface(String javaPackage, String name, List<JavaField> consts) implements JavaFile {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            JavaInterface javaInterface = (JavaInterface) o;
            return name.equals(javaInterface.name) && javaPackage.equals(javaInterface.javaPackage);
        }

        @Override
        public int hashCode() {
            int result = javaPackage.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }
}
