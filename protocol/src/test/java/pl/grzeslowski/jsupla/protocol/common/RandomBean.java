package pl.grzeslowski.jsupla.protocol.common;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClient;
import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaRegisterClientB;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaPingServer;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.SuplaSetActivityTimeout;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaEvent;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaRegisterClientResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaGetVersionResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaPingServerResultClient;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocol.common.randomizers.SuplaChannelValueRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.SuplaDataPacketRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.TimevalRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaChannelNewValueBRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaChannelNewValueRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.cs.SuplaRegisterClientBRandomizer;
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
import pl.grzeslowski.jsupla.protocol.common.randomizers.ds.SuplaRegisterDeviceRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelPackRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaChannelRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaEventRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaLocationPackRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaLocationRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sc.SuplaRegisterClientResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sd.FirmwareUpdateUrlRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sd.FirmwareUpdateUrlResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sd.SuplaRegisterDeviceResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaGetVersionResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaPingServerResultClientRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaSetActivityTimeoutResultRandomizer;
import pl.grzeslowski.jsupla.protocol.common.randomizers.sdc.SuplaVersionErrorRandomizer;

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
                         .randomize(SuplaChannelNewValue.class, new SuplaChannelNewValueBRandomizer(this))
                         .randomize(SuplaChannelNewValue.class, new SuplaChannelNewValueRandomizer(this))
                         .randomize(SuplaRegisterClientB.class, new SuplaRegisterClientBRandomizer(this))
                         .randomize(SuplaRegisterClient.class, new SuplaRegisterClientRandomizer(this))
                         // dcs
                         .randomize(SuplaPingServer.class, new SuplaPingServerRandomizer(this))
                         .randomize(SuplaSetActivityTimeout.class, new SuplaSetActivityTimeoutRandomizer(this))
                         // ds
                         .randomize(FirmwareUpdateParams.class, new FirmwareUpdateParamsRandomizer(this))
                         .randomize(SuplaChannelNewValueResult.class, new SuplaChannelNewValueResultRandomizer(this))
                         .randomize(SuplaDeviceChannelB.class, new SuplaDeviceChannelBRandomizer(this))
                         .randomize(SuplaDeviceChannel.class, new SuplaDeviceChannelRandomizer(this))
                         .randomize(SuplaDeviceChannelValue.class, new SuplaDeviceChannelValueRandomizer(this))
                         .randomize(SuplaRegisterDeviceB.class, new SuplaRegisterDeviceBRandomizer(this))
                         .randomize(SuplaRegisterDeviceC.class, new SuplaRegisterDeviceCRandomizer(this))
                         .randomize(SuplaRegisterDevice.class, new SuplaRegisterDeviceRandomizer(this))
                         // sc
                         .randomize(SuplaChannelPack.class, new SuplaChannelPackRandomizer(this))
                         .randomize(SuplaChannel.class, new SuplaChannelRandomizer(this))
                         .randomize(SuplaChannelValue.class, new SuplaChannelValueRandomizer(this))
                         .randomize(SuplaEvent.class, new SuplaEventRandomizer(this))
                         .randomize(SuplaLocationPack.class, new SuplaLocationPackRandomizer(this))
                         .randomize(SuplaLocation.class, new SuplaLocationRandomizer(this))
                         .randomize(SuplaRegisterClientResult.class, new SuplaRegisterClientResultRandomizer(this))
                         // sd
                         .randomize(FirmwareUpdateUrl.class, new FirmwareUpdateUrlRandomizer(this))
                         .randomize(FirmwareUpdateUrlResult.class, new FirmwareUpdateUrlResultRandomizer(this))
                         .randomize(SuplaChannelNewValue.class, new SuplaChannelNewValueRandomizer(this))
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
                         .randomize(Timeval.class, new TimevalRandomizer(this))
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

    public byte nextPositiveByte() {
        return (byte) nextInt(Byte.MAX_VALUE);
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

    public long nextUnsignedInt(int bound) {
        return random.nextInt(bound);
    }

    public int nextPositiveInt() {
        return Math.abs(nextInt());
    }

    public int nextPositiveInt(int bound) {
        return Math.abs(nextInt(bound));
    }
}
