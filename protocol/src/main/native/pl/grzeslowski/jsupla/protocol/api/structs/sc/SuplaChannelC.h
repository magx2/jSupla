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

  TSuplaChannelValue value;

  unsigned _supla_int_t CaptionSize;  // including the terminating null byte ('\0')
  char Caption[SUPLA_CHANNEL_CAPTION_MAXSIZE];  // Last variable in struct!
  
// instruction name TSC_SuplaChannel_C
// instruction map TSuplaChannelValue pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue 
// !instruction extends ServerClient null; //TODO
// I dont know what to set as call type
// instruction precondition caption length (int)captionSize
// i decoder false
// i encoder false