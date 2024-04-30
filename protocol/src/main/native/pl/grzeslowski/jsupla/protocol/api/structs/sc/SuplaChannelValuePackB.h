  _supla_int_t count;
  _supla_int_t total_left;

  TSC_SuplaChannelValue_B items[SUPLA_CHANNELVALUE_PACK_MAXCOUNT];  // Last variable in struct!
  
// instruction name TSC_SuplaChannelValuePack_B
// instruction map TSC_SuplaChannelValue_B SuplaChannelValueB 
// instruction import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValueB
// instruction extends ServerClient SUPLA_SC_CALL_CHANNELVALUE_PACK_UPDATE_B
// instruction precondition items length count