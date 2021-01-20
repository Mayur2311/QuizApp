package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.myquiz.SplashActivity.catList;
import static com.example.myquiz.SplashActivity.selectd_cat_index;

public class ScoreActivity extends AppCompatActivity {

    private TextView score;
    private Button done;
    String categoryName;

    public static final String ROOT_URL = "http://10.0.0.100/";

    int score_int;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = findViewById(R.id.sa_score);
        done  = findViewById(R.id.sa_done);

        categoryName = catList.get(selectd_cat_index).getName();

        String score_str = getIntent().getStringExtra("SCORE");
        score_int = Integer.parseInt(score_str);
        score.setText(score_str);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(ScoreActivity.this, "Categoty: "+categoryName+"\n Score: "+score_str, Toast.LENGTH_SHORT).show();

                insertUser();

                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                ScoreActivity.this.startActivity(intent);
                ScoreActivity.this.finish();

            }
        });

    }

    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RetroInterface api = adapter.create(RetroInterface.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                categoryName,
                score_int,

                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(ScoreActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(ScoreActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}