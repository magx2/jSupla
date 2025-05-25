package pl.grzeslowski.jsupla.generator.parser;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.Keyword;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

public class DefineParserTest {
    DefineParser defineParser = DefineParser.INSTANCE;
    Tokenizer tokenizer = new Tokenizer();
    Token.Position currentPosition = new Token.Position(13, 37);

    @Test
    public void simpleDefine() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("#define SUPLA_LOCATION_PWD_MAXSIZE 33"));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SUPLA_LOCATION_PWD_MAXSIZE",
                new SimpleToken(currentPosition, "33"))
        );
    }

    @Test
    public void simpleDefineWithSplitAndComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            #define SUPLA_LOCATION_PWD_MAXSIZE \\
            33 // foo boo
            """));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SUPLA_LOCATION_PWD_MAXSIZE",
                List.of(new SimpleToken(currentPosition, "33")),
                "foo boo"));
    }

    @Test
    public void simpleDefineWithMinus() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("#define SUPLA_LOCATION_PWD_MAXSIZE -33"));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SUPLA_LOCATION_PWD_MAXSIZE",
                List.of(
                    new Keyword(currentPosition, MINUS),
                    new SimpleToken(currentPosition, "33"))));
    }

    @Test
    public void simpleDefineComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("#define SUPLA_LOCATION_PWD_MAXSIZE 33 // foo boo"));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SUPLA_LOCATION_PWD_MAXSIZE",
                List.of(new SimpleToken(currentPosition, "33")),
                "foo boo"));
    }

    @Test
    public void combinedValues() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("#define SUPLA_ACTION_CAP_SHORT_PRESS_x1 (1 << 11)"));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SUPLA_ACTION_CAP_SHORT_PRESS_x1",
                List.of(
                    new Keyword(currentPosition, BRACKET_ROUND_OPEN),
                    new SimpleToken(currentPosition, "1"),
                    new Keyword(currentPosition, DOUBLE_LESS_THAN),
                    new SimpleToken(currentPosition, "11"),
                    new Keyword(currentPosition, BRACKET_ROUND_CLOSE)))
        );
    }

    @Test
    public void noValue() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("#define SPROTO_WITHOUT_OUT_BUFFER \n"));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SPROTO_WITHOUT_OUT_BUFFER",
                List.of(new Keyword(currentPosition, ENTER))));
    }

    @Test
    public void noValueComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("#define SPROTO_WITHOUT_OUT_BUFFER   // Temporary. It will be removed.\n"));
        tokens.poll();

        // when
        var defines = defineParser.parseDefine(tokens);

        // then
        assertThat(tokens.isEmpty())
            .as("Tokens: %s", tokens)
            .isTrue();
        assertThat(defines).isEqualToComparingFieldByField(
            new Define(
                "SPROTO_WITHOUT_OUT_BUFFER",
                List.of(new Keyword(currentPosition, ENTER)),
                "Temporary. It will be removed."));
    }
}
