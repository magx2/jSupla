package pl.grzeslowski.jsupla.protocol.api.decoders;

public interface PrimitiveDecoder {
    long parseUnsignedInt(byte[] bytes, int offset);

    long parseUnsignedLong(byte[] bytes, int offset);

    int parseInt(byte[] bytes, int offset);

    long parseLong(byte[] bytes, int offset);

    short parseUnsignedByte(byte[] bytes, int offset);

    byte parseByte(byte[] bytes, int offset);

    byte[] copyOfRange(byte[] original, int from, int to);
}
