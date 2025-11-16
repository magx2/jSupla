package pl.grzeslowski.jsupla.protocol.api.structs;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unionCheck;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.unsigned;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

/**
 * Channel numbers for thermometer config. Channels have to be local andnumbering is the same as for registration message
 * <p>
 * Original code:
 * <pre>
 * typedef struct {
 * union {
 * _supla_int_t MainThermometerChannelId;
 * // Channel numbers for thermometer config. Channels have to be local and
 * // numbering is the same as for registration message
 * unsigned char MainThermometerChannelNo;
 * };
 *
 * union {
 * _supla_int_t AuxThermometerChannelId;
 * unsigned char
 * AuxThermometerChannelNo;  // If the channel number points to itself, it
 * // means that the aux thermometer is not set.
 * };
 *
 * union {
 * _supla_int_t BinarySensorChannelId;
 * unsigned char
 * BinarySensorChannelNo;  // If the channel number points to itself, it
 * // means that the binary sensor is not set.
 * };
 *
 * // SUPLA_HVAC_AUX_THERMOMETER_TYPE_
 * unsigned char AuxThermometerType;
 * unsigned char AntiFreezeAndOverheatProtectionEnabled;
 * // bit map SUPLA_HVAC_ALGORITHM_ (readonly)
 * unsigned _supla_int16_t AvailableAlgorithms;
 * // only one value of SUPLA_HVAC_ALGORITHM_
 * unsigned _supla_int16_t UsedAlgorithm;
 * // Below Min TimeS parameters defines minimum time of relay/output to be
 * // be disabled or enabled in seconds. It is used to prevent to frequent relay
 * // state change.
 * // Allowed values are 0-600 (10 minutes)
 * unsigned _supla_int16_t MinOnTimeS;   // minimum allowed time for output to
 * // be enabled
 * unsigned _supla_int16_t MinOffTimeS;  // minimum allowed time for output to
 * // be disabled
 * signed char OutputValueOnError;       // -100 cool, 0 off (default), 100 heat
 * unsigned char Subfunction;            // SUPLA_HVAC_SUBFUNCTION_
 * unsigned char TemperatureSetpointChangeSwitchesToManualMode;  // 0 - off,
 * // 1 - on (def)
 * unsigned char AuxMinMaxSetpointEnabled;  // 0 - off (default), 1 - on
 * // For HEAT_COOL thermostats we have two outpus. They can either use
 * // shared output for heating/cooling action and second output for heat vs
 * // cool mode selection, or they can use separate outputs - one for heating
 * // and one for cooling
 * unsigned char UseSeparateHeatCoolOutputs;  // 0 - off (default), 1 - on
 * HvacParameterFlags ParameterFlags;
 *
 * union {
 * _supla_int_t MasterThermostatChannelId;
 * struct {
 * unsigned char MasterThermostatIsSet;  // 0 - no; 1 - yes
 * unsigned char MasterThermostatChannelNo;
 * };  // v. &#62;= 25
 * };
 *
 * union {
 * _supla_int_t HeatOrColdSourceSwitchChannelId;
 * struct {
 * unsigned char HeatOrColdSourceSwitchIsSet;  // 0 - no; 1 - yes
 * unsigned char HeatOrColdSourceSwitchChannelNo;
 * };  // v. &#62;= 25
 * };
 *
 * union {
 * _supla_int_t PumpSwitchChannelId;
 * struct {
 * unsigned char PumpSwitchIsSet;  // 0 - no; 1 - yes
 * unsigned char PumpSwitchChannelNo;
 * };  // v. &#62;= 25
 * };
 *
 * // TemperatureControlType allows to switch between work based on main
 * // thermometer (room) and aux thermometer (heater/cooler).
 * // Option is available only for SUPLA_CHANNELFNC_HVAC_THERMOSTAT
 * // If set to 0, then it is not supported.
 * unsigned char TemperatureControlType;  // SUPLA_HVAC_TEMPERATURE_CONTROL_TYPE_
 *
 * unsigned char Reserved[48 - sizeof(HvacParameterFlags) -
 * sizeof(_supla_int_t) - sizeof(_supla_int_t) -
 * sizeof(_supla_int_t) - sizeof(unsigned char)];
 * THVACTemperatureCfg Temperatures;
 * } TChannelConfig_HVAC;  // v. &#62;= 21
 * </pre>
 * @param mainThermometerChannelId
 * @param mainThermometerChannelNo unsigned Byte
 * @param auxThermometerChannelId
 * @param auxThermometerChannelNo If the channel number points to itself, it. unsigned Byte
 * @param binarySensorChannelId
 * @param binarySensorChannelNo If the channel number points to itself, it. unsigned Byte
 * @param auxThermometerType unsigned byte
 * @param antiFreezeAndOverheatProtectionEnabled bit map SUPLA_HVAC_ALGORITHM_ (readonly). unsigned byte
 * @param availableAlgorithms only one value of SUPLA_HVAC_ALGORITHM_. unsigned short
 * @param usedAlgorithm Below Min TimeS parameters defines minimum time of relay/output to be be disabled or enabled in seconds. It is used to prevent to frequent relay state change. Allowed values are 0-600 (10 minutes). unsigned short
 * @param minOnTimeS minimum allowed time for output to be enabled. unsigned short
 * @param minOffTimeS minimum allowed time for output to be disabled. unsigned short
 * @param outputValueOnError -100 cool, 0 off (default), 100 heat
 * @param subfunction SUPLA_HVAC_SUBFUNCTION_. unsigned byte
 * @param temperatureSetpointChangeSwitchesToManualMode 0 - off, 1 - on (def). unsigned byte
 * @param auxMinMaxSetpointEnabled 0 - off (default), 1 - on For HEAT_COOL thermostats we have two outpus. They can either use shared output for heating/cooling action and second output for heat vs cool mode selection, or they can use separate outputs - one for heating and one for cooling. unsigned byte
 * @param useSeparateHeatCoolOutputs 0 - off (default), 1 - on. unsigned byte
 * @param parameterFlags
 * @param masterThermostatChannelId
 * @param masterThermostatIsSet
 * @param masterThermostatChannelNo
 * @param heatOrColdSourceSwitchChannelId
 * @param pumpSwitchChannelId
 * @param temperatureControlType SUPLA_HVAC_TEMPERATURE_CONTROL_TYPE_. unsigned byte
 * @param localUILockingCapabilities LOCAL_UI_LOCK_*. unsigned char
 * @param localUILock LOCAL_UI_LOCK_*. unsigned char
 * @param minAllowedTemperatureSetpointFromLocalUI min/max allowed parameters are used only with LocalUILock &amp; LOCAL_UI_LOCK_TEMPERATURE
 * @param maxAllowedTemperatureSetpointFromLocalUI min/max allowed parameters are used only with LocalUILock &amp; LOCAL_UI_LOCK_TEMPERATURE
 * @param temperatures - sizeof(HvacParameterFlags) - sizeof(_supla_int_t) - sizeof(_supla_int_t) - sizeof(_supla_int_t)]. unsigned byte
 */
public record ChannelConfigHVAC(
        Integer mainThermometerChannelId,
        Short mainThermometerChannelNo,
        Integer auxThermometerChannelId,
        Short auxThermometerChannelNo,
        Integer binarySensorChannelId,
        Short binarySensorChannelNo,
        short auxThermometerType,
        short antiFreezeAndOverheatProtectionEnabled,
        int availableAlgorithms,
        int usedAlgorithm,
        int minOnTimeS,
        int minOffTimeS,
        byte outputValueOnError,
        short subfunction,
        short temperatureSetpointChangeSwitchesToManualMode,
        short auxMinMaxSetpointEnabled,
        short useSeparateHeatCoolOutputs,
        HvacParameterFlags parameterFlags,
        Integer masterThermostatChannelId,
        Short masterThermostatIsSet,
        Short masterThermostatChannelNo,
        Integer heatOrColdSourceSwitchChannelId,
        Integer pumpSwitchChannelId,
        short temperatureControlType,
        short localUILockingCapabilities,
        short localUILock,
        short minAllowedTemperatureSetpointFromLocalUI,
        short maxAllowedTemperatureSetpointFromLocalUI,
        HVACTemperatureCfg temperatures)
        implements ProtoWithSize {
    public static final int RESERVED_SIZE =
            48 - HvacParameterFlags.SIZE - INT_SIZE * 3 - BYTE_SIZE * 3 - SHORT_SIZE * 2;

    public ChannelConfigHVAC {
        mainThermometerChannelNo = unsigned(mainThermometerChannelNo);
        unionCheck(mainThermometerChannelId, mainThermometerChannelNo);
        auxThermometerChannelNo = unsigned(auxThermometerChannelNo);
        unionCheck(auxThermometerChannelId, auxThermometerChannelNo);
        binarySensorChannelNo = unsigned(binarySensorChannelNo);
        unionCheck(binarySensorChannelId, binarySensorChannelNo);
        auxThermometerType = unsigned(auxThermometerType);
        antiFreezeAndOverheatProtectionEnabled = unsigned(antiFreezeAndOverheatProtectionEnabled);
        availableAlgorithms = unsigned(availableAlgorithms);
        usedAlgorithm = unsigned(usedAlgorithm);
        minOnTimeS = unsigned(minOnTimeS);
        minOffTimeS = unsigned(minOffTimeS);
        subfunction = unsigned(subfunction);
        temperatureSetpointChangeSwitchesToManualMode =
                unsigned(temperatureSetpointChangeSwitchesToManualMode);
        auxMinMaxSetpointEnabled = unsigned(auxMinMaxSetpointEnabled);
        useSeparateHeatCoolOutputs = unsigned(useSeparateHeatCoolOutputs);
        if ((masterThermostatIsSet == null && masterThermostatChannelNo != null)
                || (masterThermostatIsSet != null && masterThermostatChannelNo == null)) {
            throw new IllegalArgumentException(
                    "if `masterThermostatIsSet` is set then `masterThermostatChannelNo` also needs"
                            + " to be set (and vice versa). "
                            + format(
                                    "masterThermostatIsSet=%s, masterThermostatChannelNo=%s",
                                    masterThermostatIsSet, masterThermostatChannelNo));
        }
        unionCheck(masterThermostatChannelId, masterThermostatIsSet);
        unionCheck(heatOrColdSourceSwitchChannelId);
        unionCheck(pumpSwitchChannelId);
        temperatureControlType = unsigned(temperatureControlType);
        // here is manual fix
        // length was `48` instead of `48 - HvacParameterFlags.SIZE - INT_SIZE * 3`
        localUILockingCapabilities = unsigned(localUILockingCapabilities);
        localUILock = unsigned(localUILock);
    }

    /* no call type */

    @Override
    public int protoSize() {
        return unionSize(
                        INT_SIZE // mainThermometerChannelId
                        ,
                        CHAR_SIZE // mainThermometerChannelNo
                        )
                + unionSize(
                        INT_SIZE // auxThermometerChannelId
                        ,
                        CHAR_SIZE // auxThermometerChannelNo
                        )
                + unionSize(
                        INT_SIZE // binarySensorChannelId
                        ,
                        CHAR_SIZE // binarySensorChannelNo
                        )
                + CHAR_SIZE // auxThermometerType
                + CHAR_SIZE // antiFreezeAndOverheatProtectionEnabled
                + SHORT_SIZE // availableAlgorithms
                + SHORT_SIZE // usedAlgorithm
                + SHORT_SIZE // minOnTimeS
                + SHORT_SIZE // minOffTimeS
                + BYTE_SIZE // outputValueOnError
                + CHAR_SIZE // subfunction
                + CHAR_SIZE // temperatureSetpointChangeSwitchesToManualMode
                + CHAR_SIZE // auxMinMaxSetpointEnabled
                + CHAR_SIZE // useSeparateHeatCoolOutputs
                + parameterFlags.protoSize() // parameterFlags
                + unionSize(
                        INT_SIZE // masterThermostatChannelId
                        )
                + unionSize(
                        INT_SIZE // heatOrColdSourceSwitchChannelId
                        )
                + unionSize(
                        INT_SIZE // pumpSwitchChannelId
                        )
                + RESERVED_SIZE * BYTE_SIZE // reserved
                + temperatures.protoSize() // temperatures
                + CHAR_SIZE // localUILockingCapabilities;
                + CHAR_SIZE // localUILock;
                + SHORT_SIZE // minAllowedTemperatureSetpointFromLocalUI;
                + SHORT_SIZE // maxAllowedTemperatureSetpointFromLocalUI;
        ;
    }
}
