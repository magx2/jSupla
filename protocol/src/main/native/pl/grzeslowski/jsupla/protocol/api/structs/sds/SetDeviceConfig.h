  unsigned char EndOfDataFlag;  // 1 - last message; 0 - more messages will come
  unsigned char zero[8];        // for future use
  unsigned _supla_int64_t AvailableFields;             // bit map of SUPLA_DEVICE_CONFIG_FIELD_
  unsigned _supla_int64_t Fields;  // bit map of SUPLA_DEVICE_CONFIG_FIELD_
  unsigned _supla_int16_t ConfigSize;
  char Config[SUPLA_DEVICE_CONFIG_MAXSIZE];  // Last variable in struct!
// i name TSDS_SetDeviceConfig
// i version 21
// i javadoc SUPLA_DS_CALL_SET_DEVICE_CONFIG <p> SUPLA_SD_CALL_SET_DEVICE_CONFIG <p> Config field should contain fields stored in order as they appear in <p> Fields parameter. Size of parameter depends on Field type.