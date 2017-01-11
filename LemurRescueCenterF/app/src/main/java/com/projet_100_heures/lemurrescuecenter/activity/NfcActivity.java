package com.projet_100_heures.lemurrescuecenter.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.business.nfc.NfcUtils;

public class NfcActivity extends AppCompatActivity {
    NfcAdapter mAdapter;
    TextView msgNfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
    }
    @Override
    protected void onResume() {
        super.onResume();

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if(mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
        else {
            Toast.makeText(this.getApplicationContext(),"Adapter NFC null",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String message = NfcUtils.resoudreIntent(intent);
        msgNfc = (TextView) findViewById(R.id.messageNfc);
        msgNfc.setText(message);
    }
}
