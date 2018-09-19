package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.TimevalEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaChannelNewValueBEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.cs.SuplaRegisterClientBEncoder;
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
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaRegisterDeviceEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelEncoder;
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
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaChannelNewValueBEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.cs.SuplaRegisterClientBEncoderImpl;
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
import pl.grzeslowski.jsupla.protocol.impl.encoders.ds.SuplaRegisterDeviceEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sc.SuplaChannelEncoderImpl;
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
    private final pl.grzeslowski.jsupla.protocol.api.encoders.sd.SuplaChannelNewValueEncoder suplaChannelNewValueEncoder;
    private final SuplaRegisterClientBEncoder suplaRegisterClientBEncoder;
    private final SuplaRegisterClientEncoder suplaRegisterClientEncoder;

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

    // sc
    private final SuplaChannelEncoder suplaChannelEncoder;
    private final SuplaChannelPackEncoder suplaChannelPackEncoder;
    private final pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelValueEncoder suplaChannelValueEncoderSc;
    private final SuplaEventEncoder suplaEventEncoder;
    private final SuplaLocationEncoder suplaLocationEncoder;
    private final SuplaLocationPackEncoder suplaLocationPackEncoder;
    private final SuplaRegisterClientResultEncoder suplaRegisterClientResultEncoder;

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
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final T proto) {

        // cs
        if (proto instanceof SuplaChannelNewValueB) {
            return (Encoder<T>) suplaChannelNewValueBEncoder;
        }
        if (proto instanceof pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue) {
            return (Encoder<T>) suplaChannelNewValueEncoder;
        }
        if (proto instanceof SuplaRegisterClientB) {
            return (Encoder<T>) suplaRegisterClientBEncoder;
        }
        if (proto instanceof SuplaRegisterClient) {
            return (Encoder<T>) suplaRegisterClientEncoder;
        }

        // dcs
        if (proto instanceof SuplaPingServer) {
            return (Encoder<T>) suplaPingServerEncoder;
        }
        if (proto instanceof SuplaSetActivityTimeout) {
            return (Encoder<T>) suplaSetActivityTimeoutEncoder;
        }

        // ds
        if (proto instanceof SuplaFirmwareUpdateParams) {
            return (Encoder<T>) firmwareUpdateParamsEncoder;
        }
        if (proto instanceof SuplaChannelNewValueResult) {
            return (Encoder<T>) suplaChannelNewValueResultEncoder;
        }
        if (proto instanceof SuplaDeviceChannelB) {
            return (Encoder<T>) suplaDeviceChannelBEncoder;
        }
        if (proto instanceof SuplaDeviceChannel) {
            return (Encoder<T>) suplaDeviceChannelEncoder;
        }
        if (proto instanceof SuplaDeviceChannelValue) {
            return (Encoder<T>) suplaDeviceChannelValueEncoder;
        }
        if (proto instanceof SuplaRegisterDeviceB) {
            return (Encoder<T>) suplaRegisterDeviceBEncoder;
        }
        if (proto instanceof SuplaRegisterDeviceC) {
            return (Encoder<T>) suplaRegisterDeviceCEncoder;
        }
        if (proto instanceof SuplaRegisterDevice) {
            return (Encoder<T>) suplaRegisterDeviceEncoder;
        }

        // sc
        if (proto instanceof SuplaChannel) {
            return (Encoder<T>) suplaChannelEncoder;
        }
        if (proto instanceof SuplaChannelPack) {
            return (Encoder<T>) suplaChannelPackEncoder;
        }
        if (proto instanceof pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue) {
            return (Encoder<T>) suplaChannelValueEncoderSc;
        }
        if (proto instanceof SuplaEvent) {
            return (Encoder<T>) suplaEventEncoder;
        }
        if (proto instanceof SuplaLocation) {
            return (Encoder<T>) suplaLocationEncoder;
        }
        if (proto instanceof SuplaLocationPack) {
            return (Encoder<T>) suplaLocationPackEncoder;
        }
        if (proto instanceof SuplaRegisterClientResult) {
            return (Encoder<T>) suplaRegisterClientResultEncoder;
        }

        // sd
        if (proto instanceof SuplaFirmwareUpdateUrl) {
            return (Encoder<T>) firmwareUpdateUrlEncoder;
        }
        if (proto instanceof SuplaFirmwareUpdateUrlResult) {
            return (Encoder<T>) firmwareUpdateUrlResultEncoder;
        }
        if (proto instanceof pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue) {
            return (Encoder<T>) suplaChannelNewValueEncoderSd;
        }
        if (proto instanceof SuplaRegisterDeviceResult) {
            return (Encoder<T>) suplaRegisterDeviceResultEncoder;
        }

        // sdc
        if (proto instanceof SuplaGetVersionResult) {
            return (Encoder<T>) suplaGetVersionResultEncoder;
        }
        if (proto instanceof SuplaPingServerResultClient) {
            return (Encoder<T>) suplaPingServerResultClientEncoder;
        }
        if (proto instanceof SuplaSetActivityTimeoutResult) {
            return (Encoder<T>) suplaSetActivityTimeoutResultEncoder;
        }
        if (proto instanceof SuplaVersionError) {
            return (Encoder<T>) suplaVersionErrorEncoder;
        }

        // common
        if (proto instanceof SuplaChannelValue) {
            return (Encoder<T>) suplaChannelValueEncoder;
        }
        if (proto instanceof SuplaDataPacket) {
            return (Encoder<T>) suplaDataPacketEncoder;
        }
        if (proto instanceof SuplaTimeval) {
            return (Encoder<T>) timevalEncoder;
        }

        throw new IllegalArgumentException(format("do not know %s", proto.getClass().getSimpleName()));
    }
}
