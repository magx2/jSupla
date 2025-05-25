package pl.grzeslowski.jsupla.generator.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.parser.ParserException;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class ValueExpander {
    public static final ValueExpander INSTANCE = new ValueExpander();
    private static final Logger log = LoggerFactory.getLogger(ValueExpander.class);

    private ValueExpander() {
    }

    String expand(List<? extends Token> tokens) {
        log.debug("Expanding tokens {}", tokens);
        if (tokens.isEmpty()) {
            return "true";
        }
        return tokens.stream()
            .map(this::mapToken)
            .collect(Collectors.joining(" "));
    }

    private String mapToken(Token token) {
        requireNonNull(token);
        if (token instanceof Token.CommentToken) {
            throw new ParserException(token.position(), "Cannot accept commentToken!");
        }
        if (token instanceof Token.Keyword keyword) {
            return switch (keyword.keyword()) {
                case TYPEDEF, EQUAL, UNION, SIZEOF, SEMICOLON, COLON, DEFINE, STRUCT,
                     BRACKET_SQUARE_CLOSE, BRACKET_SQUARE_OPEN, BRACKET_CURLY_CLOSE, BRACKET_CURLY_OPEN ->
                    throw new ParserException(token.position(), "Cannot accept " + keyword);
                case GREATER_THAN, DOUBLE_GREATER_THAN,
                     LEFT_SHIFT_BIT_SET, SLASH, RIGHT_SHIFT_BIT_SET, MINUS, STAR, NOT_EQUAL, PLUS, DOUBLE_EQUAL,
                     AND_AND, OR_OR, SLASH_EQUAL, STAR_EQUAL, MINUS_EQUAL, PLUS_EQUAL, LESS_EQUALS,
                     DOUBLE_LESS_THAN, LESS_THAN, GREATER_EQUAL,
                     BRACKET_ROUND_CLOSE, BRACKET_ROUND_OPEN -> keyword.keyword().getKeyword();
                case ENTER -> "true";
            };
        }
        if (token instanceof Token.SimpleToken simpleToken) {
            var value = simpleToken.value();
            if (value.length() > 1) {
                if (value.endsWith("ULL")) {
                    // 123ULL -> 123L Java has no unsigned long long, just long (64-bit signed).
                    return value.substring(0, value.length() - "ULL".length()) + "L";
                }
                if (value.endsWith("UL")) {
                    // 123UL -> 123L Java has no unsigned long long, just long (64-bit signed).
                    return value.substring(0, value.length() - "UL".length()) + "L";
                }
                if (value.endsWith("LL")) {
                    // 123LL -> 123L Java long is 64-bit signed (same as C's long long).
                    return value.substring(0, value.length() - "LL".length()) + "L";
                }
                if (value.endsWith("L")) {
                    // 123L -> 123L Direct match in Java.
                    return value;
                }
                if (value.endsWith("U")) {
                    // 123U -> 123 Java int is 32-bit signed, no unsigned keyword
                    return value.substring(0, value.length() - "U".length());
                }
            }
            if (value.startsWith("0x")) {
                return value + "L";
            }
            return value;
        }
        throw new ParserException(token.position(), "Unknown token " + token);
    }
}
