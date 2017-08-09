package pl.grzeslowski.jsupla.protocol.call_types;

public enum ServerClientCallType {
    SUPLA_SC_CALL_REGISTER_CLIENT_RESULT(90),
    SUPLA_SC_CALL_LOCATION_UPDATE(130),
    SUPLA_SC_CALL_LOCATIONPACK_UPDATE(140),
    SUPLA_SC_CALL_CHANNEL_UPDATE(150),
    SUPLA_SC_CALL_CHANNELPACK_UPDATE(160),
    SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE(170),
    SUPLA_SC_CALL_EVENT(190);

    private final int value;

    ServerClientCallType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
