package com.example.bloodlife;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bloodlife.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ListView drawerList;
    private ActivityMainBinding binding;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private ArrayList<DrawerData> drawerItems = new ArrayList<>();
    AdapterView.OnItemClickListener seekerDrawerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment frag = null;
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    frag = new HomeFragment();
                    break;

                case 1:
                    frag = new BloodRequestManagement();
                    break;

            }
            if (frag != null)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameContainerFL, frag)
                        .commit();
            binding.drawerLayoutDL.closeDrawers();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        goToHomeFragment();
       setToolbar();
        initDrawer();
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayoutDL, binding.toolbarTL,
                R.string.app_name, R.string.app_name) {

            @SuppressLint("RestrictedApi")
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }


        };
        binding.drawerLayoutDL.addDrawerListener(toggle);

    }

    private void setToolbar() {
        setSupportActionBar(binding.toolbarTL);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }


    private void goToHomeFragment() {
        replaceFragment(R.id.frameContainerFL, new HomeFragment());
    }

    private void initDrawer() {
        drawerItems.clear();
         //   TypedArray icons = getResources().obtainTypedArray(R.array.salesman_icon);
            String[] names = getResources().getStringArray(R.array.drawerItems);
            for (int i = 0; i < names.length; i++) {
                DrawerData drawerData = new DrawerData(names[i]);
                drawerItems.add(drawerData);
            }
        DrawerAdapter drawerAdapter = new DrawerAdapter(this, 0, drawerItems);
        binding.drawerList.setAdapter(drawerAdapter);
        binding.drawerList.setOnItemClickListener(seekerDrawerListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        binding.drawerLayoutDL.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (toggle.onOptionsItemSelected(item)) {
            initDrawer();
            return true;
        }
        switch (itemId) {
            case R.id.notification_item:
               replaceFragment(R.id.frameContainerFL, new NotificationFragment());
                return true;

            case R.id.menu_phone:
                // Handle menu item 2 selection
                return true;

            case android.R.id.home:
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameContainerFL);
                if (!(fragment instanceof HomeFragment)) {
                    onBackPressed();
                }
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayoutDL.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayoutDL.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}