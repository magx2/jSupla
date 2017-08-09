package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.proto.structs.ds.TDS_SuplaRegisterDevice_B;
import pl.grzeslowski.jsupla.server.entities.DeviceChannelB;
import pl.grzeslowski.jsupla.server.entities.requests.DeviceRegisterRequestB;

import java.util.ArrayList;
import java.util.List;

import static pl.grzeslowski.jsupla.proto.decoders.PrimitiveParser.parseHexString;
import static pl.grzeslowski.jsupla.proto.decoders.PrimitiveParser.parseUtf8String;

public class DeviceRegisterRequestBParser implements Parser<DeviceRegisterRequestB, TDS_SuplaRegisterDevice_B> {
    @Override
    public DeviceRegisterRequestB parse(TDS_SuplaRegisterDevice_B proto) {
        int locationId = proto.locationId;
        String locationPassword = parseUtf8String(proto.locationPwd);
        String guid = parseHexString(proto.guid);
        String name = parseUtf8String(proto.name);
        String softVersion = parseUtf8String(proto.softVer);
        List<? extends DeviceChannelB> channels = new ArrayList<>();// TODO

        return new DeviceRegisterRequestB(locationId, locationPassword, guid, name, softVersion, channels);
    }
}
