package com.projet_100_heures.lemurrescuecenter.business.dao;

import android.os.AsyncTask;

import com.projet_100_heures.lemurrescuecenter.business.mapping.MappingLemur;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by corto_000 on 05/01/2017.
 */

public class RetrieveLemurWeightTask extends AsyncTask<Void,Void, JSONArray> {


    private static final String URL_GET_TEST_Weight ="https://trello-attachments.s3.amazonaws.com/58532b6c95e4b53eb15676bb/586b574e130cd59c7da0f167/b27300ac81659c46c095015b83e6dd63/PoidsNAME.json";
    private final RetrieveLemurWeightTask.LemurWeightListenner lemurWeightListenner;
    private OkHttpClient client;

    public RetrieveLemurWeightTask(RetrieveLemurWeightTask.LemurWeightListenner callback) {
        this.lemurWeightListenner = callback;
    }

    @Override
    protected JSONArray doInBackground(Void... params) {
        String response="";
        JSONArray jsonArray = new JSONArray();

        client = new OkHttpClient().newBuilder().build();

        try {
            Request request = new Request.Builder()
                    .url(URL_GET_TEST_Weight)
                    .build();

            response = client.newCall(request)
                    .execute()
                    .body()
                    .string();
            try {
                jsonArray = new JSONArray(response);

            }
            catch (JSONException e){
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    @Override
    protected void onPostExecute(JSONArray s) {
        super.onPostExecute(s);
        super.cancel(true);
        LemurModel lemurModel = new LemurModel();
        lemurModel = MappingLemur.mappingWeightLemurModel(lemurModel, s);
        lemurWeightListenner.onLemurWeightRetrieved(lemurModel);
    }

    public interface LemurWeightListenner {
        void onLemurWeightRetrieved(LemurModel lemurModel);
    }
}
