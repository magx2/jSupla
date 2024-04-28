// taken from https://github.com/SUPLA/supla-core/blob/master/supla-common/proto.h#L176
#define SUPLA_PROTO_VERSION 23
#define SUPLA_PROTO_VERSION_MIN 1
#define SUPLA_MAX_DATA_SIZE 10240
#define SUPLA_RC_MAX_DEV_COUNT 50
#define SUPLA_SOFTVER_MAXSIZE 21

#define SUPLA_CAPTION_MAXSIZE 401

#define SUPLA_GUID_SIZE 16
#define SUPLA_GUID_HEXSIZE 33
#define SUPLA_LOCATION_PWD_MAXSIZE 33
#define SUPLA_ACCESSID_PWD_MAXSIZE 33
#define SUPLA_LOCATION_CAPTION_MAXSIZE SUPLA_CAPTION_MAXSIZE
#define SUPLA_LOCATIONPACK_MAXCOUNT 20
#define SUPLA_CHANNEL_CAPTION_MAXSIZE SUPLA_CAPTION_MAXSIZE
#define SUPLA_CHANNEL_GROUP_CAPTION_MAXSIZE SUPLA_CAPTION_MAXSIZE
#define SUPLA_CHANNELPACK_MAXCOUNT 20
#define SUPLA_URL_HOST_MAXSIZE 101
#define SUPLA_URL_PATH_MAXSIZE 101
#define SUPLA_SERVER_NAME_MAXSIZE 65
#define SUPLA_EMAIL_MAXSIZE 256                     // ver. >= 7
#define SUPLA_PASSWORD_MAXSIZE 64                   // ver. >= 10
#define SUPLA_AUTHKEY_SIZE 16                       // ver. >= 7
#define SUPLA_AUTHKEY_HEXSIZE 33                    // ver. >= 7
#define SUPLA_OAUTH_TOKEN_MAXSIZE 256               // ver. >= 10
#define SUPLA_CHANNELGROUP_PACK_MAXCOUNT 20         // ver. >= 9
#define SUPLA_CHANNELGROUP_CAPTION_MAXSIZE 401      // ver. >= 9
#define SUPLA_CHANNELVALUE_PACK_MAXCOUNT 20         // ver. >= 9
#define SUPLA_CHANNELEXTENDEDVALUE_PACK_MAXCOUNT 5  // ver. >= 10
#define SUPLA_CHANNELEXTENDEDVALUE_PACK_MAXDATASIZE (SUPLA_MAX_DATA_SIZE - 50)            // ver. >= 10
#define SUPLA_CALCFG_DATA_MAXSIZE 128   // ver. >= 10
#define SUPLA_TIMEZONE_MAXSIZE 51       // ver. >= 11
#define SUPLA_ACTION_PARAM_MAXSIZE 500  // ver. >= 18

#define SUPLA_CHANNELGROUP_RELATION_PACK_MAXCOUNT 100  // ver. >= 9

#define SUPLA_SCENE_CAPTION_MAXSIZE SUPLA_CAPTION_MAXSIZE  // ver. >= 18
#define SUPLA_SCENE_PACK_MAXCOUNT 20                       // ver. >= 18
#define SUPLA_SCENE_STATE_PACK_MAXCOUNT 20                 // ver. >= 18

#define SUPLA_CHANNEL_RELATION_PACK_MAXCOUNT 100  // ver. >= 21

// taken from https://github.com/SUPLA/supla-core/blob/master/supla-common/proto.h#L294
#define SUPLA_RESULT_RESPONSE_TIMEOUT -8
#define SUPLA_RESULT_CANT_CONNECT_TO_HOST -7
#define SUPLA_RESULT_HOST_NOT_FOUND -6
#define SUPLA_RESULT_CALL_NOT_ALLOWED -5
#define SUPLA_RESULT_DATA_TOO_LARGE -4
#define SUPLA_RESULT_BUFFER_OVERFLOW -3
#define SUPLA_RESULT_DATA_ERROR -2
#define SUPLA_RESULT_VERSION_ERROR -1
#define SUPLA_RESULT_FALSE 0
#define SUPLA_RESULT_TRUE 1

// SUPLA_RESULTCODE_ are sent in TSuplaDataPacket.data (unsigned char)
#define SUPLA_RESULTCODE_NONE 0
#define SUPLA_RESULTCODE_UNSUPORTED 1
#define SUPLA_RESULTCODE_FALSE 2
#define SUPLA_RESULTCODE_TRUE 3
#define SUPLA_RESULTCODE_TEMPORARILY_UNAVAILABLE 4
#define SUPLA_RESULTCODE_BAD_CREDENTIALS 5
#define SUPLA_RESULTCODE_LOCATION_CONFLICT 6
#define SUPLA_RESULTCODE_CHANNEL_CONFLICT 7
#define SUPLA_RESULTCODE_DEVICE_DISABLED 8
#define SUPLA_RESULTCODE_ACCESSID_DISABLED 9
#define SUPLA_RESULTCODE_LOCATION_DISABLED 10
#define SUPLA_RESULTCODE_CLIENT_DISABLED 11
#define SUPLA_RESULTCODE_CLIENT_LIMITEXCEEDED 12
#define SUPLA_RESULTCODE_DEVICE_LIMITEXCEEDED 13
#define SUPLA_RESULTCODE_GUID_ERROR 14
#define SUPLA_RESULTCODE_DEVICE_LOCKED 15                          // ver. >= 22
#define SUPLA_RESULTCODE_REGISTRATION_DISABLED 17                  // ver. >= 7
#define SUPLA_RESULTCODE_ACCESSID_NOT_ASSIGNED 18                  // ver. >= 7
#define SUPLA_RESULTCODE_AUTHKEY_ERROR 19                          // ver. >= 7
#define SUPLA_RESULTCODE_NO_LOCATION_AVAILABLE 20                  // ver. >= 7
#define SUPLA_RESULTCODE_USER_CONFLICT 21                          // Deprecated
#define SUPLA_RESULTCODE_UNAUTHORIZED 22                           // ver. >= 10
#define SUPLA_RESULTCODE_AUTHORIZED 23                             // ver. >= 10
#define SUPLA_RESULTCODE_NOT_ALLOWED 24                            // ver. >= 12
#define SUPLA_RESULTCODE_CHANNELNOTFOUND 25                        // ver. >= 12
#define SUPLA_RESULTCODE_UNKNOWN_ERROR 26                          // ver. >= 12
#define SUPLA_RESULTCODE_DENY_CHANNEL_BELONG_TO_GROUP 27           // ver. >= 12
#define SUPLA_RESULTCODE_DENY_CHANNEL_HAS_SCHEDULE 28              // ver. >= 12
#define SUPLA_RESULTCODE_DENY_CHANNEL_IS_ASSOCIETED_WITH_SCENE 29  // ver. >= 12
#define SUPLA_RESULTCODE_DENY_CHANNEL_IS_ASSOCIETED_WITH_ACTION_TRIGGER 30                                              // ver. >= 16
#define SUPLA_RESULTCODE_ACCESSID_INACTIVE 31     // ver. >= 17
#define SUPLA_RESULTCODE_CFG_MODE_REQUESTED 32    // ver. >= 18
#define SUPLA_RESULTCODE_ACTION_UNSUPPORTED 33    // ver. >= 19
#define SUPLA_RESULTCODE_SUBJECT_NOT_FOUND 34     // ver. >= 19
#define SUPLA_RESULTCODE_INCORRECT_PARAMETERS 35  // ver. >= 19
#define SUPLA_RESULTCODE_CLIENT_NOT_EXISTS 36     // ver. >= 19
#define SUPLA_RESULTCODE_COUNTRY_REJECTED 37
#define SUPLA_RESULTCODE_CHANNEL_IS_OFFLINE 38                    // ver. >= 19
#define SUPLA_RESULTCODE_NOT_REGISTERED 39                        // ver. >= 20
#define SUPLA_RESULTCODE_DENY_CHANNEL_IS_ASSOCIETED_WITH_VBT 40   // >= 20
#define SUPLA_RESULTCODE_DENY_CHANNEL_IS_ASSOCIETED_WITH_PUSH 41  // >= 20

#define SUPLA_OAUTH_RESULTCODE_ERROR 0         // ver. >= 10
#define SUPLA_OAUTH_RESULTCODE_SUCCESS 1       // ver. >= 10
#define SUPLA_OAUTH_TEMPORARILY_UNAVAILABLE 2  // ver. >= 10

#define SUPLA_DEVICE_NAME_MAXSIZE 201
#define SUPLA_CLIENT_NAME_MAXSIZE 201
#define SUPLA_SENDER_NAME_MAXSIZE 201
#define SUPLA_INITIATOR_NAME_MAXSIZE SUPLA_SENDER_NAME_MAXSIZE

#define SUPLA_CHANNELMAXCOUNT 128

#define SUPLA_CHANNELVALUE_SIZE 8

#define SUPLA_CHANNELEXTENDEDVALUE_SIZE 1024

#define SUPLA_CHANNELTYPE_SENSORNO 1000  // name DEPRECATED
// use BINARYSENSOR instead
#define SUPLA_CHANNELTYPE_BINARYSENSOR 1000
#define SUPLA_CHANNELTYPE_SENSORNC 1010        // DEPRECATED
#define SUPLA_CHANNELTYPE_DISTANCESENSOR 1020  // ver. >= 5
#define SUPLA_CHANNELTYPE_CALLBUTTON 1500      // ver. >= 4
#define SUPLA_CHANNELTYPE_RELAYHFD4 2000       // DEPRECATED
#define SUPLA_CHANNELTYPE_RELAYG5LA1A 2010     // DEPRECATED
#define SUPLA_CHANNELTYPE_2XRELAYG5LA1A 2020   // DEPRECATED
#define SUPLA_CHANNELTYPE_RELAY 2900
#define SUPLA_CHANNELTYPE_THERMOMETERDS18B20 3000  // DEPRECATED
#define SUPLA_CHANNELTYPE_DHT11 3010               // ver. >= 4  DEPRECATED
#define SUPLA_CHANNELTYPE_DHT22 3020               // ver. >= 4  DEPRECATED
#define SUPLA_CHANNELTYPE_DHT21 3022               // ver. >= 5  DEPRECATED
#define SUPLA_CHANNELTYPE_AM2302 3030              // ver. >= 4  DEPRECATED
#define SUPLA_CHANNELTYPE_AM2301 3032              // ver. >= 5  DEPRECATED

#define SUPLA_CHANNELTYPE_THERMOMETER 3034            // ver. >= 8
#define SUPLA_CHANNELTYPE_HUMIDITYSENSOR 3036         // ver. >= 8
#define SUPLA_CHANNELTYPE_HUMIDITYANDTEMPSENSOR 3038  // ver. >= 8
#define SUPLA_CHANNELTYPE_WINDSENSOR 3042             // ver. >= 8
#define SUPLA_CHANNELTYPE_PRESSURESENSOR 3044         // ver. >= 8
#define SUPLA_CHANNELTYPE_RAINSENSOR 3048             // ver. >= 8
#define SUPLA_CHANNELTYPE_WEIGHTSENSOR 3050           // ver. >= 8
#define SUPLA_CHANNELTYPE_WEATHER_STATION 3100        // ver. >= 8

#define SUPLA_CHANNELTYPE_DIMMER 4000            // ver. >= 4
#define SUPLA_CHANNELTYPE_RGBLEDCONTROLLER 4010  // ver. >= 4
#define SUPLA_CHANNELTYPE_DIMMERANDRGBLED 4020   // ver. >= 4

#define SUPLA_CHANNELTYPE_ELECTRICITY_METER 5000  // ver. >= 10
#define SUPLA_CHANNELTYPE_IMPULSE_COUNTER 5010    // ver. >= 10

#define SUPLA_CHANNELTYPE_THERMOSTAT 6000                   // ver. >= 11
#define SUPLA_CHANNELTYPE_THERMOSTAT_HEATPOL_HOMEPLUS 6010  // ver. >= 11
#define SUPLA_CHANNELTYPE_HVAC 6100                         // ver. >= 21

#define SUPLA_CHANNELTYPE_VALVE_OPENCLOSE 7000              // ver. >= 12
#define SUPLA_CHANNELTYPE_VALVE_PERCENTAGE 7010             // ver. >= 12
#define SUPLA_CHANNELTYPE_BRIDGE 8000                       // ver. >= 12
#define SUPLA_CHANNELTYPE_GENERAL_PURPOSE_MEASUREMENT 9000  // ver. >= 23
#define SUPLA_CHANNELTYPE_GENERAL_PURPOSE_METER 9010        // ver. >= 23
#define SUPLA_CHANNELTYPE_ENGINE 10000                      // ver. >= 12
#define SUPLA_CHANNELTYPE_ACTIONTRIGGER 11000               // ver. >= 16
#define SUPLA_CHANNELTYPE_DIGIGLASS 12000                   // ver. >= 12

#define SUPLA_CHANNELDRIVER_MCP23008 2

#define SUPLA_CHANNELFNC_NONE 0
#define SUPLA_CHANNELFNC_CONTROLLINGTHEGATEWAYLOCK 10
#define SUPLA_CHANNELFNC_CONTROLLINGTHEGATE 20
#define SUPLA_CHANNELFNC_CONTROLLINGTHEGARAGEDOOR 30
#define SUPLA_CHANNELFNC_THERMOMETER 40
#define SUPLA_CHANNELFNC_HUMIDITY 42
#define SUPLA_CHANNELFNC_HUMIDITYANDTEMPERATURE 45
#define SUPLA_CHANNELFNC_OPENINGSENSOR_GATEWAY 50
#define SUPLA_CHANNELFNC_OPENINGSENSOR_GATE 60
#define SUPLA_CHANNELFNC_OPENINGSENSOR_GARAGEDOOR 70
#define SUPLA_CHANNELFNC_NOLIQUIDSENSOR 80
#define SUPLA_CHANNELFNC_CONTROLLINGTHEDOORLOCK 90
#define SUPLA_CHANNELFNC_OPENINGSENSOR_DOOR 100
#define SUPLA_CHANNELFNC_CONTROLLINGTHEROLLERSHUTTER 110
#define SUPLA_CHANNELFNC_CONTROLLINGTHEROOFWINDOW 115  // ver. >= 13
#define SUPLA_CHANNELFNC_OPENINGSENSOR_ROLLERSHUTTER 120
#define SUPLA_CHANNELFNC_OPENINGSENSOR_ROOFWINDOW 125  // ver. >= 13
#define SUPLA_CHANNELFNC_POWERSWITCH 130
#define SUPLA_CHANNELFNC_LIGHTSWITCH 140
#define SUPLA_CHANNELFNC_RING 150
#define SUPLA_CHANNELFNC_ALARM 160
#define SUPLA_CHANNELFNC_NOTIFICATION 170
#define SUPLA_CHANNELFNC_DIMMER 180
#define SUPLA_CHANNELFNC_RGBLIGHTING 190
#define SUPLA_CHANNELFNC_DIMMERANDRGBLIGHTING 200
#define SUPLA_CHANNELFNC_DEPTHSENSOR 210                   // ver. >= 5
#define SUPLA_CHANNELFNC_DISTANCESENSOR 220                // ver. >= 5
#define SUPLA_CHANNELFNC_OPENINGSENSOR_WINDOW 230          // ver. >= 8
#define SUPLA_CHANNELFNC_HOTELCARDSENSOR 235               // ver. >= 21
#define SUPLA_CHANNELFNC_ALARMARMAMENTSENSOR 236           // ver. >= 21
#define SUPLA_CHANNELFNC_MAILSENSOR 240                    // ver. >= 8
#define SUPLA_CHANNELFNC_WINDSENSOR 250                    // ver. >= 8
#define SUPLA_CHANNELFNC_PRESSURESENSOR 260                // ver. >= 8
#define SUPLA_CHANNELFNC_RAINSENSOR 270                    // ver. >= 8
#define SUPLA_CHANNELFNC_WEIGHTSENSOR 280                  // ver. >= 8
#define SUPLA_CHANNELFNC_WEATHER_STATION 290               // ver. >= 8
#define SUPLA_CHANNELFNC_STAIRCASETIMER 300                // ver. >= 8
#define SUPLA_CHANNELFNC_ELECTRICITY_METER 310             // ver. >= 10
#define SUPLA_CHANNELFNC_IC_ELECTRICITY_METER 315          // ver. >= 12
#define SUPLA_CHANNELFNC_IC_GAS_METER 320                  // ver. >= 10
#define SUPLA_CHANNELFNC_IC_WATER_METER 330                // ver. >= 10
#define SUPLA_CHANNELFNC_IC_HEAT_METER 340                 // ver. >= 10
#define SUPLA_CHANNELFNC_IC_EVENTS 350                     // ver. >= 21
#define SUPLA_CHANNELFNC_IC_SECONDS 360                    // ver. >= 21
#define SUPLA_CHANNELFNC_THERMOSTAT_HEATPOL_HOMEPLUS 410   // ver. >= 11
#define SUPLA_CHANNELFNC_HVAC_THERMOSTAT 420               // ver. >= 21
#define SUPLA_CHANNELFNC_HVAC_THERMOSTAT_HEAT_COOL 422     // ver. >= 21
#define SUPLA_CHANNELFNC_HVAC_DRYER 423                    // ver. >= 21
#define SUPLA_CHANNELFNC_HVAC_FAN 424                      // ver. >= 21
#define SUPLA_CHANNELFNC_HVAC_THERMOSTAT_DIFFERENTIAL 425  // ver. >= 21
#define SUPLA_CHANNELFNC_HVAC_DOMESTIC_HOT_WATER 426       // ver. >= 21
#define SUPLA_CHANNELFNC_VALVE_OPENCLOSE 500               // ver. >= 12
#define SUPLA_CHANNELFNC_VALVE_PERCENTAGE 510              // ver. >= 12
#define SUPLA_CHANNELFNC_GENERAL_PURPOSE_MEASUREMENT 520   // ver. >= 23
#define SUPLA_CHANNELFNC_GENERAL_PURPOSE_METER 530         // ver. >= 23
#define SUPLA_CHANNELFNC_CONTROLLINGTHEENGINESPEED 600     // ver. >= 12
#define SUPLA_CHANNELFNC_ACTIONTRIGGER 700                 // ver. >= 16
#define SUPLA_CHANNELFNC_DIGIGLASS_HORIZONTAL 800          // ver. >= 14
#define SUPLA_CHANNELFNC_DIGIGLASS_VERTICAL 810            // ver. >= 14
#define SUPLA_CHANNELFNC_CONTROLLINGTHEFACADEBLIND 900     // ver. >= 17

#define SUPLA_BIT_FUNC_CONTROLLINGTHEGATEWAYLOCK 0x00000001
#define SUPLA_BIT_FUNC_CONTROLLINGTHEGATE 0x00000002
#define SUPLA_BIT_FUNC_CONTROLLINGTHEGARAGEDOOR 0x00000004
#define SUPLA_BIT_FUNC_CONTROLLINGTHEDOORLOCK 0x00000008
#define SUPLA_BIT_FUNC_CONTROLLINGTHEROLLERSHUTTER 0x00000010
#define SUPLA_BIT_FUNC_POWERSWITCH 0x00000020
#define SUPLA_BIT_FUNC_LIGHTSWITCH 0x00000040
#define SUPLA_BIT_FUNC_STAIRCASETIMER 0x00000080                // ver. >= 8
#define SUPLA_BIT_FUNC_THERMOMETER 0x00000100                   // ver. >= 12
#define SUPLA_BIT_FUNC_HUMIDITYANDTEMPERATURE 0x00000200        // ver. >= 12
#define SUPLA_BIT_FUNC_HUMIDITY 0x00000400                      // ver. >= 12
#define SUPLA_BIT_FUNC_WINDSENSOR 0x00000800                    // ver. >= 12
#define SUPLA_BIT_FUNC_PRESSURESENSOR 0x00001000                // ver. >= 12
#define SUPLA_BIT_FUNC_RAINSENSOR 0x00002000                    // ver. >= 12
#define SUPLA_BIT_FUNC_WEIGHTSENSOR 0x00004000                  // ver. >= 12
#define SUPLA_BIT_FUNC_CONTROLLINGTHEROOFWINDOW 0x00008000      // ver. >= 13
#define SUPLA_BIT_FUNC_CONTROLLINGTHEFACADEBLIND 0x00010000     // ver. >= 17
#define SUPLA_BIT_FUNC_HVAC_THERMOSTAT 0x00020000               // ver. >= 21
#define SUPLA_BIT_FUNC_HVAC_THERMOSTAT_HEAT_COOL 0x00040000     // ver. >= 21
#define SUPLA_BIT_FUNC_HVAC_THERMOSTAT_DIFFERENTIAL 0x00080000  // ver. >= 21
#define SUPLA_BIT_FUNC_HVAC_DOMESTIC_HOT_WATER 0x00100000       // ver. >= 21

#define SUPLA_EVENT_CONTROLLINGTHEGATEWAYLOCK 10
#define SUPLA_EVENT_CONTROLLINGTHEGATE 20
#define SUPLA_EVENT_CONTROLLINGTHEGARAGEDOOR 30
#define SUPLA_EVENT_CONTROLLINGTHEDOORLOCK 40
#define SUPLA_EVENT_CONTROLLINGTHEROLLERSHUTTER 50
#define SUPLA_EVENT_CONTROLLINGTHEROOFWINDOW 55
#define SUPLA_EVENT_POWERONOFF 60
#define SUPLA_EVENT_LIGHTONOFF 70
#define SUPLA_EVENT_STAIRCASETIMERONOFF 80       // ver. >= 9
#define SUPLA_EVENT_VALVEOPENCLOSE 90            // ver. >= 12
#define SUPLA_EVENT_SET_BRIDGE_VALUE_FAILED 100  // ver. >= 12

#define SUPLA_URL_PROTO_HTTP 0x01
#define SUPLA_URL_PROTO_HTTPS 0x02

#define SUPLA_PLATFORM_UNKNOWN 0
#define SUPLA_PLATFORM_ESP8266 1

#define SUPLA_TARGET_CHANNEL 0
#define SUPLA_TARGET_GROUP 1
#define SUPLA_TARGET_IODEVICE 2

#define SUPLA_MFR_UNKNOWN 0
#define SUPLA_MFR_ACSOFTWARE 1
#define SUPLA_MFR_TRANSCOM 2
#define SUPLA_MFR_LOGI 3
#define SUPLA_MFR_ZAMEL 4
#define SUPLA_MFR_NICE 5
#define SUPLA_MFR_ITEAD 6
#define SUPLA_MFR_DOYLETRATT 7
#define SUPLA_MFR_HEATPOL 8
#define SUPLA_MFR_FAKRO 9
#define SUPLA_MFR_PEVEKO 10
#define SUPLA_MFR_WEKTA 11
#define SUPLA_MFR_STA_SYSTEM 12
#define SUPLA_MFR_DGF 13
#define SUPLA_MFR_COMELIT 14
#define SUPLA_MFR_POLIER 15
#define SUPLA_MFR_ERGO_ENERGIA 16
#define SUPLA_MFR_SOMEF 17

// BIT map definition for TDS_SuplaRegisterDevice_*::Flags (32 bit)
#define SUPLA_DEVICE_FLAG_CALCFG_ENTER_CFG_MODE 0x0010    // ver. >= 17
#define SUPLA_DEVICE_FLAG_SLEEP_MODE_ENABLED 0x0020       // ver. >= 18
#define SUPLA_DEVICE_FLAG_CALCFG_SET_TIME 0x0040          // ver. >= 21
#define SUPLA_DEVICE_FLAG_DEVICE_CONFIG_SUPPORTED 0x0080  // ver. >= 21
#define SUPLA_DEVICE_FLAG_DEVICE_LOCKED 0x0100            // ver. >= 22

// BIT map definition for TDS_SuplaDeviceChannel_C::Flags (32 bit)
#define SUPLA_CHANNEL_FLAG_ZWAVE_BRIDGE 0x0001  // ver. >= 12
#define SUPLA_CHANNEL_FLAG_IR_BRIDGE 0x0002     // ver. >= 12
#define SUPLA_CHANNEL_FLAG_RF_BRIDGE 0x0004     // ver. >= 12
// Free bit for future use: 0x0008
#define SUPLA_CHANNEL_FLAG_CHART_TYPE_BAR 0x0010  // ver. >= 12 // DEPRECATED
#define SUPLA_CHANNEL_FLAG_CHART_DS_TYPE_DIFFERENTAL 0x0020  // ver. >= 12 // DEPRECATED
#define SUPLA_CHANNEL_FLAG_CHART_INTERPOLATE_MEASUREMENTS  0x0040                                                   // ver. >= 12 // DEPRECATED
#define SUPLA_CHANNEL_FLAG_RS_SBS_AND_STOP_ACTIONS 0x0080  // ver. >= 17
#define SUPLA_CHANNEL_FLAG_RGBW_COMMANDS_SUPPORTED 0x0100  // ver. >= 21
// Free bits for future use:  0x0200, 0x0400, 0x0800
#define SUPLA_CHANNEL_FLAG_RS_AUTO_CALIBRATION 0x1000    // ver. >= 15
#define SUPLA_CHANNEL_FLAG_CALCFG_RESET_COUNTERS 0x2000  // ver. >= 15
#define SUPLA_CHANNEL_FLAG_CALCFG_RECALIBRATE 0x4000     // ver. >= 15
// Free bits for future use: 0x8000
#define SUPLA_CHANNEL_FLAG_CHANNELSTATE 0x00010000                 // ver. >= 12
#define SUPLA_CHANNEL_FLAG_PHASE1_UNSUPPORTED 0x00020000           // ver. >= 12
#define SUPLA_CHANNEL_FLAG_PHASE2_UNSUPPORTED 0x00040000           // ver. >= 12
#define SUPLA_CHANNEL_FLAG_PHASE3_UNSUPPORTED 0x00080000           // ver. >= 12
#define SUPLA_CHANNEL_FLAG_TIME_SETTING_NOT_AVAILABLE 0x00100000   // ver. >= 12
#define SUPLA_CHANNEL_FLAG_RSA_ENCRYPTED_PIN_REQUIRED 0x00200000   // ver. >= 12
#define SUPLA_CHANNEL_FLAG_OFFLINE_DURING_REGISTRATION 0x00400000  // ver. >= 12
#define SUPLA_CHANNEL_FLAG_ZIGBEE_BRIDGE 0x00800000                // ver. >= 12
#define SUPLA_CHANNEL_FLAG_COUNTDOWN_TIMER_SUPPORTED 0x01000000    // ver. >= 12
#define SUPLA_CHANNEL_FLAG_LIGHTSOURCELIFESPAN_SETTABLE 0x02000000  // ver. >= 12
#define SUPLA_CHANNEL_FLAG_POSSIBLE_SLEEP_MODE_deprecated 0x04000000  // ver. >= 12  DEPRECATED
#define SUPLA_CHANNEL_FLAG_RUNTIME_CHANNEL_CONFIG_UPDATE 0x08000000                                           // ver. >= 21
#define SUPLA_CHANNEL_FLAG_WEEKLY_SCHEDULE 0x10000000  // ver. >= 21
#define SUPLA_CHANNEL_FLAG_HAS_PARENT 0x20000000       // ver. >= 21

// taken from https://github.com/SUPLA/supla-core/blob/master/supla-common/proto.h#L112
#define SUPLA_TAG_SIZE 5

#define EM_VAR_FREQ 0x0001
#define EM_VAR_VOLTAGE 0x0002
#define EM_VAR_CURRENT 0x0004
#define EM_VAR_POWER_ACTIVE 0x0008
#define EM_VAR_POWER_REACTIVE 0x0010
#define EM_VAR_POWER_APPARENT 0x0020
#define EM_VAR_POWER_FACTOR 0x0040
#define EM_VAR_PHASE_ANGLE 0x0080
#define EM_VAR_FORWARD_ACTIVE_ENERGY 0x0100
#define EM_VAR_REVERSE_ACTIVE_ENERGY 0x0200
#define EM_VAR_FORWARD_REACTIVE_ENERGY 0x0400
#define EM_VAR_REVERSE_REACTIVE_ENERGY 0x0800
#define EM_VAR_CURRENT_OVER_65A 0x1000
#define EM_VAR_FORWARD_ACTIVE_ENERGY_BALANCED 0x2000
#define EM_VAR_REVERSE_ACTIVE_ENERGY_BALANCED 0x4000

#define EM_VAR_VOLTAGE_PHASE_ANGLE_12 0x10000  // ver. >= 22
#define EM_VAR_VOLTAGE_PHASE_ANGLE_13 0x20000  // ver. >= 22
#define EM_VAR_VOLTAGE_PHASE_SEQUENCE 0x40000  // ver. >= 22
#define EM_VAR_CURRENT_PHASE_SEQUENCE 0x80000  // ver. >= 22

#define EM_VAR_POWER_ACTIVE_KW 0x100000
#define EM_VAR_POWER_REACTIVE_KVAR 0x200000
#define EM_VAR_POWER_APPARENT_KVA 0x400000

#define EM_PHASE_SEQUENCE_VOLTAGE 0x01
#define EM_PHASE_SEQUENCE_CURRENT 0x02

#define EM_MEASUREMENT_COUNT 5

#define EM_VALUE_FLAG_PHASE1_ON 0x01
#define EM_VALUE_FLAG_PHASE2_ON 0x02
#define EM_VALUE_FLAG_PHASE3_ON 0x04

#define RS_VALUE_FLAG_TILT_IS_SET 0x1
#define RS_VALUE_FLAG_CALIBRATION_FAILED 0x2
#define RS_VALUE_FLAG_CALIBRATION_LOST 0x4
#define RS_VALUE_FLAG_MOTOR_PROBLEM 0x8
#define RS_VALUE_FLAG_CALIBRATION_IN_PROGRESS 0x10

#define SUPLA_FACADEBLIND_TYPE_STANDS_IN_POSITION_WHILE_TILTING 1
#define SUPLA_FACADEBLIND_TYPE_CHANGES_POSITION_WHILE_TILTING 2
#define SUPLA_FACADEBLINE_TYPE_TILTS_ONLY_WHEN_FULLY_CLOSED 3
#define SUPLA_CALCFG_RESULT_FALSE 0                          // ver. >= 12
#define SUPLA_CALCFG_RESULT_TRUE 1                           // ver. >= 12
#define SUPLA_CALCFG_RESULT_DONE 2                           // ver. >= 12
#define SUPLA_CALCFG_RESULT_IN_PROGRESS 3                    // ver. >= 12
#define SUPLA_CALCFG_RESULT_NODE_FOUND 4                     // ver. >= 12
#define SUPLA_CALCFG_RESULT_SENDER_CONFLICT 100              // ver. >= 12
#define SUPLA_CALCFG_RESULT_TIMEOUT 101                      // ver. >= 12
#define SUPLA_CALCFG_RESULT_NOT_SUPPORTED 102                // ver. >= 12
#define SUPLA_CALCFG_RESULT_ID_NOT_EXISTS 103                // ver. >= 12
#define SUPLA_CALCFG_RESULT_UNAUTHORIZED 104                 // ver. >= 12
#define SUPLA_CALCFG_RESULT_DEBUG 105                        // ver. >= 12
#define SUPLA_CALCFG_RESULT_NOT_SUPPORTED_IN_SLAVE_MODE 106  // ver. >= 12

#define SUPLA_CALCFG_CMD_GET_CHANNEL_FUNCLIST 1000        // v. >= 11
#define SUPLA_CALCFG_CMD_CANCEL_ALL_CLIENT_COMMANDS 1010  // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_RESET_AND_CLEAR 2000       // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_ADD_NODE 2010              // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_REMOVE_NODE 2020           // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_GET_NODE_LIST 2030         // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_GET_ASSIGNED_NODE_ID 2040  // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_ASSIGN_NODE_ID 2050        // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_GET_WAKE_UP_SETTINGS 2060  // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_SET_WAKE_UP_TIME 2070      // v. >= 12
#define SUPLA_CALCFG_CMD_ZWAVE_CONFIG_MODE_ACTIVE 4000    // v. >= 12
#define SUPLA_CALCFG_CMD_DEBUG_STRING 5000                // v. >= 12
#define SUPLA_CALCFG_CMD_PROGRESS_REPORT 5001             // v. >= 12
#define SUPLA_CALCFG_CMD_SET_LIGHTSOURCE_LIFESPAN 6000    // v. >= 12
#define SUPLA_CALCFG_CMD_RESET_COUNTERS 7000              // v. >= 15
#define SUPLA_CALCFG_CMD_RECALIBRATE 8000                 // v. >= 15
#define SUPLA_CALCFG_CMD_ENTER_CFG_MODE 9000              // v. >= 17
#define SUPLA_CALCFG_CMD_SET_TIME 9100                    // v. >= 21

#define SUPLA_CALCFG_DATATYPE_RS_SETTINGS 1000
#define SUPLA_CALCFG_DATATYPE_FB_SETTINGS 1100  // v. >= 17

#define CALCFG_ZWAVE_SCREENTYPE_UNKNOWN 0
#define CALCFG_ZWAVE_SCREENTYPE_MULTILEVEL 1
#define CALCFG_ZWAVE_SCREENTYPE_BINARY 2
#define CALCFG_ZWAVE_SCREENTYPE_MULTILEVEL_AUTOSHADE 3
#define CALCFG_ZWAVE_SCREENTYPE_MULTILEVEL_COLOR_CONTROL 4
#define CALCFG_ZWAVE_SCREENTYPE_BINARY_COLOR_CONTROL 5
#define CALCFG_ZWAVE_SCREENTYPE_SENSOR 6

#define ZWAVE_NODE_NAME_MAXSIZE 50

#define ZWAVE_NODE_FLAG_CHANNEL_ASSIGNED 0x1
#define ZWAVE_NODE_FLAG_WAKEUP_TIME_SETTABLE 0x2

// Values from other fields are applied in a standard way
#define RGBW_COMMAND_NOT_SET 0
// Ignores all other bytes and turns on the dimmer
#define RGBW_COMMAND_TURN_ON_DIMMER 1
// Ignores all other bytes and turns off the dimmer
#define RGBW_COMMAND_TURN_OFF_DIMMER 2
// Ignores all other bytes and toggles the dimmer
#define RGBW_COMMAND_TOGGLE_DIMMER 3
// Ignores all other bytes and turns on the RGB
#define RGBW_COMMAND_TURN_ON_RGB 4
// Ignores all other bytes and turns off the RGB
#define RGBW_COMMAND_TURN_OFF_RGB 5
// Ignores all other bytes and toggles the RGB
#define RGBW_COMMAND_TOGGLE_RGB 6
// Ignores all other bytes and turns on the RGB and Dimmer
#define RGBW_COMMAND_TURN_ON_ALL 7
// Ignores all other bytes and turns off the RGB and Dimmer
#define RGBW_COMMAND_TURN_OFF_ALL 8
// Ignores all other bytes and toggles the RGB and Dimmer (with sync, so both
// will be off or both will be on)
#define RGBW_COMMAND_TOGGLE_ALL 9
// Stores brightness value and ignores all other bytes, if dimmer is off, it
// stays off
#define RGBW_COMMAND_SET_BRIGHTNESS_WITHOUT_TURN_ON 10
// Stores colorBrightness value and ignores all other bytes.
// If RGB is off, it stays off
#define RGBW_COMMAND_SET_COLOR_BRIGHTNESS_WITHOUT_TURN_ON 11
// Stores color value (R, G, B) and ignores all other bytes.
// If RGB is off, it stays off
#define RGBW_COMMAND_SET_RGB_WITHOUT_TURN_ON 12
// Start brightness dimmer iteration
#define RGBW_COMMAND_START_ITERATE_DIMMER 13
// Start color brightness iteration
#define RGBW_COMMAND_START_ITERATE_RGB 14
// Start dimmer and rgb brightness iteration
#define RGBW_COMMAND_START_ITERATE_ALL 15
// Stop brightness dimmer iteration
#define RGBW_COMMAND_STOP_ITERATE_DIMMER 16
// Stop color brightness iteration
#define RGBW_COMMAND_STOP_ITERATE_RGB 17
// Stop dimmer and rgb brightness iteration
#define RGBW_COMMAND_STOP_ITERATE_ALL 18

#define SUPLA_RELAY_FLAG_OVERCURRENT_RELAY_OFF 0x1
#define DIGIGLASS_TOO_LONG_OPERATION_WARNING 0x1
#define DIGIGLASS_PLANNED_REGENERATION_IN_PROGRESS 0x2
#define DIGIGLASS_REGENERATION_AFTER_20H_IN_PROGRESS 0x4
#define THERMOSTAT_SCHEDULE_DAY_SUNDAY 0x01
#define THERMOSTAT_SCHEDULE_DAY_MONDAY 0x02
#define THERMOSTAT_SCHEDULE_DAY_TUESDAY 0x04
#define THERMOSTAT_SCHEDULE_DAY_WEDNESDAY 0x08
#define THERMOSTAT_SCHEDULE_DAY_THURSDAY 0x10
#define THERMOSTAT_SCHEDULE_DAY_FRIDAY 0x20
#define THERMOSTAT_SCHEDULE_DAY_SATURDAY 0x40
#define THERMOSTAT_SCHEDULE_DAY_ALL 0xFF

#define THERMOSTAT_SCHEDULE_HOURVALUE_TYPE_TEMPERATURE 0
#define THERMOSTAT_SCHEDULE_HOURVALUE_TYPE_PROGRAM 1

// Tempeature definitions for Heatpol thermostat
// TThermostatTemperatureCfg
#define TEMPERATURE_INDEX1 0x0001
#define TEMPERATURE_INDEX2 0x0002
#define TEMPERATURE_INDEX3 0x0004
#define TEMPERATURE_INDEX4 0x0008
#define TEMPERATURE_INDEX5 0x0010
#define TEMPERATURE_INDEX6 0x0020
#define TEMPERATURE_INDEX7 0x0040
#define TEMPERATURE_INDEX8 0x0080
#define TEMPERATURE_INDEX9 0x0100
#define TEMPERATURE_INDEX10 0x0200
// TThermostatTemperatureCfg has only 10 fields

// Heatpol: Thermostat configuration commands - ver. >= 11
#define SUPLA_THERMOSTAT_CMD_TURNON 1
#define SUPLA_THERMOSTAT_CMD_SET_MODE_AUTO 2
#define SUPLA_THERMOSTAT_CMD_SET_MODE_COOL 3
#define SUPLA_THERMOSTAT_CMD_SET_MODE_HEAT 4
#define SUPLA_THERMOSTAT_CMD_SET_MODE_NORMAL 5
#define SUPLA_THERMOSTAT_CMD_SET_MODE_ECO 6
#define SUPLA_THERMOSTAT_CMD_SET_MODE_TURBO 7
#define SUPLA_THERMOSTAT_CMD_SET_MODE_DRY 8
#define SUPLA_THERMOSTAT_CMD_SET_MODE_FANONLY 9
#define SUPLA_THERMOSTAT_CMD_SET_MODE_PURIFIER 10
#define SUPLA_THERMOSTAT_CMD_SET_SCHEDULE 11
#define SUPLA_THERMOSTAT_CMD_SET_TIME 12
#define SUPLA_THERMOSTAT_CMD_SET_TEMPERATURE 13

// Heatpol: Thermostat value flags - ver. >= 11
#define SUPLA_THERMOSTAT_VALUE_FLAG_ON 0x0001
#define SUPLA_THERMOSTAT_VALUE_FLAG_AUTO_MODE 0x0002
#define SUPLA_THERMOSTAT_VALUE_FLAG_COOL_MODE 0x0004
#define SUPLA_THERMOSTAT_VALUE_FLAG_HEAT_MODE 0x0008
#define SUPLA_THERMOSTAT_VALUE_FLAG_ECO_MODE 0x0010
#define SUPLA_THERMOSTAT_VALUE_FLAG_DRY_MODE 0x0020
#define SUPLA_THERMOSTAT_VALUE_FLAG_FANONLY_MODE 0x0040
#define SUPLA_THERMOSTAT_VALUE_FLAG_PURIFIER_MODE 0x0080

// Heatpol: Thermostat fields - ver. >= 11
#define THERMOSTAT_FIELD_MeasuredTemperatures 0x01
#define THERMOSTAT_FIELD_PresetTemperatures 0x02
#define THERMOSTAT_FIELD_Flags 0x04
#define THERMOSTAT_FIELD_Values 0x08
#define THERMOSTAT_FIELD_Time 0x10
#define THERMOSTAT_FIELD_Schedule 0x20

#define SUPLA_CHANNELSTATE_FIELD_IPV4 0x0001
#define SUPLA_CHANNELSTATE_FIELD_MAC 0x0002
#define SUPLA_CHANNELSTATE_FIELD_BATTERYLEVEL 0x0004
#define SUPLA_CHANNELSTATE_FIELD_BATTERYPOWERED 0x0008
#define SUPLA_CHANNELSTATE_FIELD_WIFIRSSI 0x0010
#define SUPLA_CHANNELSTATE_FIELD_WIFISIGNALSTRENGTH 0x0020
#define SUPLA_CHANNELSTATE_FIELD_BRIDGENODESIGNALSTRENGTH 0x0040
#define SUPLA_CHANNELSTATE_FIELD_UPTIME 0x0080
#define SUPLA_CHANNELSTATE_FIELD_CONNECTIONUPTIME 0x0100
#define SUPLA_CHANNELSTATE_FIELD_BATTERYHEALTH 0x0200
#define SUPLA_CHANNELSTATE_FIELD_BRIDGENODEONLINE 0x0400
#define SUPLA_CHANNELSTATE_FIELD_LASTCONNECTIONRESETCAUSE 0x0800
#define SUPLA_CHANNELSTATE_FIELD_LIGHTSOURCELIFESPAN 0x1000
#define SUPLA_CHANNELSTATE_FIELD_LIGHTSOURCEOPERATINGTIME 0x2000

#define SUPLA_LASTCONNECTIONRESETCAUSE_UNKNOWN 0
#define SUPLA_LASTCONNECTIONRESETCAUSE_ACTIVITY_TIMEOUT 1
#define SUPLA_LASTCONNECTIONRESETCAUSE_WIFI_CONNECTION_LOST 2
#define SUPLA_LASTCONNECTIONRESETCAUSE_SERVER_CONNECTION_LOST 3

#define SUPLA_CHANNEL_CONFIG_MAXSIZE 512  // v. <= 19 - 128; v. >= 21 - 512
#define SUPLA_DEVICE_CONFIG_MAXSIZE 512

// Default type is used for standard channel config (user modifiable)
#define SUPLA_CONFIG_TYPE_DEFAULT 0
// Weekly schedule
#define SUPLA_CONFIG_TYPE_WEEKLY_SCHEDULE 2
// For SUPLA_CHANNELFNC_HVAC_THERMOSTAT, ALT weekly schedule is used for
// cooling subfuction, while standard weelkly schedule is used for heating
#define SUPLA_CONFIG_TYPE_ALT_WEEKLY_SCHEDULE 3

#define SUPLA_DEVCFG_STATUS_LED_ON_WHEN_CONNECTED 0
#define SUPLA_DEVCFG_STATUS_LED_OFF_WHEN_CONNECTED 1
#define SUPLA_DEVCFG_STATUS_LED_ALWAYS_OFF 2

#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_NONE 1
#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_TEMPERATURE 2 
#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_TEMPERATURE_AND_HUMIDITY 4 
#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_TIME 8
#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_TIME_DATE 16  
#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_TEMPERATURE_TIME 32 
#define SUPLA_DEVCFG_HOME_SCREEN_CONTENT_MAIN_AND_AUX_TEMPERATURE 64

#define SUPLA_CONFIG_RESULT_FALSE 0
#define SUPLA_CONFIG_RESULT_TRUE 1
#define SUPLA_CONFIG_RESULT_DATA_ERROR 2
#define SUPLA_CONFIG_RESULT_TYPE_NOT_SUPPORTED 3
#define SUPLA_CONFIG_RESULT_FUNCTION_NOT_SUPPORTED 4
#define SUPLA_CONFIG_RESULT_LOCAL_CONFIG_DISABLED 5
#define SUPLA_CONFIG_RESULT_NOT_ALLOWED 6
#define SUPLA_CONFIG_RESULT_DEVICE_NOT_FOUND 7 