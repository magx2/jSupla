  char position;  // -1 - not set (actual behavior is device specific) <p> 0 - STOP <p> 1 - DOWN <p> 2 - UP <p> 3 - DOWN_OR_STOP <p> 4 - UP_OR_STOP <p> 5 - STEP_BY_STEP <p> 10-110 - target position + 10
  char tilt;      // -1 - not set (actual behavior is device specific) <p> 10-110 - target position + 10
  char reserved[6];
// instruction name TCSD_FacadeBlindValue
// instruction javadoc Facade blind channel value payload