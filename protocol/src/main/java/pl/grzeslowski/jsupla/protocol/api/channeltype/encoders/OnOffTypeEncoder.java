package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOffValue;

public class OnOffTypeEncoder implements ChannelValueEncoder<OnOffValue> {
    @Override
    public void encode(OnOffValue value, byte[] bytes) {
        bytes[0] =
                switch (value) {
                    case ON -> 1;
                    case OFF -> 0;
                };
    }
}
