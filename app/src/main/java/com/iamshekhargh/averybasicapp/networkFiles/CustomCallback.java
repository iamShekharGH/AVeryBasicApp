package com.iamshekhargh.averybasicapp.networkFiles;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by <<-- iamShekharGH -->>
 * on 12 March 2021
 * at 9:42 PM.
 */
public abstract class CustomCallback<T> implements Callback<T> {

    public abstract void onDataArrived(T t);

    public abstract void onError(String message);

    @Override
    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        onDataArrived(response.body());
    }

    @Override
    public void onFailure(@NotNull Call<T> call, Throwable t) {
        onError("onFailure \t::" + t.toString());
        t.printStackTrace();
    }
}
