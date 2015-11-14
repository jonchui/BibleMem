package com.faithcomesbyhearing.dbt;

import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

class RestClient {

    private final String BASE_URL = "http://dbt.io";
    private final String API_PARAM = "key";
    private final String API_VERSION_PARAM = "v";
    private String apiKey;

    public RestClient(String key) {
        apiKey = key;
    }

    public InputStreamReader execute(String endpoint, HashMap<String, String> params) throws Exception {
        Uri.Builder builder = Uri.parse(BASE_URL).buildUpon();
        builder.path(endpoint);
        builder.appendQueryParameter(API_PARAM, apiKey);
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getValue() == null || param.getValue().equals("")) {
                continue;
            }
            builder.appendQueryParameter(param.getKey(), param.getValue());
        }
        builder.appendQueryParameter(API_VERSION_PARAM, "2");
        OkHttpClient client = new OkHttpClient();
        OkUrlFactory factory = new OkUrlFactory(client);
        HttpURLConnection connection = factory.open(new URL(builder.build().toString()));
        InputStream is = connection.getInputStream();
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception(connection.getResponseMessage());
        }
        InputStreamReader isr = new InputStreamReader(is);
        return isr;
    }
}
