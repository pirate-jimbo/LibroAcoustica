package com.example.libroacoustica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

public class Pdfview extends AppCompatActivity {

    PDFView pdfView;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_pdfview);

        pdfView = (PDFView)findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position",-1);

        displayPDF();
    }
    private void displayPDF()
    {
        Log.d("jimbo", "paise: "+position);
        Log.d("jimbo", "paise2 "+Book.fileList.get(position).toString());
        pdfView.fromFile(Book.fileList.get(position))
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}