package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.server.entities.misc.ds.DeviceChannelsB;
import pl.grzeslowski.jsupla.server.entities.requests.ds.RegisterDeviceRequestB;

import java.util.ArrayList;

import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public class DeviceRegisterRequestBParser implements Parser<RegisterDeviceRequestB, SuplaRegisterDeviceB> {
    @Override
    public RegisterDeviceRequestB parse(SuplaRegisterDeviceB proto) {
        int locationId = proto.locationId;
        String locationPassword = INSTANCE.parseUtf8String(proto.locationPwd, 0, proto.locationPwd.length);
        String guid = "TODO";// TODO INSTANCE.parseHexString(proto.guid);
        String name = INSTANCE.parseUtf8String(proto.name, 0, proto.name.length);
        String softVersion = INSTANCE.parseUtf8String(proto.softVer, 0, proto.softVer.length);
        DeviceChannelsB channels = new DeviceChannelsB(new ArrayList<>());// TODO

        return new RegisterDeviceRequestB(locationId, locationPassword, guid, name, softVersion, channels);
    }
}
