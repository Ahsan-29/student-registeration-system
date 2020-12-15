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

public class MainActivity2 extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5;
    Button create;
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        textViewResult = findViewById(R.id.text_view_result);
        et1 = (EditText) findViewById(R.id.e1);
        et2 = (EditText) findViewById(R.id.e2);
        et3 = (EditText) findViewById(R.id.e3);
        et4 = (EditText) findViewById(R.id.e4);
        et5 = (EditText) findViewById(R.id.e5);
        create = (Button) findViewById(R.id.create);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.30:3306/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPost();
            }
        });

    }

    private void createPost() {

        Map<String, String> fields = new HashMap<>();
        fields.put("id", et1.getText().toString());
        fields.put("email", et2.getText().toString());
        fields.put("password", et3.getText().toString());
        fields.put("name", et4.getText().toString());
        fields.put("School", et5.getText().toString());

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity2.this, "Data not added in record", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(MainActivity2.this, "Data added in record sucessfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
