package com.iamshekhargh.averybasicapp.networkFiles.headers;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by <<-- iamShekharGH -->>
 * on 10 March 2021
 * at 10:05 PM.
 */
public class AuthTokenHeader implements Interceptor {

    private static String auth_token = "";
    Context c;

    public static String getAuth_token() {
        return auth_token;
    }

    public static void setAuth_token(String auth_token) {
        AuthTokenHeader.auth_token = auth_token;
    }

    public AuthTokenHeader(Context c) {
        this.c = c;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder b = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json");
        if (this.c != null) {
            // set auth_token from shared prefrences
            if (!auth_token.isEmpty())
                b.addHeader("auth_token", auth_token);
        }

        Request r = b.method(original.method(), original.body()).build();
        return chain.proceed(r);
    }
}
