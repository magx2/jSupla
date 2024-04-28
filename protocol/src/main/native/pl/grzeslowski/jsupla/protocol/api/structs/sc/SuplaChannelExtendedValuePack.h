// TSC_SuplaChannelExtendedValuePack
  _supla_int_t count;
  _supla_int_t total_left;
  unsigned _supla_int_t pack_size;

  char pack[SUPLA_CHANNELEXTENDEDVALUE_PACK_MAXDATASIZE];  // Last variable in struct!

// instruction extends ServerClient SUPLA_SC_CALL_CHANNELEXTENDEDVALUE_PACK_UPDATE 