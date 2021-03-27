package com.iamshekhargh.averybasicapp.networkFiles;


import com.iamshekhargh.averybasicapp.models.response.UserResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by <<-- iamShekharGH -->>
 * on 10 March 2021
 * at 9:53 PM.
 */
public interface ApiEndpoints {

    @GET("todos/2/")
    Call<UserResponseModel> getUserInfo();

    @GET("albums/1/photos")
    Call<List<UserResponseModel>> getAllList();
}
