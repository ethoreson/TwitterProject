package com.epicodus.twitterproject.services;

import android.util.Log;

import com.epicodus.twitterproject.Constants;
import com.epicodus.twitterproject.models.Representative;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
//
public class GoogleService {

    public static void findRepresentatives(String zipCode, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GOOGLE_BASE_URL + "address=" + zipCode + "&includeOffices=true&fields=divisions%2Ckind%2Coffices(name%2Croles%2Csources)%2Cofficials(channels%2Cname%2Cparty%2Cphones%2CphotoUrl)&key=AIzaSyALQDoCiusD0Poqe2mDgGo78zoQy31U2N0").newBuilder();
        urlBuilder.addQueryParameter(Constants.GOOGLE_ZIPCODE_QUERY_PARAMETER, zipCode);
        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)
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

                    ArrayList<String> phoneArray = new ArrayList<>();
                    for (int j=0; j < officialsJSON.length(); j++) {
                        JSONObject telephoneJSON = officialsJSON.getJSONObject(j);
                        String phone = telephoneJSON.getString("phones");
                        phoneArray.add(phone);
                    }
                    String phone = representativeJSON.getString("phones");



                    Representative representative = new Representative(name, party, phone);
                    representatives.add(representative);
                    Log.d("string", "processResults: " + representative);
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





