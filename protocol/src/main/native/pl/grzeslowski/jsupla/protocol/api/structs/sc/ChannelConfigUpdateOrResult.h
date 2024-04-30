  unsigned char Result;       // SUPLA_CONFIG_RESULT_*. It matters when it is a response to SUPLA_CS_CALL_GET_CHANNEL_CONFIG or SUPLA_CS_CALL_SET_CHANNEL_CONFIG
  TSCS_ChannelConfig Config;  // Last variable in struct!
// i name TSC_ChannelConfigUpdateOrResult
// i javadoc SUPLA_SC_CALL_CHANNEL_CONFIG_UPDATE 
// i map TSCS_ChannelConfig pl.grzeslowski.jsupla.protocol.api.structs.scs.ChannelConfig
// i decoder false
// i encoder false