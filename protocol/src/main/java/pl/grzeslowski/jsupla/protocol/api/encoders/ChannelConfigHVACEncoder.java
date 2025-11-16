package pl.grzeslowski.jsupla.protocol.api.encoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.structs.ChannelConfigHVAC;

public class ChannelConfigHVACEncoder implements ProtoWithSizeEncoder<ChannelConfigHVAC> {
    public static final ChannelConfigHVACEncoder INSTANCE = new ChannelConfigHVACEncoder();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(ChannelConfigHVAC proto) {
        val bytes = new byte[proto.protoSize()];
        int offset = 0;

        if (proto.mainThermometerChannelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(proto.mainThermometerChannelId, bytes, offset);
        } else if (proto.mainThermometerChannelNo != null) {
            PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                    proto.mainThermometerChannelNo, bytes, offset);
        } else {
            throw new UnionException(proto, "mainThermometerChannelId", "mainThermometerChannelNo");
        }
        offset += INT_SIZE;

        if (proto.auxThermometerChannelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(proto.auxThermometerChannelId, bytes, offset);
        } else if (proto.auxThermometerChannelNo != null) {
            PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                    proto.auxThermometerChannelNo, bytes, offset);
        } else {
            throw new UnionException(proto, "auxThermometerChannelId", "auxThermometerChannelNo");
        }
        offset += INT_SIZE;

        if (proto.binarySensorChannelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(proto.binarySensorChannelId, bytes, offset);
        } else if (proto.binarySensorChannelNo != null) {
            PrimitiveEncoder.INSTANCE.writeUnsignedByte(proto.binarySensorChannelNo, bytes, offset);
        } else {
            throw new UnionException(proto, "binarySensorChannelId", "binarySensorChannelNo");
        }
        offset += INT_SIZE;

        offset +=
                PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                        proto.auxThermometerType, bytes, offset);
        offset +=
                PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                        proto.antiFreezeAndOverheatProtectionEnabled, bytes, offset);
        offset +=
                PrimitiveEncoder.INSTANCE.writeUnsignedShort(
                        proto.availableAlgorithms, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedShort(proto.usedAlgorithm, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedShort(proto.minOnTimeS, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedShort(proto.minOffTimeS, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeByte(proto.outputValueOnError, bytes, offset);
        offset += PrimitiveEncoder.INSTANCE.writeUnsignedByte(proto.subfunction, bytes, offset);
        offset +=
                PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                        proto.temperatureSetpointChangeSwitchesToManualMode, bytes, offset);
        offset +=
                PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                        proto.auxMinMaxSetpointEnabled, bytes, offset);
        offset +=
                PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                        proto.useSeparateHeatCoolOutputs, bytes, offset);
        offset +=
                PrimitiveEncoder.INSTANCE.writeByteArray(
                        HvacParameterFlagsEncoder.INSTANCE.encode(proto.parameterFlags),
                        bytes,
                        offset);

        if (proto.masterThermostatChannelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(proto.masterThermostatChannelId, bytes, offset);
        } else if (proto.masterThermostatIsSet != null && proto.masterThermostatChannelNo != null) {
            int add =
                    PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                            proto.masterThermostatIsSet, bytes, offset);
            PrimitiveEncoder.INSTANCE.writeUnsignedByte(
                    proto.masterThermostatChannelNo, bytes, offset + add);
        } else {
            throw new UnionException(
                    proto,
                    "masterThermostatChannelId",
                    "[masterThermostatIsSet|masterThermostatChannelNo]");
        }
        offset += INT_SIZE;

        // encoding only heatOrColdSourceSwitchChannelId in this union
        if (proto.heatOrColdSourceSwitchChannelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(
                    proto.heatOrColdSourceSwitchChannelId, bytes, offset);
        } else {
            throw new UnionException(
                    proto,
                    "heatOrColdSourceSwitchChannelId",
                    "[heatOrColdSourceSwitchIsSet|heatOrColdSourceSwitchChannelNo]");
        }
        offset += INT_SIZE;

        // encoding only pumpSwitchChannelId in this union
        if (proto.pumpSwitchChannelId != null) {
            PrimitiveEncoder.INSTANCE.writeInt(proto.pumpSwitchChannelId, bytes, offset);
        } else {
            throw new UnionException(
                    proto, "pumpSwitchChannelId", "[pumpSwitchIsSet|pumpSwitchChannelNo]");
        }
        offset += INT_SIZE;

        // just go through reserved
        offset += ChannelConfigHVAC.RESERVED_SIZE;

        offset +=
                PrimitiveEncoder.INSTANCE.writeByteArray(
                        HVACTemperatureCfgEncoder.INSTANCE.encode(proto.temperatures),
                        bytes,
                        offset);

        return bytes;
    }
}
