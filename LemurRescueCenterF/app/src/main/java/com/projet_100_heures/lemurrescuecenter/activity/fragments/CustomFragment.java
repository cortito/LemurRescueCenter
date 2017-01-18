package com.projet_100_heures.lemurrescuecenter.activity.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.business.dao.CustomizeLemurTask;
import com.projet_100_heures.lemurrescuecenter.business.mapping.MappingLemur;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomFragment extends Fragment implements CustomizeLemurTask.CustomizeLemurListenner {

    EditText numId;
    EditText age;
    EditText sex;
    EditText name;
    EditText entryDate;
    EditText origin;
    EditText entryNature;
    EditText lastOwner;
    EditText leavingDate;
    EditText leavingNature;
    EditText leavingCommentary;
    FloatingActionButton customButton;

    JSONObject jsonObject = new JSONObject();
    LemurModel lemurModelBuffer = new LemurModel();
    CustomizeLemurTask customizeLemurTask;

   // private OnFragmentInteractionListener mListener;

    public CustomFragment() {
        // Required empty public constructor
    }

    public void displayprofilHints(){


        numId = (EditText)getActivity().findViewById(R.id.editLemurNumId);
        age = (EditText) getActivity().findViewById(R.id.editLemurAge);
        sex = (EditText) getActivity().findViewById(R.id.editLemurGender);
        name = (EditText) getActivity().findViewById(R.id.editLemurName);
        entryDate = (EditText) getActivity().findViewById(R.id.editlemurEntryDate);
        origin = (EditText) getActivity().findViewById(R.id.editLemurOrigin);
        entryNature = (EditText) getActivity().findViewById(R.id.editLemurEntryNature);
        lastOwner = (EditText) getActivity().findViewById(R.id.editLemurLastOwner);
        leavingDate = (EditText) getActivity().findViewById(R.id.editLemurLeavingDate);
        leavingNature = (EditText) getActivity().findViewById(R.id.editLemurLeavingNature);
        leavingCommentary = (EditText) getActivity().findViewById(R.id.editLemurLeavingCommentary);
        customButton = (FloatingActionButton) getActivity().findViewById(R.id.FlButton);


        if(lemurModelBuffer != null) {
            if (lemurModelBuffer.getIdLemur() != null) {
                numId.setHint(lemurModelBuffer.getIdLemur());
                numId.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getBirthDate() != null) {
                age.setHint(lemurModelBuffer.getBirthDate());
                age.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getGender() != null) {
                sex.setHint(lemurModelBuffer.getGender());
                sex.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getName() != null) {
                name.setHint(lemurModelBuffer.getName());
                name.setHintTextColor(Color.BLACK);
            }

            if (lemurModelBuffer.getEntryDate() != null) {
                entryDate.setHint(lemurModelBuffer.getEntryDate());
                entryDate.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getOrigin() != null) {
                origin.setHint(lemurModelBuffer.getOrigin());
                origin.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getEntryNature() != null) {
                entryNature.setHint(lemurModelBuffer.getEntryNature());
                entryNature.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getLastOwner() != null) {
                lastOwner.setHint(lemurModelBuffer.getLastOwner());
                lastOwner.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getLeaveDate() != null) {
                leavingDate.setHint(lemurModelBuffer.getLeaveDate());
                leavingDate.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getLeaveNature() != null) {
                leavingNature.setHint(lemurModelBuffer.getLeaveNature());
                leavingNature.setHintTextColor(Color.BLACK);
            }
            if (lemurModelBuffer.getLeaveCommentary() != null) {
                leavingCommentary.setHint(lemurModelBuffer.getLeaveCommentary());
                leavingCommentary.setHintTextColor(Color.BLACK);
            }
        }
        else {
            Toast.makeText(getActivity(),"Cherchez un lémurien avant de faire une modification ! ", Toast.LENGTH_SHORT).show();
        }


        /*String idLemur;
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        idLemur=bundle.getString("idLemur");

        idLemur= Integer.toString(lemurModelBuffer.getIdDB());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idDB", idLemur);
        }catch (JSONException e) {
            Log.e("Json", e.getMessage());
        }

        /*RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(ge);
        retrieveLemurTask.execute(jsonObject);*/

        //ImageButton submitButton = (ImageButton) getActivity().findViewById(R.id.submitEdit);
        /*submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                try {
                    jsonObject.put("numeroIdentification", numId.getText());
                    jsonObject.put("dateDeNaissance", age.getText());
                    jsonObject.put("sexe", sex.getText());
                    jsonObject.put("nom", name.getText());
                    jsonObject.put("dateEntree", entryDate.getText());
                    jsonObject.put("origine", origin.getText());
                    jsonObject.put("natureEntree", entryNature.getText());
                    jsonObject.put("ancienProprietaire", lastOwner.getText());
                    jsonObject.put("dateSortie", leavingDate.getText());
                    jsonObject.put("natureSortie", leavingNature.getText());
                    jsonObject.put("commentaireSortie", leavingCommentary.getText());

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                LemurModel lemurModelupdate;
                lemurModelupdate = MappingLemur.updateLemurModel(lemurModelBuffer,jsonObject);
                //envoie du Json par post :
                try {
                    JSONObject jsonObjectUpdate = new JSONObject(lemurModelupdate.toJSON());
                    Log.d("Tag123",lemurModelupdate.toJSON());
                    customizeLemurTask = new CustomizeLemurTask(CustomFragment.this);
                    customizeLemurTask.execute(jsonObjectUpdate);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });*/
    }

    public void sendLemurCustom(LemurModel lemurModel){
        lemurModelBuffer = lemurModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_custom, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayprofilHints();

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    jsonObject.put("numeroIdentification", numId.getText());
                    jsonObject.put("dateDeNaissance", age.getText());
                    jsonObject.put("sexe", sex.getText());
                    jsonObject.put("nom", name.getText());
                    jsonObject.put("dateEntree", entryDate.getText());
                    jsonObject.put("origine", origin.getText());
                    jsonObject.put("natureEntree", entryNature.getText());
                    jsonObject.put("ancienProprietaire", lastOwner.getText());
                    jsonObject.put("dateSortie", leavingDate.getText());
                    jsonObject.put("natureSortie", leavingNature.getText());
                    jsonObject.put("commentaireSortie", leavingCommentary.getText());

                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                LemurModel lemurModelupdate;
                lemurModelupdate = MappingLemur.updateLemurModel(lemurModelBuffer,jsonObject);
                //envoie du Json par post :
                try {
                    JSONObject jsonObjectUpdate = new JSONObject(lemurModelupdate.toJSON());
                    Log.d("Tag123",lemurModelupdate.toJSON());
                    customizeLemurTask = new CustomizeLemurTask(CustomFragment.this, getActivity());
                    customizeLemurTask.execute(jsonObjectUpdate);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onLemurPosted(LemurModel lemurModel) {
        Toast.makeText(getActivity(),"Modifications envoyées !",Toast.LENGTH_SHORT).show();
    }
}
