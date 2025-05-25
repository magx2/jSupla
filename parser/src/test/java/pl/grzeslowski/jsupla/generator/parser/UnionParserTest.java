package pl.grzeslowski.jsupla.generator.parser;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.parser.Field.SimpleField;
import pl.grzeslowski.jsupla.generator.parser.Field.UnionField;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UnionParserTest {
    UnionParser unionParser = UnionParser.INSTANCE;
    Tokenizer tokenizer = new Tokenizer();
    Token.Position currentPosition = new Token.Position(13, 37);

    @Test
    public void simpleUnion() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            union {
             char x;
             int y;
            };
            """));

        // when
        var union = unionParser.parseUnion(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(union).isEqualTo(new UnionField(
            List.of(
                new SimpleField("x", List.of(new SimpleToken(currentPosition, "char"))),
                new SimpleField("y", List.of(new SimpleToken(currentPosition, "int"))))));
    }

    @Test
    public void simpleUnionWithComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            union {
             char x;
             int y;
            }; // foo
            """));

        // when
        var union = unionParser.parseUnion(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(union).isEqualTo(new UnionField(
            List.of(
                new SimpleField("x", List.of(new SimpleToken(currentPosition, "char"))),
                new SimpleField("y", List.of(new SimpleToken(currentPosition, "int")))),
            "foo"));
    }


    @Test
    public void unionWithStruct() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            union {
             char x;
             struct {
                   char a;
                   char b;
             };  // v. >= 25
             int y;
            };
            """));

        // when
        var union = unionParser.parseUnion(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(union).isEqualTo(new UnionField(
            List.of(
                new SimpleField("x", List.of(new SimpleToken(currentPosition, "char"))),
                new UnionField.UnionStruct(
                    List.of(
                        new SimpleField("a", List.of(new SimpleToken(currentPosition, "char"))),
                        new SimpleField("b", List.of(new SimpleToken(currentPosition, "char")))),
                    "v. >= 25"),
                new SimpleField("y", List.of(new SimpleToken(currentPosition, "int"))))));
    }
}