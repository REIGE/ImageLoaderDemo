package com.reige.imageloaderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageDataSource.OnImagesLoadedListener{

    private RecyclerView rvImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化布局
        initData();//初始化数据
        new Thread(()->{
            getAllFiles(new File("/"));
        }).start();
//        new ImageDataSource(this, null, this);

    }

    private void initData() {

    }

    private void initView() {
        rvImages = (RecyclerView) findViewById(R.id.rv_images);
    }

    void getAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    getAllFiles(f);
                } else {
                   if(f.getName().endsWith(".jpg")){
                       Log.e("图片",f.getName());
                   }
                }
            }
        }
    }

    @Override
    public void onImagesLoaded(List<ImageFolder> imageFolders) {
        ArrayList<ImageItem> images = imageFolders.get(0).images;
        for (ImageItem i: images){
            String name = i.name;
            String path = i.path;
            Log.e("name",name+"path"+path);
        }
    }
}
