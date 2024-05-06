package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelC;

@javax.annotation.Generated(value = "Struct original name: TDS_SuplaDeviceChannel_C", date = "2024-05-06T21:16:48.661+02:00[Europe/Belgrade]")
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class SuplaDeviceChannelCDecoder implements pl.grzeslowski.jsupla.protocol.api.decoders.ds.DeviceServerDecoder<SuplaDeviceChannelC> {
    public static final SuplaDeviceChannelCDecoder INSTANCE = new SuplaDeviceChannelCDecoder();

    @Override
    public SuplaDeviceChannelC decode(byte[] bytes, int offset) {
        throw new java.lang.UnsupportedOperationException("Do not support decoding structs with unions!");
    }
}