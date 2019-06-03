package com.example.murilomarcineiro.stalker.view;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.murilomarcineiro.stalker.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView ivPic;
    private Bitmap pic;
    private GalleryAdapter.GalleryListener galleryListener;

    public GalleryViewHolder(@NonNull View itemView, GalleryAdapter.GalleryListener galleryListener) {
        super(itemView);
        ivPic = (ImageView) itemView.findViewById(R.id.ivPic);
        itemView.setOnClickListener(this);
        this.galleryListener = galleryListener;
    }

    public void bind(Bitmap pic){
        this.pic = pic;
        ivPic.setImageBitmap(pic);
    }

    @Override
    public void onClick(View v) {
        galleryListener.onClickPhoto(this.pic);
    }
}
