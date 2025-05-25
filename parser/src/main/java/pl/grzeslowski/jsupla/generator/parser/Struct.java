package pl.grzeslowski.jsupla.generator.parser;

import org.jetbrains.annotations.NotNull;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import javax.annotation.Nullable;
import java.util.List;

public record Struct(String name,
                     List<? extends Field> fields,
                     Token.Position start,
                     Token.Position end,
                     @Nullable String orderComment,
                     @Nullable String comment) implements Comparable<Struct> {
    public Struct(String name, List<? extends Field> fields, Token.Position start, Token.Position end) {
        this(name, fields, start, end, null, null);
    }

    @Override
    public int compareTo(@NotNull Struct o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Struct struct = (Struct) o;
        return name.equals(struct.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
