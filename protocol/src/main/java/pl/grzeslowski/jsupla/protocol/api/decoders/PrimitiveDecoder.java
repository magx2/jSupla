package pl.grzeslowski.jsupla.protocol.api.decoders;

public interface PrimitiveDecoder {
    long parseUnsignedInt(byte[] bytes, int offset);

    int parseInt(byte[] bytes, int offset);

    short parseUnsignedByte(byte[] bytes, int offset);

    byte parseByte(byte[] bytes, int offset);

    String parseString(byte[] bytes, int offset, int length);

    String parseString(byte[] bytes);

    String parseString(char[] bytes);

    byte[] copyOfRange(byte[] original, int from, int to);
}
