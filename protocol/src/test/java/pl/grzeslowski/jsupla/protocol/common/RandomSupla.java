package pl.grzeslowski.jsupla.protocol.common;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientC;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResultB;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.common.randomizers.SuplaDataPacketRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.TimevalRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaChannelNewValueBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaChannelNewValueRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaNewValueRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaRegisterClientBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaRegisterClientCRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaRegisterClientRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.dcs.SuplaPingServerRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.dcs.SuplaSetActivityTimeoutRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.FirmwareUpdateParamsRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaChannelNewValueResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaDeviceChannelBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaDeviceChannelRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaDeviceChannelValueRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaRegisterDeviceBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaRegisterDeviceCRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaRegisterDeviceDRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaRegisterDeviceRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelGroupRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelGroupRelationPackRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelGroupRelationRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelPackBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelPackRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelValuePackRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelValueRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaEventRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaLocationPackRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaLocationRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaRegisterClientResultBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaRegisterClientResultRandomizer;
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

public class RandomSupla extends EnhancedRandom {
    public static final RandomSupla RANDOM_SUPLA = new RandomSupla(1337);
    @SuppressWarnings("FieldCanBeLocal") private final Logger logger = LoggerFactory.getLogger(RandomSupla.class);
    private final EnhancedRandom random;

    private RandomSupla(final long seed) {
        logger.info("Starting RandomSupla with seed {}.", seed);
        random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                     .objectPoolSize(1_000)
                     .seed(123L)
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
        final byte[] bytes = new byte[byteSize];
        while (true) {
            String nextString = this.nextString(byteSize);
            byte[] stringBytes = nextString.getBytes(UTF_8);
            if (stringBytes.length < byteSize) {
                arraycopy(stringBytes, 0, bytes, 0, stringBytes.length);
                return bytes;
            }
        }
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
        while (true) {
            final String string = random.nextObject(String.class);
            if (string.length() >= 1 && string.length() <= maxLength) {
                return string;
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
