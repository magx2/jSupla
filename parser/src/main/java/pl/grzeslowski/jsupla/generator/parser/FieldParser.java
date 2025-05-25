package pl.grzeslowski.jsupla.generator.parser;

import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

@Slf4j
class FieldParser {
    static final FieldParser INSTANCE = new FieldParser();
    static final Map<String, String> sizeMap = Map.of(
        "char", "BYTE_SIZE",
        "byte", "BYTE_SIZE",
        "_supla_int16_t", "SHORT_SIZE",
        "_supla_int_t", "INT_SIZE",
        "int", "INT_SIZE",
        "_supla_int64_t", "LONG_SIZE",
        "long", "LONG_SIZE",
        "double", "DOUBLE_SIZE"
    );

    private FieldParser() {
    }

    Field parseField(TokenQueue tokens) {
        var token = tokens.poll();
        var subTokens = new LinkedList<Token>();
        while (!token.is(COLON) && !token.is(SEMICOLON)) {
            subTokens.add(token);
            token = tokens.poll();
        }
        String byteSize = null;
        if (token.is(COLON)) {
            byteSize = tokens.poll(SimpleToken.class).value();
            tokens.poll();// eat `;`
        }

        String comment = null;
        if (tokens.peek() instanceof Token.CommentToken commentToken) {
            comment = commentToken.comment();
            tokens.poll(); // remove comment from th queue
        }
        var lastToken = subTokens.get(subTokens.size() - 1);
        if (lastToken.is(BRACKET_SQUARE_CLOSE)) {
            // parse array field
            var length = new LinkedList<Token>();
            var idx = subTokens.size() - 2;
            for (; idx >= 0; idx--) {
                var currentToken = subTokens.get(idx);
                if (currentToken.is(BRACKET_SQUARE_OPEN)) {
                    idx--;
                    var tk = subTokens.get(idx);
                    if (!tk.is(BRACKET_SQUARE_CLOSE)) {
                        break;
                    }
                    length.addFirst(new Token.Keyword(tk.position(), STAR));
                } else {
                    length.addFirst(currentToken);
                }
            }
            var namToken = subTokens.get(idx);
            if (!(namToken instanceof SimpleToken simpleToken)) {
                throw new ParserException(namToken.position(), "Name token should be simple token, got " + namToken.getClass().getSimpleName() + " " + namToken);
            }
            var type = subTokens.subList(0, idx);
            var name = simpleToken.value();
            var arrayField = new Field.ArrayField(
                name,
                type,
                length,
                findSize(name, type, unmodifiableList(length)),
                comment);
            log.debug("Parsed field " + arrayField);
            return arrayField;
        }

        // parse simple field
        if (!(lastToken instanceof SimpleToken simpleToken)) {
            throw new ParserException(lastToken.position(), "Name token should be simple token, got " + lastToken.getClass().getSimpleName() + " " + lastToken);
        }

        var name = simpleToken.value();
        if (byteSize == null) {
            byteSize = findSize(name, unmodifiableList(subTokens));
        }
        var simpleField = new Field.SimpleField(name, subTokens.subList(0, subTokens.size() - 1), byteSize, comment);
        log.debug("Parsed field " + simpleField);
        return simpleField;
    }

    private String findSize(String name, Collection<Token> type) {
        var tempTokens = new ArrayList<>(type);
        { // check for sizeof
            var queue = new TokenQueue(tempTokens);
            tempTokens.clear();
            while (!queue.isEmpty()) {
                var poll = queue.poll();
                if (!poll.is(SIZEOF)) {
                    tempTokens.add(poll);
                    continue;
                }
                // is sizeof
                queue.pollAndCheck(BRACKET_ROUND_OPEN);
                var poll1 = queue.poll();
                if (poll1 instanceof SimpleToken st && st.value().equals("unsigned")) {
                    // polling one more token for the use case: [50 - sizeof(unsigned char)]
                    poll1 = queue.poll();
                }
                tempTokens.add(poll1);
                queue.pollAndCheck(BRACKET_ROUND_CLOSE);
            }
        }
        var finalSize = tempTokens.stream()
            .map(token -> {
                if (token instanceof SimpleToken simpleToken) {
                    var value = simpleToken.value();
                    if (value == null) {
                        return null;
                    }
                    var size = sizeMap.get(value);
                    if (size != null) {
                        return size;
                    }
                    try {
                        Long.parseLong(value);
                        return value;
                    } catch (NumberFormatException e) {
                        // swallow
                    }
                    if (value.startsWith("SUPLA_")) {
                        return value;
                    }
                }
                if (token instanceof Token.Keyword keyword) {
                    return keyword.keyword().getKeyword();
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
        if (finalSize.isBlank()) {
            return name + ".size()";
        }
        return finalSize;
    }

    private String findSize(String name, Collection<Token> type, Collection<Token> subTokens) {
        var tempTokens = new ArrayList<>(subTokens);
        { // check for sizeof
            var queue = new TokenQueue(tempTokens);
            tempTokens.clear();
            while (!queue.isEmpty()) {
                var poll = queue.poll();
                if (!poll.is(SIZEOF)) {
                    tempTokens.add(poll);
                    continue;
                }
                // is sizeof
                queue.pollAndCheck(BRACKET_ROUND_OPEN);
                var poll1 = queue.poll();
                if (poll1 instanceof SimpleToken st && st.value().equals("unsigned")) {
                    // polling one more token for the use case: [50 - sizeof(unsigned char)]
                    poll1 = queue.poll();
                }
                tempTokens.add(poll1);
                queue.pollAndCheck(BRACKET_ROUND_CLOSE);
            }
        }
        { // check doule array
            var queue = new TokenQueue(tempTokens);
            tempTokens.clear();
            while (!queue.isEmpty()) {
                var poll = queue.poll();
                if (!poll.is(BRACKET_SQUARE_CLOSE)) {
                    tempTokens.add(poll);
                    continue;
                }
                var poll2 = queue.poll();
                if (poll2 == null) {
                    tempTokens.add(poll);
                    continue;
                }
                if (!poll2.is(BRACKET_SQUARE_OPEN)) {
                    tempTokens.add(poll);
                    tempTokens.add(poll2);
                    continue;
                }
                // had `][` tokens
                tempTokens.add(new Token.Keyword(poll.position(), STAR));
                tempTokens.add(queue.poll());
            }
        }
        var arraySize = tempTokens.stream()
            .map(token -> {
                if (token instanceof SimpleToken simpleToken) {
                    var value = simpleToken.value();
                    if (value == null) {
                        return null;
                    }
                    var size = sizeMap.get(value);
                    if (size != null) {
                        return size;
                    }
                    try {
                        Long.parseLong(value);
                        return value;
                    } catch (NumberFormatException e) {
                        // swallow
                    }
                    if (value.startsWith("SUPLA_")) {
                        return value;
                    }
                }
                if (token instanceof Token.Keyword keyword) {
                    return keyword.keyword().getKeyword();
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));

        return "%s * (%s)".formatted(findSize(name, type), arraySize);
    }

    private boolean contains(LinkedList<Token> subTokens, String token) {
        return subTokens.stream()
            .filter(t -> t instanceof SimpleToken)
            .map(SimpleToken.class::cast)
            .map(SimpleToken::value)
            .anyMatch(token::equals);
    }
}
