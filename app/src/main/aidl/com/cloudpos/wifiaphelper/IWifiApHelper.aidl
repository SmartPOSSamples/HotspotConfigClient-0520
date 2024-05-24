// IWifiApHelper.aidl
package com.cloudpos.wifiaphelper;

interface IWifiApHelper {
// wifiApName: WLAN hotspot network name;
// wifiApPassword: WLAN hotspot password;
// wifiApSecurityKeyManagement: WLAN hotspot security: String None = "None", String WPA2_PSK = "WPA2_PSK";
// If you do not set a password, the 'wifiApSecurityKeyManagement' needs to be set to null or 'None'.
// If you do set a password, password length must have at least 8 characters , the 'wifiApSecurityKeyManagement' needs to be set to 'WPA2_PSK'.
    boolean startWifiAp(String wifiApName, String wifiApPassword, String wifiApSecurityKeyManagement);
    boolean stopWifiAp();
}