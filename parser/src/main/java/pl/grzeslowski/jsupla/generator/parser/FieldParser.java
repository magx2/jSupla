package pl.grzeslowski.jsupla.generator.parser;

import lombok.extern.slf4j.Slf4j;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.LinkedList;
import java.util.TreeSet;

import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

@Slf4j
class FieldParser {
    static final FieldParser INSTANCE = new FieldParser();

    private FieldParser() {
    }

    Field parseField(TokenQueue tokens) {
        var token = tokens.poll();
        var fields = new TreeSet<Field>();
        var subTokens = new LinkedList<Token>();
        while (!token.is(COLON) && !token.is(SEMICOLON)) {
            subTokens.add(token);
            token = tokens.poll();
        }
        Integer byteSize = null;
        if (token.is(COLON)) {
            var value = tokens.poll(Token.SimpleToken.class).value();
            byteSize = Integer.parseInt(value);
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
                    if (!subTokens.get(idx).is(BRACKET_SQUARE_CLOSE)) {
                        break;
                    }
                } else {
                    length.addFirst(currentToken);
                }
            }
            var namToken = subTokens.get(idx);
            if (!(namToken instanceof Token.SimpleToken simpleToken)) {
                throw new ParserException(namToken.position(), "Name token should be simple token, got " + namToken.getClass().getSimpleName() + " " + namToken);
            }
            var arrayField = new Field.ArrayField(
                simpleToken.value(),
                subTokens.subList(0, idx),
                length,
                comment);
            log.debug("Parsed field " + arrayField);
            return arrayField;
        }

        // parse simple field
        if (!(lastToken instanceof Token.SimpleToken simpleToken)) {
            throw new ParserException(lastToken.position(), "Name token should be simple token, got " + lastToken.getClass().getSimpleName() + " " + lastToken);
        }

        var simpleField = new Field.SimpleField(simpleToken.value(), subTokens.subList(0, subTokens.size() - 1), byteSize, comment);
        log.debug("Parsed field " + simpleField);
        return simpleField;
    }
}
