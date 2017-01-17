package com.projet_100_heures.lemurrescuecenter.business.dao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.projet_100_heures.lemurrescuecenter.model.LemurModel;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by corto_000 on 05/01/2017.
 */

public class CustomizeLemurTask extends AsyncTask<JSONObject,Void, Void >{
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String URL_UPDATE_POST = "http://192.168.1.20:1818/FrontLRCWebService/rest/updateLemurien";
    private final CustomizeLemurTask.CustomizeLemurListenner customizeLemurListenner;
    private final Activity activity;
    private ProgressDialog progressDialog;

    public CustomizeLemurTask(CustomizeLemurTask.CustomizeLemurListenner callback, Activity activity) {
        this.customizeLemurListenner = callback;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Chargement ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(JSONObject... params) {

        try {
            String response = post(URL_UPDATE_POST,params[0].toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String post(String url, String json) throws IOException{
        client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Toast.makeText(activity,"Problème de connexion ! Modifications non envoyées !",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.cancel();
    }

    public interface CustomizeLemurListenner {
        void onLemurPosted(LemurModel lemurModel);
    }
}
