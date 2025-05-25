package pl.grzeslowski.jsupla.generator.generator;

import pl.grzeslowski.jsupla.generator.generator.JavaFile.JavaRecord;

import java.util.Map;
import java.util.SortedSet;

public record JavaEnv(
    JavaFile.JavaInterface protoConsts,
    SortedSet<JavaRecord> records,
    Map<String, String> classToJavaClass) {
}
