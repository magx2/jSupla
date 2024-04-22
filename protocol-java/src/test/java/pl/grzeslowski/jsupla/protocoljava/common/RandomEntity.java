package pl.grzeslowski.jsupla.protocoljava.common;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.common.RandomSupla;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.RegisterDeviceResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.GetVersionResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.SetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;

import java.util.stream.Stream;

public class RandomEntity extends EnhancedRandom {
    public static final RandomEntity RANDOM_ENTITY = new RandomEntity(1337);
    @SuppressWarnings("FieldCanBeLocal")
    private final Logger logger = LoggerFactory.getLogger(RandomSupla.class);
    private final EnhancedRandom random;

    @SuppressWarnings("WeakerAccess")
    public RandomEntity(final int seed) {
        logger.info("Starting RandomEntity with seed {}.", seed);
        random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .objectPoolSize(1_000)
            .stringLengthRange(5, 255)
            .seed(seed)
            .randomize(ChannelValue.class, new ChannelValueRandomizer(this))
            .randomize(RegisterDevice.class, new RegisterDeviceRandomizer(this))
            .randomize(RegisterDeviceB.class, new RegisterDeviceBRandomizer(this))
            .randomize(RegisterDeviceC.class, new RegisterDeviceCRandomizer(this))
            .randomize(RegisterDeviceD.class, new RegisterDeviceDRandomizer(this))
            .randomize(ChannelPack.class, new ChannelPackRandomizer(this))
            .randomize(LocationPack.class, new LocationPackRandomizer(this))
            .randomize(RegisterClientResult.class, new RegisterClientResultRandomizer(this))
            .randomize(RegisterClientResultB.class, new RegisterClientResultBRandomizer(this))
            .randomize(RegisterDeviceResult.class, new RegisterDeviceResultRandomizer(this))
            .randomize(GetVersionResult.class, new GetVersionResultRandomizer(this))
            .randomize(SetActivityTimeoutResult.class, new SetActivityTimeoutResultRandomizer(this))
            .randomize(VersionError.class, new VersionErrorRandomizer(this))
            .randomize(ChannelValuePack.class, new ChannelValuePackRandomizer(this))
            .randomize(NewValue.class, new NewValueRandomizer(this))
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
}
