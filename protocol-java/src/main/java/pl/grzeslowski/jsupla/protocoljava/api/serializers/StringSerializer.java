package pl.grzeslowski.jsupla.protocoljava.api.serializers;

public interface StringSerializer {
    byte[] serialize(String string, int length);

    byte[] serialize(String string);

    byte[] serializePassword(char[] password, int length);

    byte[] serializeHexString(String hex);
}
