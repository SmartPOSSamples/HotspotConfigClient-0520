package com.smartpos.hotspot;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.cloudpos.wifiaphelper.IWifiApHelper;

public class AidlMgr {

    public void startWifiAp(Context context, String wifiName, String password, String security) {
        ServiceConnection systemExtConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IWifiApHelper wifiApHelper = IWifiApHelper.Stub.asInterface(service);
                try {
                    wifiApHelper.startWifiAp(wifiName, password, security);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        ComponentName comp = new ComponentName("com.cloudpos.wifiaphelper", "com.cloudpos.wifiaphelper.WifiApHelperService");
        Intent intent = new Intent();
        intent.setComponent(comp);
        context.bindService(intent, systemExtConn, Context.BIND_AUTO_CREATE);
        context.startService(intent);
    }


    public void stop(Context context) {
        ServiceConnection systemExtConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IWifiApHelper wifiApHelper = IWifiApHelper.Stub.asInterface(service);
                try {
                    wifiApHelper.stopWifiAp();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        ComponentName comp = new ComponentName("com.cloudpos.wifiaphelper", "com.cloudpos.wifiaphelper.WifiApHelperService");
        Intent intent = new Intent();
        intent.setComponent(comp);
        context.bindService(intent, systemExtConn, Context.BIND_AUTO_CREATE);
        context.startService(intent);
    }

}
