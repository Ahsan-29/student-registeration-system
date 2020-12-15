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

public class MainActivity4 extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5;
    Button update;
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


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


        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepost();

            }
        });
    }

    private void updatepost() {
        Post post = new Post(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), et5.getText().toString());
        Call<Post> call = jsonPlaceHolderApi.putPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity4.this, "I.D not available", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(MainActivity4.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

                /*Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "User ID: " + postResponse.getEmail() + "\n";
                content += "Title: " + postResponse.getPassword() + "\n";
                content += "Text: " + postResponse.getName() + "\n\n";
                content += "Text: " + postResponse.getSchool() + "\n\n";
                textViewResult.setText(content);

                 */


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
