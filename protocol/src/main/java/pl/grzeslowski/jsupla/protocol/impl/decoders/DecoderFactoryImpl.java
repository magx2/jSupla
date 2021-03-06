package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
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
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
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
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaNewValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientCDecoderImpl;
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
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceDDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.SuplaRegisterDeviceDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelGroupDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelGroupRelationDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelGroupRelationPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelPackBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValuePackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaEventDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaLocationDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaLocationPackDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaRegisterClientResultBDecoderImpl;
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
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT_C;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_C;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_REGISTER_DEVICE_D;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELGROUP_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELPACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELPACK_UPDATE_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_UPDATE_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_EVENT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_LOCATIONPACK_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_LOCATION_UPDATE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_B;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_CHANNEL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_REGISTER_DEVICE_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_GETVERSION_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_VERSIONERROR;

public final class DecoderFactoryImpl implements DecoderFactory {
    public static final DecoderFactoryImpl INSTANCE = new DecoderFactoryImpl();

    private DecoderFactoryImpl() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> Decoder<T> getDecoder(final Class<T> proto) {

        // cs
        if (SuplaChannelNewValueB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelNewValueBDecoderImpl.INSTANCE;
        }
        if (SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelNewValueDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientBDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterClient.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientDecoderImpl.INSTANCE;
        }
        if (SuplaNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaNewValueDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientC.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientCDecoderImpl.INSTANCE;
        }

        // dcs
        if (SuplaPingServer.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaPingServerDecoderImpl.INSTANCE;
        }
        if (SuplaSetActivityTimeout.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaSetActivityTimeoutDecoderImpl.INSTANCE;
        }

        // ds
        if (SuplaFirmwareUpdateParams.class.isAssignableFrom(proto)) {
            return (Decoder<T>) FirmwareUpdateParamsDecoderImpl.INSTANCE;
        }
        if (SuplaChannelNewValueResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelNewValueResultDecoderImpl.INSTANCE;
        }
        if (SuplaDeviceChannelB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDeviceChannelBDecoderImpl.INSTANCE;
        }
        if (SuplaDeviceChannel.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDeviceChannelDecoderImpl.INSTANCE;
        }
        if (SuplaDeviceChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDeviceChannelValueDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceBDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceC.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceCDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterDevice.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceD.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceDDecoderImpl.INSTANCE;
        }

        // sc
        if (SuplaChannel.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelDecoderImpl.INSTANCE;
        }
        if (SuplaChannelPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelPackDecoderImpl.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl.INSTANCE;
        }
        if (SuplaEvent.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaEventDecoderImpl.INSTANCE;
        }
        if (SuplaLocation.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaLocationDecoderImpl.INSTANCE;
        }
        if (SuplaLocationPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaLocationPackDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientResultDecoderImpl.INSTANCE;
        }
        if (SuplaChannelGroupRelation.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelGroupRelationDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientResultB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientResultBDecoderImpl.INSTANCE;
        }
        if (SuplaChannelGroupRelationPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelGroupRelationPackDecoderImpl.INSTANCE;
        }
        if (SuplaChannelB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelBDecoderImpl.INSTANCE;
        }
        if (SuplaChannelGroup.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelGroupDecoderImpl.INSTANCE;
        }
        if (SuplaChannelPackB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelPackBDecoderImpl.INSTANCE;
        }
        if (SuplaChannelValuePack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelValuePackDecoderImpl.INSTANCE;
        }

        // sd
        if (SuplaFirmwareUpdateUrl.class.isAssignableFrom(proto)) {
            return (Decoder<T>) FirmwareUpdateUrlDecoderImpl.INSTANCE;
        }
        if (SuplaFirmwareUpdateUrlResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) FirmwareUpdateUrlResultDecoderImpl.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) 
                           pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceResultDecoderImpl.INSTANCE;
        }

        // sdc
        if (SuplaGetVersionResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaGetVersionResultDecoderImpl.INSTANCE;
        }
        if (SuplaPingServerResultClient.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaPingServerResultClientDecoderImpl.INSTANCE;
        }
        if (SuplaSetActivityTimeoutResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaSetActivityTimeoutResultDecoderImpl.INSTANCE;
        }
        if (SuplaVersionError.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaVersionErrorDecoderImpl.INSTANCE;
        }

        // common
        if (SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelValueDecoderImpl.INSTANCE;
        }
        if (SuplaDataPacket.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDataPacketDecoderImpl.INSTANCE;
        }
        if (SuplaTimeval.class.isAssignableFrom(proto)) {
            return (Decoder<T>) TimevalDecoderImpl.INSTANCE;
        }

        throw new IllegalArgumentException(format("Don't know decoder for %s", proto));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize & ProtoWithCallType> Decoder<T> getDecoder(final CallType callType) {
        requireNonNull(callType);

        // cs
        if (callType == SUPLA_CS_CALL_CHANNEL_SET_VALUE_B) {
            return (Decoder<T>) SuplaChannelNewValueBDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_CHANNEL_SET_VALUE) {
            return (Decoder<T>) SuplaChannelNewValueDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT_B) {
            return (Decoder<T>) SuplaRegisterClientBDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT) {
            return (Decoder<T>) SuplaRegisterClientDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_SET_VALUE) {
            return (Decoder<T>) SuplaNewValueDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT_C) {
            return (Decoder<T>) SuplaRegisterClientCDecoderImpl.INSTANCE;
        }

        // dcs
        if (callType == SUPLA_DCS_CALL_PING_SERVER) {
            return (Decoder<T>) SuplaPingServerDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT) {
            return (Decoder<T>) SuplaSetActivityTimeoutDecoderImpl.INSTANCE;
        }

        // ds
        if (callType == SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL) {
            return (Decoder<T>) FirmwareUpdateParamsDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED) {
            return (Decoder<T>) SuplaDeviceChannelValueDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT) {
            return (Decoder<T>) SuplaChannelNewValueResultDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_B) {
            return (Decoder<T>) SuplaRegisterDeviceBDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_C) {
            return (Decoder<T>) SuplaRegisterDeviceCDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE) {
            return (Decoder<T>) SuplaRegisterDeviceDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_D) {
            return (Decoder<T>) SuplaRegisterDeviceDDecoderImpl.INSTANCE;
        }

        // sc
        if (callType == SUPLA_SC_CALL_CHANNEL_UPDATE) {
            return (Decoder<T>) SuplaChannelDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELPACK_UPDATE) {
            return (Decoder<T>) SuplaChannelPackDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE) {
            return (Decoder<T>) pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_EVENT) {
            return (Decoder<T>) SuplaEventDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_LOCATION_UPDATE) {
            return (Decoder<T>) SuplaLocationDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_LOCATIONPACK_UPDATE) {
            return (Decoder<T>) SuplaLocationPackDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_REGISTER_CLIENT_RESULT) {
            return (Decoder<T>) SuplaRegisterClientResultDecoderImpl.INSTANCE;
        }
        // TODO I think that SuplaChannelGroupRelation might be only send with SuplaChannelGroupRelationPack 
        //        if (callType == SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE) {
        //            return (Decoder<T>) SuplaChannelGroupRelationDecoderImpl.INSTANCE;
        //        }
        if (callType == SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_B) {
            return (Decoder<T>) SuplaRegisterClientResultBDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE) {
            return (Decoder<T>) SuplaChannelGroupRelationPackDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNEL_UPDATE_B) {
            return (Decoder<T>) SuplaChannelBDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELGROUP_PACK_UPDATE) {
            return (Decoder<T>) SuplaChannelGroupDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELPACK_UPDATE_B) {
            return (Decoder<T>) SuplaChannelPackBDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE) {
            return (Decoder<T>) SuplaChannelValuePackDecoderImpl.INSTANCE;
        }

        // sd
        if (callType == SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT) {
            return (Decoder<T>) FirmwareUpdateUrlResultDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SD_CALL_CHANNEL_SET_VALUE) {
            return (Decoder<T>) 
                           pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SD_CALL_REGISTER_DEVICE_RESULT) {
            return (Decoder<T>) SuplaRegisterDeviceResultDecoderImpl.INSTANCE;
        }

        // sdc
        if (callType == SUPLA_SDC_CALL_GETVERSION_RESULT) {
            return (Decoder<T>) SuplaGetVersionResultDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SDC_CALL_PING_SERVER_RESULT) {
            return (Decoder<T>) SuplaPingServerResultClientDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT) {
            return (Decoder<T>) SuplaSetActivityTimeoutResultDecoderImpl.INSTANCE;
        }
        if (callType == SUPLA_SDC_CALL_VERSIONERROR) {
            return (Decoder<T>) SuplaVersionErrorDecoderImpl.INSTANCE;
        }

        throw new IllegalArgumentException(format("Don't know decoder for %s", callType));
    }
}
