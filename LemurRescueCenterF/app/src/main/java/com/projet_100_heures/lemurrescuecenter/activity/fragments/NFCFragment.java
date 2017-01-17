package com.projet_100_heures.lemurrescuecenter.activity.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.activity.Communicator;
import com.projet_100_heures.lemurrescuecenter.business.nfc.NfcUtils;


public class NFCFragment extends Fragment {

    
    TextView textView;
    int selector = 0;
    Communicator comm;
    boolean TagDiscovered = false;
    ProgressDialog progressDialog;

    public NFCFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nfc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readNFC();
    }
    public void readNFC() {

        textView = (TextView) getActivity().findViewById(R.id.resultRead);
        selector = 1;
        comm.sendSelector(selector);
        Log.i("tag","selector " + selector);

            }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        comm = (Communicator) context;
    }

    public void isTagDiscovered(boolean b){
        TagDiscovered = b;
    }

    public void getTextFromNDEFMessage(NdefMessage ndefMessage){

        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        if (ndefRecords != null && ndefRecords.length > 0) {
            NdefRecord ndefRecord = ndefRecords[0];
            String tagcontent = NfcUtils.getTextFromNDEFRecord(ndefRecord);
            textView.setText(tagcontent);
        } else {
            Log.e("Tag", "no NDEFRecord found !");
        }
    }

    public void setIdTag (String str){
        textView.setText(str);

        Toast.makeText(getActivity(),"Tag lu !! ", Toast.LENGTH_SHORT).show();
    }
}





