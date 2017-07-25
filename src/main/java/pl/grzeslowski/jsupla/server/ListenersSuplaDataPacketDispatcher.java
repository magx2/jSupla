package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.proto.parsers.TDS_SuplaDeviceChannel_BParser;
import pl.grzeslowski.jsupla.proto.parsers.TDS_SuplaRegisterDevice_BParser;
import pl.grzeslowski.jsupla.proto.structs.TDS_SuplaRegisterDevice_B;
import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;
import pl.grzeslowski.jsupla.server.entities.DeviceChannelB;
import pl.grzeslowski.jsupla.server.entities.DeviceRegisterEventB;
import pl.grzeslowski.jsupla.server.entities.RegisterDeviceResult;
import pl.grzeslowski.jsupla.server.listeners.Listeners;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.consts.CallTypes.SUPLA_DS_CALL_REGISTER_DEVICE_B;
import static pl.grzeslowski.jsupla.consts.CallTypes.SUPLA_SDC_CALL_VERSIONERROR;
import static pl.grzeslowski.jsupla.proto.parsers.PrimitiveParser.parseString;
import static pl.grzeslowski.jsupla.proto.parsers.PrimitiveParser.parseUtf8String;

public class ListenersSuplaDataPacketDispatcher implements SuplaDataPacketDispatcher {
    private final Listeners listeners;

    public ListenersSuplaDataPacketDispatcher(Listeners listeners) {
        this.listeners = requireNonNull(listeners);
    }

    @Override
    public TSuplaDataPacket dispatch(TSuplaDataPacket dataPacket) {
        if (dataPacket.callType == SUPLA_DS_CALL_REGISTER_DEVICE_B) {
            final TDS_SuplaRegisterDevice_BParser parser = new TDS_SuplaRegisterDevice_BParser(new TDS_SuplaDeviceChannel_BParser());
            final TDS_SuplaRegisterDevice_B registerDeviceB = parser.parse(dataPacket.data);


            int locationId = registerDeviceB.locationId;
            String locationPassword = parseUtf8String(registerDeviceB.locationPwd);
            String guid = parseString(registerDeviceB.guid);
            String name = parseUtf8String(registerDeviceB.name);
            String softVersion = parseUtf8String(registerDeviceB.softVer);
            List<? extends DeviceChannelB> channels = new ArrayList<>(); // TODO implement parsing channels
            final DeviceRegisterEventB deviceRegisterEventB = new DeviceRegisterEventB(locationId, locationPassword, guid, name, softVersion, channels);

            return listeners.getDeviceRegisterListener()
                    .map(l -> l.onDeviceRegisterEvent(deviceRegisterEventB))
                    .map(this::parseDataPacket)
                    .orElse(null);
        }
        return null;
    }

    int id = 1;

    private TSuplaDataPacket parseDataPacket(RegisterDeviceResult registerDeviceResult) {


        short version = (short) registerDeviceResult.getVersion();
        long rrId = id++;
        long callType = SUPLA_SDC_CALL_VERSIONERROR;
        long dataSize = 0;
        byte[] data = new byte[0];

        return new TSuplaDataPacket(version, rrId, callType, dataSize, data);
    }
}
