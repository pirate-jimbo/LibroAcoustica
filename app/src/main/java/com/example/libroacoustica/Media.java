package com.example.libroacoustica;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class Media extends AppCompatActivity {

    ListView myListViewforaudio;
    public static ArrayList<File>audioList;
    String[]items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_media);

        myListViewforaudio=(ListView) findViewById(R.id.Audios);

        runtimePermission();

        /*myListViewforaudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(),Audio.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });*/
    }

    public void runtimePermission(){

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        display();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {


                        permissionToken.continuePermissionRequest();



                    }
                }).check();

    }

    public ArrayList<File> findaudio(File file){
        ArrayList<File>arrayList = new ArrayList<>();
        File[]files =file.listFiles();

        for(File singleFile: files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden()){

                arrayList.addAll(findaudio(singleFile));
            }
            else
            {
                if(singleFile.getName().endsWith((".txt")))
                {
                    arrayList.add(singleFile);
                }
            }
        }

        audioList=new ArrayList<File>(arrayList);

        for (File singleFile: audioList)
        {
            Log.d("jimbo", "ki ki ase "+singleFile.toString());
        }


        return arrayList;
    }

    void display(){
        final ArrayList<File>myAudios =findaudio(Environment.getExternalStorageDirectory());
        items=new String[myAudios.size()];
        for(int i=0;i<myAudios.size();i++)
        {
            items[i]=myAudios.get(i).getName().toString().replace(".txt",".txt");
        }
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        myListViewforaudio .setAdapter(myAdapter);


        myListViewforaudio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Position", "kon jaygay ase "+position);

                String audioName=myListViewforaudio.getItemAtPosition(position).toString();

                startActivity(new Intent(getApplicationContext(),Audio.class)
                .putExtra("audio",myAudios)
                .putExtra("audioname",audioName)
                .putExtra("pos",position));

            }
        });

    }



}