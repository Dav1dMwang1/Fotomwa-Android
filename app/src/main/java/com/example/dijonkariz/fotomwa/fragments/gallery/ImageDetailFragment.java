package com.example.dijonkariz.fotomwa.fragments.gallery;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.model.ImageModel;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDetailFragment extends Fragment {
    private static final String TAG = ImageDetailFragment.class.getSimpleName();
    private static final String EXTRA_TRANSITION_NAME= "transition_name";
    private static final String EXTRA_IMAGE = "image_item";

    public ImageDetailFragment() {}

    public static ImageDetailFragment newInstance(ImageModel image, String transitionName) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_IMAGE, image);
        args.putString(EXTRA_TRANSITION_NAME, transitionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_image_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);
        final ImageModel image = getArguments().getParcelable(EXTRA_IMAGE);

        final PhotoView imageView = view.findViewById(R.id.detail_image);
        imageView.setTransitionName(transitionName);

        Glide.with(getActivity()).asBitmap()
                .load(image.getUrl())
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        startPostponedEnterTransition();
                        imageView.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}
