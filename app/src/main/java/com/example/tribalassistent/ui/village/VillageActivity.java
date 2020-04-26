package com.example.tribalassistent.ui.village;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.tribalassistent.R;
import com.google.android.material.tabs.TabLayout;

public class VillageActivity extends AppCompatActivity {
    private static final String TAG = "VillageActivity";
    private int villageId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Village activity starting.. ");

        villageId = getIntent().getExtras().getInt("village");
        setContentView(R.layout.activity_village);
        setupViewPager();
    }


    private void setupViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.add(new HomeFragment(villageId));
        adapter.add(new QueueFragment(villageId));
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_queue);
    }

    public Integer getVillageId() {
        return villageId;
    }
}