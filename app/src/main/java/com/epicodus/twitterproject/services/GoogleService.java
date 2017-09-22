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
                //JSONArray googleJSON = new JSONArray (jsonData);
                JSONArray officialsJSON = googleJSON.getJSONArray("officials");
                for (int i= 0; i < officialsJSON.length(); i++) {
                    JSONObject representativeJSON = officialsJSON.getJSONObject(i);
                    String name = representativeJSON.getString("name");
                    String party = representativeJSON.getString("party");

                    //JSONObject telephoneJSON = phoneJSON.getJSONObject(0);
                    //JSONArray phonesJSON = googleJSON.getJSONArray("phones");
                    ArrayList<String> phoneArray = new ArrayList<>();
                    for (int j=0; j < officialsJSON.length(); j++) {
                        JSONObject telephoneJSON = officialsJSON.getJSONObject(j);
                        String phone = telephoneJSON.getString("phones");
                        phoneArray.add(phone);
                    }
                    String phone = representativeJSON.getString("phones");

                    Log.d("string", "PHONE: " + phoneArray);

//                    //JSONArray channelsJSON = googleJSON.getJSONArray("channels");
//                    ArrayList<String> channelsArray = new ArrayList<>();
//                    for (int k=0; k < officialsJSON.length(); k++) {
//                        JSONObject socialMediaJSON = officialsJSON.getJSONObject(k);
//                        String channels = socialMediaJSON.getString("channels");
//                        for (int l=0; l < officialsJSON.length(); l++) {
//                            JSONArray keysJSON = officialsJSON.getJSONArray(l);
//                            String key = keysJSON.getString("type");
//                            channelsArray.add(key);
//                        }
//                        for (int m=0; m < officialsJSON.length(); m++) {
//                            JSONObject valuesJSON = officialsJSON.getJSONObject(m);
//                            String value = valuesJSON.getString("id");
//                            channelsArray.add(value);
//                        }
//                        //channelsArray.add(channels);
//                    }
//
//                    Log.d("string", "CHANNELS: " + channelsArray);

//                    JSONArray phoneJSON = representativeJSON.getArray("phone");
//                    JSONObject phoneOneJSON = phoneJSON.getJSONObject(0);
//                    String phone = representativeJSON.getJSONArray("phone");
//                    ArrayList<String> phone = new ArrayList<>();
//                    JSONArray phoneJSON = representativeJSON.getJSONArray("officials").getJSONObject(0).getJSONArray("phones");
//                    for (int j = 0; j < phoneJSON.length(); j++) {
//                        phone.add(phoneJSON.getJSONObject(j).getString("phones").toString());
//                    }

//                    ArrayList<String> channels = new ArrayList<>();
//                    JSONArray channelsJSON = representativeJSON.getJSONArray("channels");
//                    String channel = channelsJSON.toString();
//                    ArrayList<String> phone = new ArrayList<>();
//                    JSONArray phoneJSON = representativeJSON.getJSONArray("phones");
//                    String phones = phoneJSON.toString();

                    Representative representative = new Representative(name, party, phoneArray);
                    representatives.add(representative);
                    Log.d("string", "processResults: " + representative);
                }


        //            JSONArray channelsJSON = representativeJSON.getJSONArray("channels");
     //               JSONObject channelOneJSON = channelsJSON.getJSONObject(0);
                    //JSONArray channelsJSON = representativeJSON.getJSONArray("channels").getJSONObject(0).getJSONArray("channels");
//                    for (int k = 0; k < channelsJSON.length(); k++) {
//                        channels.add(channelsJSON.getJSONObject(k).getString("channels").toString());
//                        Log.d("string", "processResults: " + channels);
//                    }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return representatives;
    }
}





