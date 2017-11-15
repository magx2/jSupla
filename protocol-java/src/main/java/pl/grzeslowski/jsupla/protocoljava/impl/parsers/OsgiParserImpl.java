package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

import pl.grzeslowski.jsupla.protocol.api.ProtocolContext;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ServerClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.ServerDeviceClientParser;

final class OsgiParserImpl extends ParserImpl {
    @SuppressWarnings("unchecked")
    OsgiParserImpl() {
        super(
                ProtocolContext.PROTOCOL_CONTEXT.getService(ClientServerParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceClientServerParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceServerParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ServerClientParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ServerDeviceParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ServerDeviceClientParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceChannelParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(DeviceChannelBParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(FirmwareUpdateUrlParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(ChannelValueParser.class),
                ProtocolContext.PROTOCOL_CONTEXT.getService(TimevalParser.class)
        );
    }
}
