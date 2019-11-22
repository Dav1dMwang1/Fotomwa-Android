package com.example.dijonkariz.fotomwa.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
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
import android.widget.Button;
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
    private static final String TAG_HOME = HomeFragment.class.getSimpleName();
    private static final String TAG_PHOTOS = PhotosFragment.class.getSimpleName();
    private static final String TAG_ORDERS = OrdersFragment.class.getSimpleName();
    private static final String TAG_NOTIFICATIONS = NotificationsFragment.class.getSimpleName();
    private static final String TAG_SETTINGS = SettingsFragment.class.getSimpleName();
    private static final String TAG_ABOUT = AboutUsActivity.class.getSimpleName();
    private static final String TAG_PRIVACY = PrivacyPolicyActivity.class.getSimpleName();
    private static String CURRENT_TAG = TAG_HOME;

    public static int navItemIndex = 0;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbar;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawerView;
    private Toolbar toolbar;
    private ImageView sideNavUserProfileImg, userProfileImg;
    private ProgressBar progressBar;
    private Handler handler;
    private TextView textName, textRole;
    private Button editProfile, viewProfile, moreRecentOrders;
    // Make sure to be using androidx.appcompat.app.ActionBarDrawerToggle version.
    private ActionBarDrawerToggle drawerToggle;

    private static final String urlProfileImg = "https://kiss100.s3.amazonaws.com/wp-content/uploads/2019/06/kenyan-men.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        appBarLayout = findViewById(R.id.appbar_main);
        collapsingToolbar = appBarLayout.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitleEnabled(false);

        toolbar = appBarLayout.findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.fmwa_red));
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        handler = new Handler();

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        navigationDrawerView = findViewById(R.id.side_nav_view);
        View navHeader = navigationDrawerView.getHeaderView(0);
        textName = navHeader.findViewById(R.id.user_name);
        textRole = navHeader.findViewById(R.id.user_desc);
        sideNavUserProfileImg = navHeader.findViewById(R.id.img_profile);
        userProfileImg = findViewById(R.id.user_profile_img);
        editProfile = findViewById(R.id.edit_profile);
        viewProfile = findViewById(R.id.view_profile);

        progressBar = navHeader.findViewById(R.id.progressBar_cyclic);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        loadNavHeader();

//        OnClick Listerners for the Buttons
        launchEditProfile();
        launchViewProfile();

        navigationDrawerView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.i(TAG, "NavigationItemSelectedListener");
                selectDrawerItem(menuItem);
                return true;
            }
        });

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.view_orders:
                        Toast.makeText(LandingPageActivity.this, R.string.view_orders, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.make_order:
                        Toast.makeText(LandingPageActivity.this, R.string.new_order, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.order_progress:
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
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // when fragment is notifications, load the menu created for notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        // user is in notifications fragment
        // and selected 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // user is in notifications fragment
        // and selected 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        boolean shouldLoadHomeFragOnBackPress = true;
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed();
    }

    public void selectDrawerItem (MenuItem menuItem) {
//        Fragment fragment = null;
//        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_orders:
                navItemIndex = 1;
                CURRENT_TAG = TAG_ORDERS;
//                fragmentClass = OrdersFragment.class;
                break;
            case R.id.nav_photos:
                navItemIndex = 2;
                CURRENT_TAG = TAG_PHOTOS;
//                fragmentClass = PhotosFragment.class;
                break;
            case R.id.nav_notifications:
                navItemIndex = 3;
                CURRENT_TAG = TAG_NOTIFICATIONS;
                break;
            case R.id.nav_settings:
                navItemIndex = 4;
                CURRENT_TAG = TAG_SETTINGS;
                break;
            case R.id.nav_about_us:
                startActivity(new Intent(LandingPageActivity.this, AboutUsActivity.class));
                drawerLayout.closeDrawers();
                return;
            case R.id.nav_privacy_policy:
                startActivity(new Intent(LandingPageActivity.this, PrivacyPolicyActivity.class));
                drawerLayout.closeDrawers();
                return;
            case R.id.nav_home:
            default:
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                break;
        }

        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        menuItem.setChecked(true);

        loadHomeFragment();
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
                .into(userProfileImg);

//        Load Main Order Profile Image
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
                .into(sideNavUserProfileImg);

        // showing dot next to notifications label
        navigationDrawerView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        handler.post(mPendingRunnable);

        drawerLayout.closeDrawers();

        invalidateOptionsMenu();

    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 1:
                Toast.makeText(LandingPageActivity.this, R.string.photos_fragment_title, Toast.LENGTH_LONG).show();
                return new PhotosFragment();
            case 2:
                Toast.makeText(LandingPageActivity.this, R.string.orders_fragment_title, Toast.LENGTH_LONG).show();
                return new OrdersFragment();
            case 3:
                Toast.makeText(LandingPageActivity.this, R.string.notifications_fragment_title, Toast.LENGTH_LONG).show();
                return new NotificationsFragment();
            case 4:
                Toast.makeText(LandingPageActivity.this, R.string.settings_fragment_title, Toast.LENGTH_LONG).show();
                return new SettingsFragment();
            case 0:
            default:
                Toast.makeText(LandingPageActivity.this, R.string.home_fragment_title, Toast.LENGTH_LONG).show();
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        Objects.requireNonNull(getSupportActionBar()).setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationDrawerView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void initCollapsingToolbar() {
//        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
//        AppBarLayout appBarLayout = findViewById(R.id.appbar_main);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

//    Listener for Launching Editing Order Profile Activity
    private void launchEditProfile() {
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LandingPageActivity.this, getString(R.string.launch_editProfile), Toast.LENGTH_LONG).show();
            }
        });
    }

//    Listener for Launching Viewing Order Profile Activity
    private void launchViewProfile() {
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LandingPageActivity.this, getString(R.string.launch_viewProfile), Toast.LENGTH_LONG).show();
            }
        });
    }

//    private void OrdersRecyclerView () {
//
//    }
}
