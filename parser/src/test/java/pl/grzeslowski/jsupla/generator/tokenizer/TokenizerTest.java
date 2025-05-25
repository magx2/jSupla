package pl.grzeslowski.jsupla.generator.tokenizer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.CommentToken;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.Keyword;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.Position;
import pl.grzeslowski.jsupla.generator.tokenizer.Token.SimpleToken;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static pl.grzeslowski.jsupla.generator.tokenizer.Token.CodeKeyword.*;

public class TokenizerTest {
    Tokenizer tokenizer = new Tokenizer();
    Position p = new Position(13, 37);

    @Test
    public void tokenizeDefine() {
        // given
        var code = "#define SUPLA_LOCATION_PWD_MAXSIZE 33\n";

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, DEFINE),
            new SimpleToken(p, "SUPLA_LOCATION_PWD_MAXSIZE"),
            new SimpleToken(p, "33"),
            new Keyword(p, ENTER));
        assertPositions(tokens,
            pos(1, "".length() + 1),
            pos(1, "#define ".length() + 1),
            pos(1, "#define SUPLA_LOCATION_PWD_MAXSIZE ".length() + 1),
            pos(1, "#define SUPLA_LOCATION_PWD_MAXSIZE 33".length() + 1));
    }

    @Test
    public void tokenizeDefineWithSplit() {
        // given
        var code = """
            #define SUPLA_LOCATION_PWD_MAXSIZE \\
            33
            """;

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, DEFINE),
            new SimpleToken(p, "SUPLA_LOCATION_PWD_MAXSIZE"),
            new SimpleToken(p, "33"),
            new Keyword(p, ENTER));
        assertPositions(tokens,
            pos(1, "".length() + 1),
            pos(1, "#define ".length() + 1),
            pos(2, 1),
            pos(2, "33".length() + 1));
    }

    @Test
    public void tokenizeDefineWithSplitAndComment() {
        // given
        var code = """
            #define SUPLA_LOCATION_PWD_MAXSIZE \\
            33 // foo boo
            """;

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, DEFINE),
            new SimpleToken(p, "SUPLA_LOCATION_PWD_MAXSIZE"),
            new SimpleToken(p, "33"),
            new CommentToken(p, "foo boo"));
        assertPositions(tokens,
            pos(1, "".length() + 1),
            pos(1, "#define ".length() + 1),
            pos(2, 1),
            pos(2, "33 ".length() + 1));
    }

    @Test
    public void oneAsUnsignedLongLong() {
        // given
        var code = "#define SUPLA_LOCATION_PWD_MAXSIZE 1ULL";

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, DEFINE),
            new SimpleToken(p, "SUPLA_LOCATION_PWD_MAXSIZE"),
            new SimpleToken(p, "1ULL"));
    }

    @Test
    public void tokenizeDefine2() {
        // given
        var code = "#define SUPLA_ACTION_CAP_SHORT_PRESS_x1 (1 << 11)";

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, DEFINE),
            new SimpleToken(p, "SUPLA_ACTION_CAP_SHORT_PRESS_x1"),
            new Keyword(p, BRACKET_ROUND_OPEN),
            new SimpleToken(p, "1"),
            new Keyword(p, DOUBLE_LESS_THAN),
            new SimpleToken(p, "11"),
            new Keyword(p, BRACKET_ROUND_CLOSE));
    }

    @Test
    public void tokenizeDefine3() {
        // given
        var code = "#define SUPLA_VALVE_FLAG_FLOODING 0x1";

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, DEFINE),
            new SimpleToken(p, "SUPLA_VALVE_FLAG_FLOODING"),
            new SimpleToken(p, "0x1"));
    }

    @Test
    public void tokenizeSimpleStruct() {
        // given
        var code = """
            typedef struct {
              // device|client -> server
              struct _supla_timeval now;
            } TDCS_SuplaPingServer;
            """;

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, TYPEDEF),
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            new CommentToken(p, "device|client -> server"),
            new Keyword(p, STRUCT),
            new SimpleToken(p, "_supla_timeval"),
            new SimpleToken(p, "now"),
            new Keyword(p, SEMICOLON),
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new SimpleToken(p, "TDCS_SuplaPingServer"),
            new Keyword(p, SEMICOLON));
        assertPositions(tokens,
            pos(1, 1),
            pos(1, "typedef ".length() + 1),
            pos(1, "typedef struct ".length() + 1),
            pos(2, " /".length() + 1),
            pos(3, "  ".length() + 1),
            pos(3, "  struct ".length() + 1),
            pos(3, "  struct _supla_timeval ".length() + 1),
            pos(3, "  struct _supla_timeval now".length() + 1),
            pos(4, "".length() + 1),
            pos(4, "} ".length() + 1),
            pos(4, "} TDCS_SuplaPingServer".length() + 1));
    }

    @Test
    public void tokenizeStructWithFieldWithSize() {
        // given
        var code = """
                typedef struct {
              // device|client -> server
              struct _supla_timeval now;
            } TDCS_SuplaPingServer;
            """;

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, TYPEDEF),
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            new CommentToken(p, "device|client -> server"),
            new Keyword(p, STRUCT),
            new SimpleToken(p, "_supla_timeval"),
            new SimpleToken(p, "now"),
            new Keyword(p, SEMICOLON),
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new SimpleToken(p, "TDCS_SuplaPingServer"),
            new Keyword(p, SEMICOLON));
        assertPositions(tokens,
            pos(1, 1),
            pos(1, "typedef ".length() + 1),
            pos(1, "typedef struct ".length() + 1),
            pos(2, " /".length() + 1),
            pos(3, "  ".length() + 1),
            pos(3, "  struct ".length() + 1),
            pos(3, "  struct _supla_timeval ".length() + 1),
            pos(3, "  struct _supla_timeval now".length() + 1),
            pos(4, "".length() + 1),
            pos(4, "} ".length() + 1),
            pos(4, "} TDCS_SuplaPingServer".length() + 1));
    }

    @Test
    public void tokenizeSimpleStructWithComments() {
        // given
        var code = """
            typedef struct {
                unsigned _supla_int_t FieldName : 1;
            } TSuplaChannelExtendedValue;
            """;

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, TYPEDEF),
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            new SimpleToken(p, "unsigned"),
            new SimpleToken(p, "_supla_int_t"),
            new SimpleToken(p, "FieldName"),
            new Keyword(p, COLON),
            new SimpleToken(p, "1"),
            new Keyword(p, SEMICOLON),
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new SimpleToken(p, "TSuplaChannelExtendedValue"),
            new Keyword(p, SEMICOLON));
    }

    @Test
    public void tokenizeSingleStructWithComments() {
        // given
        var code = """
            // Config used for thermometers, humidity sensors, and thermometers with
            // humidity channels.
            // Correction is always applied by io device. Parameter
            // AdjustmentAppliedByDevice is added in order to handle older versions where
            // correction was applied by server. Devices supporting this setting will
            // retreive config from server and if AdjustmentAppliedByDevice is set to 0,
            // then they will store new correction value, set AdjustmentAppliedByDevice to 1
            // and send it to server, so server will no longer apply correction.
            typedef struct {
              _supla_int16_t TemperatureAdjustment;     // * 0.01
              unsigned char AdjustmentAppliedByDevice;  // 1/true - by device
                                                        // 0/false - by server
              // Min/Max allowed adjustment values that channel supports. If set to 0, then
              // field is not supported by device and Cloud should use default -10..10 range
              // for correction. Otherwise, Cloud should use these values.
              _supla_int16_t MinTemperatureAdjustment;  // * 0.01
              unsigned char Reserved[27 - 4 * sizeof(_supla_int16_t)];
            } TChannelConfig_TemperatureAndHumidity;  // v. >= 21
            """;

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new CommentToken(p, """
                Config used for thermometers, humidity sensors, and thermometers with
                humidity channels.
                Correction is always applied by io device. Parameter
                AdjustmentAppliedByDevice is added in order to handle older versions where
                correction was applied by server. Devices supporting this setting will
                retreive config from server and if AdjustmentAppliedByDevice is set to 0,
                then they will store new correction value, set AdjustmentAppliedByDevice to 1
                and send it to server, so server will no longer apply correction."""),
            new Keyword(p, TYPEDEF),
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            //_supla_int16_t TemperatureAdjustment;     // * 0.01
            new SimpleToken(p, "_supla_int16_t"),
            new SimpleToken(p, "TemperatureAdjustment"),
            new Keyword(p, SEMICOLON),
            new CommentToken(p, "* 0.01"),
            //  unsigned char AdjustmentAppliedByDevice;  // 1/true - by device
            //                                            // 0/false - by server
            new SimpleToken(p, "unsigned"),
            new SimpleToken(p, "char"),
            new SimpleToken(p, "AdjustmentAppliedByDevice"),
            new Keyword(p, SEMICOLON),
            new CommentToken(p, """
                1/true - by device
                0/false - by server
                Min/Max allowed adjustment values that channel supports. If set to 0, then
                field is not supported by device and Cloud should use default -10..10 range
                for correction. Otherwise, Cloud should use these values."""),
            //  _supla_int16_t MinTemperatureAdjustment;  // * 0.01
            new SimpleToken(p, "_supla_int16_t"),
            new SimpleToken(p, "MinTemperatureAdjustment"),
            new Keyword(p, SEMICOLON),
            new CommentToken(p, "* 0.01"),
            //  unsigned char Reserved[27 - 4 * sizeof(_supla_int16_t)];
            new SimpleToken(p, "unsigned"),
            new SimpleToken(p, "char"),
            new SimpleToken(p, "Reserved"),
            new Keyword(p, BRACKET_SQUARE_OPEN),
            new SimpleToken(p, "27"),
            new Keyword(p, MINUS),
            new SimpleToken(p, "4"),
            new Keyword(p, STAR),
            new Keyword(p, SIZEOF),
            new Keyword(p, BRACKET_ROUND_OPEN),
            new SimpleToken(p, "_supla_int16_t"),
            new Keyword(p, BRACKET_ROUND_CLOSE),
            new Keyword(p, BRACKET_SQUARE_CLOSE),
            new Keyword(p, SEMICOLON),
            //   } TChannelConfig_TemperatureAndHumidity;  // v. >= 21
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new SimpleToken(p, "TChannelConfig_TemperatureAndHumidity"),
            new Keyword(p, SEMICOLON),
            new CommentToken(p, "v. >= 21"));
    }

    @Test
    public void tokenizeUnion() {
        // given
        var code = """
            typedef struct {
              union {
                _supla_int_t PumpSwitchChannelId;
              };
            } TChannelConfig_HVAC;""";

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, TYPEDEF),
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            //union
            new Keyword(p, UNION),
            new Keyword(p, BRACKET_CURLY_OPEN),
            new SimpleToken(p, "_supla_int_t"),
            new SimpleToken(p, "PumpSwitchChannelId"),
            new Keyword(p, SEMICOLON),
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new Keyword(p, SEMICOLON),
            // end struct
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new SimpleToken(p, "TChannelConfig_HVAC"),
            new Keyword(p, SEMICOLON));
    }

    @Test
    public void tokenizeUnionStruct() {
        // given
        var code = """
            typedef struct {
              union {
                _supla_int_t PumpSwitchChannelId;
                struct {
                  unsigned char PumpSwitchChannelNo;
                };
              };
            } TChannelConfig_HVAC;""";

        // when
        var tokens = tokenizer.tokenize(code);

        // then
        assertThat(tokens).containsExactly(
            new Keyword(p, TYPEDEF),
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            //union
            new Keyword(p, UNION),
            new Keyword(p, BRACKET_CURLY_OPEN),
            new SimpleToken(p, "_supla_int_t"),
            new SimpleToken(p, "PumpSwitchChannelId"),
            new Keyword(p, SEMICOLON),
            // unnamed struct
            new Keyword(p, STRUCT),
            new Keyword(p, BRACKET_CURLY_OPEN),
            new SimpleToken(p, "unsigned"),
            new SimpleToken(p, "char"),
            new SimpleToken(p, "PumpSwitchChannelNo"),
            new Keyword(p, SEMICOLON),
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new Keyword(p, SEMICOLON),
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new Keyword(p, SEMICOLON),
            // end struct
            new Keyword(p, BRACKET_CURLY_CLOSE),
            new SimpleToken(p, "TChannelConfig_HVAC"),
            new Keyword(p, SEMICOLON));
    }

    private static @NotNull Position pos(int line, int column) {
        return new Position(line, column);
    }

    void assertPositions(List<Token> tokens, Position... positions) {
        assertThat(tokens.stream()
            .map(Token::position)
            .toList()).containsExactly(positions);
    }
}