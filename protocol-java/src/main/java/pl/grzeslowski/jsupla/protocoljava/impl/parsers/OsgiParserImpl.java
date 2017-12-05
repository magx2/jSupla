package pl.grzeslowski.jsupla.protocoljava.impl.parsers;

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

import static pl.grzeslowski.jsupla.protocoljava.api.ProtocolJavaContext.PROTOCOL_JAVA_CONTEXT;

final class OsgiParserImpl extends ParserImpl {
    @SuppressWarnings("unchecked")
    OsgiParserImpl() {
        super(
                PROTOCOL_JAVA_CONTEXT.getService(ClientServerParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceClientServerParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceServerParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(ServerClientParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(ServerDeviceParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(ServerDeviceClientParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceChannelParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(DeviceChannelBParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(FirmwareUpdateUrlParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(ChannelValueParser.class),
                PROTOCOL_JAVA_CONTEXT.getService(TimevalParser.class)
        );
    }
}
