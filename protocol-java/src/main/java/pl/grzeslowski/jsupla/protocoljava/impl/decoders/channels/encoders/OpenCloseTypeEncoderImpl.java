package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.OpenCloseTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OpenClose;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class OpenCloseTypeEncoderImpl implements OpenCloseTypeEncoder {
    @Override
    public byte[] encode(final OpenClose openClose) {
        requireNonNull(openClose);
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        bytes[0] = (byte) (openClose == OpenClose.OPEN ? 1 : 0);
        return bytes;
    }
}
