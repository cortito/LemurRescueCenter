package com.projet_100_heures.lemurrescuecenter.business.nfc;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;

/**
 * Created by corto_000 on 10/01/2017.
 */

public class NfcUtils {
    private static NdefMessage msgs[];
    private static NdefMessage[] messages;
    private static String message;

    public static String resoudreIntent(Intent intent) {

        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {

            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            NdefMessage msg = (NdefMessage) rawMsgs[0];
            message = new String(msg.getRecords()[0].getPayload());

            } else {
                return "nothing";
            }
        return message;
    }

    public static String getTextFromNDEFRecord(NdefRecord ndefRecord) {
        String message = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String texEncoding =((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageSize = payload[0] & 0063;
            message = new String(payload,languageSize + 1, payload.length - languageSize - 1, texEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }


}
