package pl.grzeslowski.jsupla.protocol.common;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaChannelNewValueRandomizer;

import java.util.stream.Stream;

public class RandomBean extends EnhancedRandom {
    public static final RandomBean RANDOM_BEAN = new RandomBean(1337);
    @SuppressWarnings("FieldCanBeLocal") private final Logger logger = LoggerFactory.getLogger(RandomBean.class);
    private final EnhancedRandom random;

    private RandomBean(final long seed) {
        logger.info("Starting RandomBean with seed {}.", seed);
        random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                         .seed(123L)
                         // cs
                         .randomize(SuplaChannelNewValue.class, new SuplaChannelNewValueRandomizer(this))
                         // dcs
                         // ds
                         // sc
                         // sd
                         // sdc
                         .build();
    }

    @Override
    public <T> T nextObject(final Class<T> type, final String... excludedFields) {
        return random.nextObject(type, excludedFields);
    }

    @Override
    public <T> Stream<T> objects(final Class<T> type, final int amount, final String... excludedFields) {
        return random.objects(type, amount, excludedFields);
    }

    public byte[] nextByteArray(int size) {
        byte[] bytes = new byte[size];
        this.nextBytes(bytes);
        return bytes;
    }

    public byte nextByte() {
        return (byte) (nextInt(Byte.MAX_VALUE - Byte.MIN_VALUE) + Byte.MIN_VALUE);
    }

    public byte nextByte(byte bound) {
        return (byte) nextInt(bound);
    }

    public short nextUnsignedByte() {
        return (short) nextInt(255);
    }

    public short nextUnsignedByte(final short bound) {
        return (short) nextInt(bound);
    }

    public long nextUnsignedInt() {
        return random.nextInt(Integer.MAX_VALUE); // This is not max uint but it's OK
    }
}
