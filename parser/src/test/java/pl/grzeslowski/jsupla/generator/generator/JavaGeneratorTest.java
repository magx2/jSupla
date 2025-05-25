package pl.grzeslowski.jsupla.generator.generator;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.parser.ParsedFile;
import pl.grzeslowski.jsupla.generator.parser.Parser;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class JavaGeneratorTest {
    JavaGenerator generator = JavaGenerator.INSTANCE;

    @Test
    public void integrationTest() throws IOException {
        // given
        var lines = new BufferedReader(
                new InputStreamReader(
                        requireNonNull(JavaGeneratorTest.class.getClassLoader().getResourceAsStream("proto.h")),
                        UTF_8))
                .lines()
                .toList();
        String protoh = String.join("\n", lines);
        var tokens = Tokenizer.INSTANCE.tokenize(protoh);
        ParsedFile parsedFile = Parser.INSTANCE.parse(tokens);

        // when
        var javaEnv = generator.generate(parsedFile, lines);
        var protoConsts = JavaFileGenerator.INSTANCE.generate(javaEnv.protoConsts(), javaEnv.classToJavaClass());

        // then
        assertThat(protoConsts).isNotBlank();
        assertThat(protoConsts).contains("public interface ProtoConsts {");
        assertThat(javaEnv.records()).isNotEmpty();
        assertThat(javaEnv.classToJavaClass()).isNotEmpty();

        var path = Paths.get("D:\\repos\\jSupla\\protocol\\build\\generated\\sources\\jsupla\\main\\");
        Files.createDirectories(path);

        { // ProtoConsts
            var filePath = path.resolve("pl/grzeslowski/jsupla/protocol/api/consts/ProtoConsts.java");
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, protoConsts);
        }

        // records
        javaEnv.records()
                .forEach(record -> {
                    var content = JavaRecordGenerator.INSTANCE.generate(record, javaEnv.classToJavaClass());
                    try {
                        var name = NameMapper.INSTANCE.mapRecordName(record.name()) + ".java";
                        String javaPackage = record.javaPackage().replaceAll("\\.", "/");
                        var filePath = path.resolve(javaPackage + "/" + name);
                        Files.createDirectories(filePath.getParent());
                        Files.writeString(filePath, content);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}