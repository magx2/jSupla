// TDS_SuplaRegisterDevice_E

  char Email[SUPLA_EMAIL_MAXSIZE];  // UTF8
  char AuthKey[SUPLA_AUTHKEY_SIZE];

  char GUID[SUPLA_GUID_SIZE];

  char Name[SUPLA_DEVICE_NAME_MAXSIZE];  // UTF8
  char SoftVer[SUPLA_SOFTVER_MAXSIZE];

  char ServerName[SUPLA_SERVER_NAME_MAXSIZE];

  _supla_int_t Flags;  // SUPLA_DEVICE_FLAG_*
  _supla_int16_t ManufacturerID;
  _supla_int16_t ProductID;

  unsigned char channel_count;
  TDS_SuplaDeviceChannel_C channels[SUPLA_CHANNELMAXCOUNT];  // Last variable in struct!
  
// instruction map TDS_SuplaDeviceChannel_C SuplaDeviceChannelC 
// instruction import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelC
// instruction extends DeviceServer SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED
// instruction precondition channels length channelCount