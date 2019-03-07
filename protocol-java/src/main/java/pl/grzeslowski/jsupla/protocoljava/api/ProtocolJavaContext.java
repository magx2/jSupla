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
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ColorTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.RelayTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.StoppableOpenCloseEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ThermometerTypeChannelEncoder;
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
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.TimevalSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ChannelNewValueBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ChannelNewValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.RegisterClientBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.RegisterClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.DeviceClientServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.PingServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs.SetActivityTimeoutSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.ChannelNewValueResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceServerSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.FirmwareUpdateParamsSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceCSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.EventSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.LocationSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.RegisterClientResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ServerClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.FirmwareUpdateUrlSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.RegisterDeviceResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ServerDeviceSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.GetVersionResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.PingServerResultClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.ServerDeviceClientSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.SetActivityTimeoutResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc.VersionErrorSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ChannelTypeDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ColorTypeChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.RelayTypeChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.ThermometerTypeChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype.SuplaChannelValueToChannelTypeImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders.tochanneltype.SuplaDeviceChannelToChannelTypeImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.ChannelTypeEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.ColorTypeChannelEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.RelayTypeChannelEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.StoppableOpenCloseEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.ThermometerTypeChannelEncoderImpl;
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
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.ChannelNewValueResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.DeviceChannelBParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.DeviceChannelParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.DeviceChannelValueParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.DeviceServerParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.FirmwareUpdateParamsParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.RegisterDeviceBParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds.RegisterDeviceCParserImpl;
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
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.FirmwareUpdateUrlParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.FirmwareUpdateUrlResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.RegisterDeviceResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd.ServerDeviceParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.GetVersionResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.PingServerResultClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.ServerDeviceClientParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.SetActivityTimeoutResultParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.parsers.sdc.VersionErrorParserImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ChannelValueSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.SerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.TimevalSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ClientServerSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.RegisterClientSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.DeviceClientServerSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.PingServerSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.ChannelNewValueResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.DeviceChannelBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.DeviceChannelSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.DeviceChannelValueSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.DeviceServerSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.FirmwareUpdateParamsSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.RegisterDeviceBSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.RegisterDeviceCSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds.RegisterDeviceSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.ChannelPackSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.ChannelSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.EventSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.LocationPackSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.LocationSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.RegisterClientResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.ServerClientSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.FirmwareUpdateUrlResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.FirmwareUpdateUrlSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.RegisterDeviceResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.ServerDeviceSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc.GetVersionResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc.PingServerResultClientSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc.ServerDeviceClientSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc.SetActivityTimeoutResultSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.sdc.VersionErrorSerializerImpl;

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
        put(ColorTypeChannelEncoder.class, ColorTypeChannelEncoderImpl.INSTANCE);
        put(RelayTypeChannelEncoder.class, RelayTypeChannelEncoderImpl.INSTANCE);
        put(ThermometerTypeChannelEncoder.class, ThermometerTypeChannelEncoderImpl.INSTANCE);
        put(StoppableOpenCloseEncoder.class, StoppableOpenCloseEncoderImpl.INSTANCE);
        put(ChannelTypeEncoder.class, new ChannelTypeEncoderImpl(
                                                                        getService(ColorTypeChannelEncoder.class),
                                                                        getService(RelayTypeChannelEncoder.class),
                                                                        getService(ThermometerTypeChannelEncoder.class),
                                                                        getService(StoppableOpenCloseEncoder.class)
        ));
        put(DeviceChannelSerializer.class, DeviceChannelSerializerImpl.INSTANCE);
        put(DeviceChannelBSerializer.class, DeviceChannelBSerializerImpl.INSTANCE);
        put(FirmwareUpdateUrlSerializer.class, FirmwareUpdateUrlSerializerImpl.INSTANCE);
        put(ChannelValueSerializer.class, ChannelValueSerializerImpl.INSTANCE);
        put(TimevalSerializer.class, TimevalSerializerImpl.INSTANCE);

        // ClientServerSerializer
        put(ChannelNewValueSerializer.class, ChannelNewValueSerializerImpl.INSTANCE);
        put(ChannelNewValueBSerializer.class, ChannelNewValueBSerializerImpl.INSTANCE);
        put(RegisterClientSerializer.class, RegisterClientSerializerImpl.INSTANCE);
        put(RegisterClientBSerializer.class, RegisterClientBSerializerImpl.INSTANCE);
        put(ClientServerSerializer.class, ClientServerSerializerImpl.INSTANCE);

        // DeviceClientServerSerializer
        put(PingServerSerializer.class, PingServerSerializerImpl.INSTANCE);
        put(SetActivityTimeoutSerializer.class, SetActivityTimeoutSerializerImpl.INSTANCE);
        put(DeviceClientServerSerializer.class, DeviceClientServerSerializerImpl.INSTANCE);

        // DeviceServerSerializer
        put(ChannelNewValueResultSerializer.class, ChannelNewValueResultSerializerImpl.INSTANCE);
        put(RegisterDeviceSerializer.class, RegisterDeviceSerializerImpl.INSTANCE);
        put(RegisterDeviceBSerializer.class, RegisterDeviceBSerializerImpl.INSTANCE);
        put(RegisterDeviceCSerializer.class, RegisterDeviceCSerializerImpl.INSTANCE);
        put(DeviceChannelValueSerializer.class, DeviceChannelValueSerializerImpl.INSTANCE);
        put(FirmwareUpdateParamsSerializer.class, FirmwareUpdateParamsSerializerImpl.INSTANCE);
        put(DeviceServerSerializer.class, DeviceServerSerializerImpl.INSTANCE);

        // ServerClientSerializer
        put(ChannelSerializer.class, ChannelSerializerImpl.INSTANCE);
        put(ChannelPackSerializer.class, ChannelPackSerializerImpl.INSTANCE);
        put(pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelValueSerializer.class,
                pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc.ChannelValueSerializerImpl.INSTANCE);
        put(EventSerializer.class, EventSerializerImpl.INSTANCE);
        put(LocationSerializer.class, LocationSerializerImpl.INSTANCE);
        put(LocationPackSerializer.class, LocationPackSerializerImpl.INSTANCE);
        put(RegisterClientResultSerializer.class, RegisterClientResultSerializerImpl.INSTANCE);
        put(ServerClientSerializer.class, ServerClientSerializerImpl.INSTANCE);

        // ServerDeviceSerializer
        put(
                pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ChannelNewValueSerializer.class,
                pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd.ChannelNewValueSerializerImpl.INSTANCE);
        put(FirmwareUpdateUrlResultSerializer.class,
                FirmwareUpdateUrlResultSerializerImpl.INSTANCE);
        put(RegisterDeviceResultSerializer.class, RegisterDeviceResultSerializerImpl.INSTANCE);
        put(ServerDeviceSerializer.class, ServerDeviceSerializerImpl.INSTANCE);

        // ServerDeviceClientSerializer
        put(GetVersionResultSerializer.class, GetVersionResultSerializerImpl.INSTANCE);
        put(PingServerResultClientSerializer.class,
                PingServerResultClientSerializerImpl.INSTANCE);
        put(SetActivityTimeoutResultSerializer.class, SetActivityTimeoutResultSerializerImpl.INSTANCE);
        put(VersionErrorSerializer.class, VersionErrorSerializerImpl.INSTANCE);
        put(ServerDeviceClientSerializer.class,ServerDeviceClientSerializerImpl.INSTANCE);

        put(Serializer.class, SerializerImpl.INSTANCE);
    }

    @SuppressWarnings("unchecked")
    private void initParsers() {

        // commons
        put(StringParser.class, StringParserImpl.INSTANCE);
        put(ColorTypeChannelDecoder.class, ColorTypeChannelDecoderImpl.INSTANCE);
        put(RelayTypeChannelDecoder.class, RelayTypeChannelDecoderImpl.INSTANCE);
        put(ThermometerTypeChannelDecoder.class, ThermometerTypeChannelDecoderImpl.INSTANCE);
        put(ChannelTypeDecoder.class, new ChannelTypeDecoderImpl(
                                                                        getService(ColorTypeChannelDecoder.class),
                                                                        getService(RelayTypeChannelDecoder.class),
                                                                        getService(ThermometerTypeChannelDecoder.class)
        ));
        SuplaDeviceChannelToChannelTypeImpl suplaDeviceChannelToChannelType = new SuplaDeviceChannelToChannelTypeImpl();
        put(SuplaDeviceChannelToChannelType.class, suplaDeviceChannelToChannelType);
        put(SuplaDeviceChannelBToChannelType.class, suplaDeviceChannelToChannelType);
        put(SuplaChannelValueToChannelType.class, SuplaChannelValueToChannelTypeImpl.INSTANCE);
        put(DeviceChannelParser.class,
                new DeviceChannelParserImpl(
                                                   getService(ChannelTypeDecoder.class),
                                                   getService(SuplaDeviceChannelToChannelType.class)));
        put(DeviceChannelBParser.class,
                new DeviceChannelBParserImpl(
                                                    getService(ChannelTypeDecoder.class),
                                                    getService(SuplaDeviceChannelBToChannelType.class)));
        put(FirmwareUpdateUrlParser.class, FirmwareUpdateUrlParserImpl.INSTANCE);
        put(ChannelValueParser.class,
                new pl.grzeslowski.jsupla.protocoljava.impl.parsers
                            .ChannelValueParserImpl(getService(ChannelTypeDecoder.class),
                                                           getService(SuplaChannelValueToChannelType.class)));
        put(TimevalParser.class, TimevalParserImpl.INSTANCE);

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
        put(RegisterClientBParser.class, RegisterClientBParserImpl.INSTANCE);
        put(RegisterClientParser.class, RegisterClientParserImpl.INSTANCE);
        put(ClientServerParser.class, new ClientServerParserImpl(
                                                                        getService(ChannelNewValueBParser.class),
                                                                        getService(ChannelNewValueParser.class),
                                                                        getService(RegisterClientBParser.class),
                                                                        getService(RegisterClientParser.class)
        ));

        // DeviceClientServerParser
        put(PingServerParser.class, PingServerParserImpl.INSTANCE);
        put(SetActivityTimeoutParser.class, SetActivityTimeoutParserImpl.INSTANCE);
        put(DeviceClientServerParser.class,
                new DeviceClientServerParserImpl(
                                                        getService(PingServerParser.class),
                                                        getService(SetActivityTimeoutParser.class)
                ));

        // DeviceServerParser
        put(ChannelNewValueResultParser.class, ChannelNewValueResultParserImpl.INSTANCE);
        put(DeviceChannelValueParser.class,
                new DeviceChannelValueParserImpl(
                                                        getService(ChannelTypeDecoder.class),
                        getService(DeviceChannelValueParser.TypeMapper.class)));
        put(RegisterDeviceParser.class, new RegisterDeviceParserImpl(
                                                                            getService(StringParser.class),
                                                                            getService(DeviceChannelParser.class)));
        put(FirmwareUpdateParamsParser.class, FirmwareUpdateParamsParserImpl.INSTANCE);
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
        put(LocationParser.class, LocationParserImpl.INSTANCE);
        put(ChannelPackParser.class, ChannelPackParserImpl.INSTANCE);
        put(EventParser.class, EventParserImpl.INSTANCE);
        put(pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser.class,
                ChannelValueParserImpl.INSTANCE);
        put(LocationPackParser.class, LocationPackParserImpl.INSTANCE);
        put(RegisterClientResultParser.class, RegisterClientResultParserImpl.INSTANCE);
        put(ServerClientParser.class,
                new ServerClientParserImpl(
                                                  getService(LocationParser.class),
                                                  getService(ChannelPackParser.class),
                                                  getService(EventParser.class),
                                                  getService(pl.grzeslowski.jsupla.protocoljava.api.parsers.sc
                                                                     .ChannelValueParser.class),
                                                  getService(ChannelParser.class),
                                                  getService(LocationPackParser.class),
                                                  getService(RegisterClientResultParser.class)
                ));

        // ServerDeviceParser
        put(pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser.class,
                new ChannelNewValueParserImpl(
                                                     getService(ChannelTypeDecoder.class),
                        getService(DeviceChannelValueParser.TypeMapper.class)));
        put(FirmwareUpdateUrlResultParser.class,
                FirmwareUpdateUrlResultParserImpl.INSTANCE);
        put(RegisterDeviceResultParser.class, RegisterDeviceResultParserImpl.INSTANCE);
        put(ServerDeviceParser.class,
                new ServerDeviceParserImpl(
                                                  getService(pl.grzeslowski.jsupla.protocoljava.api.parsers.sd
                                                                     .ChannelNewValueParser.class),
                                                  getService(FirmwareUpdateUrlResultParser.class),
                                                  getService(RegisterDeviceResultParser.class)
                ));

        // ServerDeviceClientParser
        put(VersionErrorParser.class, VersionErrorParserImpl.INSTANCE);
        put(GetVersionResultParser.class, GetVersionResultParserImpl.INSTANCE);
        put(SetActivityTimeoutResultParser.class, SetActivityTimeoutResultParserImpl.INSTANCE);
        put(PingServerResultClientParser.class, PingServerResultClientParserImpl.INSTANCE);
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
            throw ServiceNotFoundException.INSTANCE);
        }
        return (T) service;
    }
}
