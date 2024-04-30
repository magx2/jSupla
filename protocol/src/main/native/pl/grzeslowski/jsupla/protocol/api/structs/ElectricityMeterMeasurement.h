  // 3 phases
  unsigned _supla_int16_t freq;        // * 0.01 Hz
  unsigned _supla_int16_t voltage[3];  // * 0.01 V
  unsigned _supla_int16_t current[3];  // * 0.001A (0.01A WHEN EM_VAR_CURRENT_OVER_65A)
  _supla_int_t power_active[3];  // * 0.00001W (0.01kW WHEN EM_VAR_POWER_ACTIVE_KW)
  _supla_int_t power_reactive[3];  // * 0.00001var (0.01kvar WHEN EM_VAR_POWER_REACTIVE_KVAR)
  _supla_int_t power_apparent[3];  // * 0.00001VA (0.01kVA WHEN EM_VAR_POWER_APPARENT_KVA)
  _supla_int16_t power_factor[3];  // * 0.001
  _supla_int16_t phase_angle[3];   // * 0.1 degree
// instruction name TElectricityMeter_Measurement;   
// instruction version 10
// instruction size static