package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.*;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.*;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceServerParserImpl implements DeviceServerParser<DeviceServerEntity, DeviceServer> {
    private final ChannelNewValueResultParser channelNewValueResultParser;
    private final DeviceChannelValueParser deviceChannelValueParser;
    private final RegisterDeviceParser registerDeviceParser;
    private final FirmwareUpdateParamsParser firmwareUpdateParamsParser;
    private final RegisterDeviceBParser registerDeviceBParser;
    private final RegisterDeviceCParser registerDeviceCParser;
    private final RegisterDeviceDParser registerDeviceDParser;

    public DeviceServerParserImpl(final ChannelNewValueResultParser channelNewValueResultParser,
                                  final DeviceChannelValueParser deviceChannelValueParser,
                                  final RegisterDeviceParser registerDeviceParser,
                                  final FirmwareUpdateParamsParser firmwareUpdateParamsParser,
                                  final RegisterDeviceBParser registerDeviceBParser,
                                  final RegisterDeviceCParser registerDeviceCParser,
                                  final RegisterDeviceDParser registerDeviceDParser) {
        this.channelNewValueResultParser = requireNonNull(channelNewValueResultParser);
        this.deviceChannelValueParser = requireNonNull(deviceChannelValueParser);
        this.registerDeviceParser = requireNonNull(registerDeviceParser);
        this.firmwareUpdateParamsParser = requireNonNull(firmwareUpdateParamsParser);
        this.registerDeviceBParser = requireNonNull(registerDeviceBParser);
        this.registerDeviceCParser = requireNonNull(registerDeviceCParser);
        this.registerDeviceDParser = registerDeviceDParser;
    }

    @Override
    public DeviceServerEntity parse(@NotNull final DeviceServer proto) {
        requireNonNull(proto);
        if (proto instanceof SuplaChannelNewValueResult) {
            return channelNewValueResultParser.parse((SuplaChannelNewValueResult) proto);
        } else if (proto instanceof SuplaDeviceChannelValue) {
            return deviceChannelValueParser.parse((SuplaDeviceChannelValue) proto);
        } else if (proto instanceof SuplaFirmwareUpdateParams) {
            return firmwareUpdateParamsParser.parse((SuplaFirmwareUpdateParams) proto);
        } else if (proto instanceof SuplaRegisterDevice) {
            return registerDeviceParser.parse((SuplaRegisterDevice) proto);
        } else if (proto instanceof SuplaRegisterDeviceB) {
            return registerDeviceBParser.parse((SuplaRegisterDeviceB) proto);
        } else if (proto instanceof SuplaRegisterDeviceC) {
            return registerDeviceCParser.parse((SuplaRegisterDeviceC) proto);
        } else if (proto instanceof SuplaRegisterDeviceD) {
            return registerDeviceDParser.parse((SuplaRegisterDeviceD) proto);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to parser! %s",
            proto.getClass(), proto));
    }
}
