package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.StoppableOpenCloseEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.StoppableOpenClose;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class StoppableOpenCloseEncoderImpl implements StoppableOpenCloseEncoder {
    @Override
    public byte[] encode(final StoppableOpenClose stoppableOpenClose) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        switch (stoppableOpenClose) {
            case STOP:
                bytes[0] = 0;
                break;
            case OPEN:
                bytes[0] = 1;
                break;
            case CLOSE:
                bytes[0] = 2;
                break;
            default:
                throw new UnsupportedOperationException("Don't know this type " + stoppableOpenClose + "!");
        }
        return bytes;
    }
}
