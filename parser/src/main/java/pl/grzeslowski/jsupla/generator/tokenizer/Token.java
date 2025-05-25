package pl.grzeslowski.jsupla.generator.tokenizer;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static java.util.Arrays.stream;

public sealed interface Token {
    Position position();

    boolean is(Token token);

    default boolean is(CodeKeyword codeKeyword) {
        return false;
    }

    public record Position(int line, int column) {
        @Override
        public @NotNull String toString() {
            return "%s:%s".formatted(line, column);
        }
    }

    public record Keyword(Position position, CodeKeyword keyword) implements Token {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Keyword keyword1 = (Keyword) o;
            return keyword == keyword1.keyword;
        }

        @Override
        public int hashCode() {
            return keyword.hashCode();
        }

        @Override
        public @NotNull String toString() {
            return "Keyword{" +
                "keyword=" + keyword +
                '}';
        }

        @Override
        public boolean is(Token token) {
            if (!(token instanceof Keyword keyword)) {
                return false;
            }
            return this.keyword == keyword.keyword();
        }

        @Override
        public boolean is(CodeKeyword codeKeyword) {
            return keyword == codeKeyword;
        }
    }

    public enum CodeKeyword {
        TYPEDEF("typedef"), STRUCT("struct"), DEFINE("define"), SEMICOLON(";"), COLON(":"), SIZEOF("sizeof"),
        ENTER("\n"),
        UNION("union"),
        GREATER_THAN(">"), DOUBLE_GREATER_THAN(">>"), GREATER_EQUAL(">="),
        LESS_THAN("<"), DOUBLE_LESS_THAN("<<"), LESS_EQUALS("<="),
        PLUS_EQUAL("+="), MINUS_EQUAL("-="), STAR_EQUAL("*="), SLASH_EQUAL("/="),
        AND_AND("&&"), OR_OR("|"),
        EQUAL("="), DOUBLE_EQUAL("=="), NOT_EQUAL("!="),
        PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"),
        RIGHT_SHIFT_BIT_SET("<<="), LEFT_SHIFT_BIT_SET(">>="),
        BRACKET_CURLY_OPEN("{"), BRACKET_CURLY_CLOSE("}"),
        BRACKET_SQUARE_OPEN("["), BRACKET_SQUARE_CLOSE("]"),
        BRACKET_ROUND_OPEN("("), BRACKET_ROUND_CLOSE(")");
        private final String keyword;

        CodeKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }

        public static Optional<CodeKeyword> parseKeyword(String keyword) {
            return stream(values())
                .filter(v -> v.keyword.equals(keyword))
                .findAny();
        }
    }

    public record SimpleToken(Position position, String value) implements Token {
        public SimpleToken(Position position, char c) {
            this(position, Character.toString(c));
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            SimpleToken that = (SimpleToken) o;
            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public @NotNull String toString() {
            return "SimpleToken{" +
                "value='" + value + '\'' +
                '}';
        }

        @Override
        public boolean is(Token token) {
            if (!(token instanceof SimpleToken st)) {
                return false;
            }
            return this.value.equals(st.value());
        }
    }

    public record CommentToken(Position position, String comment) implements Token {
        public static CommentToken squash(CommentToken a, CommentToken b) {
            return new CommentToken(a.position, a.comment() + "\n" + b.comment());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            CommentToken that = (CommentToken) o;
            return comment.equals(that.comment);
        }

        @Override
        public int hashCode() {
            return comment.hashCode();
        }

        @Override
        public @NotNull String toString() {
            return "CommentToken{" +
                "comment='" + comment + '\'' +
                '}';
        }

        @Override
        public boolean is(Token token) {
            if (!(token instanceof CommentToken ct)) {
                return false;
            }
            return this.comment.equals(ct.comment());
        }
    }
}
 
