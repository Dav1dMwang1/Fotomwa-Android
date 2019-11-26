package com.example.dijonkariz.fotomwa.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.activity.UserProfileActivity;

public class EditProfileFragment extends Fragment {
    private static final String TAG = EditProfileFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Launched Edit Profile");
        return inflater.inflate(R.layout.edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView viewProfile = view.findViewById(R.id.view_profile_icon);
        viewProfile.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), UserProfileActivity.class).putExtra("USER_CHOICE", "VIEW")); getActivity().finish();
        });
    }
}
