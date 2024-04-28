  _supla_int_t DeviceId;
  unsigned char EndOfDataFlag;  // 1 - last message; 0 - more messages will come
  unsigned char zero[8];        // for future use
  unsigned _supla_int64_t AvailableFields;             // bit map of SUPLA_DEVICE_CONFIG_FIELD_
  unsigned _supla_int64_t Fields;  // bit map of SUPLA_DEVICE_CONFIG_FIELD_
  unsigned _supla_int16_t ConfigSize;
  char Config[SUPLA_DEVICE_CONFIG_MAXSIZE];  // Last variable in struct!
// i name TSCS_DeviceConfig
// i version 21
// i precondition config length configSize