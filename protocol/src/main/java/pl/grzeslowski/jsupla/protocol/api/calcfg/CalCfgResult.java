package pl.grzeslowski.jsupla.protocol.api.calcfg;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import java.util.Arrays;
import pl.grzeslowski.jsupla.protocol.api.serialization.ProtocolCodecException;

public enum CalCfgResult {
    FALSE(SUPLA_CALCFG_RESULT_FALSE),
    TRUE(SUPLA_CALCFG_RESULT_TRUE),
    DONE(SUPLA_CALCFG_RESULT_DONE),
    IN_PROGRESS(SUPLA_CALCFG_RESULT_IN_PROGRESS),
    NODE_FOUND(SUPLA_CALCFG_RESULT_NODE_FOUND),
    SENDER_CONFLICT(SUPLA_CALCFG_RESULT_SENDER_CONFLICT),
    TIMEOUT(SUPLA_CALCFG_RESULT_TIMEOUT),
    NOT_SUPPORTED(SUPLA_CALCFG_RESULT_NOT_SUPPORTED),
    ID_NOT_EXISTS(SUPLA_CALCFG_RESULT_ID_NOT_EXISTS),
    UNAUTHORIZED(SUPLA_CALCFG_RESULT_UNAUTHORIZED),
    DEBUG(SUPLA_CALCFG_RESULT_DEBUG),
    NOT_SUPPORTED_IN_SLAVE_MODE(SUPLA_CALCFG_RESULT_NOT_SUPPORTED_IN_SLAVE_MODE);

    private final int value;

    CalCfgResult(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static CalCfgResult fromValue(int value) {
        return Arrays.stream(values())
                .filter(result -> result.value == value)
                .findFirst()
                .orElseThrow(
                        () ->
                                new ProtocolCodecException(
                                        "Unsupported SUPLA_CALCFG_RESULT value " + value));
    }
}
