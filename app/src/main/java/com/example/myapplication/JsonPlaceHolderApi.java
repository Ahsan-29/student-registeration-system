package com.example.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {


    @FormUrlEncoded
    @POST("create")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    ////////

    @GET("list")
    Call<List<Post>> getPosts();

    /////////

    @DELETE("delete/{id}")
    Call<Void> deletePost(@Path("id") String id);

    /////////

    @PUT("update/{id}")
    Call<Post> putPost(@Body Post post);
    //Call<Post> putPost(@FieldMap Map<String, String> fields);


}