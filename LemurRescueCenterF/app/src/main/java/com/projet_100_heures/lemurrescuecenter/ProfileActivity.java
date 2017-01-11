package com.projet_100_heures.lemurrescuecenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.projet_100_heures.lemurrescuecenter.activity.CustomActivity;
import com.projet_100_heures.lemurrescuecenter.activity.NfcActivity;
import com.projet_100_heures.lemurrescuecenter.activity.StatsActivity;
import com.projet_100_heures.lemurrescuecenter.activity.VetoFileActivity;
import com.projet_100_heures.lemurrescuecenter.business.alertDialog.SearchLemurDialog;
import com.projet_100_heures.lemurrescuecenter.business.dao.RetrieveLemurTask;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity implements RetrieveLemurTask.LemurListenner, SearchLemurDialog.SearchLemurIdListenner {
    LemurModel lemurModelintent;
    private static final String TAG = ProfileActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);


        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingButton);
        ImageButton vetoFileButton = (ImageButton) findViewById(R.id.vetoButton);
        ImageButton statsButton = (ImageButton) findViewById(R.id.statButton);
        ImageButton customsButton = (ImageButton) findViewById(R.id.customButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ProfileActivity.this, NfcActivity.class);
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
                intent.putExtra("idLemur",Integer.toString(lemurModelintent.getIdDB()));

                String str = Integer.toString(lemurModelintent.getIdDB());
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
        if (id == R.id.searchLemur) {
            SearchLemurDialog alertDialog = new SearchLemurDialog();
            alertDialog.setmListener(ProfileActivity.this);

            alertDialog.show(getFragmentManager(), TAG);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLemurRetrieved(LemurModel lemurModel) {

        lemurModelintent= new LemurModel();
        lemurModelintent=lemurModel;

        AppCompatTextView name = (AppCompatTextView) findViewById(R.id.lemurName);
        if(!lemurModel.getName().equals("")){
            name.setText(lemurModel.getName());
        }
        else {
            name.setText("Non déterminé");
        }

        AppCompatTextView age = (AppCompatTextView) findViewById(R.id.lemureAge);
        if(!lemurModel.getBirthDate().equals("")){
            age.setText(lemurModel.getBirthDate());
        }
        else {
            age.setText("Non déterminé");
        }

        AppCompatTextView sexe = (AppCompatTextView) findViewById(R.id.lemurGender);
        if(!lemurModel.getGender().equals("")){
            sexe.setText(lemurModel.getGender());
        }
        else {
            sexe.setText("Non déterminé");
        }

        AppCompatTextView numIdentification = (AppCompatTextView) findViewById(R.id.num_Identification);
        if(!lemurModel.getIdLemur().equals("")){
            numIdentification.setText(lemurModel.getIdLemur());
        }
        else {
            numIdentification.setText("Non déterminé");
        }

        AppCompatTextView origine = (AppCompatTextView) findViewById(R.id.LemurOrigin);
        if(!lemurModel.getOrigin().equals("")){
            origine.setText(lemurModel.getOrigin());
        }
        else {
            origine.setText("Non déterminé");
        }

        AppCompatTextView entryDate = (AppCompatTextView) findViewById(R.id.lemurEntryDate);
        if(!lemurModel.getEntryDate().equals("")){
            entryDate.setText(lemurModel.getEntryDate());
        }
        else {
            entryDate.setText("Non déterminé");
        }

        AppCompatTextView entryNature = (AppCompatTextView) findViewById(R.id.LemurEntryNature);
        if(!lemurModel.getEntryNature().equals("")){
            entryNature.setText(lemurModel.getEntryNature());
        }
        else {
            entryNature.setText("Non déterminé");
        }

        AppCompatTextView lastOwner = (AppCompatTextView) findViewById(R.id.LemurLastOwner);
        if(!lemurModel.getLastOwner().equals("")){
            lastOwner.setText(lemurModel.getLastOwner());
        }
        else {
            lastOwner.setText("Non déterminé");
        }

        AppCompatTextView leavingDate = (AppCompatTextView) findViewById(R.id.LemurLeavingDate);
        if(!lemurModel.getLeaveDate().equals("")){
            leavingDate.setText(lemurModel.getLeaveDate());
        }
        else {
            leavingDate.setText("Non déterminé");
        }

        AppCompatTextView leavingNature = (AppCompatTextView) findViewById(R.id.LemurLeavingNature);
        if(!lemurModel.getLeaveNature().equals("")){
            leavingNature.setText(lemurModel.getLeaveNature());
        }
        else {
            leavingNature.setText("Non déterminé");
        }
        AppCompatTextView leavingCommentary = (AppCompatTextView) findViewById(R.id.LemurLeavingCommentary);
        if(!lemurModel.getLeaveCommentary().equals("")){
            leavingCommentary.setText(lemurModel.getLeaveCommentary());
        }
        else {
            leavingCommentary.setText("Non déterminé");
        }
    }

    @Override
    public void onIDBRetrieved(String idDB) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("idDB", idDB);
        }catch (JSONException e) {
            Log.e("Jobect", e.getMessage());
        }

        RetrieveLemurTask retrieveLemurTask = new RetrieveLemurTask(ProfileActivity.this);
        retrieveLemurTask.execute(jsonObject);
    }

}
