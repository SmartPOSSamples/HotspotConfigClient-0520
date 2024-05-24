package com.smartpos.hotspot;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText networkName, et_password;
    private RadioButton none, wpa2;
    private TextView tv_password;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        none = (RadioButton) findViewById(R.id.rb_security_none);
        wpa2 = (RadioButton) findViewById(R.id.rb_security_wpa2);

        aSwitch = (Switch) findViewById(R.id.switch1);
        networkName = (EditText) findViewById(R.id.hotspotname);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_password = (TextView) findViewById(R.id.tv_password);

        aSwitch.setOnClickListener(this);
        none.setOnClickListener(this);
        wpa2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch1:
                if (aSwitch.isChecked()) {
                    if (et_password.getText().toString().trim().length() < 8) {
                        Toast.makeText(this, "The password must have at least 8 characters.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    setWlanhotspot(networkName, et_password);
                    none.setEnabled(false);
                    wpa2.setEnabled(false);
                    networkName.setEnabled(false);
                    et_password.setEnabled(false);
                    Log.d("TAG", "enable hotspot");
                    Toast.makeText(this, "enabling hotspot.", Toast.LENGTH_LONG).show();
                } else {
                    none.setEnabled(true);
                    wpa2.setEnabled(true);
                    networkName.setEnabled(true);
                    et_password.setEnabled(true);
                    stophotspot();
                    Log.d("TAG", "disable hotspot");
                    Toast.makeText(this, "diable hotspot.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rb_security_none:
                tv_password.setVisibility(View.GONE);
                et_password.setVisibility(View.GONE);
                break;
            case R.id.rb_security_wpa2:
                tv_password.setVisibility(View.VISIBLE);
                et_password.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setWlanhotspot(EditText networkName, EditText password) {
        String name = "";
        String pwd = "";
        if (networkName != null && networkName.length() > 1) {
            name = networkName.getText().toString();
        }
        if (none.isChecked()) {
            AidlMgr aidlMgr = new AidlMgr();
            aidlMgr.startWifiAp(this, name, pwd, "None");//String None = "None", String WPA2_PSK = "WPA2_PSK";
        } else if (wpa2.isChecked()) {
            if (password != null && password.length() > 1) {
                pwd = password.getText().toString();
            }
            AidlMgr aidlMgr = new AidlMgr();
            aidlMgr.startWifiAp(this, name, pwd, "WPA2_PSK");//String None = "None", String WPA2_PSK = "WPA2_PSK";
        }
    }

    private void stophotspot() {
        AidlMgr aidlMgr = new AidlMgr();
        aidlMgr.stop(this);
    }

}