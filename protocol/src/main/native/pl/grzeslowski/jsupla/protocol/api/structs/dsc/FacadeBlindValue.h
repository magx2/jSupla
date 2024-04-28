  char position;  // -1 == calibration. -1 - 100%, DSC
  char tilt;      // -1 == not used/calibration, -1 - 100%, DSC
  char reserved;
  _supla_int16_t flags;             // DSC
  unsigned char tilt_0_angle;       // SC
  unsigned char tilt_100_angle;     // SC
  unsigned char facade_blind_type;  // DSC SUPLA_FACADEBLIND_TYPE_*
// instruction name TDSC_FacadeBlindValue
// instruction javadoc Facade blind channel value payload