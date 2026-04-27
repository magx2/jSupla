package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

import java.util.Arrays;
import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryReader;
import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryWriter;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public record CalCfgFirmwareCheckResult(short result, byte[] softVer, byte[] changelogUrl)
        implements ProtoWithSize {
    public static final int SIZE = Byte.BYTES + SUPLA_SOFTVER_MAXSIZE + SUPLA_URL_PATH_MAXSIZE;

    public CalCfgFirmwareCheckResult {
        FirmwareCheckResultCode.fromValue(result);
        validateFixedLength("SoftVer", softVer, SUPLA_SOFTVER_MAXSIZE);
        validateFixedLength("ChangelogUrl", changelogUrl, SUPLA_URL_PATH_MAXSIZE);
        softVer = softVer.clone();
        changelogUrl = changelogUrl.clone();
    }

    public static CalCfgFirmwareCheckResult of(
            FirmwareCheckResultCode result, String softVer, String changelogUrl) {
        return new CalCfgFirmwareCheckResult(
                result.unsignedByteValue(),
                BinaryWriter.encodeFixedString(softVer, SUPLA_SOFTVER_MAXSIZE),
                BinaryWriter.encodeFixedString(changelogUrl, SUPLA_URL_PATH_MAXSIZE));
    }

    @Override
    public int protoSize() {
        return SIZE;
    }

    public FirmwareCheckResultCode resultCode() {
        return FirmwareCheckResultCode.fromValue(result);
    }

    public String softVerString() {
        return BinaryReader.decodeFixedString(softVer);
    }

    public String changelogUrlString() {
        return BinaryReader.decodeFixedString(changelogUrl);
    }

    @Override
    public byte[] softVer() {
        return softVer.clone();
    }

    @Override
    public byte[] changelogUrl() {
        return changelogUrl.clone();
    }

    @Override
    public String toString() {
        return "CalCfgFirmwareCheckResult["
                + "result="
                + result
                + ", softVer="
                + Arrays.toString(softVer)
                + ", changelogUrl="
                + Arrays.toString(changelogUrl)
                + "]";
    }

    private static void validateFixedLength(String fieldName, byte[] bytes, int expectedLength) {
        if (bytes.length != expectedLength) {
            throw new ProtocolCodecException(
                    fieldName
                            + " length "
                            + bytes.length
                            + " does not match fixed size "
                            + expectedLength);
        }
    }
}
