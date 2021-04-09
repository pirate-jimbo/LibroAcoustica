package com.example.libroacoustica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_feedback);

        EditText edit1=(EditText)findViewById(R.id.edit1);
        EditText edit2=(EditText)findViewById(R.id.edit2);
        Button button=(Button)findViewById(R.id.buttonf);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("message/html");
                //intent.putExtra(Intent.EXTRA_EMAIL,new String("minhjaulislam.aust@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback From App");
                intent.putExtra(Intent.EXTRA_TEXT,"Name:"+edit1.getText()+"\n Message:"+edit2.getText());
                intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "minhjaulislam.aust@gmail.com" });
                
                try {
                    startActivity(Intent.createChooser(intent, "Please select E-mail"));
                
                }
                catch  (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(Feedback.this, "There are no Email Clients", Toast.LENGTH_SHORT).show();
                    
                }
            }
        });
    }
}