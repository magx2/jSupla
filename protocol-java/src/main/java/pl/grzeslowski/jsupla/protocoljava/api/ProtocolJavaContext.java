package pl.grzeslowski.jsupla.protocoljava.api;

import pl.grzeslowski.jsupla.JSuplaContext;
import pl.grzeslowski.jsupla.exceptions.ServiceNotFoundException;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SdSuplaChannelNewValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelNewValueBToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelNewValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ChannelNewValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.ClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.RegisterClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.ChannelNewValueResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.FirmwareUpdateParamsParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceCParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.EventParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.LocationParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.RegisterClientResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ServerClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.GetVersionResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.PingServerResultClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.ServerDeviceClientParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.SetActivityTimeoutResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.VersionErrorParser;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.StringParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.ChannelNewValueBParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.ClientServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.RegisterClientBParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.RegisterClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs.DeviceClientServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs.PingServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs.SetActivityTimeoutParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.ChannelNewValueResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.DeviceChannelValueParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.DeviceServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.RegisterDeviceParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.ChannelPackParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.ChannelParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.ChannelValueParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.EventParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.LocationPackParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.LocationParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.RegisterClientResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.ServerClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.ChannelNewValueParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.FirmwareUpdateUrlResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.RegisterDeviceResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.ServerDeviceParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.GetVersionResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.PingServerResultClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.ServerDeviceClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.SetActivityTimeoutResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.VersionErrorParserImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ProtocolJavaContext implements JSuplaContext {
    PROTOCOL_JAVA_CONTEXT;

    private final Map<Class<?>, Object> contextMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    ProtocolJavaContext() {
        initParsers();
    }

    private void initParsers() {
        // parsers
        put(StringParser.class, StringParserImpl.INSTANCE);

        // ClientServerParser
        put(ChannelNewValueBParser.class, new ChannelNewValueBParserImpl(getService(ChannelTypeDecoder.class), getService(SuplaChannelNewValueBToChannelType.class)));
        put(ChannelNewValueParser.class, new pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.ChannelNewValueParserImpl(getService(ChannelTypeDecoder.class), getService(SuplaChannelNewValueToChannelType.class)));
        put(RegisterClientBParser.class, new RegisterClientBParserImpl(getService(StringParser.class)));
        put(RegisterClientParser.class, new RegisterClientParserImpl(getService(StringParser.class)));
        put(ClientServerParser.class, new ClientServerParserImpl(
                                                                        getService(ChannelNewValueBParser.class),
                                                                        getService(ChannelNewValueParser.class),
                                                                        getService(RegisterClientBParser.class),
                                                                        getService(RegisterClientParser.class)
        ));

        // DeviceClientServerParser
        put(PingServerParser.class, new PingServerParserImpl(getService(TimevalParser.class)));
        put(SetActivityTimeoutParser.class, new SetActivityTimeoutParserImpl());
        put(DeviceClientServerParser.class, new DeviceClientServerParserImpl(
                                                                                    getService(PingServerParser.class),
                                                                                    getService(SetActivityTimeoutParser.class)
        ));

        // DeviceServerParser
        put(ChannelNewValueResultParser.class, new ChannelNewValueResultParserImpl());
        put(DeviceChannelValueParser.class, new DeviceChannelValueParserImpl(getService(ChannelTypeDecoder.class), getService(SuplaDeviceChannelValueToChannelType.class)));
        put(RegisterDeviceParser.class, new RegisterDeviceParserImpl(getService(StringParser.class), getService(DeviceChannelParser.class)));
        put(FirmwareUpdateParamsParser.class, new FirmwareUpdateParamsParserImpl());
        put(RegisterDeviceBParser.class, new RegisterDeviceBParserImpl());
        put(RegisterDeviceCParser.class, new RegisterDeviceCParserImpl());
        put(DeviceServerParser.class, new DeviceServerParserImpl(
                                                                        getService(ChannelNewValueResultParser.class),
                                                                        getService(DeviceChannelValueParser.class),
                                                                        getService(RegisterDeviceParser.class),
                                                                        getService(FirmwareUpdateParamsParser.class),
                                                                        getService(RegisterDeviceBParser.class),
                                                                        getService(RegisterDeviceCParser.class)
        ));

        // ServerClientParser
        put(LocationParser.class, new LocationParserImpl(getService(StringParser.class)));
        put(ChannelPackParser.class, new ChannelPackParserImpl(getService(ChannelParser.class)));
        put(EventParser.class, new EventParserImpl(getService(StringParser.class)));
        put(pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser.class, new ChannelValueParserImpl(getService(ChannelValueParser.class)));
        put(ChannelParser.class, new ChannelParserImpl(getService(ChannelValueParser.class), getService(StringParser.class)));
        put(LocationPackParser.class, new LocationPackParserImpl(getService(LocationParser.class)));
        put(RegisterClientResultParser.class, new RegisterClientResultParserImpl());
        put(ServerClientParser.class, new ServerClientParserImpl(
                                                                        getService(LocationParser.class),
                                                                        getService(ChannelPackParser.class),
                                                                        getService(EventParser.class),
                                                                        getService(pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser.class),
                                                                        getService(ChannelParser.class),
                                                                        getService(LocationPackParser.class),
                                                                        getService(RegisterClientResultParser.class)
        ));

        // ServerDeviceParser
        put(pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser.class, new ChannelNewValueParserImpl(getService(ChannelTypeDecoder.class), getService(SdSuplaChannelNewValueToChannelType.class)));
        put(FirmwareUpdateUrlResultParser.class, new FirmwareUpdateUrlResultParserImpl(getService(FirmwareUpdateUrlParser.class)));
        put(RegisterDeviceResultParser.class, new RegisterDeviceResultParserImpl());
        put(ServerDeviceParser.class, new ServerDeviceParserImpl(
                                                                        getService(pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser.class),
                                                                        getService(FirmwareUpdateUrlResultParser.class),
                                                                        getService(RegisterDeviceResultParser.class)
        ));

        // ServerDeviceClientParser
        put(VersionErrorParser.class, new VersionErrorParserImpl());
        put(GetVersionResultParser.class, new GetVersionResultParserImpl(getService(StringParser.class)));
        put(SetActivityTimeoutResultParser.class, new SetActivityTimeoutResultParserImpl());
        put(PingServerResultClientParser.class, new PingServerResultClientParserImpl(getService(TimevalParser.class)));
        put(ServerDeviceClientParser.class, new ServerDeviceClientParserImpl(
                                                                                    getService(VersionErrorParser.class),
                                                                                    getService(GetVersionResultParser.class),
                                                                                    getService(SetActivityTimeoutResultParser.class),
                                                                                    getService(PingServerResultClientParser.class)
        ));

        put(Parser.class, new ParserImpl(
                                                getService(ClientServerParser.class),
                                                getService(DeviceClientServerParser.class),
                                                getService(DeviceServerParser.class),
                                                getService(ServerClientParser.class),
                                                getService(ServerDeviceParser.class),
                                                getService(ServerDeviceClientParser.class),
                                                getService(DeviceChannelParser.class),
                                                getService(DeviceChannelBParser.class),
                                                getService(FirmwareUpdateUrlParser.class),
                                                getService(ChannelValueParser.class),
                                                getService(TimevalParser.class)
        ));
    }

    private <T> void put(Class<T> key, T value) {
        contextMap.put(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getService(final Class<T> serviceClass) throws ServiceNotFoundException {
        Object service = contextMap.get(serviceClass);
        if (service == null) {
            throw new ServiceNotFoundException(serviceClass);
        }
        return (T) service;
    }
}
