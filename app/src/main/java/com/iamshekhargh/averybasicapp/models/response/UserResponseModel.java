package com.iamshekhargh.averybasicapp.models.response;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * Created by <<-- iamShekharGH -->>
 * on 14 March 2021
 * at 1:30 PM.
 * <p>
 * {
 * "userId": 1,
 * "id": 2,
 * "title": "quis ut nam facilis et officia qui",
 * "completed": false
 * }
 */
public class UserResponseModel {


    @SerializedName("userId")
    int userId;
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("completed")
    boolean completed;

    @NotNull
    @Override
    public String toString() {
        return "UserResponseModel{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
