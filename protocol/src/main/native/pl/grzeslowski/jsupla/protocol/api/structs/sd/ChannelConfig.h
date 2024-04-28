  unsigned char ChannelNumber;
  _supla_int_t Func;
  unsigned char ConfigType;  // SUPLA_CONFIG_TYPE_
  unsigned _supla_int16_t ConfigSize;
  char Config[SUPLA_CHANNEL_CONFIG_MAXSIZE];  // TChannelConfig_*
// i name TSD_ChannelConfig
// i version 16
// i javadoc SUPLA_SD_CALL_GET_CHANNEL_CONFIG_RESULT <p> SUPLA_DS_CALL_SET_CHANNEL_CONFIG <p> SUPLA_SD_CALL_SET_CHANNEL_CONFIG