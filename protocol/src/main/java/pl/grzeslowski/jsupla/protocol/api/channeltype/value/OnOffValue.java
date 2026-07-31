package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

public enum OnOffValue implements AbstractOnOffValue {
    ON,
    OFF;

    @Override
    public OnOffValue toCommonBase() {
        return this;
    }
}
