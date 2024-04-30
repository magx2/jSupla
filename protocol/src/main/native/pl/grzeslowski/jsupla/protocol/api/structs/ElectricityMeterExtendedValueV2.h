  unsigned _supla_int64_t total_forward_active_energy[3];    // * 0.00001 kWh
  unsigned _supla_int64_t total_reverse_active_energy[3];    // * 0.00001 kWh
  unsigned _supla_int64_t total_forward_reactive_energy[3];  // * 0.00001 kvarh
  unsigned _supla_int64_t total_reverse_reactive_energy[3];  // * 0.00001 kvarh
  unsigned _supla_int64_t total_forward_active_energy_balanced;  // * 0.00001 kWh  Vector phase-to-phase balancing
  unsigned _supla_int64_t total_reverse_active_energy_balanced;  // * 0.00001 kWh Vector phase-to-phase balancing

  
  _supla_int_t total_cost;           // * 0.01 <p> The price per unit, total cost and currency is overwritten by the server <p> total_cost == SUM(total_forward_active_energy[n] * price_per_unit
  _supla_int_t total_cost_balanced;  // * 0.01
  _supla_int_t price_per_unit;       // * 0.0001
  
  char currency[3];// Currency Code A https://www.nationsonline.org/oneworld/currencies.htm

  _supla_int_t measured_values;
  _supla_int_t period;  // Approximate period between measurements in seconds
  _supla_int_t m_count;
  TElectricityMeter_Measurement m[EM_MEASUREMENT_COUNT];  // Last variable in struct!
// instruction name TElectricityMeter_ExtendedValue_V2                     
// instruction version 12
// instruction map TElectricityMeter_Measurement ElectricityMeterMeasurement
// instruction precondition m length mCount