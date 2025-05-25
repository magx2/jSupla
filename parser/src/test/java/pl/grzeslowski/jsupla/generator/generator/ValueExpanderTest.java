package pl.grzeslowski.jsupla.generator.generator;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.PLUS;

public class ValueExpanderTest {
    ValueExpander expander = ValueExpander.INSTANCE;
    Token.Position currentPosition = new Token.Position(13, 37);

    @Test
    public void simple() {
        // given
        var tokens = List.of(new SimpleToken(currentPosition, "1"));

        // when
        var expand = expander.expand(tokens);

        // then
        assertThat(expand).isEqualTo("1");
    }

    @Test
    public void empty() {
        // given
        var tokens = List.<Token>of();

        // when
        var expand = expander.expand(tokens);

        // then
        assertThat(expand).isEqualTo("true");
    }

    @Test
    public void oneOperation() {
        // given
        var tokens = List.of(
            new SimpleToken(currentPosition, "1"),
            new Token.Keyword(currentPosition, PLUS),
            new SimpleToken(currentPosition, "2"));

        // when
        var expand = expander.expand(tokens);

        // then
        assertThat(expand).isEqualTo("1 + 2");
    }

    @Test
    public void oxnumber() {
        // given
        var tokens = List.of(new SimpleToken(currentPosition, "0x40000000"));

        // when
        var expand = expander.expand(tokens);

        // then
        assertThat(expand).isEqualTo("0x40000000L");
    }

    @Test
    public void doubleLessThan() {
        // given
        var tokens = Tokenizer.INSTANCE.tokenize("(1ULL << 7)");

        // when
        var expand = expander.expand(tokens);

        // then
        assertThat(expand).isEqualTo("( 1L << 7 )");
    }
}