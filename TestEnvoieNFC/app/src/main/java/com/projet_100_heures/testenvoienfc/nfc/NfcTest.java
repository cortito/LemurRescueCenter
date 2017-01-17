package com.projet_100_heures.testenvoienfc.nfc;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Created by corto_000 on 10/01/2017.
 */

public class NfcTest {

    public static NdefRecord creerRecord(String message)
    {
        byte[] langBytes = Locale.ENGLISH.getLanguage().getBytes(Charset.forName("US-ASCII"));
        byte[] textBytes = message.getBytes(Charset.forName("UTF-8"));
        char status = (char) (langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        data[0] = (byte) status;
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    public static NdefMessage creerMessage(NdefRecord record)
    {
        NdefRecord[] records = new NdefRecord[1];
        records[0] = record;
        NdefMessage message = new NdefMessage(records);
        return message;
    }
}
