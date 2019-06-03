package com.example.murilomarcineiro.stalker.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.murilomarcineiro.stalker.R;
import com.example.murilomarcineiro.stalker.model.Person;

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {
    private ArrayList<Bitmap> picList;
    private GalleryListener galleryListener;

    public GalleryAdapter(GalleryListener galleryListener, ArrayList<String> paths){
        this.picList = this.getBitmaps(paths);
        this.galleryListener = galleryListener;
    }

    public ArrayList<Bitmap> getBitmaps(ArrayList<String> paths){
        ArrayList<Bitmap> bitmaps = new ArrayList<>();

        Bitmap bitmap;
        for(String path : paths){
            File image = new File(path);
            bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
            bitmaps.add(bitmap);
        }

        return bitmaps;
    }

    public interface GalleryListener{
        void onClickPhoto(Bitmap pic);
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_gallery, viewGroup, false);
        GalleryViewHolder galleryViewHolder = new GalleryViewHolder(view, galleryListener);
        return galleryViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder galleryViewHolder, int i) {
        galleryViewHolder.bind(this.picList.get(i));
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(picList!=null)
            ret = this.picList.size();
        return ret;
    }
}
