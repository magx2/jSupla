package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaDataPacketDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaChannelNewValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientBDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.cs.SuplaRegisterClientDecoder;
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
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaSetActivityTimeoutResultDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaVersionErrorDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientBDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaRegisterClientDecoderImpl;
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
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaSetActivityTimeoutResultDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sdc.SuplaVersionErrorDecoderImpl;

import static java.lang.String.format;

public final class DecoderFactoryImpl implements DecoderFactory {

    // cs
    private final SuplaChannelNewValueBDecoder suplaChannelNewValueBDecoder;
    private final SuplaChannelNewValueDecoder suplaChannelNewValueDecoder;
    private final SuplaRegisterClientBDecoder suplaRegisterClientBDecoder;
    private final SuplaRegisterClientDecoder suplaRegisterClientDecoder;

    // dcs
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
    private final pl.grzeslowski.jsupla.protocol.api.decoders.sd.SuplaChannelNewValueDecoder suplaChannelNewValueDecoderSd;
    private final SuplaRegisterDeviceResultDecoder suplaRegisterDeviceResultDecoder;

    // sdc
    private final SuplaGetVersionResultDecoder suplaGetVersionResultDecoder;
    private final SuplaSetActivityTimeoutResultDecoder suplaSetActivityTimeoutResultDecoder;
    private final SuplaVersionErrorDecoder suplaVersionErrorDecoder;

    // common
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;
    private final SuplaDataPacketDecoder suplaDataPacketDecoder;

    public DecoderFactoryImpl(final PrimitiveDecoder primitiveDecoder) {

        // common
        suplaChannelValueDecoder = new SuplaChannelValueDecoderImpl(primitiveDecoder);
        suplaDataPacketDecoder = new SuplaDataPacketDecoderImpl(primitiveDecoder);

        // cs
        suplaChannelNewValueBDecoder = new SuplaChannelNewValueBDecoderImpl(primitiveDecoder);
        suplaChannelNewValueDecoder = new SuplaChannelNewValueDecoderImpl(primitiveDecoder);
        suplaRegisterClientBDecoder = new SuplaRegisterClientBDecoderImpl(primitiveDecoder);
        suplaRegisterClientDecoder = new SuplaRegisterClientDecoderImpl(primitiveDecoder);

        // dcs
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
        suplaChannelDecoder = new SuplaChannelDecoderImpl(primitiveDecoder, suplaChannelValueDecoder);
        suplaChannelPackDecoder = new SuplaChannelPackDecoderImpl(primitiveDecoder, suplaChannelDecoder);
        suplaChannelValueDecoderSc = new pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelValueDecoderImpl(primitiveDecoder, suplaChannelValueDecoder);
        suplaEventDecoder = new SuplaEventDecoderImpl(primitiveDecoder);
        suplaLocationDecoder = new SuplaLocationDecoderImpl(primitiveDecoder);
        suplaLocationPackDecoder = new SuplaLocationPackDecoderImpl(primitiveDecoder, suplaLocationDecoder);
        suplaRegisterClientResultDecoder = new SuplaRegisterClientResultDecoderImpl(primitiveDecoder);

        // sd
        firmwareUpdateUrlDecoder = new FirmwareUpdateUrlDecoderImpl(primitiveDecoder);
        firmwareUpdateUrlResultDecoder = new FirmwareUpdateUrlResultDecoderImpl(primitiveDecoder, firmwareUpdateUrlDecoder);
        suplaChannelNewValueDecoderSd = new pl.grzeslowski.jsupla.protocol.impl.decoders.sd.SuplaChannelNewValueDecoderImpl(primitiveDecoder);
        suplaRegisterDeviceResultDecoder = new SuplaRegisterDeviceResultDecoderImpl(primitiveDecoder);

        // sdc
        suplaGetVersionResultDecoder = new SuplaGetVersionResultDecoderImpl(primitiveDecoder);
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
        if (proto instanceof SuplaSetActivityTimeout) {
            return (Decoder<T>) suplaSetActivityTimeoutDecoder;
        }

        // ds
        if (proto instanceof FirmwareUpdateParams) {
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
        if (proto instanceof FirmwareUpdateUrl) {
            return (Decoder<T>) firmwareUpdateUrlDecoder;
        }
        if (proto instanceof FirmwareUpdateUrlResult) {
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

        throw new IllegalArgumentException(format("Don't know decoder for %s", proto));
    }
}
