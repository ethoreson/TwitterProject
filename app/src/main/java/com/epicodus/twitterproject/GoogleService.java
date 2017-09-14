package com.epicodus.twitterproject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
}





