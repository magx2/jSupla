package pl.grzeslowski.jsupla.generator.generator;

import pl.grzeslowski.jsupla.generator.generator.ProtoTypeFinder.ProtoType;

import java.util.List;

import static java.util.Arrays.stream;

class NameMapper {
    public static final NameMapper INSTANCE = new NameMapper();
    private final List<String> prefixes;

    private NameMapper() {
        prefixes = stream(ProtoType.values())
            .map(ProtoType::getType)
            .toList();
    }

    String mapClassName(String name) {
        return mapRecordName(name);
    }

    String mapRecordName(String name) {
        var prefix = prefixes.stream()
            .filter(name::startsWith)
            .findFirst();
        if (prefix.isPresent()) {
            name = name.substring(prefix.get().length());
        }
        return name.replace("_", "");
    }

    public String mapFieldName(String name) {
        String result;
        if (name.contains("_")) {
            // snake_case → camelCase
            String[] parts = name.split("_");
            StringBuilder sb = new StringBuilder(parts[0].toLowerCase());
            for (int i = 1; i < parts.length; i++) {
                if (!parts[i].isEmpty()) {
                    sb.append(Character.toUpperCase(parts[i].charAt(0)))
                        .append(parts[i].substring(1).toLowerCase());
                }
            }
            result = sb.toString();
        } else {
            // PascalCase → camelCase
            if (name.isEmpty()) {
                result = name;
            } else {
                result = Character.toLowerCase(name.charAt(0)) + name.substring(1);
            }
        }
        if ("default".equals(result)) {
            result += "Field";
        }
        return result;
    }

}
