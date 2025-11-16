package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterExtendedValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterExtendedValueV2;
import pl.grzeslowski.jsupla.protocol.api.structs.ElectricityMeterMeasurement;

class ElectricityMeterTestPayloadBuilder {
    private static final BigInteger[] FORWARD_ACTIVE = arrayOf(200_000L, 300_000L, 400_000L);
    private static final BigInteger[] REVERSE_ACTIVE = arrayOf(400_000L, 200_000L, 100_000L);
    private static final BigInteger[] FORWARD_REACTIVE = arrayOf(100_000L, 200_000L, 300_000L);
    private static final BigInteger[] REVERSE_REACTIVE = arrayOf(100_000L, 100_000L, 100_000L);
    private static final byte[] CURRENCY = new byte[] {'P', 'L', 'N'};

    private static final ElectricityMeterMeasurement MEASUREMENT =
            new ElectricityMeterMeasurement(
                    5_000,
                    new int[] {23_000, 23_100, 23_200},
                    new int[] {1_000, 2_000, 3_000},
                    new int[] {100_000, 200_000, 300_000},
                    new int[] {400_000, 500_000, 600_000},
                    new int[] {700_000, 800_000, 900_000},
                    new short[] {900, 950, 990},
                    new short[] {100, 200, 300});

    byte[] buildV1() {
        ElectricityMeterExtendedValue value =
                new ElectricityMeterExtendedValue(
                        FORWARD_ACTIVE,
                        REVERSE_ACTIVE,
                        FORWARD_REACTIVE,
                        REVERSE_REACTIVE,
                        12_345,
                        45_678,
                        CURRENCY,
                        3,
                        60,
                        1,
                        new ElectricityMeterMeasurement[] {MEASUREMENT});
        return encode(value);
    }

    byte[] buildV2(BigInteger forwardBalanced, BigInteger reverseBalanced) {
        ElectricityMeterExtendedValueV2 value =
                new ElectricityMeterExtendedValueV2(
                        FORWARD_ACTIVE,
                        REVERSE_ACTIVE,
                        FORWARD_REACTIVE,
                        REVERSE_REACTIVE,
                        forwardBalanced,
                        reverseBalanced,
                        12_345,
                        54_321,
                        45_678,
                        CURRENCY,
                        3,
                        60,
                        1,
                        new ElectricityMeterMeasurement[] {MEASUREMENT});
        return encode(value);
    }

    ElectricityMeterMeasurement measurement() {
        return MEASUREMENT;
    }

    private static BigInteger[] arrayOf(long... values) {
        BigInteger[] result = new BigInteger[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = BigInteger.valueOf(values[i]);
        }
        return result;
    }

    private byte[] encode(ElectricityMeterExtendedValue value) {
        ByteBuffer buffer = ByteBuffer.allocate(value.size()).order(ByteOrder.LITTLE_ENDIAN);
        writeUnsignedLongArray(buffer, value.totalForwardActiveEnergy);
        writeUnsignedLongArray(buffer, value.totalReverseActiveEnergy);
        writeUnsignedLongArray(buffer, value.totalForwardReactiveEnergy);
        writeUnsignedLongArray(buffer, value.totalReverseReactiveEnergy);
        buffer.putInt(value.totalCost);
        buffer.putInt(value.pricePerUnit);
        buffer.put(value.currency);
        buffer.putInt(value.measuredValues);
        buffer.putInt(value.period);
        buffer.putInt(value.mCount);
        for (ElectricityMeterMeasurement measurement : value.m) {
            writeMeasurement(buffer, measurement);
        }
        return buffer.array();
    }

    private byte[] encode(ElectricityMeterExtendedValueV2 value) {
        ByteBuffer buffer = ByteBuffer.allocate(value.size()).order(ByteOrder.LITTLE_ENDIAN);
        writeUnsignedLongArray(buffer, value.totalForwardActiveEnergy);
        writeUnsignedLongArray(buffer, value.totalReverseActiveEnergy);
        writeUnsignedLongArray(buffer, value.totalForwardReactiveEnergy);
        writeUnsignedLongArray(buffer, value.totalReverseReactiveEnergy);
        buffer.putLong(value.totalForwardActiveEnergyBalanced.longValue());
        buffer.putLong(value.totalReverseActiveEnergyBalanced.longValue());
        buffer.putInt(value.totalCost);
        buffer.putInt(value.totalCostBalanced);
        buffer.putInt(value.pricePerUnit);
        buffer.put(value.currency);
        buffer.putInt(value.measuredValues);
        buffer.putInt(value.period);
        buffer.putInt(value.mCount);
        for (ElectricityMeterMeasurement measurement : value.m) {
            writeMeasurement(buffer, measurement);
        }
        return buffer.array();
    }

    private void writeUnsignedLongArray(ByteBuffer buffer, BigInteger[] values) {
        for (BigInteger value : values) {
            buffer.putLong(value.longValue());
        }
    }

    private void writeMeasurement(ByteBuffer buffer, ElectricityMeterMeasurement measurement) {
        buffer.putShort((short) measurement.freq);
        writeUnsignedShortArray(buffer, measurement.voltage);
        writeUnsignedShortArray(buffer, measurement.current);
        writeIntArray(buffer, measurement.powerActive);
        writeIntArray(buffer, measurement.powerReactive);
        writeIntArray(buffer, measurement.powerApparent);
        writeShortArray(buffer, measurement.powerFactor);
        writeShortArray(buffer, measurement.phaseAngle);
    }

    private void writeUnsignedShortArray(ByteBuffer buffer, int[] values) {
        for (int value : values) {
            buffer.putShort((short) value);
        }
    }

    private void writeIntArray(ByteBuffer buffer, int[] values) {
        for (int value : values) {
            buffer.putInt(value);
        }
    }

    private void writeShortArray(ByteBuffer buffer, short[] values) {
        for (short value : values) {
            buffer.putShort(value);
        }
    }
}
