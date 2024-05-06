package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.structs.dsc.ChannelState;

public class ChannelStateExtendedValue extends ChannelState {
    public ChannelStateExtendedValue(int receiverId, int channelId, short channelNumber, int fields, int defaultIconField, long iPv4, short[] mAC, short batteryLevel, short batteryPowered, byte wiFiRSSI, short wiFiSignalStrength, short bridgeNodeOnline, short bridgeNodeSignalStrength, long uptime, long connectionUptime, short batteryHealth, short lastConnectionResetCause, int lightSourceLifespan, short lightSourceLifespanLeft, int lightSourceOperatingTime, byte[] emptySpace) {
        super(receiverId, channelId, channelNumber, fields, defaultIconField, iPv4, mAC, batteryLevel, batteryPowered, wiFiRSSI, wiFiSignalStrength, bridgeNodeOnline, bridgeNodeSignalStrength, uptime, connectionUptime, batteryHealth, lastConnectionResetCause, lightSourceLifespan, lightSourceLifespanLeft, lightSourceOperatingTime, emptySpace);
    }
}
