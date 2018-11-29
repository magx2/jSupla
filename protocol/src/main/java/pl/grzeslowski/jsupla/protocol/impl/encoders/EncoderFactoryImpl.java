package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaChannelNewValueBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaNewValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaRegisterClientBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaRegisterClientCEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaRegisterClientEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.dcs.SuplaPingServerEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.dcs.SuplaSetActivityTimeoutEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.FirmwareUpdateParamsEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaChannelNewValueResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceCEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceDEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupRelationEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelGroupRelationPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelPackBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaEventEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaLocationEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaLocationPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaRegisterClientResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.FirmwareUpdateUrlEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.FirmwareUpdateUrlResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.SuplaRegisterDeviceResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaGetVersionResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaPingServerResultClientEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaSetActivityTimeoutResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaVersionErrorEncoder;
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
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaEventEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaLocationEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaLocationPackEncoderImpl;
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

    // cs
    private final SuplaChannelNewValueBEncoder suplaChannelNewValueBEncoder;
    private final pl.grzeslowski.jsupla.protocol.api.encoders.sd.SuplaChannelNewValueEncoder
            suplaChannelNewValueEncoder;
    private final SuplaRegisterClientBEncoder suplaRegisterClientBEncoder;
    private final SuplaRegisterClientEncoder suplaRegisterClientEncoder;
    private final SuplaNewValueEncoder suplaNewValueEncoder;
    private final SuplaRegisterClientCEncoder suplaRegisterClientCEncoder;

    // dcs
    private final SuplaPingServerEncoder suplaPingServerEncoder;
    private final SuplaSetActivityTimeoutEncoder suplaSetActivityTimeoutEncoder;

    // ds
    private final FirmwareUpdateParamsEncoder firmwareUpdateParamsEncoder;
    private final SuplaChannelNewValueResultEncoder suplaChannelNewValueResultEncoder;
    private final SuplaDeviceChannelBEncoder suplaDeviceChannelBEncoder;
    private final SuplaDeviceChannelEncoder suplaDeviceChannelEncoder;
    private final SuplaDeviceChannelValueEncoder suplaDeviceChannelValueEncoder;
    private final SuplaRegisterDeviceBEncoder suplaRegisterDeviceBEncoder;
    private final SuplaRegisterDeviceCEncoder suplaRegisterDeviceCEncoder;
    private final SuplaRegisterDeviceEncoder suplaRegisterDeviceEncoder;
    private final SuplaRegisterDeviceDEncoder suplaRegisterDeviceDEncoder;

    // sc
    private final SuplaChannelEncoder suplaChannelEncoder;
    private final SuplaChannelPackEncoder suplaChannelPackEncoder;
    private final pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelValueEncoder suplaChannelValueEncoderSc;
    private final SuplaEventEncoder suplaEventEncoder;
    private final SuplaLocationEncoder suplaLocationEncoder;
    private final SuplaLocationPackEncoder suplaLocationPackEncoder;
    private final SuplaRegisterClientResultEncoder suplaRegisterClientResultEncoder;
    private final SuplaChannelBEncoder suplaChannelBEncoder;
    private final SuplaChannelGroupEncoder suplaChannelGroupEncoder;
    private final SuplaChannelGroupRelationEncoder suplaChannelGroupRelationEncoder;
    private final SuplaChannelGroupRelationPackEncoder suplaChannelGroupRelationPackEncoder;
    private final SuplaChannelPackBEncoder suplaChannelPackBEncoder;

    // sd
    private final FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder;
    private final FirmwareUpdateUrlResultEncoder firmwareUpdateUrlResultEncoder;
    private final pl.grzeslowski.jsupla.protocol.api.encoders.sd.SuplaChannelNewValueEncoder
            suplaChannelNewValueEncoderSd;
    private final SuplaRegisterDeviceResultEncoder suplaRegisterDeviceResultEncoder;

    // sdc
    private final SuplaGetVersionResultEncoder suplaGetVersionResultEncoder;
    private final SuplaPingServerResultClientEncoder suplaPingServerResultClientEncoder;
    private final SuplaSetActivityTimeoutResultEncoder suplaSetActivityTimeoutResultEncoder;
    private final SuplaVersionErrorEncoder suplaVersionErrorEncoder;

    // common
    private final SuplaChannelValueEncoder suplaChannelValueEncoder;
    private final SuplaDataPacketEncoder suplaDataPacketEncoder;
    private final TimevalEncoder timevalEncoder;

    public EncoderFactoryImpl(final PrimitiveEncoder primitiveEncoder) {

        // common
        suplaChannelValueEncoder = new SuplaChannelValueEncoderImpl(primitiveEncoder);
        suplaDataPacketEncoder = new SuplaDataPacketEncoderImpl(primitiveEncoder);
        timevalEncoder = new TimevalEncoderImpl(primitiveEncoder);

        // cs
        suplaChannelNewValueBEncoder = new SuplaChannelNewValueBEncoderImpl(primitiveEncoder);
        suplaChannelNewValueEncoder = new SuplaChannelNewValueEncoderImpl(primitiveEncoder);
        suplaRegisterClientBEncoder = new SuplaRegisterClientBEncoderImpl(primitiveEncoder);
        suplaRegisterClientEncoder = new SuplaRegisterClientEncoderImpl(primitiveEncoder);
        suplaNewValueEncoder = new SuplaNewValueEncoderImpl(primitiveEncoder);
        suplaRegisterClientCEncoder = new SuplaRegisterClientCEncoderImpl(primitiveEncoder);

        // dcs
        suplaPingServerEncoder = new SuplaPingServerEncoderImpl(timevalEncoder);
        suplaSetActivityTimeoutEncoder = new SuplaSetActivityTimeoutEncoderImpl(primitiveEncoder);

        // ds
        firmwareUpdateParamsEncoder = new FirmwareUpdateParamsEncoderImpl(primitiveEncoder);
        suplaChannelNewValueResultEncoder = new SuplaChannelNewValueResultEncoderImpl(primitiveEncoder);
        suplaDeviceChannelBEncoder = new SuplaDeviceChannelBEncoderImpl(primitiveEncoder);
        suplaDeviceChannelEncoder = new SuplaDeviceChannelEncoderImpl(primitiveEncoder);
        suplaDeviceChannelValueEncoder = new SuplaDeviceChannelValueEncoderImpl(primitiveEncoder);
        suplaRegisterDeviceBEncoder = new SuplaRegisterDeviceBEncoderImpl(primitiveEncoder, suplaDeviceChannelBEncoder);
        suplaRegisterDeviceCEncoder = new SuplaRegisterDeviceCEncoderImpl(primitiveEncoder, suplaDeviceChannelBEncoder);
        suplaRegisterDeviceEncoder = new SuplaRegisterDeviceEncoderImpl(primitiveEncoder, suplaDeviceChannelEncoder);
        suplaRegisterDeviceDEncoder = new SuplaRegisterDeviceDEncoderImpl(primitiveEncoder, suplaDeviceChannelBEncoder);

        // sc
        suplaChannelEncoder = new SuplaChannelEncoderImpl(primitiveEncoder, suplaChannelValueEncoder);
        suplaChannelPackEncoder = new SuplaChannelPackEncoderImpl(primitiveEncoder, suplaChannelEncoder);
        // @formatter:off
        suplaChannelValueEncoderSc = new pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelValueEncoderImpl(
                primitiveEncoder, suplaChannelValueEncoder);
        // @formatter:on
        suplaEventEncoder = new SuplaEventEncoderImpl(primitiveEncoder);
        suplaLocationEncoder = new SuplaLocationEncoderImpl(primitiveEncoder);
        suplaLocationPackEncoder = new SuplaLocationPackEncoderImpl(primitiveEncoder, suplaLocationEncoder);
        suplaRegisterClientResultEncoder = new SuplaRegisterClientResultEncoderImpl(primitiveEncoder);
        suplaChannelBEncoder = new SuplaChannelBEncoderImpl(primitiveEncoder, suplaChannelValueEncoder);
        suplaChannelGroupEncoder = new SuplaChannelGroupEncoderImpl(primitiveEncoder);
        suplaChannelGroupRelationEncoder = new SuplaChannelGroupRelationEncoderImpl(primitiveEncoder);
        suplaChannelGroupRelationPackEncoder = new SuplaChannelGroupRelationPackEncoderImpl(primitiveEncoder,
                suplaChannelGroupRelationEncoder);
        suplaChannelPackBEncoder = new SuplaChannelPackBEncoderImpl(primitiveEncoder, suplaChannelBEncoder);

        // sd
        firmwareUpdateUrlEncoder = new FirmwareUpdateUrlEncoderImpl(primitiveEncoder);
        firmwareUpdateUrlResultEncoder = new FirmwareUpdateUrlResultEncoderImpl(primitiveEncoder,
                firmwareUpdateUrlEncoder);
        suplaChannelNewValueEncoderSd =
                new pl.grzeslowski.jsupla.protocol.impl.encoders.sd.SuplaChannelNewValueEncoderImpl(primitiveEncoder);
        suplaRegisterDeviceResultEncoder = new SuplaRegisterDeviceResultEncoderImpl(primitiveEncoder);

        // sdc
        suplaGetVersionResultEncoder = new SuplaGetVersionResultEncoderImpl(primitiveEncoder);
        suplaPingServerResultClientEncoder = new SuplaPingServerResultClientEncoderImpl(timevalEncoder);
        suplaSetActivityTimeoutResultEncoder = new SuplaSetActivityTimeoutResultEncoderImpl(primitiveEncoder);
        suplaVersionErrorEncoder = new SuplaVersionErrorEncoderImpl(primitiveEncoder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final Class<T> proto) {

        // cs
        if (SuplaChannelNewValueB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelNewValueBEncoder;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelNewValueEncoder;
        }
        if (SuplaRegisterClientB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterClientBEncoder;
        }
        if (SuplaRegisterClient.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterClientEncoder;
        }
        if (SuplaNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaNewValueEncoder;
        }
        if (SuplaRegisterClientC.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterClientCEncoder;
        }

        // dcs
        if (SuplaPingServer.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaPingServerEncoder;
        }
        if (SuplaSetActivityTimeout.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaSetActivityTimeoutEncoder;
        }

        // ds
        if (SuplaFirmwareUpdateParams.class.isAssignableFrom(proto)) {
            return (Encoder<T>) firmwareUpdateParamsEncoder;
        }
        if (SuplaChannelNewValueResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelNewValueResultEncoder;
        }
        if (SuplaDeviceChannelB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaDeviceChannelBEncoder;
        }
        if (SuplaDeviceChannel.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaDeviceChannelEncoder;
        }
        if (SuplaDeviceChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaDeviceChannelValueEncoder;
        }
        if (SuplaRegisterDeviceB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterDeviceBEncoder;
        }
        if (SuplaRegisterDeviceC.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterDeviceCEncoder;
        }
        if (SuplaRegisterDevice.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterDeviceEncoder;
        }
        if (SuplaRegisterDeviceD.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterDeviceDEncoder;
        }

        // sc
        if (SuplaChannel.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelEncoder;
        }
        if (SuplaChannelPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelPackEncoder;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelValueEncoderSc;
        }
        if (SuplaEvent.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaEventEncoder;
        }
        if (SuplaLocation.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaLocationEncoder;
        }
        if (SuplaLocationPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaLocationPackEncoder;
        }
        if (SuplaRegisterClientResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterClientResultEncoder;
        }
        if (SuplaChannelB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelBEncoder;
        }
        if (SuplaChannelGroup.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelGroupEncoder;
        }
        if (SuplaChannelGroupRelation.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelGroupRelationEncoder;
        }
        if (SuplaChannelGroupRelationPack.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelGroupRelationPackEncoder;
        }
        if (SuplaChannelPackB.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelPackBEncoder;
        }

        // sd
        if (SuplaFirmwareUpdateUrl.class.isAssignableFrom(proto)) {
            return (Encoder<T>) firmwareUpdateUrlEncoder;
        }
        if (SuplaFirmwareUpdateUrlResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) firmwareUpdateUrlResultEncoder;
        }
        if (pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelNewValueEncoderSd;
        }
        if (SuplaRegisterDeviceResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaRegisterDeviceResultEncoder;
        }

        // sdc
        if (SuplaGetVersionResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaGetVersionResultEncoder;
        }
        if (SuplaPingServerResultClient.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaPingServerResultClientEncoder;
        }
        if (SuplaSetActivityTimeoutResult.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaSetActivityTimeoutResultEncoder;
        }
        if (SuplaVersionError.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaVersionErrorEncoder;
        }

        // common
        if (SuplaChannelValue.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaChannelValueEncoder;
        }
        if (SuplaDataPacket.class.isAssignableFrom(proto)) {
            return (Encoder<T>) suplaDataPacketEncoder;
        }
        if (SuplaTimeval.class.isAssignableFrom(proto)) {
            return (Encoder<T>) timevalEncoder;
        }

        throw new IllegalArgumentException(format("Can't find encoder for class %s", proto));
    }
}
