package pl.grzeslowski.jsupla.protocol.decoders;

import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.structs.ds.TDS_SuplaDeviceChannel_B;
import pl.grzeslowski.jsupla.protocol.structs.ds.TDS_SuplaRegisterDevice_B;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser.parseInt;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class TDS_SuplaRegisterDevice_BDecoder implements Decoder<TDS_SuplaRegisterDevice_B> {
    private final Decoder<TDS_SuplaDeviceChannel_B> channelDecoder;

    public TDS_SuplaRegisterDevice_BDecoder(Decoder<TDS_SuplaDeviceChannel_B> channelDecoder) {
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public TDS_SuplaRegisterDevice_B decode(TSuplaDataPacket dataPacket) {
        final byte[] bytes = dataPacket.data;
        int offset = 0;

        int locationId = parseInt(bytes, offset);
        offset += INT_SIZE;

        byte[] locationPwd = Arrays.copyOfRange(bytes, offset, offset + SUPLA_LOCATION_PWD_MAXSIZE);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;

        byte[] guid = Arrays.copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        byte[] name = Arrays.copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        byte[] softVer = Arrays.copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        short channelCount = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        TDS_SuplaDeviceChannel_B[] channels = new TDS_SuplaDeviceChannel_B[0]; // TODO

        return new TDS_SuplaRegisterDevice_B(locationId, locationPwd, guid, name, softVer, channelCount, channels);
    }
}