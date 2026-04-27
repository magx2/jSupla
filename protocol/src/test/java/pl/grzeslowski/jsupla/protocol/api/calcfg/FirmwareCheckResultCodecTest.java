package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.FirmwareCheckResultCode.SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE;
import static pl.grzeslowski.jsupla.protocol.api.FirmwareCheckResultCode.SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_NOT_AVAILABLE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.FirmwareCheckResultCode;
import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryWriter;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

class FirmwareCheckResultCodecTest {
    private final FirmwareCheckResultCodec codec = FirmwareCheckResultCodec.INSTANCE;

    @Test
    void firmwareCheckResultShouldRoundTrip() {
        CalCfgFirmwareCheckResult result =
                CalCfgFirmwareCheckResult.of(
                        SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE,
                        "4.8.1",
                        "/firmware/changelog");

        CalCfgFirmwareCheckResult decoded = codec.decode(codec.encode(result));

        assertThat(decoded.resultCode()).isEqualTo(SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE);
        assertThat(decoded.softVer()).containsExactly(result.softVer());
        assertThat(decoded.changelogUrl()).containsExactly(result.changelogUrl());
        assertThat(decoded.softVerString()).isEqualTo("4.8.1");
        assertThat(decoded.changelogUrlString()).isEqualTo("/firmware/changelog");
    }

    @Test
    void firmwareCheckResultShouldDecodeAllRequiredResultCodes() {
        for (FirmwareCheckResultCode resultCode : FirmwareCheckResultCode.values()) {
            CalCfgFirmwareCheckResult result =
                    CalCfgFirmwareCheckResult.of(resultCode, "1.0.0", "/changelog");

            CalCfgFirmwareCheckResult decoded = codec.decode(codec.encode(result));

            assertThat(decoded.resultCode()).isEqualTo(resultCode);
        }
    }

    @Test
    void fixedSizeStringShouldDecodeWithoutNullTerminator() {
        byte[] softVer = repeated('a', SUPLA_SOFTVER_MAXSIZE);
        byte[] changelogUrl = repeated('b', SUPLA_URL_PATH_MAXSIZE);

        CalCfgFirmwareCheckResult decoded =
                codec.decode(
                        codec.encode(
                                new CalCfgFirmwareCheckResult(
                                        (short)
                                                SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE
                                                        .getValue(),
                                        softVer,
                                        changelogUrl)));

        assertThat(decoded.softVerString()).isEqualTo("a".repeat(SUPLA_SOFTVER_MAXSIZE));
        assertThat(decoded.changelogUrlString()).isEqualTo("b".repeat(SUPLA_URL_PATH_MAXSIZE));
    }

    @Test
    void fixedSizeStringShouldDecodeWithTrailingNullPadding() {
        byte[] softVer = BinaryWriter.encodeFixedString("2.0.0", SUPLA_SOFTVER_MAXSIZE);
        byte[] changelogUrl = BinaryWriter.encodeFixedString("/changes", SUPLA_URL_PATH_MAXSIZE);

        CalCfgFirmwareCheckResult decoded =
                codec.decode(
                        codec.encode(
                                new CalCfgFirmwareCheckResult(
                                        (short)
                                                SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_NOT_AVAILABLE
                                                        .getValue(),
                                        softVer,
                                        changelogUrl)));

        assertThat(decoded.softVerString()).isEqualTo("2.0.0");
        assertThat(decoded.changelogUrlString()).isEqualTo("/changes");
    }

    @Test
    void malformedFirmwareCheckResultShouldRejectUnknownResultCode() {
        byte[] encoded =
                codec.encode(
                        CalCfgFirmwareCheckResult.of(
                                SUPLA_FIRMWARE_CHECK_RESULT_UPDATE_AVAILABLE, "1", "/"));
        encoded[0] = 99;

        assertThatThrownBy(() -> codec.decode(encoded))
                .isInstanceOf(ProtocolCodecException.class)
                .hasMessageContaining("Unsupported SUPLA_FIRMWARE_CHECK_RESULT value 99");
    }

    private static byte[] repeated(char value, int size) {
        byte[] bytes = new String(new char[size]).replace('\0', value).getBytes(UTF_8);
        return Arrays.copyOf(bytes, size);
    }
}
