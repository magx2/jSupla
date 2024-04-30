  unsigned char Result;      // SUPLA_CONFIG_RESULT_*. It matters when it is a response to SUPLA_CS_CALL_GET_DEVICE_CONFIG
  TSCS_DeviceConfig Config;  // Last variable in struct!
// i name TSC_DeviceConfigUpdateOrResult
// i javadoc SUPLA_SC_CALL_DEVICE_CONFIG_UPDATE_OR_RESULT
// i map TSCS_DeviceConfig pl.grzeslowski.jsupla.protocol.api.structs.scs.DeviceConfig
// i decoder false
// i encoder false