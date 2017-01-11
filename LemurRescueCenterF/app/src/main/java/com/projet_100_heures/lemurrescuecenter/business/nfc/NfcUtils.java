package com.projet_100_heures.lemurrescuecenter.business.nfc;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Parcelable;

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

            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                messages = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    messages[i] = (NdefMessage) rawMsgs[i];

                    NdefRecord record = messages[i].getRecords()[i];
                    byte[] id = record.getId();
                    short tnf = record.getTnf();
                    byte[] type = record.getType();
                    message = new String(record.getPayload());
                    return message;
                }
            } else {
                return "nothing";
            }
        }
        return message;
    }
}
