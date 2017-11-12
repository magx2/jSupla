package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.ChannelNewValueResultParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.DeviceServerParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.FirmwareUpdateParamsParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceCParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.RegisterDeviceParser;

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

    public DeviceServerParserImpl(final ChannelNewValueResultParser channelNewValueResultParser,
                                  final DeviceChannelValueParser deviceChannelValueParser,
                                  final RegisterDeviceParser registerDeviceParser,
                                  final FirmwareUpdateParamsParser firmwareUpdateParamsParser,
                                  final RegisterDeviceBParser registerDeviceBParser,
                                  final RegisterDeviceCParser registerDeviceCParser) {
        this.channelNewValueResultParser = requireNonNull(channelNewValueResultParser);
        this.deviceChannelValueParser = requireNonNull(deviceChannelValueParser);
        this.registerDeviceParser = requireNonNull(registerDeviceParser);
        this.firmwareUpdateParamsParser = requireNonNull(firmwareUpdateParamsParser);
        this.registerDeviceBParser = requireNonNull(registerDeviceBParser);
        this.registerDeviceCParser = requireNonNull(registerDeviceCParser);
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
        }
        throw new IllegalArgumentException(format("Don't know this type of proto. Class name: %s.",
                proto.getClass().getSimpleName()));
    }
}
