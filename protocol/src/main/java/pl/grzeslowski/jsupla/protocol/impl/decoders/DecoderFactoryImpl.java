package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaDataPacketDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaPingServerDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaSetActivityTimeoutDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.FirmwareUpdateParamsDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaChannelNewValueResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceCDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaRegisterDeviceDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelPackDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaEventDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationPackDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaRegisterClientResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaRegisterDeviceResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaGetVersionResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaPingServerResultClientDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaSetActivityTimeoutResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaVersionErrorDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.dcs.SuplaPingServerDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.dcs.SuplaSetActivityTimeoutDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.FirmwareUpdateParamsDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaChannelNewValueResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaDeviceChannelValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceCDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaEventDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaLocationDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaLocationPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaRegisterClientResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.FirmwareUpdateUrlDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.FirmwareUpdateUrlResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaRegisterDeviceResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaGetVersionResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaPingServerResultClientDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaSetActivityTimeoutResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaVersionErrorDecoderImpl;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_CHANNEL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_CHANNEL_SET_VALUE_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_C;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELPACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_EVENT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_LOCATIONPACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_LOCATION_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_CHANNEL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_REGISTER_DEVICE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_GETVERSION_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_VERSIONERROR;

public final class DecoderFactoryImpl implements DecoderFactory {

    // cs
    private final SuplaChannelNewValueBDecoder suplaChannelNewValueBDecoder;
    private final SuplaChannelNewValueDecoder suplaChannelNewValueDecoder;
    private final SuplaRegisterClientBDecoder suplaRegisterClientBDecoder;
    private final SuplaRegisterClientDecoder suplaRegisterClientDecoder;

    // dcs
    private final SuplaPingServerDecoder suplaPingServerDecoder;
    private final SuplaSetActivityTimeoutDecoder suplaSetActivityTimeoutDecoder;

    // ds
    private final FirmwareUpdateParamsDecoder firmwareUpdateParamsDecoder;
    private final SuplaChannelNewValueResultDecoder suplaChannelNewValueResultDecoder;
    private final SuplaDeviceChannelBDecoder suplaDeviceChannelBDecoder;
    private final SuplaDeviceChannelDecoder suplaDeviceChannelDecoder;
    private final SuplaDeviceChannelValueDecoder suplaDeviceChannelValueDecoder;
    private final SuplaRegisterDeviceBDecoder suplaRegisterDeviceBDecoder;
    private final SuplaRegisterDeviceCDecoder suplaRegisterDeviceCDecoder;
    private final SuplaRegisterDeviceDecoder suplaRegisterDeviceDecoder;

    // sc
    private final SuplaChannelDecoder suplaChannelDecoder;
    private final SuplaChannelPackDecoder suplaChannelPackDecoder;
    private final pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelValueDecoder suplaChannelValueDecoderSc;
    private final SuplaEventDecoder suplaEventDecoder;
    private final SuplaLocationDecoder suplaLocationDecoder;
    private final SuplaLocationPackDecoder suplaLocationPackDecoder;
    private final SuplaRegisterClientResultDecoder suplaRegisterClientResultDecoder;

    // sd
    private final FirmwareUpdateUrlDecoder firmwareUpdateUrlDecoder;
    private final FirmwareUpdateUrlResultDecoder firmwareUpdateUrlResultDecoder;
    private final pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaChannelNewValueDecoder
            suplaChannelNewValueDecoderSd;
    private final SuplaRegisterDeviceResultDecoder suplaRegisterDeviceResultDecoder;

    // sdc
    private final SuplaGetVersionResultDecoder suplaGetVersionResultDecoder;
    private final SuplaPingServerResultClientDecoder suplaPingServerResultClientDecoder;
    private final SuplaSetActivityTimeoutResultDecoder suplaSetActivityTimeoutResultDecoder;
    private final SuplaVersionErrorDecoder suplaVersionErrorDecoder;

    // common
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;
    private final SuplaDataPacketDecoder suplaDataPacketDecoder;
    private final TimevalDecoder timevalDecoder;

    public DecoderFactoryImpl(final PrimitiveDecoder primitiveDecoder) {

        // common
        suplaChannelValueDecoder = new SuplaChannelValueDecoderImpl();
        suplaDataPacketDecoder = new SuplaDataPacketDecoderImpl(primitiveDecoder);
        timevalDecoder = new TimevalDecoderImpl(primitiveDecoder);

        // cs
        suplaChannelNewValueBDecoder = new SuplaChannelNewValueBDecoderImpl(primitiveDecoder);
        suplaChannelNewValueDecoder = new SuplaChannelNewValueDecoderImpl(primitiveDecoder);
        suplaRegisterClientBDecoder = new SuplaRegisterClientBDecoderImpl(primitiveDecoder);
        suplaRegisterClientDecoder = new SuplaRegisterClientDecoderImpl(primitiveDecoder);

        // dcs
        suplaPingServerDecoder = new SuplaPingServerDecoderImpl(timevalDecoder);
        suplaSetActivityTimeoutDecoder = new SuplaSetActivityTimeoutDecoderImpl(primitiveDecoder);

        // ds
        firmwareUpdateParamsDecoder = new FirmwareUpdateParamsDecoderImpl(primitiveDecoder);
        suplaChannelNewValueResultDecoder = new SuplaChannelNewValueResultDecoderImpl(primitiveDecoder);
        suplaDeviceChannelBDecoder = new SuplaDeviceChannelBDecoderImpl(primitiveDecoder);
        suplaDeviceChannelDecoder = new SuplaDeviceChannelDecoderImpl(primitiveDecoder);
        suplaDeviceChannelValueDecoder = new SuplaDeviceChannelValueDecoderImpl(primitiveDecoder);
        suplaRegisterDeviceBDecoder = new SuplaRegisterDeviceBDecoderImpl(primitiveDecoder, suplaDeviceChannelBDecoder);
        suplaRegisterDeviceCDecoder = new SuplaRegisterDeviceCDecoderImpl(primitiveDecoder, suplaDeviceChannelBDecoder);
        suplaRegisterDeviceDecoder = new SuplaRegisterDeviceDecoderImpl(primitiveDecoder, suplaDeviceChannelDecoder);

        // sc
        suplaChannelDecoder = new SuplaChannelDecoderImpl(suplaChannelValueDecoder);
        suplaChannelPackDecoder = new SuplaChannelPackDecoderImpl(primitiveDecoder, suplaChannelDecoder);
        // @formatter:off
        suplaChannelValueDecoderSc = new pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl(
                suplaChannelValueDecoder);
        // @formatter:on
        suplaEventDecoder = new SuplaEventDecoderImpl(primitiveDecoder);
        suplaLocationDecoder = new SuplaLocationDecoderImpl();
        suplaLocationPackDecoder = new SuplaLocationPackDecoderImpl(primitiveDecoder, suplaLocationDecoder);
        suplaRegisterClientResultDecoder = new SuplaRegisterClientResultDecoderImpl();

        // sd
        firmwareUpdateUrlDecoder = new FirmwareUpdateUrlDecoderImpl(primitiveDecoder);
        firmwareUpdateUrlResultDecoder = new FirmwareUpdateUrlResultDecoderImpl(primitiveDecoder,
                                                                                       firmwareUpdateUrlDecoder);
        suplaChannelNewValueDecoderSd =
                new pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl(primitiveDecoder);
        suplaRegisterDeviceResultDecoder = new SuplaRegisterDeviceResultDecoderImpl(primitiveDecoder);

        // sdc
        suplaGetVersionResultDecoder = new SuplaGetVersionResultDecoderImpl(primitiveDecoder);
        suplaPingServerResultClientDecoder = new SuplaPingServerResultClientDecoderImpl(timevalDecoder);
        suplaSetActivityTimeoutResultDecoder = new SuplaSetActivityTimeoutResultDecoderImpl(primitiveDecoder);
        suplaVersionErrorDecoder = new SuplaVersionErrorDecoderImpl(primitiveDecoder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> Decoder<T> getDecoder(final T proto) {

        // cs
        if (proto instanceof SuplaChannelNewValueB) {
            return (Decoder<T>) suplaChannelNewValueBDecoder;
        }
        if (proto instanceof SuplaChannelNewValue) {
            return (Decoder<T>) suplaChannelNewValueDecoder;
        }
        if (proto instanceof SuplaRegisterClientB) {
            return (Decoder<T>) suplaRegisterClientBDecoder;
        }
        if (proto instanceof SuplaRegisterClient) {
            return (Decoder<T>) suplaRegisterClientDecoder;
        }

        // dcs
        if (proto instanceof SuplaPingServer) {
            return (Decoder<T>) suplaPingServerDecoder;
        }
        if (proto instanceof SuplaSetActivityTimeout) {
            return (Decoder<T>) suplaSetActivityTimeoutDecoder;
        }

        // ds
        if (proto instanceof SuplaFirmwareUpdateParams) {
            return (Decoder<T>) firmwareUpdateParamsDecoder;
        }
        if (proto instanceof SuplaChannelNewValueResult) {
            return (Decoder<T>) suplaChannelNewValueResultDecoder;
        }
        if (proto instanceof SuplaDeviceChannelB) {
            return (Decoder<T>) suplaDeviceChannelBDecoder;
        }
        if (proto instanceof SuplaDeviceChannel) {
            return (Decoder<T>) suplaDeviceChannelDecoder;
        }
        if (proto instanceof SuplaDeviceChannelValue) {
            return (Decoder<T>) suplaDeviceChannelValueDecoder;
        }
        if (proto instanceof SuplaRegisterDeviceB) {
            return (Decoder<T>) suplaRegisterDeviceBDecoder;
        }
        if (proto instanceof SuplaRegisterDeviceC) {
            return (Decoder<T>) suplaRegisterDeviceCDecoder;
        }
        if (proto instanceof SuplaRegisterDevice) {
            return (Decoder<T>) suplaRegisterDeviceDecoder;
        }

        // sc
        if (proto instanceof SuplaChannel) {
            return (Decoder<T>) suplaChannelDecoder;
        }
        if (proto instanceof SuplaChannelPack) {
            return (Decoder<T>) suplaChannelPackDecoder;
        }
        if (proto instanceof pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue) {
            return (Decoder<T>) suplaChannelValueDecoderSc;
        }
        if (proto instanceof SuplaEvent) {
            return (Decoder<T>) suplaEventDecoder;
        }
        if (proto instanceof SuplaLocation) {
            return (Decoder<T>) suplaLocationDecoder;
        }
        if (proto instanceof SuplaLocationPack) {
            return (Decoder<T>) suplaLocationPackDecoder;
        }
        if (proto instanceof SuplaRegisterClientResult) {
            return (Decoder<T>) suplaRegisterClientResultDecoder;
        }

        // sd
        if (proto instanceof SuplaFirmwareUpdateUrl) {
            return (Decoder<T>) firmwareUpdateUrlDecoder;
        }
        if (proto instanceof SuplaFirmwareUpdateUrlResult) {
            return (Decoder<T>) firmwareUpdateUrlResultDecoder;
        }
        if (proto instanceof pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue) {
            return (Decoder<T>) suplaChannelNewValueDecoderSd;
        }
        if (proto instanceof SuplaRegisterDeviceResult) {
            return (Decoder<T>) suplaRegisterDeviceResultDecoder;
        }

        // sdc
        if (proto instanceof SuplaGetVersionResult) {
            return (Decoder<T>) suplaGetVersionResultDecoder;
        }
        if (proto instanceof SuplaPingServerResultClient) {
            return (Decoder<T>) suplaPingServerResultClientDecoder;
        }
        if (proto instanceof SuplaSetActivityTimeoutResult) {
            return (Decoder<T>) suplaSetActivityTimeoutResultDecoder;
        }
        if (proto instanceof SuplaVersionError) {
            return (Decoder<T>) suplaVersionErrorDecoder;
        }

        // common
        if (proto instanceof SuplaChannelValue) {
            return (Decoder<T>) suplaChannelValueDecoder;
        }
        if (proto instanceof SuplaDataPacket) {
            return (Decoder<T>) suplaDataPacketDecoder;
        }
        if (proto instanceof SuplaTimeval) {
            return (Decoder<T>) timevalDecoder;
        }

        throw new IllegalArgumentException(format("Don't know decoder for %s", proto));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize & ProtoWithCallType> Decoder<T> getDecoder(final CallType callType) {
        requireNonNull(callType);

        // cs
        if (callType == SUPLA_CS_CALL_CHANNEL_SET_VALUE_B) {
            return (Decoder<T>) suplaChannelNewValueBDecoder;
        }
        if (callType == SUPLA_CS_CALL_CHANNEL_SET_VALUE) {
            return (Decoder<T>) suplaChannelNewValueDecoder;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT_B) {
            return (Decoder<T>) suplaRegisterClientBDecoder;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT) {
            return (Decoder<T>) suplaRegisterClientDecoder;
        }

        // dcs
        if (callType == SUPLA_DCS_CALL_PING_SERVER) {
            return (Decoder<T>) suplaPingServerDecoder;
        }
        if (callType == SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT) {
            return (Decoder<T>) suplaSetActivityTimeoutDecoder;
        }

        // ds
        if (callType == SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL) {
            return (Decoder<T>) firmwareUpdateParamsDecoder;
        }
        if (callType == SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED) {
            return (Decoder<T>) suplaChannelNewValueResultDecoder;
        }
        if (callType == SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT) {
            return (Decoder<T>) suplaDeviceChannelValueDecoder;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_B) {
            return (Decoder<T>) suplaRegisterDeviceBDecoder;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_C) {
            return (Decoder<T>) suplaRegisterDeviceCDecoder;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE) {
            return (Decoder<T>) suplaRegisterDeviceDecoder;
        }

        // sc
        if (callType == SUPLA_SC_CALL_CHANNEL_UPDATE) {
            return (Decoder<T>) suplaChannelDecoder;
        }
        if (callType == SUPLA_SC_CALL_CHANNELPACK_UPDATE) {
            return (Decoder<T>) suplaChannelPackDecoder;
        }
        if (callType == SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE) {
            return (Decoder<T>) suplaChannelValueDecoderSc;
        }
        if (callType == SUPLA_SC_CALL_EVENT) {
            return (Decoder<T>) suplaEventDecoder;
        }
        if (callType == SUPLA_SC_CALL_LOCATION_UPDATE) {
            return (Decoder<T>) suplaLocationDecoder;
        }
        if (callType == SUPLA_SC_CALL_LOCATIONPACK_UPDATE) {
            return (Decoder<T>) suplaLocationPackDecoder;
        }
        if (callType == SUPLA_SC_CALL_REGISTER_CLIENT_RESULT) {
            return (Decoder<T>) suplaRegisterClientResultDecoder;
        }

        // sd
        if (callType == SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT) {
            return (Decoder<T>) firmwareUpdateUrlResultDecoder;
        }
        if (callType == SUPLA_SD_CALL_CHANNEL_SET_VALUE) {
            return (Decoder<T>) suplaChannelNewValueDecoderSd;
        }
        if (callType == SUPLA_SD_CALL_REGISTER_DEVICE_RESULT) {
            return (Decoder<T>) suplaRegisterDeviceResultDecoder;
        }

        // sdc
        if (callType == SUPLA_SDC_CALL_GETVERSION_RESULT) {
            return (Decoder<T>) suplaGetVersionResultDecoder;
        }
        if (callType == SUPLA_SDC_CALL_PING_SERVER_RESULT) {
            return (Decoder<T>) suplaPingServerResultClientDecoder;
        }
        if (callType == SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT) {
            return (Decoder<T>) suplaSetActivityTimeoutResultDecoder;
        }
        if (callType == SUPLA_SDC_CALL_VERSIONERROR) {
            return (Decoder<T>) suplaVersionErrorDecoder;
        }

        throw new IllegalArgumentException(format("Don't know decoder for %s", callType));
    }
}
