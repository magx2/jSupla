package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.ProtocolContext;
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

final class OsgiSerializerImpl extends SerializerImpl {
    @SuppressWarnings("unchecked")
    OsgiSerializerImpl() {
        super(
                ProtocolContext.PROTOCOL_CONTEXT.getService(ClientServerSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceClientServerSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceServerSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ServerClientSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ServerDeviceSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ServerDeviceClientSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceChannelSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceChannelBSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(FirmwareUpdateUrlSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ChannelValueSerializer.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(TimevalSerializer.class));
    }
}
