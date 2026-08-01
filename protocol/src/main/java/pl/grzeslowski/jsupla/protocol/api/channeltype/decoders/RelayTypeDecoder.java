package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFunction.*;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.List;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.*;

class RelayTypeDecoder implements ChannelValueDecoder<OnOffValue> {
    private static final List<SemanticRelayValue> SEMANTIC_RELAY_VALUES =
            List.of(
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEGATEWAYLOCK,
                            GatewayLockValue.class,
                            GatewayLockValue.UNLOCKED,
                            GatewayLockValue.LOCKED),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEGATE,
                            GateValue.class,
                            GateValue.OPEN,
                            GateValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEGARAGEDOOR,
                            GarageDoorValue.class,
                            GarageDoorValue.OPEN,
                            GarageDoorValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEDOORLOCK,
                            DoorLockValue.class,
                            DoorLockValue.UNLOCKED,
                            DoorLockValue.LOCKED),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEROLLERSHUTTER,
                            RollerShutterValue.class,
                            RollerShutterValue.OPEN,
                            RollerShutterValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_POWERSWITCH,
                            PowerSwitchValue.class,
                            PowerSwitchValue.ON,
                            PowerSwitchValue.OFF),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_LIGHTSWITCH,
                            LightSwitchValue.class,
                            LightSwitchValue.ON,
                            LightSwitchValue.OFF),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_STAIRCASETIMER,
                            StaircaseTimerValue.class,
                            StaircaseTimerValue.ON,
                            StaircaseTimerValue.OFF),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEROOFWINDOW,
                            RoofWindowValue.class,
                            RoofWindowValue.OPEN,
                            RoofWindowValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CONTROLLINGTHEFACADEBLIND,
                            FacadeBlindValue.class,
                            FacadeBlindValue.OPEN,
                            FacadeBlindValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_TERRACE_AWNING,
                            TerraceAwningValue.class,
                            TerraceAwningValue.OPEN,
                            TerraceAwningValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_PROJECTOR_SCREEN,
                            ProjectorScreenValue.class,
                            ProjectorScreenValue.OPEN,
                            ProjectorScreenValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_CURTAIN,
                            CurtainValue.class,
                            CurtainValue.OPEN,
                            CurtainValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_VERTICAL_BLIND,
                            VerticalBlindValue.class,
                            VerticalBlindValue.OPEN,
                            VerticalBlindValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_ROLLER_GARAGE_DOOR,
                            RollerGarageDoorValue.class,
                            RollerGarageDoorValue.OPEN,
                            RollerGarageDoorValue.CLOSE),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_PUMPSWITCH,
                            PumpSwitchValue.class,
                            PumpSwitchValue.ON,
                            PumpSwitchValue.OFF),
                    new SemanticRelayValue(
                            SUPLA_CHANNELFNC_HEATORCOLDSOURCESWITCH,
                            HeatOrColdSourceSwitchValue.class,
                            HeatOrColdSourceSwitchValue.ON,
                            HeatOrColdSourceSwitchValue.OFF));

    @SuppressWarnings("deprecation")
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(
                SUPLA_CHANNELTYPE_SENSORNO,
                SUPLA_CHANNELTYPE_SENSORNC,
                SUPLA_CHANNELTYPE_RELAYHFD4,
                SUPLA_CHANNELTYPE_RELAYG5LA1A,
                SUPLA_CHANNELTYPE_2XRELAYG5LA1A,
                SUPLA_CHANNELTYPE_RELAY,
                SUPLA_CHANNELTYPE_BINARYSENSOR);
    }

    @Override
    public Set<ChannelFunction> supportedChannelFunctions() {
        return Set.copyOf(supportedSemanticFunctions());
    }

    @Override
    public Class<OnOffValue> getChannelValueType() {
        return OnOffValue.class;
    }

    @Override
    public OnOffValue decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset);
        final short value = INSTANCE.parseUnsignedByte(bytes, offset);
        if (value == 1) {
            return OnOffValue.ON;
        }
        if (value == 0) {
            return OnOffValue.OFF;
        }
        throw new IllegalArgumentException(
                format("Don't know how to map value %s to ON/OFF!", value));
    }

    @Override
    public ChannelValue decode(
            final byte[] bytes, final int offset, final ChannelDescription description) {
        var semanticRelayValue = semanticRelayValue(description);
        if (semanticRelayValue != null) {
            Preconditions.sizeMin(bytes, offset);
            final short value = INSTANCE.parseUnsignedByte(bytes, offset);
            if (value == 1) {
                return semanticRelayValue.active();
            }
            if (value == 0) {
                return semanticRelayValue.inactive();
            }
            throw new IllegalArgumentException(
                    format(
                            "Don't know how to map value %s to %s!",
                            value, semanticRelayValue.type().getSimpleName()));
        }
        return decode(bytes, offset);
    }

    @Override
    public Class<? extends ChannelValue> getChannelValueType(final ChannelDescription description) {
        var semanticRelayValue = semanticRelayValue(description);
        return semanticRelayValue != null ? semanticRelayValue.type() : OnOffValue.class;
    }

    static List<ChannelFunction> supportedSemanticFunctions() {
        return SEMANTIC_RELAY_VALUES.stream().map(SemanticRelayValue::function).toList();
    }

    private SemanticRelayValue semanticRelayValue(final ChannelDescription description) {
        if (description == null || description.functions() == null) {
            return null;
        }
        var selectedValue =
                description
                        .selectedFunction()
                        .flatMap(
                                selected ->
                                        SEMANTIC_RELAY_VALUES.stream()
                                                .filter(value -> value.function() == selected)
                                                .findAny());
        if (selectedValue.isPresent()) {
            return selectedValue.get();
        }
        var values =
                SEMANTIC_RELAY_VALUES.stream()
                        .filter(value -> description.functions().contains(value.function()))
                        .toList();
        if (values.size() == 1) {
            return values.getFirst();
        }
        return null;
    }

    private record SemanticRelayValue(
            ChannelFunction function,
            Class<? extends ChannelValue> type,
            ChannelValue active,
            ChannelValue inactive) {}
}
