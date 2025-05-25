package pl.grzeslowski.jsupla.generator.parser;

import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import javax.annotation.Nullable;
import java.util.List;

public record Define(String key, List<Token> value, @Nullable String comment) {
    public Define(String key, Token value) {
        this(key, List.of(value), null);
    }

    public Define(String key, List<Token> value) {
        this(key, value, null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Define define = (Define) o;
        return key.equals(define.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
