package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.*;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaPingServerDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaSetActivityTimeoutDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.*;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.*;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.FirmwareUpdateUrlResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaRegisterDeviceResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaGetVersionResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaPingServerResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaSetActivityTimeoutResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaVersionErrorDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.*;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.*;

public final class DecoderFactory {
    public static final DecoderFactory INSTANCE = new DecoderFactory();

    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize> Decoder<T> getDecoder(final Class<T> proto) {

        // cs
        if (SuplaChannelNewValueB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelNewValueBDecoder.INSTANCE;
        }
        if (SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelNewValueDecoder.INSTANCE;
        }
        if (SuplaRegisterClientB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientBDecoder.INSTANCE;
        }
        if (SuplaRegisterClient.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientDecoder.INSTANCE;
        }
        if (SuplaNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaNewValueDecoder.INSTANCE;
        }
        if (SuplaRegisterClientC.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientCDecoder.INSTANCE;
        }

        // dcs
        if (SuplaPingServer.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaPingServerDecoder.INSTANCE;
        }
        if (SuplaSetActivityTimeout.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaSetActivityTimeoutDecoder.INSTANCE;
        }

        // ds
        if (FirmwareUpdateParams.class.isAssignableFrom(proto)) {
            return (Decoder<T>) FirmwareUpdateParamsDecoder.INSTANCE;
        }
        if (SuplaChannelNewValueResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelNewValueResultDecoder.INSTANCE;
        }
        if (SuplaDeviceChannelB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDeviceChannelBDecoder.INSTANCE;
        }
        if (SuplaDeviceChannel.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDeviceChannelDecoder.INSTANCE;
        }
        if (SuplaDeviceChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDeviceChannelValueDecoder.INSTANCE;
        }
        if (SuplaRegisterDeviceB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceBDecoder.INSTANCE;
        }
        if (SuplaRegisterDeviceC.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceCDecoder.INSTANCE;
        }
        if (SuplaRegisterDevice.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceDecoder.INSTANCE;
        }
        if (SuplaRegisterDeviceD.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceDDecoder.INSTANCE;
        }
        if (SuplaRegisterDeviceE.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceEDecoder.INSTANCE;
        }
        if (SuplaRegisterDeviceF.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceFDecoder.INSTANCE;
        }

        // sc
        if (SuplaChannel.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelDecoder.INSTANCE;
        }
        if (SuplaChannelPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelPackDecoder.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelValueDecoder.INSTANCE;
        }
        if (SuplaEvent.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaEventDecoder.INSTANCE;
        }
        if (SuplaLocation.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaLocationDecoder.INSTANCE;
        }
        if (SuplaLocationPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaLocationPackDecoder.INSTANCE;
        }
        if (SuplaRegisterClientResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientResultDecoder.INSTANCE;
        }
        if (SuplaChannelGroupRelation.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelGroupRelationDecoder.INSTANCE;
        }
        if (SuplaRegisterClientResultB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterClientResultBDecoder.INSTANCE;
        }
        if (SuplaChannelGroupRelationPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelGroupRelationPackDecoder.INSTANCE;
        }
        if (SuplaChannelB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelBDecoder.INSTANCE;
        }
        if (SuplaChannelGroup.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelGroupDecoder.INSTANCE;
        }
        if (SuplaChannelPackB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelPackBDecoder.INSTANCE;
        }
        if (SuplaChannelValuePack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelValuePackDecoder.INSTANCE;
        }

        // sd
        if (FirmwareUpdateUrl.class.isAssignableFrom(proto)) {
            return (Decoder<T>) FirmwareUpdateUrlDecoder.INSTANCE;
        }
        if (FirmwareUpdateUrlResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) FirmwareUpdateUrlResultDecoder.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>)
                pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaChannelNewValueDecoder.INSTANCE;
        }
        if (SuplaRegisterDeviceResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaRegisterDeviceResultDecoder.INSTANCE;
        }

        // sdc
        if (SuplaGetVersionResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaGetVersionResultDecoder.INSTANCE;
        }
        if (SuplaPingServerResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaPingServerResultDecoder.INSTANCE;
        }
        if (SuplaSetActivityTimeoutResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaSetActivityTimeoutResultDecoder.INSTANCE;
        }
        if (SuplaVersionError.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaVersionErrorDecoder.INSTANCE;
        }

        // common
        if (SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaChannelValueDecoder.INSTANCE;
        }
        if (SuplaDataPacket.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaDataPacketDecoder.INSTANCE;
        }
        if (SuplaTimeval.class.isAssignableFrom(proto)) {
            return (Decoder<T>) SuplaTimevalDecoder.INSTANCE;
        }

        throw new IllegalArgumentException(format("Don't know decoder for %s", proto));
    }

    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize & ProtoWithCallType> Decoder<T> getDecoder(final CallType callType) {
        requireNonNull(callType);

        // cs
        if (callType == SUPLA_CS_CALL_CHANNEL_SET_VALUE_B) {
            return (Decoder<T>) SuplaChannelNewValueBDecoder.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_CHANNEL_SET_VALUE) {
            return (Decoder<T>) SuplaChannelNewValueDecoder.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT_B) {
            return (Decoder<T>) SuplaRegisterClientBDecoder.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT) {
            return (Decoder<T>) SuplaRegisterClientDecoder.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_SET_VALUE) {
            return (Decoder<T>) SuplaNewValueDecoder.INSTANCE;
        }
        if (callType == SUPLA_CS_CALL_REGISTER_CLIENT_C) {
            return (Decoder<T>) SuplaRegisterClientCDecoder.INSTANCE;
        }

        // dcs
        if (callType == SUPLA_DCS_CALL_PING_SERVER) {
            return (Decoder<T>) SuplaPingServerDecoder.INSTANCE;
        }
        if (callType == SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT) {
            return (Decoder<T>) SuplaSetActivityTimeoutDecoder.INSTANCE;
        }

        // ds
        if (callType == SUPLA_DS_CALL_GET_FIRMWARE_UPDATE_URL) {
            return (Decoder<T>) FirmwareUpdateParamsDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED) {
            return (Decoder<T>) SuplaDeviceChannelValueDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT) {
            return (Decoder<T>) SuplaChannelNewValueResultDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE) {
            return (Decoder<T>) SuplaRegisterDeviceDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_B) {
            return (Decoder<T>) SuplaRegisterDeviceBDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_C) {
            return (Decoder<T>) SuplaRegisterDeviceCDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_D) {
            return (Decoder<T>) SuplaRegisterDeviceDDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_E) {
            return (Decoder<T>) SuplaRegisterDeviceEDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_REGISTER_DEVICE_F) {
            return (Decoder<T>) SuplaRegisterDeviceFDecoder.INSTANCE;
        }
        if (callType == SUPLA_DS_CALL_DEVICE_CHANNEL_EXTENDEDVALUE_CHANGED) {
            return (Decoder<T>) SuplaDeviceChannelExtendedValueDecoder.INSTANCE;
        }

        // sc
//        if (callType == SUPLA_SC_CALL_CHANNEL_UPDATE) {
//            return (Decoder<T>) SuplaChannelDecoder.INSTANCE;
//        }
        if (callType == SUPLA_SC_CALL_CHANNELPACK_UPDATE) {
            return (Decoder<T>) SuplaChannelPackDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNEL_VALUE_UPDATE) {
            return (Decoder<T>) pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelValueDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_EVENT) {
            return (Decoder<T>) SuplaEventDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_LOCATION_UPDATE) {
            return (Decoder<T>) SuplaLocationDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_LOCATIONPACK_UPDATE) {
            return (Decoder<T>) SuplaLocationPackDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_REGISTER_CLIENT_RESULT) {
            return (Decoder<T>) SuplaRegisterClientResultDecoder.INSTANCE;
        }
        // TODO I think that SuplaChannelGroupRelation might be only send with SuplaChannelGroupRelationPack 
        //        if (callType == SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE) {
        //            return (Decoder<T>) SuplaChannelGroupRelationDecoder.INSTANCE;
        //        }
        if (callType == SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_B) {
            return (Decoder<T>) SuplaRegisterClientResultBDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE) {
            return (Decoder<T>) SuplaChannelGroupRelationPackDecoder.INSTANCE;
        }
//        if (callType == SUPLA_SC_CALL_CHANNEL_UPDATE_B) {
//            return (Decoder<T>) SuplaChannelBDecoder.INSTANCE;
//        }
        if (callType == SUPLA_SC_CALL_CHANNELGROUP_PACK_UPDATE) {
            return (Decoder<T>) SuplaChannelGroupDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELPACK_UPDATE_B) {
            return (Decoder<T>) SuplaChannelPackBDecoder.INSTANCE;
        }
        if (callType == SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE) {
            return (Decoder<T>) SuplaChannelValuePackDecoder.INSTANCE;
        }

        // sd
        if (callType == SUPLA_SD_CALL_GET_FIRMWARE_UPDATE_URL_RESULT) {
            return (Decoder<T>) FirmwareUpdateUrlResultDecoder.INSTANCE;
        }
        if (callType == SUPLA_SD_CALL_CHANNEL_SET_VALUE) {
            return (Decoder<T>)
                pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaChannelNewValueDecoder.INSTANCE;
        }
        if (callType == SUPLA_SD_CALL_REGISTER_DEVICE_RESULT) {
            return (Decoder<T>) SuplaRegisterDeviceResultDecoder.INSTANCE;
        }

        // sdc
        if (callType == SUPLA_SDC_CALL_GETVERSION_RESULT) {
            return (Decoder<T>) SuplaGetVersionResultDecoder.INSTANCE;
        }
        if (callType == SUPLA_SDC_CALL_PING_SERVER_RESULT) {
            return (Decoder<T>) SuplaPingServerResultDecoder.INSTANCE;
        }
        if (callType == SUPLA_SDC_CALL_SET_ACTIVITY_TIMEOUT_RESULT) {
            return (Decoder<T>) SuplaSetActivityTimeoutResultDecoder.INSTANCE;
        }
        if (callType == SUPLA_SDC_CALL_VERSIONERROR) {
            return (Decoder<T>) SuplaVersionErrorDecoder.INSTANCE;
        }

        throw new IllegalArgumentException(format("Don't know decoder for %s", callType));
    }
}
