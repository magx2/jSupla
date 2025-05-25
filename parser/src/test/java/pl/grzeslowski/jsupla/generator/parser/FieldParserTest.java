package pl.grzeslowski.jsupla.generator.parser;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.Keyword;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

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
                new SimpleToken(currentPosition, "char")),
            "BYTE_SIZE",
            null));
    }

    @Test
    public void complexField() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("TActionTriggerProperties MaxAllowedTemperatureSetpointFromLocalUI;"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.SimpleField(
            "MaxAllowedTemperatureSetpointFromLocalUI",
            List.of(new SimpleToken(currentPosition, "TActionTriggerProperties")),
            "MaxAllowedTemperatureSetpointFromLocalUI.size()",
            null));
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
            "1",
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
            "24",
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
            "BYTE_SIZE",
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
            List.of(new SimpleToken(currentPosition, "8")),
            "BYTE_SIZE * (8)",
            null));
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
                new Keyword(currentPosition, STAR),
                new SimpleToken(currentPosition, "37")),
            "BYTE_SIZE * (13 * 37)",
            null));
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
            "BYTE_SIZE * (8)",
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
                new Keyword(currentPosition, PLUS),
                new SimpleToken(currentPosition, "SUPLA_PN_BODY_MAXSIZE")),
            "BYTE_SIZE * (SUPLA_PN_TITLE_MAXSIZE + SUPLA_PN_BODY_MAXSIZE)",
            null));
    }

    @Test
    public void complicatedArrayFieldWithSizeOf() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("unsigned char Reserved[27 - 4 * sizeof(_supla_int16_t)];"));

        // when
        var field = fieldParser.parseField(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(field).isEqualToComparingFieldByFieldRecursively(new Field.ArrayField(
            "Reserved",
            List.of(
                new SimpleToken(currentPosition, "unsigned"),
                new SimpleToken(currentPosition, "char")),
            List.of(
                new SimpleToken(currentPosition, "27"),
                new Keyword(currentPosition, MINUS),
                new SimpleToken(currentPosition, "4"),
                new Keyword(currentPosition, STAR),
                new Keyword(currentPosition, SIZEOF),
                new Keyword(currentPosition, BRACKET_ROUND_OPEN),
                new SimpleToken(currentPosition, "_supla_int16_t"),
                new Keyword(currentPosition, BRACKET_ROUND_CLOSE)),
            "BYTE_SIZE * (27 - 4 * SHORT_SIZE)",
            null));
    }
}