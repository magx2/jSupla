package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.decoders.*;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaPingServerDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.dcs.SuplaSetActivityTimeoutDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.*;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.*;
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
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.*;
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
import pl.grzeslowski.jsupla.protocol.impl.decoders.ds.*;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.*;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.FirmwareUpdateUrlDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.FirmwareUpdateUrlResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaRegisterDeviceResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaGetVersionResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaPingServerResultClientDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaSetActivityTimeoutResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaVersionErrorDecoderImpl;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_SET_ACTIVITY_TIMEOUT;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.*;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.*;

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
    private final SuplaChannelGroupRelationDecoder suplaChannelGroupRelationDecoder;

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
        suplaChannelGroupRelationDecoder = new SuplaChannelGroupRelationDecoderImpl();

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
    public <T extends ProtoWithSize> Decoder<T> getDecoder(final Class<T> proto) {

        // cs
        if (SuplaChannelNewValueB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelNewValueBDecoder;
        }
        if (SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelNewValueDecoder;
        }
        if (SuplaRegisterClientB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterClientBDecoder;
        }
        if (SuplaRegisterClient.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterClientDecoder;
        }

        // dcs
        if (SuplaPingServer.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaPingServerDecoder;
        }
        if (SuplaSetActivityTimeout.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaSetActivityTimeoutDecoder;
        }

        // ds
        if (SuplaFirmwareUpdateParams.class.isAssignableFrom(proto)) {
            return (Decoder<T>) firmwareUpdateParamsDecoder;
        }
        if (SuplaChannelNewValueResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelNewValueResultDecoder;
        }
        if (SuplaDeviceChannelB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaDeviceChannelBDecoder;
        }
        if (SuplaDeviceChannel.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaDeviceChannelDecoder;
        }
        if (SuplaDeviceChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaDeviceChannelValueDecoder;
        }
        if (SuplaRegisterDeviceB.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterDeviceBDecoder;
        }
        if (SuplaRegisterDeviceC.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterDeviceCDecoder;
        }
        if (SuplaRegisterDevice.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterDeviceDecoder;
        }

        // sc
        if (SuplaChannel.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelDecoder;
        }
        if (SuplaChannelPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelPackDecoder;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelValueDecoderSc;
        }
        if (SuplaEvent.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaEventDecoder;
        }
        if (SuplaLocation.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaLocationDecoder;
        }
        if (SuplaLocationPack.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaLocationPackDecoder;
        }
        if (SuplaRegisterClientResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterClientResultDecoder;
        }
        if (SuplaChannelGroupRelation.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelGroupRelationDecoder;
        }

        // sd
        if (SuplaFirmwareUpdateUrl.class.isAssignableFrom(proto)) {
            return (Decoder<T>) firmwareUpdateUrlDecoder;
        }
        if (SuplaFirmwareUpdateUrlResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) firmwareUpdateUrlResultDecoder;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelNewValueDecoderSd;
        }
        if (SuplaRegisterDeviceResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaRegisterDeviceResultDecoder;
        }

        // sdc
        if (SuplaGetVersionResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaGetVersionResultDecoder;
        }
        if (SuplaPingServerResultClient.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaPingServerResultClientDecoder;
        }
        if (SuplaSetActivityTimeoutResult.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaSetActivityTimeoutResultDecoder;
        }
        if (SuplaVersionError.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaVersionErrorDecoder;
        }

        // common
        if (SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaChannelValueDecoder;
        }
        if (SuplaDataPacket.class.isAssignableFrom(proto)) {
            return (Decoder<T>) suplaDataPacketDecoder;
        }
        if (SuplaTimeval.class.isAssignableFrom(proto)) {
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
            return (Decoder<T>) suplaDeviceChannelValueDecoder;
        }
        if (callType == SUPLA_DS_CALL_CHANNEL_SET_VALUE_RESULT) {
            return (Decoder<T>) suplaChannelNewValueResultDecoder;
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
        if (callType == SUPLA_SC_CALL_CHANNELGROUP_RELATION_PACK_UPDATE) {
            return (Decoder<T>) suplaChannelGroupRelationDecoder;
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
