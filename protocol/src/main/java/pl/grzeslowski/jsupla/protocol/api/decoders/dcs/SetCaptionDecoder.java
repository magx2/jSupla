package pl.grzeslowski.jsupla.protocol.api.decoders.dcs;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SetCaption;

public class SetCaptionDecoder
        implements pl.grzeslowski.jsupla.protocol.api.decoders.dcs.DeviceClientServerDecoder<
                SetCaption> {
    public static final SetCaptionDecoder INSTANCE = new SetCaptionDecoder();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public SetCaption decode(byte[] bytes, int offset) {
        // FYI I dont know why I'm always parsing ID and not channel number,
        // but it's working...
        val id = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        val captionSize = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        val caption =
                PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                        bytes, offset, offset + (int) captionSize);
        offset += captionSize * BYTE_SIZE;

        return new SetCaption(null, id, captionSize, caption);
    }
}
