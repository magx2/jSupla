package pl.grzeslowski.jsupla.protocol.api.decoders.dsc;

import static pl.grzeslowski.jsupla.protocol.api.ChannelStateField.*;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dsc.ChannelState;

public class ChannelStateDecoder
        implements pl.grzeslowski.jsupla.protocol.api.decoders.dsc.DeviceServerClientDecoder<
                ChannelState> {
    public static final ChannelStateDecoder INSTANCE = new ChannelStateDecoder();

    @SuppressWarnings({"UnusedAssignment", "ConstantValue"})
    @Override
    public ChannelState decode(byte[] bytes, int offset) {
        int receiverId = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        // union
        // lets assume it's Server -> Client
        Integer channelId = null;
        Short channelNumber = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += INT_SIZE;

        int fields = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        // union
        // I have no idea which one to parse
        // just parse-first int and ignore the second
        Integer defaultIconField = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        Long switchCycleCount = null;
        offset += INT_SIZE;

        long iPv4 = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        int mACLength = 6;
        short[] mAC = new short[mACLength];
        for (int i = 0; i < mACLength; i++) {
            mAC[i] = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
            offset += BYTE_SIZE;
        }

        short batteryLevel = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        short batteryPowered = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        byte wiFiRSSI = PrimitiveDecoder.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        short wiFiSignalStrength = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        short bridgeNodeOnline = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        short bridgeNodeSignalStrength = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        long uptime = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        long connectionUptime = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        short batteryHealth = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        short lastConnectionResetCause = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        int lightSourceLifespan = PrimitiveDecoder.INSTANCE.parseUnsignedShort(bytes, offset);
        offset += SHORT_SIZE;

        // union
        val lightSourceLifespanLeftFlag =
                (fields & SUPLA_CHANNELSTATE_FIELD_LIGHTSOURCELIFESPAN.getValue()) != 0;
        val lightSourceOperatingTimeFlag =
                (fields & SUPLA_CHANNELSTATE_FIELD_LIGHTSOURCEOPERATINGTIME.getValue()) != 0;
        val operatingTimeFlag = (fields & SUPLA_CHANNELSTATE_FIELD_OPERATINGTIME.getValue()) != 0;

        Short lightSourceLifespanLeft = null;
        Integer lightSourceOperatingTime = null;
        Long operatingTime = null;
        if (!lightSourceLifespanLeftFlag && !lightSourceOperatingTimeFlag && !operatingTimeFlag) {
            // just set zero to make everybody happy
            lightSourceOperatingTime = 0;
        }
        if (lightSourceLifespanLeftFlag) {
            lightSourceLifespanLeft = PrimitiveDecoder.INSTANCE.parseShort(bytes, offset);
        }
        if (lightSourceOperatingTimeFlag) {
            lightSourceOperatingTime = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        }
        if (operatingTimeFlag) {
            operatingTime = PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
        }
        offset += INT_SIZE;

        int emptySpaceLength = 1;
        byte[] emptySpace = new byte[emptySpaceLength];
        System.arraycopy(bytes, offset, emptySpace, 0, emptySpaceLength);
        offset += emptySpaceLength * BYTE_SIZE;

        return new ChannelState(
                receiverId,
                channelId,
                channelNumber,
                fields,
                defaultIconField,
                switchCycleCount,
                iPv4,
                mAC,
                batteryLevel,
                batteryPowered,
                wiFiRSSI,
                wiFiSignalStrength,
                bridgeNodeOnline,
                bridgeNodeSignalStrength,
                uptime,
                connectionUptime,
                batteryHealth,
                lastConnectionResetCause,
                lightSourceLifespan,
                lightSourceLifespanLeft,
                lightSourceOperatingTime,
                operatingTime,
                (byte) 1,
                emptySpace);
    }
}
