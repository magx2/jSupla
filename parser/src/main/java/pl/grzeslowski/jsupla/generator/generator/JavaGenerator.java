package pl.grzeslowski.jsupla.generator.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.generator.JavaFile.JavaInterface;
import pl.grzeslowski.jsupla.generator.parser.Define;
import pl.grzeslowski.jsupla.generator.parser.Field;
import pl.grzeslowski.jsupla.generator.parser.Field.ArrayField;
import pl.grzeslowski.jsupla.generator.parser.Field.SimpleField;
import pl.grzeslowski.jsupla.generator.parser.Field.UnionField;
import pl.grzeslowski.jsupla.generator.parser.ParsedFile;
import pl.grzeslowski.jsupla.generator.parser.Struct;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Long.parseLong;
import static java.util.Collections.singletonList;
import static java.util.function.Predicate.not;
import static pl.grzeslowski.jsupla.generator.generator.JavaType.PrimitiveType.*;

public class JavaGenerator {
    public static final JavaGenerator INSTANCE = new JavaGenerator();
    private static final Logger log = LoggerFactory.getLogger(JavaGenerator.class);
    private static final String BASE_PACKAGE = "pl.grzeslowski.jsupla.protocol.api.test";//todo
    private final ValueExpander valueExpander;
    private final TypeFinder typeFinder;
    private final ProtoTypeFinder protoTypeFinder;
    private final NameMapper nameMapper;

    private JavaGenerator() {
        this(ValueExpander.INSTANCE, TypeFinder.INSTANCE, ProtoTypeFinder.INSTANCE, NameMapper.INSTANCE);
    }

    private JavaGenerator(ValueExpander valueExpander, TypeFinder typeFinder, ProtoTypeFinder protoTypeFinder, NameMapper nameMapper) {
        this.valueExpander = valueExpander;
        this.typeFinder = typeFinder;
        this.protoTypeFinder = protoTypeFinder;
        this.nameMapper = nameMapper;
    }

    public JavaEnv generate(ParsedFile file, List<String> lines) {
        var protoConsts = generateDefinesFile(file.defines());
        var records = file.structs()
                .stream()
                .map((Struct struct) -> generateRecordFile(struct, lines))
                .collect(Collectors.toCollection(TreeSet::new));
        return new JavaEnv(
                protoConsts,
                records,
                buildMap(records));
    }

    private Map<String, String> buildMap(Collection<JavaFile.JavaRecord> records) {
        return records.stream()
                .collect(Collectors.toMap(
                        JavaFile.JavaRecord::name,
                        x -> x.javaPackage() + "." + nameMapper.mapRecordName(x.name())));
    }

    private JavaInterface generateDefinesFile(Collection<Define> defines) {
        var consts = defines.stream()
                .map(this::mapDefine)
                .collect(Collectors.groupingBy(
                        JavaField::name,
                        LinkedHashMap::new,
                        Collectors.toList()
                ))
                .values()
                .stream()
                .map(list -> list.stream().filter(x -> x.type() instanceof PrimitiveType).toList())
                .filter(not(Collection::isEmpty))
                .map(this::unique)
                .filter(Objects::nonNull)
                .toList();
        return new JavaInterface(
                "pl.grzeslowski.jsupla.protocol.api.consts",
                "ProtoConsts",
                consts);
    }

    private JavaField unique(List<JavaField> javaFields) {
        if (javaFields.isEmpty()) {
            throw new IllegalArgumentException("javaFields should not be empty!");
        }
        var first = javaFields.get(0);
        if (javaFields.size() == 1) {
            return first;
        }
        // check if all types are the same
        var type = first.type();
        for (int idx = 1; idx < javaFields.size(); idx++) {
            var current = javaFields.get(idx);
            if (!current.type().equals(type)) {
                log.debug("Type mismatch for {}: current type={}, expected type={}", first.name(), current.type(), type);
                return null;
            }
        }
        if (first.type() == INT || first.type() == LONG) {
            var highest = first;
            var highestValue = parseLong(highest.value());
            for (int i = 1; i < javaFields.size(); i++) {
                var current = javaFields.get(i);
                var value = parseLong(current.value());
                if (value > highestValue) {
                    highest = current;
                    highestValue = value;
                }
            }
            return highest;
        }
        if (first.type() == BOOLEAN) {
            for (var javaField : javaFields) {
                if (Boolean.parseBoolean(javaField.value())) {
                    return javaField;
                }
            }
            return first;
        }
        throw new IllegalStateException("Do not know how to handle it! javaFields=" + javaFields);
    }

    private JavaField mapDefine(Define define) {
        return new JavaField(
                typeFinder.findType(define.value()),
                define.key(),
                valueExpander.expand(define.value()),
                define.comment());
    }

    private JavaFile.JavaRecord generateRecordFile(Struct struct, List<String> lines) {
        var f = struct.fields();
        var fields = IntStream.range(0, f.size())
                .mapToObj(idx -> Map.entry(idx + 1, f.get(idx)))
                .map(entry -> mapField(entry.getValue(), entry.getKey()))
                .flatMap(Collection::stream)
                .toList();

        var structLines = lines.subList(struct.start().line() - 1, struct.end().line());
        var originalCode = String.join("\n", structLines);

        var javaPackage = BASE_PACKAGE;
        var suffix = switch (protoTypeFinder.findType(struct)) {
            case TSCD -> "scd";
            case TSDC -> "sdc";
            case TSCS -> "scs";
            case TCSD -> "csd";
            case TDCS -> "dcs";
            case TDSC -> "dsc";
            case TSDS -> "sds";
            case TCS -> "cs";
            case TSC -> "sc";
            case TSD -> "sd";
            case TDS -> "ds";
            case T -> "t";
        };
        javaPackage += "." + suffix;

        String implementInterface = switch (protoTypeFinder.findType(struct)) {
            case TSCD -> "pl.grzeslowski.jsupla.protocol.api.structs.scd.ServerClientDevice";
            case TSDC -> "pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient";
            case TSCS -> "pl.grzeslowski.jsupla.protocol.api.structs.scs.ServerClientServer";
            case TCSD -> "pl.grzeslowski.jsupla.protocol.api.structs.csd.ClientServerDevice";
            case TDCS -> "pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer";
            case TDSC -> "pl.grzeslowski.jsupla.protocol.api.structs.dsc.DeviceServerClient";
            case TSDS -> "pl.grzeslowski.jsupla.protocol.api.structs.sds.ServerDeviceServer";
            case TCS -> "pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer";
            case TSC -> "pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient";
            case TSD -> "pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice";
            case TDS -> "pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer";
            case T -> "pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize";
        };
        return new JavaFile.JavaRecord(
                javaPackage,
                struct.name(),
                fields,
                implementInterface,
                originalCode,
                struct.comment());
    }

    private List<JavaField> mapField(Field field, int index) {
        return mapField(field, index, field.comment());
    }

    private List<JavaField> mapField(Field field, int index, String comment) {
        JavaType type;
        if (field instanceof SimpleField simpleField) {
            type = typeFinder.findType(simpleField.type(), simpleField.byteSize());
            return singletonList(new JavaField(
                    type,
                    simpleField.name(),
                    null,
                    comment));
        }
        if (field instanceof ArrayField arrayField) {
            var fieldType = typeFinder.findType(arrayField.type());
            type = new ArrayType(
                    arrayField.name(),
                    fieldType,
                    null);
            return singletonList(new JavaField(
                    type,
                    arrayField.name(),
                    null,
                    comment));
        }
        if (field instanceof UnionField unionField) {
            return unionField.fields()
                    .stream()
                    .map(uf -> {
                        var c = "UNION #" + index;
                        if (uf.comment() != null) {
                            c += " - " + uf.comment();
                        }
                        return mapField(uf, index, c);
                    })
                    .flatMap(Collection::stream)
                    .toList();
        }
        if (field instanceof UnionField.UnionStruct unionStruct) {
            return unionStruct.fields()
                    .stream()
                    .map(uf -> {
                        var c = "UNION #" + index + " (STRUCT)";
                        if (uf.comment() != null) {
                            c += " - " + uf.comment();
                        }
                        return mapField(uf, index, c);
                    })
                    .flatMap(Collection::stream)
                    .toList();
        }
        throw new IllegalArgumentException("Unsupported field type: " + field);
    }
}
