  _supla_int_t ChannelId;
  _supla_int_t Func;
  unsigned char ConfigType;  // SUPLA_CONFIG_TYPE_
  unsigned _supla_int16_t ConfigSize;
  char Config[SUPLA_CHANNEL_CONFIG_MAXSIZE];  // TChannelConfig_*
// i name TSCS_ChannelConfig
// i javadoc SUPLA_CS_CALL_SET_CHANNEL_CONFIG
//i version 21