package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser.parseInt;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class TDS_SuplaRegisterDevice_BDecoder implements Decoder<SuplaRegisterDeviceB> {
    private final Decoder<SuplaDeviceChannelB> channelDecoder;

    public TDS_SuplaRegisterDevice_BDecoder(Decoder<SuplaDeviceChannelB> channelDecoder) {
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public SuplaRegisterDeviceB decode(SuplaDataPacket dataPacket) {
        final byte[] bytes = dataPacket.data;
        int offset = 0;

        final int locationId = parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] locationPwd = Arrays.copyOfRange(bytes, offset, offset + SUPLA_LOCATION_PWD_MAXSIZE);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;

        final byte[] guid = Arrays.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        final byte[] name = Arrays.copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        final byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        final short channelCount = PrimitiveParser.parseUnsignedByte(bytes, offset);

        SuplaDeviceChannelB[] channels = new SuplaDeviceChannelB[0]; // TODO

        return new SuplaRegisterDeviceB(locationId, locationPwd, guid, name, softVer, channelCount, channels);
    }
}
