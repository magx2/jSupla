package pl.grzeslowski.jsupla.protocol.api.serialization;

import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CALCFG_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_DEVICE_CALCFG_REQUEST;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import pl.grzeslowski.jsupla.protocol.api.calcfg.DeviceCalCfgRequestCodec;
import pl.grzeslowski.jsupla.protocol.api.calcfg.DeviceCalCfgResultCodec;
import pl.grzeslowski.jsupla.protocol.api.calcfg.TdsDeviceCalCfgResult;
import pl.grzeslowski.jsupla.protocol.api.calcfg.TsdDeviceCalCfgRequest;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public final class PayloadCodecRegistry {
    public static final PayloadCodecRegistry INSTANCE = new PayloadCodecRegistry();

    private final Map<Integer, PayloadCodec<? extends ProtoWithSize>> byCallType;
    private final Map<Class<?>, PayloadCodec<? extends ProtoWithSize>> byClass;

    private PayloadCodecRegistry() {
        byCallType = new HashMap<>();
        byClass = new HashMap<>();

        register(
                SUPLA_SD_CALL_DEVICE_CALCFG_REQUEST.getValue(),
                TsdDeviceCalCfgRequest.class,
                DeviceCalCfgRequestCodec.INSTANCE);
        register(
                SUPLA_DS_CALL_DEVICE_CALCFG_RESULT.getValue(),
                TdsDeviceCalCfgResult.class,
                DeviceCalCfgResultCodec.INSTANCE);
    }

    public Optional<PayloadCodec<? extends ProtoWithSize>> findForCallType(long callType) {
        if (callType > Integer.MAX_VALUE) {
            return Optional.empty();
        }
        return Optional.ofNullable(byCallType.get((int) callType));
    }

    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize> Optional<PayloadCodec<T>> findForPayload(T payload) {
        return Optional.ofNullable((PayloadCodec<T>) byClass.get(payload.getClass()));
    }

    public Optional<ProtoWithSize> decode(SuplaDataPacket packet) {
        return findForCallType(packet.callId()).map(codec -> decodePayload(codec, packet.data()));
    }

    public <T extends ProtoWithSize> Optional<byte[]> encode(T payload) {
        return findForPayload(payload).map(codec -> codec.encode(payload));
    }

    private <T extends ProtoWithSize> void register(
            int callType, Class<T> payloadType, PayloadCodec<T> codec) {
        byCallType.put(callType, codec);
        byClass.put(payloadType, codec);
    }

    private static ProtoWithSize decodePayload(
            PayloadCodec<? extends ProtoWithSize> codec, byte[] bytes) {
        return codec.decode(bytes);
    }
}
