package pl.grzeslowski.jsupla.protocoljava.api;

import pl.grzeslowski.jsupla.JSuplaContext;
import pl.grzeslowski.jsupla.exceptions.ServiceNotFoundException;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ColorTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.RelayTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ThermometerTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaChannelValueToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelBToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.tochanneltype.SuplaDeviceChannelToChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.*;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.TimevalParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.cs.*;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.DeviceClientServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.PingServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs.SetActivityTimeoutParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.*;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.*;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.RegisterDeviceResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ServerDeviceParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.RegisterDeviceResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.*;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ChannelTypeDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ColorTypeChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.RelayTypeChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ThermometerTypeChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype.SuplaChannelValueToChannelTypeImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype.SuplaDeviceChannelToChannelTypeImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.*;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.StringParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.TimevalParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.ChannelNewValueBParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.ClientServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.RegisterClientBParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs.RegisterClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs.DeviceClientServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs.PingServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.dcs.SetActivityTimeoutParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.*;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc.*;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.*;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.*;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ChannelValueSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.TimevalSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.*;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.DeviceClientServerSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.PingServerSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.*;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.*;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.FirmwareUpdateUrlResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.FirmwareUpdateUrlSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.RegisterDeviceResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.ServerDeviceSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class ProtocolJavaContext implements JSuplaContext {
    private final Map<Class<?>, Object> contextMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public ProtocolJavaContext(DeviceChannelValueParser.TypeMapper typeMapper) {
        put(DeviceChannelValueParser.TypeMapper.class, typeMapper);
        initParsers();
        initSerializers();
    }

    private void initSerializers() {

        // common
        put(StringSerializer.class, StringSerializerImpl.INSTANCE);
        put(ColorTypeChannelEncoder.class, new ColorTypeChannelEncoderImpl());
        put(RelayTypeChannelEncoder.class, new RelayTypeChannelEncoderImpl());
        put(ThermometerTypeChannelEncoder.class, new ThermometerTypeChannelEncoderImpl());
        put(StoppableOpenCloseEncoder.class, new StoppableOpenCloseEncoderImpl());
        put(ChannelTypeEncoder.class, new ChannelTypeEncoderImpl(
                                                                        getService(ColorTypeChannelEncoder.class),
                                                                        getService(RelayTypeChannelEncoder.class),
                                                                        getService(ThermometerTypeChannelEncoder.class),
                                                                        getService(StoppableOpenCloseEncoder.class)
        ));
        put(DeviceChannelSerializer.class, new DeviceChannelSerializerImpl(getService(ChannelTypeEncoder.class)));
        put(DeviceChannelBSerializer.class, new DeviceChannelBSerializerImpl(getService(ChannelTypeEncoder.class)));
        put(FirmwareUpdateUrlSerializer.class, new FirmwareUpdateUrlSerializerImpl(getService(StringSerializer.class)));
        put(ChannelValueSerializer.class, new ChannelValueSerializerImpl(getService(ChannelTypeEncoder.class)));
        put(TimevalSerializer.class, new TimevalSerializerImpl());

        // ClientServerSerializer
        put(ChannelNewValueSerializer.class, new ChannelNewValueSerializerImpl(getService(ChannelTypeEncoder.class)));
        put(ChannelNewValueBSerializer.class, new ChannelNewValueBSerializerImpl(getService(ChannelTypeEncoder.class)));
        put(RegisterClientSerializer.class, new RegisterClientSerializerImpl(getService(StringSerializer.class)));
        put(RegisterClientBSerializer.class, new RegisterClientBSerializerImpl(getService(StringSerializer.class)));
        put(ClientServerSerializer.class,
                new ClientServerSerializerImpl(
                                                      getService(ChannelNewValueSerializer.class),
                                                      getService(ChannelNewValueBSerializer.class),
                                                      getService(RegisterClientSerializer.class),
                                                      getService(RegisterClientBSerializer.class)
                ));

        // DeviceClientServerSerializer
        put(PingServerSerializer.class, new PingServerSerializerImpl(getService(TimevalSerializer.class)));
        put(SetActivityTimeoutSerializer.class, new SetActivityTimeoutSerializerImpl());
        put(DeviceClientServerSerializer.class,
                new DeviceClientServerSerializerImpl(
                                                            getService(PingServerSerializer.class),
                                                            getService(SetActivityTimeoutSerializer.class)
                ));

        // DeviceServerSerializer
        put(ChannelNewValueResultSerializer.class, new ChannelNewValueResultSerializerImpl());
        put(RegisterDeviceSerializer.class,
                new RegisterDeviceSerializerImpl(
                                                        getService(StringSerializer.class),
                                                        getService(DeviceChannelSerializer.class)));
        put(RegisterDeviceBSerializer.class,
                new RegisterDeviceBSerializerImpl(
                                                         getService(StringSerializer.class),
                                                         getService(DeviceChannelBSerializer.class)));
        put(RegisterDeviceCSerializer.class,
                new RegisterDeviceCSerializerImpl(
                                                         getService(StringSerializer.class),
                                                         getService(DeviceChannelBSerializer.class)));
        put(DeviceChannelValueSerializer.class,
                new DeviceChannelValueSerializerImpl(
                                                            getService(ChannelTypeEncoder.class)));
        put(FirmwareUpdateParamsSerializer.class, new FirmwareUpdateParamsSerializerImpl());
        put(DeviceServerSerializer.class,
                new DeviceServerSerializerImpl(
                                                      getService(ChannelNewValueResultSerializer.class),
                                                      getService(RegisterDeviceSerializer.class),
                                                      getService(RegisterDeviceBSerializer.class),
                                                      getService(RegisterDeviceCSerializer.class),
                                                      getService(DeviceChannelValueSerializer.class),
                                                      getService(FirmwareUpdateParamsSerializer.class)
                ));

        // ServerClientSerializer
        put(ChannelSerializer.class, new ChannelSerializerImpl(
                                                                      getService(ChannelValueSerializer.class),
                                                                      getService(StringSerializer.class)));
        put(ChannelPackSerializer.class, new ChannelPackSerializerImpl(getService(ChannelSerializer.class)));
        put(pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer.class,
                new pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc
                            .ChannelValueSerializerImpl(
                                                               getService(pl.grzeslowski.jsupla.protocoljava.api
                                                                                  .serializers
                                                                                  .ChannelValueSerializer.class)));
        put(EventSerializer.class, new EventSerializerImpl(getService(StringSerializer.class)));
        put(LocationSerializer.class, new LocationSerializerImpl(getService(StringSerializer.class)));
        put(LocationPackSerializer.class, new LocationPackSerializerImpl(getService(LocationSerializer.class)));
        put(RegisterClientResultSerializer.class, new RegisterClientResultSerializerImpl());
        put(ChannelGroupRelationSerializer.class, new ChannelGroupRelationSerializerImpl());
        put(RegisterClientResultBSerializer.class, new RegisterClientResultBSerializerImpl());
        put(ChannelGroupRelationPackSerializer.class,
                new ChannelGroupRelationPackSerializerImpl(getService(ChannelGroupRelationSerializer.class)));
        put(ServerClientSerializer.class,
                new ServerClientSerializerImpl(
                                                      getService(ChannelPackSerializer.class),
                                                      getService(ChannelSerializer.class),
                                                      getService(pl.grzeslowski.jsupla.protocoljava.api.serializers.sc
                                                                         .ChannelValueSerializer.class),
                                                      getService(EventSerializer.class),
                                                      getService(LocationPackSerializer.class),
                                                      getService(LocationSerializer.class),
                        getService(RegisterClientResultSerializer.class),
                        getService(ChannelGroupRelationSerializer.class),
                        getService(RegisterClientResultBSerializer.class),
                        getService(ChannelGroupRelationPackSerializer.class)));

        // ServerDeviceSerializer
        put(
                pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ChannelNewValueSerializer.class,
                new pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.ChannelNewValueSerializerImpl(getService(
                        ChannelTypeEncoder.class)));
        put(FirmwareUpdateUrlResultSerializer.class,
                new FirmwareUpdateUrlResultSerializerImpl(getService(FirmwareUpdateUrlSerializer.class)));
        put(RegisterDeviceResultSerializer.class, new RegisterDeviceResultSerializerImpl());
        put(ServerDeviceSerializer.class,
                new ServerDeviceSerializerImpl(
                                                      getService(pl.grzeslowski.jsupla.protocoljava.api.serializers.sd
                                                                         .ChannelNewValueSerializer.class),
                                                      getService(FirmwareUpdateUrlResultSerializer.class),
                                                      getService(RegisterDeviceResultSerializer.class)
                ));

        // ServerDeviceClientSerializer
        put(GetVersionResultSerializer.class, new GetVersionResultSerializerImpl(getService(StringSerializer.class)));
        put(PingServerResultClientSerializer.class,
                new PingServerResultClientSerializerImpl(getService(TimevalSerializer.class)));
        put(SetActivityTimeoutResultSerializer.class, new SetActivityTimeoutResultSerializerImpl());
        put(VersionErrorSerializer.class, new VersionErrorSerializerImpl());
        put(ServerDeviceClientSerializer.class,
                new ServerDeviceClientSerializerImpl(
                                                            getService(GetVersionResultSerializer.class),
                                                            getService(PingServerResultClientSerializer.class),
                                                            getService(SetActivityTimeoutResultSerializer.class),
                                                            getService(VersionErrorSerializer.class)
                ));

        put(Serializer.class, new SerializerImpl(
                                                        getService(ClientServerSerializer.class),
                                                        getService(DeviceClientServerSerializer.class),
                                                        getService(DeviceServerSerializer.class),
                                                        getService(ServerClientSerializer.class),
                                                        getService(ServerDeviceSerializer.class),
                                                        getService(ServerDeviceClientSerializer.class),
                                                        getService(DeviceChannelSerializer.class),
                                                        getService(DeviceChannelBSerializer.class),
                                                        getService(FirmwareUpdateUrlSerializer.class),
                                                        getService(ChannelValueSerializer.class),
                                                        getService(TimevalSerializer.class)
        ));
    }

    @SuppressWarnings("unchecked")
    private void initParsers() {

        // commons
        put(StringParser.class, StringParserImpl.INSTANCE);
        put(ColorTypeChannelDecoder.class, new ColorTypeChannelDecoderImpl());
        put(RelayTypeChannelDecoder.class, new RelayTypeChannelDecoderImpl());
        put(ThermometerTypeChannelDecoder.class, new ThermometerTypeChannelDecoderImpl());
        put(ChannelTypeDecoder.class, new ChannelTypeDecoderImpl(
                                                                        getService(ColorTypeChannelDecoder.class),
                                                                        getService(RelayTypeChannelDecoder.class),
                                                                        getService(ThermometerTypeChannelDecoder.class)
        ));
        SuplaDeviceChannelToChannelTypeImpl suplaDeviceChannelToChannelType = new SuplaDeviceChannelToChannelTypeImpl();
        put(SuplaDeviceChannelToChannelType.class, suplaDeviceChannelToChannelType);
        put(SuplaDeviceChannelBToChannelType.class, suplaDeviceChannelToChannelType);
        put(SuplaChannelValueToChannelType.class, new SuplaChannelValueToChannelTypeImpl());
        put(DeviceChannelParser.class,
                new DeviceChannelParserImpl(
                                                   getService(ChannelTypeDecoder.class),
                                                   getService(SuplaDeviceChannelToChannelType.class)));
        put(DeviceChannelBParser.class,
                new DeviceChannelBParserImpl(
                                                    getService(ChannelTypeDecoder.class),
                                                    getService(SuplaDeviceChannelBToChannelType.class)));
        put(FirmwareUpdateUrlParser.class, new FirmwareUpdateUrlParserImpl(getService(StringParser.class)));
        put(ChannelValueParser.class,
                new pl.grzeslowski.jsupla.protocoljava.impl.parsers
                            .ChannelValueParserImpl(getService(ChannelTypeDecoder.class),
                                                           getService(SuplaChannelValueToChannelType.class)));
        put(TimevalParser.class, new TimevalParserImpl());

        // ClientServerParser
        put(ChannelNewValueBParser.class,
                new ChannelNewValueBParserImpl(
                                                      getService(ChannelTypeDecoder.class),
                        getService(DeviceChannelValueParser.TypeMapper.class)));
        put(ChannelNewValueParser.class,
                new pl.grzeslowski.jsupla.protocoljava.impl.parsers.cs
                            .ChannelNewValueParserImpl(
                                                              getService(ChannelTypeDecoder.class),
                        getService(DeviceChannelValueParser.TypeMapper.class)));
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
        put(DeviceClientServerParser.class,
                new DeviceClientServerParserImpl(
                                                        getService(PingServerParser.class),
                                                        getService(SetActivityTimeoutParser.class)
                ));

        // DeviceServerParser
        put(ChannelNewValueResultParser.class, new ChannelNewValueResultParserImpl());
        put(DeviceChannelValueParser.class,
                new DeviceChannelValueParserImpl(
                                                        getService(ChannelTypeDecoder.class),
                        getService(DeviceChannelValueParser.TypeMapper.class)));
        put(RegisterDeviceParser.class, new RegisterDeviceParserImpl(
                                                                            getService(StringParser.class),
                                                                            getService(DeviceChannelParser.class)));
        put(FirmwareUpdateParamsParser.class, new FirmwareUpdateParamsParserImpl());
        put(RegisterDeviceBParser.class, new RegisterDeviceBParserImpl(
                                                                              getService(StringParser.class),
                                                                              getService(DeviceChannelBParser.class)));
        put(RegisterDeviceCParser.class, new RegisterDeviceCParserImpl(
                                                                              getService(StringParser.class),
                                                                              getService(DeviceChannelBParser.class)));
        put(DeviceServerParser.class, new DeviceServerParserImpl(
                                                                        getService(ChannelNewValueResultParser.class),
                                                                        getService(DeviceChannelValueParser.class),
                                                                        getService(RegisterDeviceParser.class),
                                                                        getService(FirmwareUpdateParamsParser.class),
                                                                        getService(RegisterDeviceBParser.class),
                                                                        getService(RegisterDeviceCParser.class)
        ));

        // ServerClientParser
        put(ChannelParser.class, new ChannelParserImpl(
                                                              getService(ChannelValueParser.class),
                                                              getService(StringParser.class)));
        put(LocationParser.class, new LocationParserImpl(getService(StringParser.class)));
        put(ChannelPackParser.class, new ChannelPackParserImpl(getService(ChannelParser.class)));
        put(EventParser.class, new EventParserImpl(getService(StringParser.class)));
        put(pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser.class,
                new ChannelValueParserImpl(getService(ChannelValueParser.class)));
        put(LocationPackParser.class, new LocationPackParserImpl(getService(LocationParser.class)));
        put(RegisterClientResultParser.class, new RegisterClientResultParserImpl());
        put(ChannelGroupRelationParser.class, new ChannelGroupRelationParserImpl());
        put(RegisterClientResultBParser.class, new RegisterClientResultBParserImpl());
        put(ChannelGroupRelationPackParser.class,
                new ChannelGroupRelationPackParserImpl(getService(ChannelGroupRelationParser.class)));
        put(ServerClientParser.class,
                new ServerClientParserImpl(
                                                  getService(LocationParser.class),
                                                  getService(ChannelPackParser.class),
                                                  getService(EventParser.class),
                                                  getService(pl.grzeslowski.jsupla.protocoljava.api.parsers.sc
                                                                     .ChannelValueParser.class),
                                                  getService(ChannelParser.class),
                                                  getService(LocationPackParser.class),
                        getService(RegisterClientResultParser.class),
                        getService(ChannelGroupRelationParser.class),
                        getService(RegisterClientResultBParser.class),
                        getService(ChannelGroupRelationPackParser.class)));

        // ServerDeviceParser
        put(pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser.class,
                new ChannelNewValueParserImpl(
                                                     getService(ChannelTypeDecoder.class),
                        getService(DeviceChannelValueParser.TypeMapper.class)));
        put(FirmwareUpdateUrlResultParser.class,
                new FirmwareUpdateUrlResultParserImpl(getService(FirmwareUpdateUrlParser.class)));
        put(RegisterDeviceResultParser.class, new RegisterDeviceResultParserImpl());
        put(ServerDeviceParser.class,
                new ServerDeviceParserImpl(
                                                  getService(pl.grzeslowski.jsupla.protocoljava.api.parsers.sd
                                                                     .ChannelNewValueParser.class),
                                                  getService(FirmwareUpdateUrlResultParser.class),
                                                  getService(RegisterDeviceResultParser.class)
                ));

        // ServerDeviceClientParser
        put(VersionErrorParser.class, new VersionErrorParserImpl());
        put(GetVersionResultParser.class, new GetVersionResultParserImpl(getService(StringParser.class)));
        put(SetActivityTimeoutResultParser.class, new SetActivityTimeoutResultParserImpl());
        put(PingServerResultClientParser.class, new PingServerResultClientParserImpl(getService(TimevalParser.class)));
        put(ServerDeviceClientParser.class,
                new ServerDeviceClientParserImpl(
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
