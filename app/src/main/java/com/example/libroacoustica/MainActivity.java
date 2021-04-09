package com.example.libroacoustica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST = 42;
    CardView cardstorage;
    CardView cardbook;
    CardView cardmedia;
    CardView cardfeedback;


    Intent myFileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        cardstorage = findViewById(R.id.storage);
        cardbook = findViewById(R.id.book);
        cardmedia = findViewById(R.id.media);

        cardfeedback = findViewById(R.id.feedback);



        cardstorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                myFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent,READ_REQUEST);

            }

        });



        cardmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myFileIntent = new Intent(MainActivity.this,Media.class);
                startActivity(myFileIntent);




            }

        });

        cardbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myFileIntent = new Intent(MainActivity.this,Book.class);
                startActivity(myFileIntent);




            }


        });


        cardfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myFileIntent = new Intent(MainActivity.this,Feedback.class);
                startActivity(myFileIntent);
            }
        });




            
        }






    }
