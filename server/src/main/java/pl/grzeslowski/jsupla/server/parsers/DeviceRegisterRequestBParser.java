package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.server.entities.misc.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequestB;

import java.util.ArrayList;

import static pl.grzeslowski.jsupla.protocoljava.impl.parsers.StringParserImpl.INSTANCE;

public class DeviceRegisterRequestBParser implements Parser<RegisterDeviceRequestB, SuplaRegisterDeviceB> {
    @Override
    public RegisterDeviceRequestB parse(SuplaRegisterDeviceB proto) {
        int locationId = proto.locationId;
        String locationPassword = INSTANCE.parse(proto.locationPwd);
        String guid = INSTANCE.parseHexString(proto.guid);
        String name = INSTANCE.parse(proto.name);
        String softVersion = INSTANCE.parse(proto.softVer);
        DeviceChannelsB channels = new DeviceChannelsB(new ArrayList<>());// TODO

        return new RegisterDeviceRequestB(locationId, locationPassword, guid, name, softVersion, channels);
    }
}
