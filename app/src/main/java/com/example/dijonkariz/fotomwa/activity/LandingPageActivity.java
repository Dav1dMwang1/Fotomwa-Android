package com.example.dijonkariz.fotomwa.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.fragments.HomeFragment;
import com.example.dijonkariz.fotomwa.fragments.NotificationsFragment;
import com.example.dijonkariz.fotomwa.fragments.OrdersFragment;
import com.example.dijonkariz.fotomwa.fragments.PhotosFragment;
import com.example.dijonkariz.fotomwa.fragments.SettingsFragment;
import com.example.dijonkariz.fotomwa.other.CircleTransform;

import java.util.Objects;

public class LandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.view_orders:
                        Toast.makeText(LandingPageActivity.this, R.string.view_orders, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.order:
                        Toast.makeText(LandingPageActivity.this, R.string.new_order, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.current_orders:
                        Toast.makeText(LandingPageActivity.this, R.string.current_order, Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }


}
