
  _supla_int_t total_cost;      // * 0.01  <p> The price per unit, total cost and currency is overwritten by the server <p> total_cost = calculated_value * price_per_unit
  _supla_int_t price_per_unit;  // * 0.0001
  
  char currency[3];// Currency Code A https://www.nationsonline.org/oneworld/currencies.htm
  char custom_unit[9];  // UTF8 including the terminating null byte ('\0')

  _supla_int_t impulses_per_unit;
  unsigned _supla_int64_t counter;
  _supla_int64_t calculated_value;   // * 0.001
// instruction name TSC_ImpulseCounter_ExtendedValue
// instruction version 10
// todo need to set it instruction extends ServerClient  