package pl.grzeslowski.jsupla.generator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.LinkedList;

import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

class DefineParser {
    static final DefineParser INSTANCE = new DefineParser();
    private static final Logger log = LoggerFactory.getLogger(DefineParser.class);

    private DefineParser() {
    }

    Define parseDefine(TokenQueue tokens) {
        var keyToken = tokens.poll();
        if (!(keyToken instanceof Token.SimpleToken simpleToken)) {
            throw new ParserException(keyToken.position(), "Key token should be simpleToken! Was " + keyToken.getClass().getSimpleName());
        }
        var values = new LinkedList<Token>();
        var openClosedBrackets = 0;
        var hasMinus = false;
        do {
            var value = tokens.poll();
            values.add(value);
            hasMinus = value.is(MINUS);
            if (value.is(BRACKET_ROUND_OPEN)) {
                openClosedBrackets++;
            } else if (value.is(BRACKET_ROUND_CLOSE)) {
                openClosedBrackets--;
                if (openClosedBrackets < 0) {
                    throw new ParserException(keyToken.position(), "There are more closing brackets than opening or brackets are in wrong order!");
                }
            }
        } while (openClosedBrackets > 0 || hasMinus);
        String comment = null;
        if (tokens.peek() instanceof Token.CommentToken commentToken) {
            comment = commentToken.comment();
            tokens.poll();
        }
        var define = new Define(simpleToken.value(), values, comment);
        log.debug("Parsed define: " + define);
        return define;
    }
}
