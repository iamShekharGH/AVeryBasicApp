package com.iamshekhargh.averybasicapp.networkFiles;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.iamshekhargh.averybasicapp.networkFiles.headers.AuthTokenHeader;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by <<-- iamShekharGH -->>
 * on 10 March 2021
 * at 9:52 PM.
 */
public class RetrofitObjectProvider {

    public static Retrofit r = null;

    public static Retrofit getClient(String url, Context c) {
        if (r == null) {
            // Interceptor for Logging Services.
            HttpLoggingInterceptor h = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient.Builder b = new OkHttpClient.Builder();
            b.addInterceptor(h);
            b.addInterceptor(new AuthTokenHeader(c));

            r = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(b.build())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();
        }
        return r;
    }
}
