package com.example.dijonkariz.fotomwa.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.fragments.HomeFragment;
import com.example.dijonkariz.fotomwa.fragments.NotificationsFragment;
import com.example.dijonkariz.fotomwa.fragments.OrdersFragment;
import com.example.dijonkariz.fotomwa.fragments.PhotosFragment;
import com.example.dijonkariz.fotomwa.fragments.SettingsFragment;
import com.example.dijonkariz.fotomwa.other.CircleTransform;

import java.util.Objects;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = LandingPageActivity.class.getSimpleName();
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_ORDERS = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";

    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawerView;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private ImageView imgProfile;
    private View navHeader;
    private ProgressBar progressBar;

    private TextView textName, textRole;

    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    private static final String urlProfileImg = "https://moodle.htwchur.ch/pluginfile.php/124614/mod_page/content/4/example.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.fmwa_red));
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        navigationDrawerView = findViewById(R.id.side_nav_view);
        navHeader = navigationDrawerView.getHeaderView(0);
        textName = navHeader.findViewById(R.id.user_name);
        textRole = navHeader.findViewById(R.id.user_desc);
        imgProfile = navHeader.findViewById(R.id.img_profile);
        progressBar = navHeader.findViewById(R.id.progressBar_cyclic);

        loadNavHeader();

        navigationDrawerView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.i(TAG, "NavigationItemSelectedListener");
                selectDrawerItem(menuItem);
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
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

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void selectDrawerItem (MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_orders:
                Toast.makeText(LandingPageActivity.this, R.string.orders_fragment_intro, Toast.LENGTH_LONG).show();
                fragmentClass = OrdersFragment.class;
                break;
            case R.id.nav_photos:
                Toast.makeText(LandingPageActivity.this, R.string.photos_fragment_intro, Toast.LENGTH_LONG).show();
                fragmentClass = PhotosFragment.class;
                break;
            case R.id.nav_notifications:
                Toast.makeText(LandingPageActivity.this, R.string.notifications_fragment_intro, Toast.LENGTH_LONG).show();
                fragmentClass = NotificationsFragment.class;
                break;
            case R.id.nav_settings:
                Toast.makeText(LandingPageActivity.this, R.string.settings_fragment_intro, Toast.LENGTH_LONG).show();
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.nav_about_us:
                Toast.makeText(LandingPageActivity.this, R.string.activity_title_about_us, Toast.LENGTH_LONG).show();
                fragmentClass = AboutUsActivity.class;
                break;
            case R.id.nav_privacy_policy:
                Toast.makeText(LandingPageActivity.this, R.string.activity_title_privacy_policy, Toast.LENGTH_LONG).show();
                fragmentClass = PrivacyPolicyActivity.class;
                break;
            case R.id.nav_home:
            default:
                Toast.makeText(LandingPageActivity.this, R.string.home_fragment_intro, Toast.LENGTH_LONG).show();
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
    }

    private void loadNavHeader() {
        // name, website
        textName.setText(getResources().getString(R.string.user_name));
        textRole.setText(getResources().getString(R.string.user_desc));

        progressBar.setVisibility(View.VISIBLE);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .apply(new RequestOptions()
                        .placeholder(getResources().getDrawable(android.R.drawable.sym_def_app_icon))
                        .error(getResources().getDrawable(android.R.drawable.sym_def_app_icon))
                        .transform(new CircleTransform())
                )
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        navigationDrawerView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }
}
