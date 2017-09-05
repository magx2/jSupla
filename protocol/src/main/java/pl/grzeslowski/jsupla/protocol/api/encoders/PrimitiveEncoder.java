package pl.grzeslowski.jsupla.protocol.api.encoders;


public interface PrimitiveEncoder {
    int writeInteger(int value, byte[] bytes, int offset);

    int writeUnsignedInteger(long value, byte[] bytes, int offset);

    long writeLong(long value, byte[] bytes, int offset);

    int writeByte(byte value, byte[] bytes, int offset);

    int writeUnsignedByte(short value, byte[] bytes, int offset);

    int writeBytes(byte[] from, byte[] to, int toOffset);
}
