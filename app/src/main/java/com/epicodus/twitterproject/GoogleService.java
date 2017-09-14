package com.epicodus.twitterproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;
//
public class GoogleService {
    public static void findRepresentatives(String zipCode, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

 //      String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url("https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyALQDoCiusD0Poqe2mDgGo78zoQy31U2N0")
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Representative> processResults(Response response) {
        ArrayList<Representative> representatives = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject googleJSON = new JSONObject(jsonData);
                JSONArray officialsJSON = googleJSON.getJSONArray("officials");

                for (int i= 0; i < officialsJSON.length(); i++) {
                    JSONObject representativeJSON = officialsJSON.getJSONObject(i);
                    String name = representativeJSON.getString("name");
                    String party = representativeJSON.getString("party");
                    ArrayList<String> channels = new ArrayList<>();
                    JSONArray channelsJSON = representativeJSON.getJSONArray("channels");

                    for (int y = 0; y < channelsJSON.length(); y++) {
                        channels.add(channelsJSON.getJSONArray(y).get(0).toString());
                    }
                    Representative representative = new Representative(name, party, channels);
                    representatives.add(representative);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return representatives;
    }
}





