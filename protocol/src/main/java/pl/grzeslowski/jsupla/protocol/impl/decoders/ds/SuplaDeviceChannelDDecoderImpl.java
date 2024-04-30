package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelDDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ActionTriggerProperties;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelD;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelD.UNION_2_SIZE;

@RequiredArgsConstructor
public final class SuplaDeviceChannelDDecoderImpl implements SuplaDeviceChannelDDecoder {
    public static final SuplaDeviceChannelDDecoderImpl INSTANCE = new SuplaDeviceChannelDDecoderImpl(
        PrimitiveDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;

    @SuppressWarnings("ConstantValue")
    @Override
    public SuplaDeviceChannelD decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaDeviceChannelD.SIZE);

        final short number = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int type = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        // ✅ union 1
        final int funcList = primitiveDecoder.parseInt(bytes, offset);
        // I do not parse actionTriggerCaps from this union cause I do not know when it can be parsed...
        Long actionTriggerCaps = null;
        offset += INT_SIZE;
        // ✖️ union 1

        final int defaultField = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;
        final long flags = primitiveDecoder.parseUnsignedLong(bytes, offset);
        offset += INT_SIZE;
        final short offline = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;
        final long valueValidityTimeSec = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        // ✅ union 2
        final byte[] value = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        ActionTriggerProperties actionTriggerProperties = null;
        HVACValue hvacValue = null;
        offset += UNION_2_SIZE;
        // ✖️ union 2

        final short defaultIcon = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        return new SuplaDeviceChannelD(
            number,
            type,
            funcList,
            actionTriggerCaps,
            defaultField,
            flags,
            offline,
            valueValidityTimeSec,
            value,
            actionTriggerProperties,
            hvacValue,
            defaultIcon);
    }
}
