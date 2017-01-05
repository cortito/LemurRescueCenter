package com.projet_100_heures.lemurrescuecenter.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.projet_100_heures.lemurrescuecenter.R;
import com.projet_100_heures.lemurrescuecenter.business.dao.CustomizeLemurTask;
import com.projet_100_heures.lemurrescuecenter.business.dao.RetrieveLemurTask;
import com.projet_100_heures.lemurrescuecenter.business.mapping.MappingLemur;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomActivity extends AppCompatActivity implements RetrieveLemurTask.LemurListenner , CustomizeLemurTask.CustomizeLemurListenner {


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

    JSONObject jsonObject = new JSONObject();
    LemurModel lemurModelBuffer = new LemurModel();
    CustomizeLemurTask customizeLemurTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        numId = (EditText)findViewById(R.id.editLemurNumId);
        age = (EditText) findViewById(R.id.editLemurAge);
        sex = (EditText) findViewById(R.id.editLemurGender);
        name = (EditText) findViewById(R.id.editLemurName);
        entryDate = (EditText) findViewById(R.id.editlemurEntryDate);
        origin = (EditText) findViewById(R.id.editLemurOrigin);
        entryNature = (EditText) findViewById(R.id.editLemurEntryNature);
        lastOwner = (EditText) findViewById(R.id.editLemurLastOwner);
        leavingDate = (EditText) findViewById(R.id.editLemurLeavingDate);
        leavingNature = (EditText) findViewById(R.id.editLemurLeavingNature);
        leavingCommentary = (EditText) findViewById(R.id.editLemurLeavingCommentary);



        RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(this);
        retrieveLemurTask.execute();

        ImageButton submitButton = (ImageButton) findViewById(R.id.submitEdit);
        submitButton.setOnClickListener(new View.OnClickListener() {
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
                LemurModel lemurModelupdate = new LemurModel();
                lemurModelupdate = MappingLemur.updateLemurModel(lemurModelBuffer,jsonObject);
                //envoie du Json par post :
                try {
                    JSONObject jsonObjectUpdate = new JSONObject(lemurModelupdate.toJSON());
                    Log.d("Tag123",lemurModelupdate.toJSON());
                    customizeLemurTask = new CustomizeLemurTask(CustomActivity.this);
                    customizeLemurTask.execute(jsonObjectUpdate);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return true;
    }

    @Override
    public void onLemurRetrieved(final LemurModel lemurModel) {

        lemurModelBuffer = lemurModel;
        Log.d("Tag",lemurModel.getIdLemur());
        Log.d("Tag2",lemurModel.getBirthDate());
        Log.d("Tag3",lemurModel.getGender());
        Log.d("Tag4",lemurModel.getName());


        if(lemurModel.getIdLemur() != null){
            numId.setHint(lemurModel.getIdLemur());
            numId.setHintTextColor(Color.BLACK);
        }
        if(lemurModel.getBirthDate() != null) {
            age.setHint(lemurModel.getBirthDate());
            age.setHintTextColor(Color.BLACK);
        }
        if(lemurModel.getGender() !=null) {
            sex.setHint(lemurModel.getGender());
            sex.setHintTextColor(Color.BLACK);
        }
        if(lemurModel.getName() != null) {
            name.setHint(lemurModel.getName());
            name.setHintTextColor(Color.BLACK);
        }

        if(lemurModel.getEntryDate() != null) {
            entryDate.setHint(lemurModel.getEntryDate());
            entryDate.setHintTextColor(Color.BLACK);
        }
        if (lemurModel.getOrigin() != null) {
            origin.setHint(lemurModel.getOrigin());
            origin.setHintTextColor(Color.BLACK);
        }
        if (lemurModel.getEntryNature() != null) {
            entryNature.setHint(lemurModel.getEntryNature());
            entryNature.setHintTextColor(Color.BLACK);
        }
        if (lemurModel.getLastOwner() != null) {
            lastOwner.setHint(lemurModel.getLastOwner());
            lastOwner.setHintTextColor(Color.BLACK);
        }
        if (lemurModel.getLeaveDate() != null) {
            leavingDate.setHint(lemurModel.getLeaveDate());
            leavingDate.setHintTextColor(Color.BLACK);
        }
        if (lemurModel.getLeaveNature() != null) {
            leavingNature.setHint(lemurModel.getLeaveNature());
            leavingNature.setHintTextColor(Color.BLACK);
        }
        if (lemurModel.getLeaveCommentary() != null) {
            leavingCommentary.setHint(lemurModel.getLeaveCommentary());
            leavingCommentary.setHintTextColor(Color.BLACK);
        }
    }

    @Override
    public void onLemurPosted(LemurModel lemurModel) {

    }
}
