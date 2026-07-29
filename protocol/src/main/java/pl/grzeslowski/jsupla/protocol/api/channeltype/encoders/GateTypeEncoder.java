package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.GateValue;

public class GateTypeEncoder implements ChannelValueEncoder<GateValue> {
    @Override
    public void encode(GateValue value, byte[] bytes) {
        bytes[0] =
                switch (value) {
                    case OPEN -> 1;
                    case CLOSE -> 0;
                };
    }
}
