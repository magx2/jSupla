package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryReader;
import pl.grzeslowski.jsupla.protocol.api.serialization.BinaryWriter;
import pl.grzeslowski.jsupla.protocol.api.serialization.PayloadCodec;

public final class FirmwareCheckResultCodec implements PayloadCodec<CalCfgFirmwareCheckResult> {
    public static final FirmwareCheckResultCodec INSTANCE = new FirmwareCheckResultCodec();

    private FirmwareCheckResultCodec() {}

    @Override
    public byte[] encode(CalCfgFirmwareCheckResult payload) {
        BinaryWriter writer = new BinaryWriter(payload.protoSize());
        writer.writeUInt8(payload.result(), "Result");
        writer.writeFixedBytes(payload.softVer(), SUPLA_SOFTVER_MAXSIZE, "SoftVer");
        writer.writeFixedBytes(payload.changelogUrl(), SUPLA_URL_PATH_MAXSIZE, "ChangelogUrl");
        return writer.toByteArray();
    }

    @Override
    public CalCfgFirmwareCheckResult decode(byte[] bytes) {
        BinaryReader reader = new BinaryReader(bytes);
        short result = reader.readUInt8("Result");
        byte[] softVer = reader.readBytes(SUPLA_SOFTVER_MAXSIZE, "SoftVer");
        byte[] changelogUrl = reader.readBytes(SUPLA_URL_PATH_MAXSIZE, "ChangelogUrl");
        reader.requireFullyConsumed("TCalCfg_FirmwareCheckResult");
        return new CalCfgFirmwareCheckResult(result, softVer, changelogUrl);
    }
}
