package com.example.zhux.designlibrarysample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAB_LAYOUT_FRAGMENT_TAG = "TAB_LAYOUT_FRAGMENT_TAG";
    private static final String FLOATING_LABEL_FRAGMENT_TAG = "FLOATING_LABEL_FRAGMENT_TAG";
    private static final String FLOATING_ACTION_BUTTON_FRAGMENT_TAG = "FLOATING_ACTION_BUTTON_FRAGMENT_TAG";
    private static final String COLLAPSING_TOOLBAR_FRAGMENT_TAG = "COLLAPSING_TOOLBAR_FRAGMENT_TAG";
    private static final String BOTTOM_NAVIGATION_FRAGMENT_TAG = "BOTTOM_NAVIGATION_FRAGMENT_TAG";

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_item_tab_layout:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, TabLayoutFragment.newInstance(), TAB_LAYOUT_FRAGMENT_TAG)
                        .commit();
                break;
            case R.id.navigation_item_floating_label:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, FloatingLabelFragment.newInstance(), FLOATING_LABEL_FRAGMENT_TAG)
                        .commit();
                break;
            case R.id.navigation_item_floating_action_button:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, FloatingActionButtonFragment.newInstance(), FLOATING_ACTION_BUTTON_FRAGMENT_TAG)
                        .commit();
                break;
            case R.id.navigation_item_collapsing_toolbar:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, CollapsingToolbarFragment.newInstance(), COLLAPSING_TOOLBAR_FRAGMENT_TAG)
                        .commit();
                break;
            case R.id.navigation_item_bottom_navigation:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content, BottomNavigationFragment.newInstance(), BOTTOM_NAVIGATION_FRAGMENT_TAG)
                        .commit();
                break;
        }

        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
