package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.*;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static java.lang.String.format;

public class EncoderFactory {
    public static final EncoderFactory INSTANCE = new EncoderFactory();


    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final Class<T> proto) {

        // cs
        if (SuplaChannelNewValueB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueBEncoder.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueEncoder.INSTANCE;
        }
        if (SuplaRegisterClientB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientBEncoder.INSTANCE;
        }
        if (SuplaRegisterClient.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientEncoder.INSTANCE;
        }
        if (SuplaNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaNewValueEncoder.INSTANCE;
        }
        if (SuplaRegisterClientC.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientCEncoder.INSTANCE;
        }

        // dcs
        if (SuplaPingServer.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaPingServerEncoder.INSTANCE;
        }
        if (SuplaSetActivityTimeout.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaSetActivityTimeoutEncoder.INSTANCE;
        }

        // ds
        if (SuplaFirmwareUpdateParams.class.isAssignableFrom(proto)) {
            return (Encoder<T>) FirmwareUpdateParamsEncoder.INSTANCE;
        }
        if (SuplaChannelNewValueResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueResultEncoder.INSTANCE;
        }
        if (SuplaDeviceChannelB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDeviceChannelBEncoder.INSTANCE;
        }
        if (SuplaDeviceChannel.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDeviceChannelEncoder.INSTANCE;
        }
        if (SuplaDeviceChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDeviceChannelValueEncoder.INSTANCE;
        }
        if (SuplaRegisterDeviceB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceBEncoder.INSTANCE;
        }
        if (SuplaRegisterDeviceC.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceCEncoder.INSTANCE;
        }
        if (SuplaRegisterDevice.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceEncoder.INSTANCE;
        }
        if (SuplaRegisterDeviceD.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceDEncoder.INSTANCE;
        }

        // sc
        if (SuplaChannel.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelEncoder.INSTANCE;
        }
        if (SuplaChannelPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelPackEncoder.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelBEncoder.INSTANCE;
        }
        if (SuplaEvent.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaEventEncoder.INSTANCE;
        }
        if (SuplaLocation.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaLocationEncoder.INSTANCE;
        }
        if (SuplaLocationPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaLocationPackEncoder.INSTANCE;
        }
        if (SuplaRegisterClientResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientResultEncoder.INSTANCE;
        }
        if (SuplaChannelB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelBEncoder.INSTANCE;
        }
        if (SuplaChannelGroup.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelGroupEncoder.INSTANCE;
        }
        if (SuplaChannelGroupRelation.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelGroupRelationEncoder.INSTANCE;
        }
        if (SuplaChannelGroupRelationPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelGroupRelationPackEncoder.INSTANCE;
        }
        if (SuplaChannelPackB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelPackBEncoder.INSTANCE;
        }
        if (SuplaChannelValuePack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelValuePackEncoder.INSTANCE;
        }
        if (SuplaRegisterClientResultB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientResultBEncoder.INSTANCE;
        }

        // sd
        if (SuplaFirmwareUpdateUrl.class.isAssignableFrom(proto)) {
            return (Encoder<T>) FirmwareUpdateUrlEncoder.INSTANCE;
        }
        if (SuplaFirmwareUpdateUrlResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) FirmwareUpdateUrlResultEncoder.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueEncoder.INSTANCE;
        }
        if (SuplaRegisterDeviceResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceResultEncoder.INSTANCE;
        }

        // sdc
        if (SuplaGetVersionResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaGetVersionResultEncoder.INSTANCE;
        }
        if (SuplaPingServerResultClient.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaPingServerResultClientEncoder.INSTANCE;
        }
        if (SuplaSetActivityTimeoutResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaSetActivityTimeoutResultEncoder.INSTANCE;
        }
        if (SuplaVersionError.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaVersionErrorEncoder.INSTANCE;
        }

        // common
        if (SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelValueEncoder.INSTANCE;
        }
        if (SuplaDataPacket.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDataPacketEncoder.INSTANCE;
        }
        if (SuplaTimeval.class.isAssignableFrom(proto)) {
            return (Encoder<T>) TimevalEncoder.INSTANCE;
        }

        throw new IllegalArgumentException(format("Can't find encoder for class %s", proto));
    }
}
