package pl.grzeslowski.jsupla.proto.decoders;

import pl.grzeslowski.jsupla.proto.structs.TDS_SuplaDeviceChannel_B;
import pl.grzeslowski.jsupla.proto.structs.TDS_SuplaRegisterDevice_B;

import static java.util.Arrays.copyOfRange;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.proto.decoders.PrimitiveParser.parseInt;

@Deprecated
@SuppressWarnings("DeprecatedIsStillUsed")
public class TDS_SuplaRegisterDevice_BDecoder implements Decoder<TDS_SuplaRegisterDevice_B> {
    private final Decoder<TDS_SuplaDeviceChannel_B> channelDecoder;

    public TDS_SuplaRegisterDevice_BDecoder(Decoder<TDS_SuplaDeviceChannel_B> channelDecoder) {
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public TDS_SuplaRegisterDevice_B decode(byte[] bytes, int offset) {
        int locationId = parseInt(bytes, offset);
        offset += INT_SIZE;

        byte[] locationPwd = copyOfRange(bytes, offset, offset + SUPLA_LOCATION_PWD_MAXSIZE);
        offset += SUPLA_LOCATION_PWD_MAXSIZE;

        byte[] guid = copyOfRange(bytes, offset, offset + SUPLA_GUID_SIZE);
        offset += SUPLA_GUID_SIZE;

        byte[] name = copyOfRange(bytes, offset, offset + SUPLA_DEVICE_NAME_MAXSIZE);
        offset += SUPLA_DEVICE_NAME_MAXSIZE;

        byte[] softVer = copyOfRange(bytes, offset, offset + SUPLA_SOFTVER_MAXSIZE);
        offset += SUPLA_SOFTVER_MAXSIZE;

        short channelCount = PrimitiveParser.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        TDS_SuplaDeviceChannel_B[] channels = new TDS_SuplaDeviceChannel_B[channelCount];
        for (int i = 0; i < channelCount; i++) {
            channels[i] = channelDecoder.decode(bytes, offset);
            offset += channels[i].size();
        }

        if (offset != bytes.length) { // TODO remove
            throw new RuntimeException(offset + " " + bytes.length);
        }
        return new TDS_SuplaRegisterDevice_B(locationId, locationPwd, guid, name, softVer, channelCount, channels);
    }
}