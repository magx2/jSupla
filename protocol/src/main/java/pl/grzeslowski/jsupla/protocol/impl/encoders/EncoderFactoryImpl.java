package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
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
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaChannelNewValueBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaNewValueEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaRegisterClientBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaRegisterClientCEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaRegisterClientEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.dcs.SuplaPingServerEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.dcs.SuplaSetActivityTimeoutEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.FirmwareUpdateParamsEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaChannelNewValueResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaDeviceChannelBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaDeviceChannelEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaDeviceChannelValueEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaRegisterDeviceBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaRegisterDeviceCEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaRegisterDeviceDEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaRegisterDeviceEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelGroupEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelGroupRelationEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelGroupRelationPackEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelPackBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelPackEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelValuePackEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaEventEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaLocationEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaLocationPackEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaRegisterClientResultBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaRegisterClientResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.FirmwareUpdateUrlEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.FirmwareUpdateUrlResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.SuplaChannelNewValueEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.SuplaRegisterDeviceResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaGetVersionResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaPingServerResultClientEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaSetActivityTimeoutResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaVersionErrorEncoderImpl;

import static java.lang.String.format;

public class EncoderFactoryImpl implements EncoderFactory {
    public static final EncoderFactoryImpl INSTANCE = new EncoderFactoryImpl();

    EncoderFactoryImpl() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final Class<T> proto) {

        // cs
        if (SuplaChannelNewValueB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueBEncoderImpl.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientBEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterClient.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientEncoderImpl.INSTANCE;
        }
        if (SuplaNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaNewValueEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientC.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientCEncoderImpl.INSTANCE;
        }

        // dcs
        if (SuplaPingServer.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaPingServerEncoderImpl.INSTANCE;
        }
        if (SuplaSetActivityTimeout.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaSetActivityTimeoutEncoderImpl.INSTANCE;
        }

        // ds
        if (SuplaFirmwareUpdateParams.class.isAssignableFrom(proto)) {
            return (Encoder<T>) FirmwareUpdateParamsEncoderImpl.INSTANCE;
        }
        if (SuplaChannelNewValueResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueResultEncoderImpl.INSTANCE;
        }
        if (SuplaDeviceChannelB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDeviceChannelBEncoderImpl.INSTANCE;
        }
        if (SuplaDeviceChannel.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDeviceChannelEncoderImpl.INSTANCE;
        }
        if (SuplaDeviceChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDeviceChannelValueEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceBEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceC.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceCEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterDevice.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceD.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceDEncoderImpl.INSTANCE;
        }

        // sc
        if (SuplaChannel.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelEncoderImpl.INSTANCE;
        }
        if (SuplaChannelPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelPackEncoderImpl.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelBEncoderImpl.INSTANCE;
        }
        if (SuplaEvent.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaEventEncoderImpl.INSTANCE;
        }
        if (SuplaLocation.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaLocationEncoderImpl.INSTANCE;
        }
        if (SuplaLocationPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaLocationPackEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientResultEncoderImpl.INSTANCE;
        }
        if (SuplaChannelB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelBEncoderImpl.INSTANCE;
        }
        if (SuplaChannelGroup.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelGroupEncoderImpl.INSTANCE;
        }
        if (SuplaChannelGroupRelation.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelGroupRelationEncoderImpl.INSTANCE;
        }
        if (SuplaChannelGroupRelationPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelGroupRelationPackEncoderImpl.INSTANCE;
        }
        if (SuplaChannelPackB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelPackBEncoderImpl.INSTANCE;
        }
        if (SuplaChannelValuePack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelValuePackEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterClientResultB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterClientResultBEncoderImpl.INSTANCE;
        }

        // sd
        if (SuplaFirmwareUpdateUrl.class.isAssignableFrom(proto)) {
            return (Encoder<T>) FirmwareUpdateUrlEncoderImpl.INSTANCE;
        }
        if (SuplaFirmwareUpdateUrlResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) FirmwareUpdateUrlResultEncoderImpl.INSTANCE;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelNewValueEncoderImpl.INSTANCE;
        }
        if (SuplaRegisterDeviceResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaRegisterDeviceResultEncoderImpl.INSTANCE;
        }

        // sdc
        if (SuplaGetVersionResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaGetVersionResultEncoderImpl.INSTANCE;
        }
        if (SuplaPingServerResultClient.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaPingServerResultClientEncoderImpl.INSTANCE;
        }
        if (SuplaSetActivityTimeoutResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaSetActivityTimeoutResultEncoderImpl.INSTANCE;
        }
        if (SuplaVersionError.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaVersionErrorEncoderImpl.INSTANCE;
        }

        // common
        if (SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaChannelValueEncoderImpl.INSTANCE;
        }
        if (SuplaDataPacket.class.isAssignableFrom(proto)) {
            return (Encoder<T>) SuplaDataPacketEncoderImpl.INSTANCE;
        }
        if (SuplaTimeval.class.isAssignableFrom(proto)) {
            return (Encoder<T>) TimevalEncoderImpl.INSTANCE;
        }

        throw new IllegalArgumentException(format("Can't find encoder for class %s", proto));
    }
}
