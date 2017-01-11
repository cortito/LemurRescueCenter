package com.projet_100_heures.testenvoienfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        disableForegroundDispatchSystem();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            Toast.makeText(this, "Envoie message NFC", Toast.LENGTH_SHORT).show();

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage ndefMessage = createNdefMessage("Message a envoer !");
            writeNdefMessage(tag,ndefMessage);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        enableForegroundDispatchSystem();
        /*
        NdefRecord record = NfcTest.creerRecord("Exemple de message à envoyer");
        NdefMessage message = NfcTest.creerMessage(record);
        nfcAdapter.setNdefPushMessage(message,this);*/


    }

    private void enableForegroundDispatchSystem() {
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        IntentFilter[] intentFilters = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
    }

    private void disableForegroundDispatchSystem() {
        nfcAdapter.disableForegroundDispatch(this);
    }

    private void formatTag(Tag tag, NdefMessage message) {
        try {
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);
            if (ndefFormatable == null) {
                Toast.makeText(this, "Le Tag n'est pas Ndef Formatable", Toast.LENGTH_SHORT).show();
            }

            ndefFormatable.connect();
            ndefFormatable.format(message);
            ndefFormatable.close();
            Toast.makeText(this, "Tag formaté ! ", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("formaTag", e.getMessage());
        }
    }

    private void writeNdefMessage(Tag tag, NdefMessage message) {

        try {

            if (tag == null) {
                Toast.makeText(this, "Le tag ne peut etre null !", Toast.LENGTH_SHORT).show();
            } else {

                Ndef ndef = Ndef.get(tag);

                if (ndef == null) {
                    formatTag(tag, message);
                } else {
                    ndef.connect();
                    if (!ndef.isWritable()) {
                        Toast.makeText(this, "On ne peut éditer le Tag ! ", Toast.LENGTH_SHORT).show();
                        ndef.close();
                    }
                    ndef.writeNdefMessage(message);
                    ndef.close();
                    Toast.makeText(this, "Tag édité ! ", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("writeNdefMessage", e.getMessage());
        }
    }

    private NdefRecord createTextRecord(String message) {
        try {
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("UTF-8");

            final byte[] text = message.getBytes("UTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte) (languageSize & 0x1F));
            payload.write(language, 0, languageSize);
            payload.write(text, 0, textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());

        } catch (UnsupportedEncodingException e) {
            Log.e("createTextRecord", e.getMessage());
        }
        return null;
    }

    private NdefMessage createNdefMessage(String message) {

        NdefRecord ndefRecord = createTextRecord(message);

        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ndefRecord});
        return ndefMessage;
    }
}
