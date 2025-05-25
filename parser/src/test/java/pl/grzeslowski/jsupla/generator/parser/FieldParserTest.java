package pl.grzeslowski.jsupla.generator.parser;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.PLUS;

public class FieldParserTest {
    FieldParser fieldParser = FieldParser.INSTANCE;
    Tokenizer tokenizer = new Tokenizer();
    Token.Position currentPosition = new Token.Position(13, 37);

    @Test
    public void simpleField() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned char command;"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.SimpleField(
            "command",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "char"))));
    }

    @Test
    public void booleanFlag() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned _supla_int_t FieldName : 1;"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.SimpleField(
            "FieldName",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "_supla_int_t")),
            1,
            null));
    }

    @Test
    public void smallerIntFlag() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned _supla_int_t IntervalStepSec : 24;"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.SimpleField(
            "IntervalStepSec",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "_supla_int_t")),
            24,
            null));
    }

    @Test
    public void simpleFieldWithComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned char command; // foo boo"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.SimpleField(
            "command",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "char")),
            "foo boo"));
    }

    @Test
    public void simpleArrayField() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned char command[8];"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.ArrayField(
            "command",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "char")),
            List.of(new SimpleToken(currentPosition, "8"))));
    }

    @Test
    public void matrixField() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned char command[13][37];"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.ArrayField(
            "command",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "char")),
            List.of(
                new SimpleToken(currentPosition, "13"),
                new SimpleToken(currentPosition, "37"))));
    }

    @Test
    public void simpleArrayFieldWithComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned char command[8]; // foo boo"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.ArrayField(
            "command",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "char")),
            List.of(new SimpleToken(currentPosition, "8")),
            "foo boo"));
    }

    @Test
    public void complicatedArrayField() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("signed char TitleAndBody[SUPLA_PN_TITLE_MAXSIZE + SUPLA_PN_BODY_MAXSIZE];"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.ArrayField(
            "TitleAndBody",
            List.of(
                new SimpleToken(currentPosition, "signed"),
                new SimpleToken(currentPosition, "char")),
            List.of(
                new SimpleToken(currentPosition, "SUPLA_PN_TITLE_MAXSIZE"),
                new Token.Keyword(currentPosition, PLUS),
                new SimpleToken(currentPosition, "SUPLA_PN_BODY_MAXSIZE"))));
    }
}