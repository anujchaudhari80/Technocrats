package com.example.bloodlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bloodlife.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ListView drawerList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        goToHomeFragment();
        Toolbar toolbar = findViewById(R.id.toolbarTL);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        initDrawer();

    }

    private void goToHomeFragment() {
        replaceFragment(R.id.frameContainerFL, new HomeFragment());
    }

    private void initDrawer() {
        drawerList = findViewById(R.id.drawer_list);
        // Define your drawer items
        String[] drawerItems = getResources().getStringArray(R.array.drawerItems);

        // Create the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, drawerItems);

        // Set the adapter to the ListView
        drawerList.setAdapter(adapter);
        // Set item click listener
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = drawerItems[position];

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.notification_item:
               replaceFragment(R.id.frameContainerFL, new NotificationFragment());
                return true;

            case R.id.menu_phone:
                // Handle menu item 2 selection
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}