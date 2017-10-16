package pl.grzeslowski.jsupla.protocoljava.api.parsers;

public interface StringParser {
    String parse(byte[] utfBytes);

    char[] parsePassword(byte[] utfBytes);

    String parseHexString(byte[] bytes);
}
