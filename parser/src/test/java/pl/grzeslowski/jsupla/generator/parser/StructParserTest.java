package pl.grzeslowski.jsupla.generator.parser;


import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.parser.Field.SimpleField;
import pl.grzeslowski.jsupla.generator.tokenizer.Token;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Tokenizer;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StructParserTest {
    StructParser structParser = StructParser.INSTANCE;
    Tokenizer tokenizer = new Tokenizer();
    Token.Position currentPosition = new Token.Position(13, 37);
    Token.Position start = new Token.Position(23, 45);
    Token.Position end = new Token.Position(56, 78);

    @Test
    public void simpleStruct() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            typedef struct {
              unsigned char command;
              char reserved;
            } TCSD_Valve;
            """));
        tokens.poll(); // poll `typedef`
        tokens.poll();// poll `struct`

        // when
        var structs = structParser.parseStruct(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(structs).isEqualToComparingFieldByFieldRecursively(
            new Struct(
                "TCSD_Valve",
                List.of(
                    new SimpleField(
                        "command",
                        List.of(
                            new SimpleToken(currentPosition, "unsigned"),
                            new SimpleToken(currentPosition, "char"))),
                    new SimpleField(
                        "reserved",
                        List.of(
                            new SimpleToken(currentPosition, "char")))),
                start, end));
    }

    @Test
    public void simpleStructWithOrderComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            typedef struct {
              // device -> server
              unsigned char command;
              char reserved;
            } TCSD_Valve;
            """));
        tokens.poll(); // poll `typedef`
        tokens.poll();// poll `struct`

        // when
        var structs = structParser.parseStruct(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(structs).isEqualToComparingFieldByFieldRecursively(
            new Struct(
                "TCSD_Valve",
                List.of(
                    new SimpleField(
                        "command",
                        List.of(
                            new SimpleToken(currentPosition, "unsigned"),
                            new SimpleToken(currentPosition, "char"))),
                    new SimpleField(
                        "reserved",
                        List.of(
                            new SimpleToken(currentPosition, "char")))),
                start, end,
                "device -> server",
                null));
    }

    @Test
    public void simpleStructWithComment() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            typedef struct {
              unsigned char command;
              char reserved;
            } TCSD_Valve; // foo
                          // boo
            """));
        tokens.poll(); // poll `typedef`
        tokens.poll();// poll `struct`

        // when
        var structs = structParser.parseStruct(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(structs).isEqualToComparingFieldByFieldRecursively(
            new Struct(
                "TCSD_Valve",
                List.of(
                    new SimpleField(
                        "command",
                        List.of(
                            new SimpleToken(currentPosition, "unsigned"),
                            new SimpleToken(currentPosition, "char"))),
                    new SimpleField(
                        "reserved",
                        List.of(
                            new SimpleToken(currentPosition, "char")))),
                start, end,
                null,
                "foo\nboo"));
    }

    @Test
    public void structWithUnion() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            typedef struct {
              unsigned char command;
              union {
                char x;
                char y;
              };
              char reserved;
            } TCSD_Valve;
            """));
        tokens.poll(); // poll `typedef`
        tokens.poll();// poll `struct`

        // when
        var structs = structParser.parseStruct(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(structs).isEqualToComparingFieldByFieldRecursively(
            new Struct(
                "TCSD_Valve",
                List.of(
                    new SimpleField(
                        "command",
                        List.of(
                            new SimpleToken(currentPosition, "unsigned"),
                            new SimpleToken(currentPosition, "char"))),
                    new Field.UnionField(
                        List.of(
                            new SimpleField("x", List.of(new SimpleToken(currentPosition, "char"))),
                            new SimpleField("y", List.of(new SimpleToken(currentPosition, "char"))))),
                    new SimpleField(
                        "reserved",
                        List.of(
                            new SimpleToken(currentPosition, "char")))),
                start, end
            ));
    }

    @Test
    public void traditionalStruct() {
        // given
        var tokens = new TokenQueue(tokenizer.tokenize("""
            struct timeval_compat {
                _supla_int_t tv_sec;
                _supla_int_t tv_usec;
            };"""));
        tokens.poll();// poll `struct`

        // when
        var structs = structParser.parseTraditionalStruct(tokens);

        // then
        assertThat(tokens.isEmpty()).isTrue();
        assertThat(structs).isEqualToComparingFieldByFieldRecursively(
            new Struct(
                "timeval_compat",
                List.of(
                    new SimpleField(
                        "tv_sec",
                        List.of(new SimpleToken(currentPosition, "_supla_int_t"))),
                    new SimpleField(
                        "tv_usec",
                        List.of(new SimpleToken(currentPosition, "_supla_int_t")))),
                start, end));
    }
}