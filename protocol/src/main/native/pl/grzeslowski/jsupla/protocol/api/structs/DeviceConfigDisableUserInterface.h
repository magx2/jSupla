  unsigned char DisableUserInterface;  // 0 - false (local UI enabled) <p> 1 - true (local UI disabled) <p> 2 - partial

  unsigned _supla_int16_t minAllowedTemperatureSetpointFromLocalUI;  // min allowed parameters are mandatory for "partial" variant
  unsigned _supla_int16_t maxAllowedTemperatureSetpointFromLocalUI;  // max allowed parameters are mandatory for "partial" variant
// i name TDeviceConfig_DisableUserInterface
// i version 21