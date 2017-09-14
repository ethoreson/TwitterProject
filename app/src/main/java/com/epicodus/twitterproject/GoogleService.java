package com.epicodus.twitterproject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;
//
public class GoogleService {

    public static void findRepresentatives(String address, Callback callback) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
