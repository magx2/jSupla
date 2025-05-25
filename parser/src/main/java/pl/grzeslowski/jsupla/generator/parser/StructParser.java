package pl.grzeslowski.jsupla.generator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;

import java.util.LinkedList;

import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

class StructParser {
    static final StructParser INSTANCE = new StructParser();
    private static final Logger log = LoggerFactory.getLogger(StructParser.class);
    private final FieldParser fieldParser;
    private final UnionParser unionParser;

    private StructParser() {
        this(FieldParser.INSTANCE, UnionParser.INSTANCE);
    }

    private StructParser(FieldParser fieldParser, UnionParser unionParser) {
        this.fieldParser = fieldParser;
        this.unionParser = unionParser;
    }

    Struct parseStruct(TokenQueue tokens) {
        var start = tokens.pollAndCheck(BRACKET_CURLY_OPEN).position();
        String orderComment = null;
        if (tokens.peek() instanceof Token.CommentToken commentToken) {
            orderComment = commentToken.comment();
            tokens.poll(); // eat comment token
        }
        var fields = new LinkedList<Field>();
        {
            Token peek;
            while (!(peek = tokens.peek()).is(BRACKET_CURLY_CLOSE)) {
                if (peek.is(UNION)) {
                    var union = unionParser.parseUnion(tokens);
                    fields.add(union);
                } else {
                    var field = fieldParser.parseField(tokens);
                    fields.add(field);
                }
            }
        }
        tokens.pollAndCheck(BRACKET_CURLY_CLOSE);
        var nameToken = tokens.poll(SimpleToken.class);
        var end = tokens.pollAndCheck(SEMICOLON).position();
        String comment = null;
        if (tokens.peek() instanceof Token.CommentToken commentToken) {
            comment = commentToken.comment();
            end = tokens.poll().position();// eat comment token
        }
        var struct = new Struct(nameToken.value(), fields, start, end, orderComment, comment);
        log.debug("Parsed struct {}", struct);
        return struct;
    }

    Struct parseTraditionalStruct(TokenQueue tokens) {
        var nameToken = tokens.poll(SimpleToken.class);
        var start = nameToken.position();
        tokens.pollAndCheck(BRACKET_CURLY_OPEN);
        var fields = new LinkedList<Field>();
        {
            Token peek;
            while (!(peek = tokens.peek()).is(BRACKET_CURLY_CLOSE)) {
                if (peek.is(UNION)) {
                    var union = unionParser.parseUnion(tokens);
                    fields.add(union);
                } else {
                    var field = fieldParser.parseField(tokens);
                    fields.add(field);
                }
            }
        }
        tokens.pollAndCheck(BRACKET_CURLY_CLOSE);
        var end = tokens.pollAndCheck(SEMICOLON).position();
        var struct = new Struct(nameToken.value(), fields, start, end);
        log.debug("Parsed traditional struct {}", struct);
        return struct;
    }
}
