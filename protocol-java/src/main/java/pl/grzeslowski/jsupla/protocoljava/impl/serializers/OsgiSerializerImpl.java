package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ServerClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.ServerDeviceClientSerializer;

import static pl.grzeslowski.jsupla.protocoljava.api.ProtocolJavaContext.PROTOCOL_JAVA_CONTEXT;

final class OsgiSerializerImpl extends SerializerImpl {
    @SuppressWarnings("unchecked")
    OsgiSerializerImpl() {
        super(
                PROTOCOL_JAVA_CONTEXT.getService(ClientServerSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceClientServerSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceServerSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(ServerClientSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(ServerDeviceSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(ServerDeviceClientSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceChannelSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceChannelBSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(FirmwareUpdateUrlSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(ChannelValueSerializer.class),
                PROTOCOL_JAVA_CONTEXT.getService(TimevalSerializer.class));
    }
}
