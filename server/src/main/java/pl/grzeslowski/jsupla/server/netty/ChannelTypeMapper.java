package pl.grzeslowski.jsupla.server.netty;


import pl.grzeslowski.jsupla.protocol.api.ChannelType;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.String.format;

public final class ChannelTypeMapper implements TypeMapper {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private List</*DeviceChannelTrait*/Object> deviceChannels;

    public void registerDevice(Object registerDevice) {
//        lock.writeLock().lock();
//        try {
//            if (deviceChannels != null) {
//                throw new IllegalStateException("Cannot set device channels twice!");
//            }
//            deviceChannels = registerDevice.getChannelsStream()
//                .sorted(comparingInt(DeviceChannelTrait::getNumber))
//                .collect(Collectors.toList());
//
//        } finally {
//            lock.writeLock().unlock();
//        }
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
//            val deviceChannel = deviceChannels.get(channelNumber);
//            if (deviceChannel == null) {
//                throw new IllegalStateException(format("There is no channel with channel number %s", channelNumber));
//            }
//            final Optional<ChannelType> channelType = ChannelType.findByValue(deviceChannel.getType());
//            if (channelType.isPresent()) {
//                return channelType.get();
//            } else {
//                throw new IllegalArgumentException(
//                    format("There is no channel type with ID=%s", deviceChannel.getType()));
//            }
            return null;
        } finally {
            readLock.unlock();
        }
    }
}
