package pl.grzeslowski.jsupla.protocol.api.calltypes;

public enum ServerClientCallType implements CallType {
    SUPLA_SC_CALL_REGISTER_CLIENT_RESULT(90),
    /**
     * @since ver. 9
     */
    SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_B(92),
    SUPLA_SC_CALL_LOCATION_UPDATE(130),
    SUPLA_SC_CALL_LOCATIONPACK_UPDATE(140),
    SUPLA_SC_CALL_CHANNEL_UPDATE(150),
    SUPLA_SC_CALL_CHANNELPACK_UPDATE(160),
    SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE(170),
    SUPLA_SC_CALL_EVENT(190),
    /**
     * @since ver. 7
     */
    SUPLA_SC_CALL_GET_OAUTH_PARAMETERS_RESULT(350),
    /**
     * @since ver. 8
     */
    SUPLA_SC_CALL_CHANNELPACK_UPDATE_B(360),
    /**
     * @since ver. 8
     */
    SUPLA_SC_CALL_CHANNEL_UPDATE_B(370),
    /**
     * @since ver. 9
     */
    SUPLA_SC_CALL_CHANNELGROUP_PACK_UPDATE(380),
    /**
     * @since ver. 9
     */
    SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE(390),
    /**
     * @since ver. 9
     */
    SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE(400);

    private final int value;

    ServerClientCallType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
