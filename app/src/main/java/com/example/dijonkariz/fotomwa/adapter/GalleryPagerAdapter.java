package com.example.dijonkariz.fotomwa.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dijonkariz.fotomwa.fragments.gallery.ImageDetailFragment;
import com.example.dijonkariz.fotomwa.model.ImageModel;

import java.util.ArrayList;

public class GalleryPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ImageModel> images;

    public GalleryPagerAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<ImageModel> images) {
        super(fm, behavior);
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ImageModel image = images.get(position);
        return ImageDetailFragment.newInstance(image, image.getName());
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
