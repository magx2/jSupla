// instruction name TSC_SuplaRegisterClientResult_C
  _supla_int_t result_code;
  _supla_int_t ClientID;
  short LocationCount;
  short ChannelCount;
  short ChannelGroupCount;
  short SceneCount;
  _supla_int_t Flags;
  unsigned char activity_timeout;
  unsigned char version;
  unsigned char version_min;
  unsigned _supla_int_t serverUnixTimestamp;  // current server time

// instruction extends ServerClient SUPLA_SC_CALL_REGISTER_CLIENT_RESULT_C 