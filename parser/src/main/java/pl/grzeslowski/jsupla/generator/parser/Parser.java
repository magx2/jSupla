package pl.grzeslowski.jsupla.generator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

public class Parser {
    public static final Parser INSTANCE = new Parser();
    private static final Logger log = LoggerFactory.getLogger(Parser.class);
    private final DefineParser defineParser;
    private final StructParser structParser;

    private Parser() {
        this(DefineParser.INSTANCE, StructParser.INSTANCE);
    }

    private Parser(DefineParser defineParser, StructParser structParser) {
        this.defineParser = defineParser;
        this.structParser = structParser;
    }

    public ParsedFile parse(Collection<? extends Token> tokens) {
        return parse(new TokenQueue(tokens));
    }

    ParsedFile parse(TokenQueue tokens) {
        var defines = new LinkedList<Define>();
        var structs = new TreeSet<Struct>();
        while (tokens.peek() != null) {
            var token = tokens.poll();
            if (token.is(DEFINE)) {
                var define = defineParser.parseDefine(tokens);
                defines.add(define);
            } else if (token.is(TYPEDEF)) {
                var type = tokens.poll();
                if (type.is(STRUCT)) {
                    var struct = structParser.parseStruct(tokens);
                    structs.add(struct);
                } else {
                    log.warn("{} Do not know how to handle type={}", type.position(), type);
                }
            } else if (!(token instanceof Token.CommentToken)
                && !(token.is(ENTER))) {
                log.warn("{} Do not know how to handle token={}", token.position(), token);
            }
        }
        var parsedFile = new ParsedFile(defines, structs);
        log.debug("Parsed file: {}", parsedFile);
        return parsedFile;
    }
}
