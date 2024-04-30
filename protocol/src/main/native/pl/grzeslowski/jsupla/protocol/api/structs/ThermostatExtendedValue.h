  unsigned char Fields;
  _supla_int16_t MeasuredTemperature[10];  // * 0.01
  _supla_int16_t PresetTemperature[10];    // * 0.01
  _supla_int16_t Flags[8];
  _supla_int16_t Values[8];
  TThermostat_Time Time;
  TThermostat_Schedule Schedule;  // 7 days x 24h (4bit/hour)
// i name TThermostat_ExtendedValue
// i version 11
// i map TThermostat_Time ThermostatTime
// i map TThermostat_Schedule ThermostatSchedule
// i decoder false
// i encoder false