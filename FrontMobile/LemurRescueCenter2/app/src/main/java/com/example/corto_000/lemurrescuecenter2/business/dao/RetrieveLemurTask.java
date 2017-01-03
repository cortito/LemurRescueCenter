package com.example.corto_000.lemurrescuecenter2.business.dao;

import android.os.AsyncTask;

import com.example.corto_000.lemurrescuecenter2.business.mapping.MappingLemur;
import com.example.corto_000.lemurrescuecenter2.model.LemurModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by corto_000 on 26/12/2016.
 */

public class RetrieveLemurTask extends AsyncTask<Void, Void, JSONObject> {
    private static final String URL_POST = "http://bab-laboratory.com/TrackYourWay/connectbdd.php";
    private static final String URL_GET_TEST ="http://bab-laboratory.com/lrc/getLemurien.html";
    private final LemurListenner lemurListenner;
    private OkHttpClient client;

    public RetrieveLemurTask(LemurListenner callback) {
        this.lemurListenner = callback;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        String response="";
        JSONObject jobject = new JSONObject();

        client = new OkHttpClient().newBuilder().build();

        try {
            Request request = new Request.Builder()
                    .url(URL_GET_TEST)
                    .build();

            response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
            try {
                jobject = new JSONObject(response);

            }
            catch (JSONException e){
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jobject;

    }

    @Override
    protected void onPostExecute(JSONObject s) {
        super.onPostExecute(s);
        super.cancel(true);
        LemurModel lemurModel = new LemurModel();
        lemurModel = MappingLemur.mappingLemurModel(lemurModel, s);
        lemurListenner.onLemurRetrieved(lemurModel);
    }

    public interface LemurListenner {
        void onLemurRetrieved(LemurModel lemurModel);
    }
}
