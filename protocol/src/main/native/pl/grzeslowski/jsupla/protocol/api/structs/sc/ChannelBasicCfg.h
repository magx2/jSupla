  char DeviceName[SUPLA_DEVICE_NAME_MAXSIZE];  // UTF8
  char DeviceSoftVer[SUPLA_SOFTVER_MAXSIZE];
  _supla_int_t DeviceID;
  _supla_int_t DeviceFlags;
  _supla_int16_t ManufacturerID;
  _supla_int16_t ProductID;

  _supla_int_t ID;
  unsigned char Number;
  _supla_int_t Type;
  _supla_int_t Func;
  _supla_int_t FuncList;

  unsigned _supla_int_t ChannelFlags;
  unsigned _supla_int_t CaptionSize;  // including the terminating null byte ('\0')
  char Caption[SUPLA_CHANNEL_CAPTION_MAXSIZE];  // Last variable in struct!
// i name TSC_ChannelBasicCfg;                          
// i version 12
// i precondition caption length (int)captionSize