package com.example.corto_000.lemurrescuecenter2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.corto_000.lemurrescuecenter2.R;
import com.example.corto_000.lemurrescuecenter2.business.dao.RetrieveLemurTask;
import com.example.corto_000.lemurrescuecenter2.model.LemurModel;

public class ProfileActivity extends AppCompatActivity implements RetrieveLemurTask.LemurListenner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);


        RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(this);
        retrieveLemurTask.execute();

        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingButton);
        ImageButton vetoFileButton = (ImageButton) findViewById(R.id.vetoButton);
        ImageButton statsButton = (ImageButton) findViewById(R.id.statButton);
        ImageButton customsButton = (ImageButton) findViewById(R.id.customButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        vetoFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, VetoFileActivity.class);
                startActivity(intent);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });

        customsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, CustomActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"it works great",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLemurRetrieved(LemurModel lemurModel) {

        AppCompatTextView name = (AppCompatTextView) findViewById(R.id.lemurName);
        name.setText(lemurModel.getName());

        AppCompatTextView age = (AppCompatTextView) findViewById(R.id.lemureAge);
        if(!lemurModel.getBirthDate().equals("")){
            age.setText(lemurModel.getBirthDate());
        }
        else {
            age.setText("Non déterminé");
        }

        AppCompatTextView sexe = (AppCompatTextView) findViewById(R.id.lemurGender);
        sexe.setText(lemurModel.getGender());

        AppCompatTextView numIdentification = (AppCompatTextView) findViewById(R.id.num_Identification);
        numIdentification.setText(lemurModel.getIdLemur());
    }
}
