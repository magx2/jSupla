package pl.grzeslowski.jsupla.generator.parser;

import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import javax.annotation.Nullable;
import java.util.List;

public sealed interface Field {
    @Nullable
    String comment();

    public record SimpleField(String name, List<Token> type, @Nullable Integer byteSize,
                              @Nullable String comment) implements Field {
        public SimpleField(String name, List<Token> type) {
            this(name, type, null, null);
        }

        public SimpleField(String name, List<Token> type, @Nullable String comment) {
            this(name, type, null, comment);
        }
    }

    public record ArrayField(String name, List<Token> type, List<Token> length,
                             @Nullable String comment) implements Field {
        public ArrayField(String name, List<Token> type, List<Token> length) {
            this(name, type, length, null);
        }
    }

    public record UnionField(List<Field> fields, @Nullable String comment) implements Field {
        public UnionField(List<Field> field) {
            this(field, null);
        }

        public record UnionStruct(List<Field> fields, @Nullable String comment) implements Field {
        }
    }
}
