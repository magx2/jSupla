package pl.grzeslowski.jsupla.generator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.generator.parser.Field.UnionField;
import pl.grzeslowski.jsupla.generator.parser.Field.UnionField.UnionStruct;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.LinkedList;

import static java.util.stream.Collectors.joining;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

class UnionParser {
    static UnionParser INSTANCE = new UnionParser();
    private static final Logger log = LoggerFactory.getLogger(UnionParser.class);
    private final FieldParser fieldParser;

    private UnionParser() {
        this(FieldParser.INSTANCE);
    }

    private UnionParser(FieldParser fieldParser) {
        this.fieldParser = fieldParser;
    }

    UnionField parseUnion(TokenQueue tokens) {
        tokens.pollAndCheck(UNION);
        tokens.pollAndCheck(BRACKET_CURLY_OPEN);
        var fields = new LinkedList<Field>();
        while (!tokens.peek().is(BRACKET_CURLY_CLOSE)) {
            if (tokens.peek().is(STRUCT)) {
                tokens.poll(); // eat `struct` keyword
                var struct = parseStruct(tokens);
                fields.add(struct);
            } else {
                var field = fieldParser.parseField(tokens);
                fields.add(field);
            }
        }
        tokens.pollAndCheck(BRACKET_CURLY_CLOSE);
        tokens.pollAndCheck(SEMICOLON);
        String comment = null;
        var maybeComment = tokens.peek();
        if (maybeComment instanceof Token.CommentToken commentToken) {
            comment = commentToken.comment();
            tokens.poll(); // eat comment
        }
        var byteSize = findByteSize(fields);
        var union = new UnionField(fields, byteSize, comment);
        log.debug("Union parsed: {}", union);
        return union;
    }

    private UnionStruct parseStruct(TokenQueue tokens) {
        tokens.pollAndCheck(BRACKET_CURLY_OPEN); // eat `{`
        var fields = new LinkedList<Field>();
        while (!tokens.peek().is(BRACKET_CURLY_CLOSE)) {
            var field = fieldParser.parseField(tokens);
            fields.add(field);
        }
        tokens.pollAndCheck(BRACKET_CURLY_CLOSE);
        tokens.pollAndCheck(SEMICOLON);
        String comment = null;
        var maybeComment = tokens.peek();
        if (maybeComment instanceof Token.CommentToken commentToken) {
            comment = commentToken.comment();
            tokens.poll(); // eat comment
        }
        var byteSize = findByteSize(fields);
        return new UnionStruct(fields, byteSize, comment);
    }

    private static String findByteSize(LinkedList<Field> fields) {
        var byteSizes = fields.stream()
            .map(Field::byteSize)
            .distinct()
            .toList();
        String byteSize;
        if (byteSizes.size() == 1) {
            byteSize = byteSizes.get(0);
        } else {
            byteSize = byteSizes.stream().collect(joining(", ", "max(", ")"));
        }
        return byteSize;
    }
}
