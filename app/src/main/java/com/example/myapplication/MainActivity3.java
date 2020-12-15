package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5;
    Button get;
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        textViewResult = findViewById(R.id.text_view_result);
        et1 = (EditText) findViewById(R.id.e1);
        et2 = (EditText) findViewById(R.id.e2);
        et3 = (EditText) findViewById(R.id.e3);
        et4 = (EditText) findViewById(R.id.e4);
        et5 = (EditText) findViewById(R.id.e5);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.30:3306/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        get = (Button) findViewById(R.id.get);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getposts();
            }
        });
    }


    private void getposts() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "\nID: " + post.getId();
                    content += "\nemail: " + post.getEmail();
                    content += "\npassword: " + post.getPassword();
                    content += "\nname: " + post.getName();
                    content += "\nschool: " + post.getSchool() + "\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}