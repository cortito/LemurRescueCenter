package com.projet_100_heures.lemurrescuecenter.business.alertDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.projet_100_heures.lemurrescuecenter.R;

/**
 * Created by corto_000 on 17/01/2017.
 */

public class AddWeightLemurDialog extends DialogFragment {

    private AddWeightLemurDialog.AddWeightLemurListenner mListener;


    public void setmListener(AddWeightLemurDialog.AddWeightLemurListenner mListener) {
        this.mListener = mListener;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LayoutInflater inflater;
        inflater = getActivity().getLayoutInflater();
        final View viewInflater;
        viewInflater = inflater.inflate(R.layout.dialog_add, null);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        viewInflater.findViewById(R.id.Id_Lemur);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(viewInflater)
                .setMessage("Ajouter un Lémurien")
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText date = (EditText) viewInflater.findViewById(R.id.date_add);
                        EditText weight = (EditText) viewInflater.findViewById(R.id.weight_add);
                        mListener.onWeightRetrieved(date.getText().toString(),weight.getText().toString() );

                    }
                })
                .setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface AddWeightLemurListenner {
        void onWeightRetrieved(String date , String weight);
    }
}
