  unsigned _supla_int16_t year;
  unsigned char month;
  unsigned char day;
  unsigned char dayOfWeek;  // 1 = Sunday, 2 = Monday, …, 7 = Saturday
  unsigned char hour;
  unsigned char min;
  unsigned char sec;
  unsigned _supla_int_t timezoneSize;  // including the terminating null byte ('\0')
  char timezone[SUPLA_TIMEZONE_MAXSIZE];  // Last variable in struct!
// i name TSDC_UserLocalTimeResult
