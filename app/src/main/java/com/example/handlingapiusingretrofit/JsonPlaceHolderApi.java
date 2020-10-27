package com.example.handlingapiusingretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/")
    Call<List<Post>> getPosts();

    @GET("/")
    Call<List<Post>> getPostsQueryBarcode(@Query("barcode") String code);

    @POST("post.php")
    Call<Post> addNewEntry(@Body Post post);
}
