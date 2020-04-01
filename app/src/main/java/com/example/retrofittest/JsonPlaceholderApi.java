package com.example.retrofittest;

import com.example.retrofittest.models.Comment;
import com.example.retrofittest.models.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonPlaceholderApi {

    @GET("posts")       //relative url
    Call<List<Post>> getPosts(              //retrofit automatically puts a question mark before
            @Query("userId") Integer[] userId,    //@Query and an equal to after it(posts?userId=1)
            @Query("_sort") String sort,        //can pass more than one userId using array
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> paramaters);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);    //it puts int postId into {id} and converts to string

    @GET
    Call<List<Comment>> getComments(@Url String url);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String content
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String,String> fields);

}
