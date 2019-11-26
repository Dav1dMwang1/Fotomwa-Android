package com.example.dijonkariz.fotomwa.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.GalleryAdapter;
import com.example.dijonkariz.fotomwa.fragments.gallery.GalleryViewPagerFragment;
import com.example.dijonkariz.fotomwa.interfaces.GalleryItemClickListener;
import com.example.dijonkariz.fotomwa.model.ImageModel;
import com.example.dijonkariz.fotomwa.other.Utils;

import java.util.ArrayList;


public class PhotosFragment extends Fragment implements GalleryItemClickListener {
    private static final String TAG = PhotosFragment.class.getSimpleName();
    private ArrayList<ImageModel> images;
    private RecyclerView galleryRecyclerView;

    public PhotosFragment() {}

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);

//        galleryRecyclerView = view.findViewById(R.id.gallery_recycler_view);

//        images = new ArrayList<>();
//        mAdapter = new GalleryAdapter(images, getContext());
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
//        galleryRecyclerView.setLayoutManager(mLayoutManager);
//        galleryRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        galleryRecyclerView.setAdapter(mAdapter);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GalleryAdapter galleryAdapter = new GalleryAdapter(Utils.getData(),this);
        galleryRecyclerView = view.findViewById(R.id.gallery_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        galleryRecyclerView.setLayoutManager(gridLayoutManager);
        galleryRecyclerView.setAdapter(galleryAdapter);
    }

    @Override
    public void onGalleryItemClickListener(int position, ImageModel imageModel, ImageView imageView) {
        GalleryViewPagerFragment galleryViewPagerFragment = GalleryViewPagerFragment.newInstance(position, Utils.getData());

        getFragmentManager()
                .beginTransaction()
                .addToBackStack(TAG)
                .replace(R.id.content, galleryViewPagerFragment)
                .commit();
    }

    //    Utility Class for Image Loading
//    public static class Utils {
//
//        static String IMGS[] = {
//                "https://cdn-ep19.pressidium.com/wp-content/uploads/2018/10/differernt-types-of-cameras-canon-eos60d.jpg",
//                "https://www.adorama.com/alc/wp-content/uploads/2017/06/shutterstock_507111370-new-1-1024x453.jpg",
//                "https://images-eu.ssl-images-amazon.com/images/G/02/FST/shutterstock_607447736.jpg",
//                "https://www.photouno.com/media/webcontent/images/Priscilla_taking_pictures.jpg",
//                "https://www.photouno.com/media/webcontent/images/Priscilla_taking_pictures.jpg"
//        };
//
//        public static ArrayList<ImageModel> getData() {
//            ArrayList<ImageModel> arrayList = new ArrayList<>();
//            for (int i = 0; i < IMGS.length; i++) {
//                ImageModel imageModel = new ImageModel();
//                imageModel.setName("Image " + i);
//                imageModel.setUrl(IMGS[i]);
//                arrayList.add(imageModel);
//            }
//            return arrayList;
//        }
//    }
}
