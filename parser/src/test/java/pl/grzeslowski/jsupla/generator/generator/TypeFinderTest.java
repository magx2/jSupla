package pl.grzeslowski.jsupla.generator.generator;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.generator.generator.JavaType.PrimitiveType.*;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

public class TypeFinderTest {
    TypeFinder finder = TypeFinder.INSTANCE;
    Token.Position p = new Token.Position(13, 37);

    @Test
    public void simple() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "1"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(INT);
    }

    @Test
    public void simpleByteSize1() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "1"));

        // when
        var expand = finder.findType(tokens, 1);

        // then
        assertThat(expand).isEqualTo(BOOLEAN);
    }

    @Test
    public void simpleMinus() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "-1"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(INT);
    }

    @Test
    public void simpleLong() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "1ULL"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(LONG);
    }

    @Test
    public void simpleString() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "TDSC_ChannelState"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(new ReferenceType("TDSC_ChannelState"));
    }

    @Test
    public void expression() {
        // given
        var tokens = List.of(
            new Token.Keyword(p, BRACKET_ROUND_OPEN),
            new Token.SimpleToken(p, "SUPLA_MAX_DATA_SIZE"),
            new Token.Keyword(p, MINUS),
            new Token.SimpleToken(p, "50"),
            new Token.Keyword(p, BRACKET_ROUND_CLOSE));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(INT);
    }

    @Test
    public void moreStrings() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "long"),
            new Token.SimpleToken(p, "long"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(new ReferenceType("long"));
    }

    @Test
    public void OxType() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "0x8000"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(LONG);
    }

    @Test
    public void doubleLesThan() {
        // given
        var tokens = List.of(
            new Token.Keyword(p, BRACKET_ROUND_OPEN),
            new Token.SimpleToken(p, "1ULL"),
            new Token.Keyword(p, DOUBLE_LESS_THAN),
            new Token.SimpleToken(p, "16"),
            new Token.Keyword(p, BRACKET_ROUND_CLOSE));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(LONG);
    }

    @Test
    public void empty() {
        // given
        var tokens = List.<Token>of();

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(BOOLEAN);
    }

    @Test
    public void suplaByte() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "byte"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(BYTE);
    }

    @Test
    public void suplaUByte() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "unsigned"),
            new Token.SimpleToken(p, "byte"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(UBYTE);
    }

    @Test
    public void suplaChar() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "char"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(BYTE);
    }

    @Test
    public void suplaUChar() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "unsigned"),
            new Token.SimpleToken(p, "char"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(UBYTE);
    }

    @Test
    public void suplaShort() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "_supla_int16_t"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(SHORT);
    }

    @Test
    public void suplaUShort() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "unsigned"),
            new Token.SimpleToken(p, "_supla_int16_t"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(USHORT);
    }

    @Test
    public void suplaInt() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "_supla_int_t"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(INT);
    }

    @Test
    public void suplaUInt() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "unsigned"),
            new Token.SimpleToken(p, "_supla_int_t"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(UINT);
    }

    @Test
    public void suplaUIntByteSize24() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "unsigned"),
            new Token.SimpleToken(p, "_supla_int_t"));

        // when
        var expand = finder.findType(tokens, 24);

        // then
        assertThat(expand).isEqualTo(UINT);
    }

    @Test
    public void suplaLong() {
        // given
        var tokens = List.of(new Token.SimpleToken(p, "_supla_int64_t"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(LONG);
    }

    @Test
    public void suplaULong() {
        // given
        var tokens = List.of(
            new Token.SimpleToken(p, "unsigned"),
            new Token.SimpleToken(p, "_supla_int64_t"));

        // when
        var expand = finder.findType(tokens);

        // then
        assertThat(expand).isEqualTo(ULONG);
    }

}