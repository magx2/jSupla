  // server -> client
  char EOL;  // End Of List

  _supla_int_t Id;
  _supla_int_t DeviceID;
  _supla_int_t LocationID;
  _supla_int_t Type;
  _supla_int_t Func;
  _supla_int_t AltIcon;
  _supla_int_t UserIcon;
  _supla_int16_t ManufacturerID;
  _supla_int16_t ProductID;

  unsigned _supla_int_t Flags;
  unsigned char ProtocolVersion;
  char online;

  TSuplaChannelValue_B value;

  unsigned _supla_int_t CaptionSize;  // including the terminating null byte ('\0')
  char Caption[SUPLA_CHANNEL_CAPTION_MAXSIZE];  // Last variable in struct!

// instruction name TSC_SuplaChannel_D
// instruction map TSuplaChannelValue_B pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValueB
// instruction precondition caption length (int)captionSize
// !instruction extends ServerClient 