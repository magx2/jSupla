package pl.grzeslowski.jsupla.protocol.api.encoders.sd;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;

@javax.annotation.Generated(value = "Struct original name: TSD_SuplaChannelNewValue", date = "2024-05-12T13:22:23.935+02:00[Europe/Belgrade]")
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaChannelNewValueEncoder implements pl.grzeslowski.jsupla.protocol.api.encoders.sd.ServerDeviceEncoder<SuplaChannelNewValue> {
    public static final SuplaChannelNewValueEncoder INSTANCE = new SuplaChannelNewValueEncoder();

    @Override
    public byte[] encode(SuplaChannelNewValue proto) {
        final byte[] bytes = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.INSTANCE.writeInt(proto.senderId, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedByte(proto.channelNumber, bytes, offset);

        // union
        if (proto.durationMS != null) {
            offset += PrimitiveEncoder.INSTANCE.writeUnsignedInt(proto.durationMS, bytes, offset);
        } else {
            offset += PrimitiveEncoder.INSTANCE.writeUnsignedInt(proto.durationSec, bytes, offset);
        }

        offset += PrimitiveEncoder.INSTANCE.writeByteArray(proto.value, bytes, offset);

        return bytes;
    }
}
