package com.example.dijonkariz.fotomwa.fragments.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.GalleryPagerAdapter;
import com.example.dijonkariz.fotomwa.model.ImageModel;

import java.util.ArrayList;

public class GalleryViewPagerFragment extends Fragment {
    private static final String TAG = GalleryViewPagerFragment.class.getSimpleName();

    private static final String EXTRA_INITIAL_POS = "initial_pos";
    private static final String EXTRA_IMAGES = "images";

    public GalleryViewPagerFragment() {}

    public static GalleryViewPagerFragment newInstance(int current, ArrayList<ImageModel> images) {
        GalleryViewPagerFragment fragment = new GalleryViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_INITIAL_POS, current);
        args.putParcelableArrayList(EXTRA_IMAGES, images);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int currentItem = getArguments().getInt(EXTRA_INITIAL_POS);
        ArrayList<ImageModel> images = getArguments().getParcelableArrayList(EXTRA_IMAGES);

        GalleryPagerAdapter galleryPagerAdapter = new GalleryPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, images);
        ViewPager viewPager = view.findViewById(R.id.animal_view_pager);
        viewPager.setAdapter(galleryPagerAdapter);
        viewPager.setCurrentItem(currentItem);
    }
}
