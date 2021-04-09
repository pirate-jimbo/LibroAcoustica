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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static com.karumi.dexter.Dexter.withActivity;

public class Book extends AppCompatActivity {
    ListView myListviewpdfbook;
    public static String[]items;
    public static ArrayList<File>fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_book);

        myListviewpdfbook=(ListView) findViewById(R.id.Pdfbook);

        runtimePermission();

        myListviewpdfbook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(),Pdfview.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
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



        Dexter.withContext(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {



                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {





                    }
                }).check();


    }



    public static ArrayList<File> findbook(File root){
        ArrayList<File> at = new ArrayList<File>();
        ArrayList<File>files = new ArrayList<File>();
        if(root.isDirectory())
        {
           // Log.d("dhukse", "findbook: "+root.toString());
            //Log.d("dhukse", "findbook: "+Arrays.asList(root.listFiles()).size());
            files.addAll(Arrays.asList(root.listFiles()));
        }

        for(File singleFile : files)
        {
            //Log.d("ki ase??", "findbook: "+singleFile.toString());
        }
        //File[] files =new File(root).listFiles();
        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                at.addAll(findbook(singleFile));

            }
            else{
                //Log.d("dhukse", "findbook: ");
                if(singleFile.getName().endsWith(".pdf")){
                    at.add(singleFile);
                }
            }
        }
        fileList=new ArrayList<File>(at);
        return at;
    }





   /* private ArrayList<File> findbook(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        if(parentDir.listFiles().length>0) {

            files.addAll(Arrays.asList(parentDir.listFiles()));

        }
        //files.addAll(Arrays.asList(parentDir.listFiles()));
        while (!files.isEmpty()) {
            File file = files.remove();
            if (file.isDirectory()) {
                files.addAll(Arrays.asList(file.listFiles()));
            } else if (file.getName().endsWith(".pdf")) {
                inFiles.add(file);
            }
        }
        return inFiles;
    }*/

   /* public ArrayList<File>findbook(File file){


            ArrayList<File>arrayList = new ArrayList<>();
            File[]files =file.listFiles();
            Log.d("Find", "findbook dhukse 1: "+Environment.getExternalStorageDirectory().toString());

            for(File singleFile: files)
            {
                if(singleFile.isDirectory() && !singleFile.isHidden()){

                    arrayList.addAll(findbook(singleFile));
                }
                else
                {
                    if(singleFile.getName().endsWith((".pdf")))
                    {
                        arrayList.add(singleFile);
                        Log.d("khujtesi", "findbook: "+singleFile.toString());
                    }
                }
            }


            return arrayList;

        }*/




    void display(){
        final ArrayList<File>myBooks =findbook(Environment.getExternalStorageDirectory());
        items=new String[myBooks.size()];
        for(int i=0;i<myBooks.size();i++)
        {
            items[i]=myBooks.get(i).getName().toString().replace(".pdf",".pdf");
            Log.d("item", items[i]);
        }
        ArrayAdapter<String>myAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        myListviewpdfbook.setAdapter(myAdapter);

    }







}