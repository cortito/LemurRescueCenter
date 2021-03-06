package com.projet_100_heures.lemurrescuecenter.business.alertDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.projet_100_heures.lemurrescuecenter.R;

/**
 * Created by corto_000 on 17/01/2017.
 */

public class SearchLemurNameDialog extends DialogFragment {

    private SearchLemurNameDialog.SearchLemurNameListenner mListener;


    public void setmListener(SearchLemurNameDialog.SearchLemurNameListenner mListener) {
        this.mListener = mListener;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LayoutInflater inflater;
        inflater = getActivity().getLayoutInflater();
        final View viewInflater;
        viewInflater = inflater.inflate(R.layout.search_name_dialog, null);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        viewInflater.findViewById(R.id.Id_Lemur);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(viewInflater)
                .setMessage("Chercher un Lémurien par son Nom :")
                .setPositiveButton("Chercher", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText et = (EditText) viewInflater.findViewById(R.id.name_Search);
                        if(!(et.getText().toString().equals(""))) {
                            mListener.onNameRetrieved(et.getText().toString());
                        }
                        else {
                            Toast.makeText(getActivity(),"Veuillez donner un NOM de lémurien !",Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton(R.string.dialog_Neg_Button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public interface SearchLemurNameListenner {
        void onNameRetrieved(String name);
    }
}

