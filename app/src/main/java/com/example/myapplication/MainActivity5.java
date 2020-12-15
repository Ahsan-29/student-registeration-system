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

public class MainActivity5 extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5;
    Button delete;
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


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


        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepost();

            }
        });
    }


    private void deletepost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(et1.getText().toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity5.this, "This I.D does not exist ", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                } else {
                    Toast.makeText(MainActivity5.this, "I.D has been deleted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}