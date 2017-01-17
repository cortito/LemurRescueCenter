package com.projet_100_heures.lemurrescuecenter.business.dao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.projet_100_heures.lemurrescuecenter.business.mapping.MappingLemur;
import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by corto_000 on 05/01/2017.
 */

public class RetrieveLemurTask extends AsyncTask<Void, Void, JSONObject> {

    private static final String URL_RETRIEVE_POST = "http://192.168.1.20:1818/FrontLRCWebService/rest/getLemurien";
    private static final String URL_GET_TEST ="https://trello-attachments.s3.amazonaws.com/58532b6c95e4b53eb15676bb/586b574e130cd59c7da0f167/0409c5e38c6eca51449ffdb0cb04ae96/model.json";
    private final LemurListenner lemurListenner;
    private final Activity activity;
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    ProgressDialog progressDialog;

public RetrieveLemurTask(LemurListenner callback , Activity activity) {
        this.lemurListenner = callback;
        this.activity = activity;
        }



    /*@Override
    protected JSONObject doInBackground(JSONObject... params) {

        try {
            JSONObject response = post(URL_RETRIEVE_POST,params[0].toString());

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    /*private JSONObject post(String url, String json) throws IOException {
        client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        JSONObject jobject = new JSONObject();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            String response = client.newCall(request)
                    .execute()
                    .body()
                    .string();

            jobject = new JSONObject(response);

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jobject;
    }*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Recherche du Lémurien ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
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
        progressDialog.cancel();
        LemurModel lemurModel = new LemurModel();
        lemurModel = MappingLemur.mappingLemurModel(lemurModel, s);
        lemurListenner.onLemurRetrieved(lemurModel);
    }

    public interface LemurListenner {
        void onLemurRetrieved(LemurModel lemurModel);
    }
}
