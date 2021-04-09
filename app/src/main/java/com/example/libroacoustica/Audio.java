package com.example.libroacoustica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Audio extends AppCompatActivity {
    Button pauseButton;
    String s;

    TextToSpeech textToSpeech;
    int position = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_audio);

        position = getIntent().getIntExtra("pos",-1);

        pauseButton = findViewById(R.id.pauseButton);







        textToSpeech = new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener( ) {
                    @Override
                    public void onInit(int i) {
                   if(i == TextToSpeech.SUCCESS){
                       int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                   }
                    }
                });

        pauseButton.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                if(textToSpeech.isSpeaking())
                {
                    pauseButton.setBackgroundResource(R.drawable.icon_play);
                    textToSpeech.stop();
                }
                else
                {
                    pauseButton.setBackgroundResource(R.drawable.icon_pause);
                    Log.d("jimbo", "dhukse: "+position);
                    s = Media.audioList.get(position).toString();
                    File fileEvents = new File(s);
                    StringBuilder text = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(fileEvents));
                        String line;
                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        br.close();
                    } catch (IOException e) { }
                    String result = text.toString();

                    Log.d("jimbo" ,"kaj hoise "+s );
                    int speech = textToSpeech.speak(result,TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });






    }

    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }


}