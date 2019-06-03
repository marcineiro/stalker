package com.example.murilomarcineiro.stalker;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;


import com.example.murilomarcineiro.stalker.view.GalleryAdapter;
import com.example.murilomarcineiro.stalker.view.PhotoOpenDialogFragment;

import java.util.ArrayList;


public class PhotosActivity extends AppCompatActivity implements GalleryAdapter.GalleryListener {

    private RecyclerView rvGallery;
    private GalleryAdapter galleryAdapter;
    private PhotoOpenDialogFragment photoOpenDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        rvGallery = (RecyclerView) findViewById(R.id.rvGallery);
        ArrayList<String> paths = (ArrayList<String>) getIntent().getStringArrayListExtra(NewActivity.LIST_PATH_KEY);
        galleryAdapter = new GalleryAdapter(this, paths);


        Display display = getWindowManager().getDefaultDisplay();       // ajustar numero de fotos por linha do gridLayout
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int spanCount = (int) java.lang.Math.ceil(dpWidth / 100);

        rvGallery.setLayoutManager(new GridLayoutManager(this, spanCount));
        rvGallery.setHasFixedSize(true);
        rvGallery.setAdapter(galleryAdapter);

        photoOpenDialogFragment = new PhotoOpenDialogFragment();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_gallery_activity,menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onClickPhoto(Bitmap pic) {
        photoOpenDialogFragment.setBitmap(pic);
        FragmentManager fragmentManager = getSupportFragmentManager();
        photoOpenDialogFragment.show(fragmentManager,"showPic");
    }
}
