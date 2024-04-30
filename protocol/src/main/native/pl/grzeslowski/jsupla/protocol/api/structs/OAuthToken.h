  unsigned _supla_int_t ExpiresIn;
  unsigned _supla_int_t TokenSize;  // including the terminating null byte ('\0')
  char Token[SUPLA_OAUTH_TOKEN_MAXSIZE];  // Last variable in struct!
// instruction name TSC_OAuthToken
// instruction version 10