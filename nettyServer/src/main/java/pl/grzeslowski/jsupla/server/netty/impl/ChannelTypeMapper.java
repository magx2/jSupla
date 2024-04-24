package pl.grzeslowski.jsupla.server.netty.impl;

import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ChannelType;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.types.traits.RegisterDeviceTrait;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Comparator.comparingInt;

public final class ChannelTypeMapper implements DeviceChannelValueParser.TypeMapper {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private List<DeviceChannel> deviceChannels;

    public void registerDevice(RegisterDeviceTrait registerDevice) {
        lock.writeLock().lock();
        try {
            if (deviceChannels != null) {
                throw new IllegalStateException("Cannot set device channels twice!");
            }
            deviceChannels = registerDevice.getChannels()
                .getChannels()
                .stream()
                .sorted(comparingInt(DeviceChannel::getNumber))
                .collect(Collectors.toList());

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public ChannelType findChannelType(final int channelNumber) {
        final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            if (channelNumber > deviceChannels.size()) {
                throw new IllegalStateException(format("There is only %s elements in deviceChannels, required %s",
                    deviceChannels.size(), channelNumber));
            }
            final DeviceChannel deviceChannel = deviceChannels.get(channelNumber);
            if (deviceChannel == null) {
                throw new IllegalStateException(format("There is no channel with channel number %s", channelNumber));
            }
            final Optional<ChannelType> channelType = ChannelType.findByValue(deviceChannel.getType());
            if (channelType.isPresent()) {
                return channelType.get();
            } else {
                throw new IllegalArgumentException(
                    format("There is no channel type with ID=%s", deviceChannel.getType()));
            }
        } finally {
            readLock.unlock();
        }
    }
}
