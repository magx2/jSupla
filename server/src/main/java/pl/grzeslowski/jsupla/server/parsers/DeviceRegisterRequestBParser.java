package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.server.entities.misc.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequestB;

import java.util.ArrayList;

import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder.parseHexString;
import static pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder.parseUtf8String;

public class DeviceRegisterRequestBParser implements Parser<RegisterDeviceRequestB, SuplaRegisterDeviceB> {
    @Override
    public RegisterDeviceRequestB parse(SuplaRegisterDeviceB proto) {
        int locationId = proto.locationId;
        String locationPassword = parseUtf8String(proto.locationPwd);
        String guid = parseHexString(proto.guid);
        String name = parseUtf8String(proto.name);
        String softVersion = parseUtf8String(proto.softVer);
        DeviceChannelsB channels = new DeviceChannelsB(new ArrayList<>());// TODO

        return new RegisterDeviceRequestB(locationId, locationPassword, guid, name, softVersion, channels);
    }
}
