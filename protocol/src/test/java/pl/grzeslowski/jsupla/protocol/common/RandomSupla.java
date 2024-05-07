package pl.grzeslowski.jsupla.protocol.common;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.*;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.*;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.common.randomizers.SuplaDataPacketRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.TimevalRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.*;
import pl.grzeslowski.jsupla.protocol.common.randomizers.dcs.SuplaPingServerRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.dcs.SuplaSetActivityTimeoutRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.*;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.*;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sd.FirmwareUpdateUrlRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sd.FirmwareUpdateUrlResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sd.SuplaRegisterDeviceResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaGetVersionResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaPingServerResultClientRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaSetActivityTimeoutResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaVersionErrorRandomizer;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.System.arraycopy;
import static java.nio.charset.StandardCharsets.UTF_8;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.min;

public class RandomSupla extends EnhancedRandom {
    public static final RandomSupla RANDOM_SUPLA = new RandomSupla(1337);
    @SuppressWarnings("FieldCanBeLocal")
    private final Logger logger = LoggerFactory.getLogger(RandomSupla.class);
    private final EnhancedRandom random;

    private RandomSupla(final long seed) {
        logger.info("Starting RandomSupla with seed {}.", seed);
        random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .objectPoolSize(1_000)
            .seed(seed)
            // cs
            .randomize(SuplaChannelNewValueB.class, new SuplaChannelNewValueBRandomizer(this))
            .randomize(SuplaChannelNewValue.class, new SuplaChannelNewValueRandomizer(this))
            .randomize(SuplaRegisterClientB.class, new SuplaRegisterClientBRandomizer(this))
            .randomize(SuplaRegisterClient.class, new SuplaRegisterClientRandomizer(this))
            .randomize(SuplaRegisterClientC.class, new SuplaRegisterClientCRandomizer(this))
            .randomize(SuplaNewValue.class, new SuplaNewValueRandomizer(this))
            // dcs
            .randomize(SuplaPingServer.class, new SuplaPingServerRandomizer(this))
            .randomize(SuplaSetActivityTimeout.class, new SuplaSetActivityTimeoutRandomizer(this))
            // decoders
            .randomize(SuplaFirmwareUpdateParams.class, new FirmwareUpdateParamsRandomizer(this))
            .randomize(SuplaChannelNewValueResult.class, new SuplaChannelNewValueResultRandomizer(this))
            .randomize(SuplaDeviceChannelB.class, new SuplaDeviceChannelBRandomizer(this))
            .randomize(SuplaDeviceChannel.class, new SuplaDeviceChannelRandomizer(this))
            .randomize(SuplaDeviceChannelValue.class, new SuplaDeviceChannelValueRandomizer(this))
            .randomize(SuplaRegisterDeviceB.class, new SuplaRegisterDeviceBRandomizer(this))
            .randomize(SuplaRegisterDeviceC.class, new SuplaRegisterDeviceCRandomizer(this))
            .randomize(SuplaRegisterDeviceD.class, new SuplaRegisterDeviceDRandomizer(this))
            .randomize(SuplaRegisterDevice.class, new SuplaRegisterDeviceRandomizer(this))
            // sc
            .randomize(SuplaChannelPack.class, new SuplaChannelPackRandomizer(this))
            .randomize(SuplaChannel.class, new SuplaChannelRandomizer(this))
            .randomize(SuplaChannelB.class, new SuplaChannelBRandomizer(this))
            .randomize(SuplaChannelValue.class, new SuplaChannelValueRandomizer(this))
            .randomize(SuplaEvent.class, new SuplaEventRandomizer(this))
            .randomize(SuplaLocationPack.class, new SuplaLocationPackRandomizer(this))
            .randomize(SuplaLocation.class, new SuplaLocationRandomizer(this))
            .randomize(SuplaRegisterClientResult.class, new SuplaRegisterClientResultRandomizer(this))
            .randomize(SuplaChannelGroupRelation.class, new SuplaChannelGroupRelationRandomizer(this))
            .randomize(SuplaRegisterClientResultB.class, new SuplaRegisterClientResultBRandomizer(this))
            .randomize(SuplaChannelGroupRelationPack.class, new SuplaChannelGroupRelationPackRandomizer(this))
            .randomize(SuplaChannelValuePack.class, new SuplaChannelValuePackRandomizer(this))
            .randomize(SuplaChannelGroup.class, new SuplaChannelGroupRandomizer(this))
            .randomize(SuplaChannelPackB.class, new SuplaChannelPackBRandomizer(this))
            // sd
            .randomize(SuplaFirmwareUpdateUrl.class, new FirmwareUpdateUrlRandomizer(this))
            .randomize(SuplaFirmwareUpdateUrlResult.class, new FirmwareUpdateUrlResultRandomizer(this))
            // @formatter:off
                     .randomize(pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue.class,
                             new pl.grzeslowski.jsupla.protocol.common.randomizers.sd
                                         .SuplaChannelNewValueRandomizer(this))
                     // @formatter:on
            .randomize(SuplaRegisterDeviceResult.class, new SuplaRegisterDeviceResultRandomizer(this))
            // sdc
            .randomize(SuplaGetVersionResult.class, new SuplaGetVersionResultRandomizer(this))
            .randomize(SuplaPingServerResultClient.class, new SuplaPingServerResultClientRandomizer(this))
            .randomize(SuplaSetActivityTimeoutResult.class,
                new SuplaSetActivityTimeoutResultRandomizer(this))
            .randomize(SuplaVersionError.class, new SuplaVersionErrorRandomizer(this))
            // common
            // @formatter:off
                     .randomize(pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue.class,
                             new pl.grzeslowski.jsupla.protocol.common.randomizers.SuplaChannelValueRandomizer(
                                     this))
                     // @formatter:on
            .randomize(SuplaDataPacket.class, new SuplaDataPacketRandomizer(this))
            .randomize(SuplaTimeval.class, new TimevalRandomizer(this))
            //build
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

    public byte[] nextByteArray(int byteSize) {
        byte[] bytes = new byte[byteSize];
        this.nextBytes(bytes);
        return bytes;
    }

    public byte[] nextByteArrayFromString(int byteSize) {
        min(byteSize, 2);
        final byte[] bytes = new byte[byteSize];
        for (int i = 0; i < 100; i++) {
            String nextString = this.nextString(byteSize);
            byte[] stringBytes = nextString.getBytes(UTF_8);
            if (stringBytes.length < byteSize) {
                arraycopy(stringBytes, 0, bytes, 0, stringBytes.length);
                return bytes;
            }
        }
        throw new IllegalStateException("Cann not generate nextByteArrayFromString(" + byteSize + ")");
    }

    public byte[] nextByteArrayWithoutZerosOnEnd(int maxByteSize) {
        byte[] bytes = nextByteArray(maxByteSize);
        int end = bytes.length;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == (byte) 0) {
                end = i;
                break;
            }
        }
        return Arrays.copyOf(bytes, end);
    }

    public byte nextByte() {
        return (byte) (nextInt(Byte.MAX_VALUE - Byte.MIN_VALUE) + Byte.MIN_VALUE);
    }

    public byte nextByte(byte bound) {
        return (byte) nextInt(bound);
    }

    public byte nextPositiveByte() {
        return (byte) nextInt(Byte.MAX_VALUE);
    }

    public byte nextPositiveByte(byte bound) {
        return (byte) (nextInt(bound) + 1);
    }

    public short nextUnsignedByte() {
        return (short) nextInt(255);
    }

    public short nextUnsignedByte(final short bound) {
        return (short) nextInt(bound);
    }

    public short nextPositiveUnsignedByte() {
        return (short) (nextInt(254) + 1);
    }

    public long nextUnsignedInt() {
        return random.nextInt(Integer.MAX_VALUE); // This is not max uint but it's OK
    }

    public long nextUnsignedInt(int bound) {
        return random.nextInt(bound);
    }

    public int nextPositiveInt() {
        return Math.abs(nextInt());
    }

    public int nextPositiveInt(int bound) {
        return Math.abs(nextInt(bound - 1)) + 1;
    }

    public String nextString(final int maxLength) {
        min(maxLength, 1);
        while (true) {
            final String string = random.nextObject(String.class);
            if (string.length() >= 1 && string.length() <= maxLength) {
                return string;
            } else if (string.length() > maxLength) {
                return string.substring(0, maxLength);
            }
        }
    }

    public byte[] nextBytesWithSize(final int size) {
        final byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = nextByte();
        }
        return bytes;
    }

    public byte nextBoolByte() {
        return (byte) (random.nextBoolean() ? 1 : 0);
    }

    public long nextLong(final int bound) {
        return random.nextInt(bound);
    }
}
