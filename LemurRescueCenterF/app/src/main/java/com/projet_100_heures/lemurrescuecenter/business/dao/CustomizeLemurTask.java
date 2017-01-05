package com.projet_100_heures.lemurrescuecenter.business.dao;

import android.os.AsyncTask;

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
    private static final String URL_UPDATE_POST = "http://localhost:8080/FrontLRCWebService/rest/updateLemurien";
    private final CustomizeLemurTask.CustomizeLemurListenner customizeLemurListenner;

    public CustomizeLemurTask(CustomizeLemurTask.CustomizeLemurListenner callback) {
        this.customizeLemurListenner = callback;
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


    private String post(String url, String json) throws IOException {
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

    public interface CustomizeLemurListenner {
        void onLemurPosted(LemurModel lemurModel);
    }
}
