package com.example.dijonkariz.fotomwa.interfaces;

import android.widget.ImageView;

import com.example.dijonkariz.fotomwa.model.ImageModel;

public interface GalleryItemClickListener {
    void onGalleryItemClickListener(int position, ImageModel imageModel, ImageView imageView);
}
