package pl.grzeslowski.jsupla.protocol.api.structs;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.*;

/**
 * Channel numbers for thermometer config. Channels have to be local andnumbering is the same as for registration message
 *
 * <p>
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
 * unsigned char Reserved[48 - sizeof(HvacParameterFlags) -
 * sizeof(_supla_int_t) - sizeof(_supla_int_t) -
 * sizeof(_supla_int_t)];
 * THVACTemperatureCfg Temperatures;
 * } TChannelConfig_HVAC;
 * </pre>
 */
@lombok.EqualsAndHashCode
@lombok.ToString
@javax.annotation.Generated(value = "Struct original name: TChannelConfig_HVAC", date = "2024-10-20T23:38:23.496+02:00[Europe/Belgrade]")
public class ChannelConfigHVAC implements ProtoWithSize {

    public final Integer mainThermometerChannelId;
    /**
     * unsigned Byte
     */
    public final Short mainThermometerChannelNo;
    public final Integer auxThermometerChannelId;
    /**
     * If the channel number points to itself, it
     * <p>
     * unsigned Byte
     */
    public final Short auxThermometerChannelNo;
    public final Integer binarySensorChannelId;
    /**
     * If the channel number points to itself, it
     * <p>
     * unsigned Byte
     */
    public final Short binarySensorChannelNo;
    /**
     * unsigned byte
     */
    public final short auxThermometerType;
    /**
     * bit map SUPLA_HVAC_ALGORITHM_ (readonly)
     * <p>
     * unsigned byte
     */
    public final short antiFreezeAndOverheatProtectionEnabled;
    /**
     * only one value of SUPLA_HVAC_ALGORITHM_
     * <p>
     * unsigned short
     */
    public final int availableAlgorithms;
    /**
     * Below Min TimeS parameters defines minimum time of relay/output to be be disabled or enabled in seconds. It is used to prevent to frequent relay state change. Allowed values are 0-600 (10 minutes)
     * <p>
     * unsigned short
     */
    public final int usedAlgorithm;
    /**
     * minimum allowed time for output to be enabled
     * <p>
     * unsigned short
     */
    public final int minOnTimeS;
    /**
     * minimum allowed time for output to be disabled
     * <p>
     * unsigned short
     */
    public final int minOffTimeS;
    /**
     * -100 cool, 0 off (default), 100 heat
     */
    public final byte outputValueOnError;
    /**
     * SUPLA_HVAC_SUBFUNCTION_
     * <p>
     * unsigned byte
     */
    public final short subfunction;
    /**
     * 0 - off, 1 - on (def)
     * <p>
     * unsigned byte
     */
    public final short temperatureSetpointChangeSwitchesToManualMode;
    /**
     * 0 - off (default), 1 - on For HEAT_COOL thermostats we have two outpus. They can either use shared output for heating/cooling action and second output for heat vs cool mode selection, or they can use separate outputs - one for heating and one for cooling
     * <p>
     * unsigned byte
     */
    public final short auxMinMaxSetpointEnabled;
    /**
     * 0 - off (default), 1 - on
     * <p>
     * unsigned byte
     */
    public final short useSeparateHeatCoolOutputs;
    public final HvacParameterFlags parameterFlags;
    public final Integer masterThermostatChannelId;
    public final Integer heatOrColdSourceSwitchChannelId;
    public final Integer pumpSwitchChannelId;
    /**
     * - sizeof(HvacParameterFlags) - sizeof(_supla_int_t) - sizeof(_supla_int_t) - sizeof(_supla_int_t)]
     * <p>
     * unsigned byte
     */
    public final short[] reserved;
    public final HVACTemperatureCfg temperatures;

    public ChannelConfigHVAC(Integer mainThermometerChannelId,
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
                             Integer heatOrColdSourceSwitchChannelId,
                             Integer pumpSwitchChannelId,
                             short[] reserved,
                             HVACTemperatureCfg temperatures) {
        this.mainThermometerChannelId = mainThermometerChannelId;
        this.mainThermometerChannelNo = unsigned(mainThermometerChannelNo);
        unionCheck(mainThermometerChannelId, mainThermometerChannelNo);
        this.auxThermometerChannelId = auxThermometerChannelId;
        this.auxThermometerChannelNo = unsigned(auxThermometerChannelNo);
        unionCheck(auxThermometerChannelId, auxThermometerChannelNo);
        this.binarySensorChannelId = binarySensorChannelId;
        this.binarySensorChannelNo = unsigned(binarySensorChannelNo);
        unionCheck(binarySensorChannelId, binarySensorChannelNo);
        this.auxThermometerType = unsigned(auxThermometerType);
        this.antiFreezeAndOverheatProtectionEnabled = unsigned(antiFreezeAndOverheatProtectionEnabled);
        this.availableAlgorithms = unsigned(availableAlgorithms);
        this.usedAlgorithm = unsigned(usedAlgorithm);
        this.minOnTimeS = unsigned(minOnTimeS);
        this.minOffTimeS = unsigned(minOffTimeS);
        this.outputValueOnError = outputValueOnError;
        this.subfunction = unsigned(subfunction);
        this.temperatureSetpointChangeSwitchesToManualMode = unsigned(temperatureSetpointChangeSwitchesToManualMode);
        this.auxMinMaxSetpointEnabled = unsigned(auxMinMaxSetpointEnabled);
        this.useSeparateHeatCoolOutputs = unsigned(useSeparateHeatCoolOutputs);
        this.parameterFlags = parameterFlags;
        this.masterThermostatChannelId = masterThermostatChannelId;
        unionCheck(masterThermostatChannelId);
        this.heatOrColdSourceSwitchChannelId = heatOrColdSourceSwitchChannelId;
        unionCheck(heatOrColdSourceSwitchChannelId);
        this.pumpSwitchChannelId = pumpSwitchChannelId;
        unionCheck(pumpSwitchChannelId);
        // here is manual fix
        // length was `48` instead of `48 - HvacParameterFlags.SIZE - INT_SIZE * 3`
        this.reserved = unsigned(checkArrayLength(reserved, 48 - HvacParameterFlags.SIZE - INT_SIZE * 3));
        this.temperatures = temperatures;
    }

    /* no call type */

    @Override
    public int size() {
        return pl.grzeslowski.jsupla.protocol.api.JavaConsts.unionSize(
            INT_SIZE  // mainThermometerChannelId
            , CHAR_SIZE  // mainThermometerChannelNo
        )
            + pl.grzeslowski.jsupla.protocol.api.JavaConsts.unionSize(
            INT_SIZE  // auxThermometerChannelId
            , CHAR_SIZE  // auxThermometerChannelNo
        )
            + pl.grzeslowski.jsupla.protocol.api.JavaConsts.unionSize(
            INT_SIZE  // binarySensorChannelId
            , CHAR_SIZE  // binarySensorChannelNo
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
            + parameterFlags.size() // parameterFlags
            + pl.grzeslowski.jsupla.protocol.api.JavaConsts.unionSize(
            INT_SIZE  // masterThermostatChannelId
        )
            + pl.grzeslowski.jsupla.protocol.api.JavaConsts.unionSize(
            INT_SIZE  // heatOrColdSourceSwitchChannelId
        )
            + pl.grzeslowski.jsupla.protocol.api.JavaConsts.unionSize(
            INT_SIZE  // pumpSwitchChannelId
        )
            + (int) 48 * BYTE_SIZE // reserved
            + temperatures.size() // temperatures
            ;
    }
}
