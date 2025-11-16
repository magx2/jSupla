package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OpenClose;

public class OpenCloseTypeEncoderImpl {
    public byte[] encode(final OpenClose openClose) {
        requireNonNull(openClose);
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        bytes[0] = (byte) (openClose == OpenClose.OPEN ? 1 : 0);
        return bytes;
    }
}
