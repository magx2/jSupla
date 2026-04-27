package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import java.util.Arrays;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

public enum CalCfgCommand {
    RESET_COUNTERS(SUPLA_CALCFG_CMD_RESET_COUNTERS),
    RECALIBRATE(SUPLA_CALCFG_CMD_RECALIBRATE),
    ENTER_CFG_MODE(SUPLA_CALCFG_CMD_ENTER_CFG_MODE),
    RESET_TO_FACTORY_SETTINGS(SUPLA_CALCFG_CMD_RESET_TO_FACTORY_SETTINGS),
    CHECK_FIRMWARE_UPDATE(SUPLA_CALCFG_CMD_CHECK_FIRMWARE_UPDATE),
    START_FIRMWARE_UPDATE(SUPLA_CALCFG_CMD_START_FIRMWARE_UPDATE),
    START_SECURITY_UPDATE(SUPLA_CALCFG_CMD_START_SECURITY_UPDATE),
    SET_CFG_MODE_PASSWORD(SUPLA_CALCFG_CMD_SET_CFG_MODE_PASSWORD),
    SET_TIME(SUPLA_CALCFG_CMD_SET_TIME),
    START_SUBDEVICE_PAIRING(SUPLA_CALCFG_CMD_START_SUBDEVICE_PAIRING),
    IDENTIFY_DEVICE(SUPLA_CALCFG_CMD_IDENTIFY_DEVICE),
    IDENTIFY_SUBDEVICE(SUPLA_CALCFG_CMD_IDENTIFY_SUBDEVICE),
    RESTART_DEVICE(SUPLA_CALCFG_CMD_RESTART_DEVICE),
    RESTART_SUBDEVICE(SUPLA_CALCFG_CMD_RESTART_SUBDEVICE),
    TAKE_OCR_PHOTO(SUPLA_CALCFG_CMD_TAKE_OCR_PHOTO),
    MUTE_ALARM_SOUND(SUPLA_CALCFG_CMD_MUTE_ALARM_SOUND);

    private final int value;

    CalCfgCommand(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static CalCfgCommand fromValue(int value) {
        return Arrays.stream(values())
                .filter(command -> command.value == value)
                .findFirst()
                .orElseThrow(
                        () ->
                                new ProtocolCodecException(
                                        "Unsupported SUPLA_CALCFG_CMD value " + value));
    }
}
