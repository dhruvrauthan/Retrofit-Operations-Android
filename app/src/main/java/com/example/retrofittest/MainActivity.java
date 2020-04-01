package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofittest.adapters.CommentsRecyclerAdapter;
import com.example.retrofittest.adapters.PostsRecyclerAdapter;
import com.example.retrofittest.models.Comment;
import com.example.retrofittest.models.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //UI
    private RecyclerView mRecyclerView;
    private PostsRecyclerAdapter mPostsRecyclerAdapter;
    private CommentsRecyclerAdapter mCommentsRecyclerAdapter;

    //Variables
    private List<Post> mPostsList;
    private List<Comment> mCommentsList;
    private JsonPlaceholderApi mJsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.main_recyclerview);
        mPostsList = new ArrayList<>();
        mCommentsList = new ArrayList<>();

        retrofitOperations();

    }

    private void retrofitOperations() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mJsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

        //getPosts();
        //getComments();
        createPost();

    }

    private void createPost() {

        Map<String, String> fields= new HashMap<>();
        fields.put("userId","19");
        fields.put("title","new title");
        fields.put("body","some content");

        final Post post= new Post(19, "new title","some content" );
        Call<Post> call= mJsonPlaceholderApi.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){

                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    return ;

                }

                Post postresponse= response.body();
                mPostsList.add(post);
                initPostRecyclerView();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getComments() {

        Call<List<Comment>> call = mJsonPlaceholderApi.getComments("https://jsonplaceholder.typicode.com/posts/3/comments");
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    return;

                }

                mCommentsList = response.body();
                initCommentRecyclerView();

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getPosts() {

        Map<String,String> parameters= new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        //Call<List<Post>> call = mJsonPlaceholderApi.getPosts(new Integer[]{1,2,3}, "id", "desc");
        Call<List<Post>> call = mJsonPlaceholderApi.getPosts(parameters);
        call.enqueue(new Callback<List<Post>>() {       //this calls the method on a background thread so the app doesn't crash
            @Override
            //as one network operation is already running on main thread
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                    return;

                }

                mPostsList = response.body();
                Log.d(TAG, "onResponse: mPostsList filled");

                initPostRecyclerView();

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void initPostRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mPostsRecyclerAdapter = new PostsRecyclerAdapter(mPostsList);
        mRecyclerView.setAdapter(mPostsRecyclerAdapter);

        Log.d(TAG, "initRecyclerView: recyclerview is filled");

    }

    private void initCommentRecyclerView() {

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCommentsRecyclerAdapter= new CommentsRecyclerAdapter(mCommentsList);
        mRecyclerView.setAdapter(mCommentsRecyclerAdapter);

    }

}
