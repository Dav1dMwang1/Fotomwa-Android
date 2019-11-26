package com.example.dijonkariz.fotomwa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.fragments.profile.EditProfileFragment;
import com.example.dijonkariz.fotomwa.fragments.profile.ViewProfileFragment;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = UserProfileActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Launched User Profile Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        Toolbar toolbar = findViewById(R.id.toolbar_user_profile);
        toolbar.setTitle(getString(R.string.user_profile_activity));
        toolbar.setTitleTextColor(getResources().getColor(R.color.fmwa_red, getTheme()));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String userChoice = getIntent().getStringExtra("USER_CHOICE");
        directToFragment(userChoice);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LandingPageActivity.class)); finish();
    }

    private void directToFragment(String userChoice) {
        Log.e(TAG, "Navigating to the specified Fragment");
        Fragment nav_fragment = null;
        if (userChoice.equals("VIEW")) {
//            nav_fragment =  new ViewProfileFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
            fragmentTransaction.replace(R.id.user_profile_frame, new ViewProfileFragment(), TAG);
            fragmentTransaction.commitAllowingStateLoss();
        } else if (userChoice.equals("EDIT")) {
//            nav_fragment = new EditProfileFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
            fragmentTransaction.replace(R.id.user_profile_frame, new EditProfileFragment(), TAG);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
}
