package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterMeasurement;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.ThermostatValueGroup;

public interface PrimitiveDecoder {
    long parseUnsignedInt(byte[] bytes, int offset);

    long parseUnsignedLong(byte[] bytes, int offset);

    int parseInt(byte[] bytes, int offset);

    long parseLong(byte[] bytes, int offset);

    short parseUnsignedByte(byte[] bytes, int offset);

    byte parseByte(byte[] bytes, int offset);

    byte[] copyOfRange(byte[] original, int from, int to);

    char parseChar(byte[] bytes, int offset);

    int parseUnsignedShort(byte[] bytes, int offset);

    short parseShort(byte[] bytes, int offset);

    ElectricityMeterMeasurement parseElectricityMeterMeasurement(byte[] bytes, int offset);

    SuplaChannelValueB parseSuplaChannelValueB(byte[] bytes, int offset);

    ThermostatValueGroup parseThermostatValueGroup(byte[] bytes, int offset);
}
