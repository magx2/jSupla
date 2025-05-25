package pl.grzeslowski.jsupla.generator.parser;

import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import javax.annotation.Nullable;
import java.util.List;

public sealed interface Field {
    @Nullable
    String comment();

    String byteSize();

    public record SimpleField(String name, List<Token> type, String byteSize,
                              @Nullable String comment) implements Field {
    }

    public record ArrayField(String name, List<Token> type, List<Token> length,
                             String byteSize,
                             @Nullable String comment) implements Field {
    }

    public record UnionField(List<Field> fields,
                             String byteSize,
                             @Nullable String comment) implements Field {
        public record UnionStruct(List<Field> fields, String byteSize, @Nullable String comment) implements Field {
        }
    }
}
