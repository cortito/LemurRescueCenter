package com.projet_100_heures.lemurrescuecenter.business.dao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.projet_100_heures.lemurrescuecenter.business.mapping.MappingLemur;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by corto_000 on 05/01/2017.
 */

public class RetrieveLemurWeightTask extends AsyncTask<JSONObject,Void, JSONArray> {


    private static final String URL_RETRIEVE_WEIGHT_POST = "http://192.168.1.20:1818/FrontLRCWebService/rest/getPoids";
    private static final String URL_GET_TEST_Weight ="https://trello-attachments.s3.amazonaws.com/58532b6c95e4b53eb15676bb/586b574e130cd59c7da0f167/7bb463a273eaa642593d483f7847e48d/PoidsMANOU.json";
    private final RetrieveLemurWeightTask.LemurWeightListenner lemurWeightListenner;
    private OkHttpClient client;
    private final Activity activity;
    private ProgressDialog progressDialog;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RetrieveLemurWeightTask(RetrieveLemurWeightTask.LemurWeightListenner callback, Activity activity) {
        this.lemurWeightListenner = callback;
        this.activity = activity;
    }
    @Override
    protected JSONArray doInBackground(JSONObject... params) {

        try {
            JSONArray response = post(URL_RETRIEVE_WEIGHT_POST,params[0].toString());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private JSONArray post(String url, String json) throws IOException {
        client = new OkHttpClient();
        JSONArray jarray = null;
        JSONObject[] jsonObject = new JSONObject[50];
        String response ="";
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
             response = client.newCall(request)
                    .execute()
                    .body()
                    .string();

            Log.d("tag", response.toString());
            try {

                jarray = new JSONArray(response);

            }
            catch (JSONException e){
                e.printStackTrace();
            }


        }catch (IOException e){
            e.printStackTrace();
        }
        return jarray;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Chargement ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
/*
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
    }*/

    @Override
    protected void onPostExecute(JSONArray s) {
        super.onPostExecute(s);
        super.cancel(true);
        LemurModel lemurModel = new LemurModel();
        lemurModel = MappingLemur.mappingWeightLemurModel(lemurModel, s);
        lemurWeightListenner.onLemurWeightRetrieved(lemurModel);
        progressDialog.cancel();
    }
    public interface LemurWeightListenner {
        void onLemurWeightRetrieved(LemurModel lemurModel);
    }
}
