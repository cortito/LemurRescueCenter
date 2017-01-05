package com.projet_100_heures.lemurrescuecenter.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    JSONObject jsonObject = new JSONObject();
    LemurModel lemurModelBuffer = new LemurModel();
    CustomizeLemurTask customizeLemurTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        numId = (EditText)findViewById(R.id.num_Identification_custom);
        age = (EditText) findViewById(R.id.editlemureAge);
        sex = (EditText) findViewById(R.id.editLemurGender);
        name = (EditText) findViewById(R.id.editlemurName);

        RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(this);
        retrieveLemurTask.execute();

        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                try {
                    jsonObject.put("numeroIdentification", numId.getText());
                    jsonObject.put("dateDeNaissance", age.getText());
                    jsonObject.put("sexe", sex.getText());
                    jsonObject.put("nom", name.getText());
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
    }

    @Override
    public void onLemurPosted(LemurModel lemurModel) {

    }
}
