package com.example.tribalassistent.ui.village;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.repositories.VillageRepository;
import com.google.android.material.tabs.TabLayout;

public class VillageActivity extends AppCompatActivity implements Observer<VillageGameBatch> {
    private static final String TAG = "VillageActivity";
    private HomeFragment homeFragment;
    private QueueFragment queueFragment;
    private int villageId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Village activity starting.. ");
        villageId = getIntent().getExtras().getInt("village");
        setContentView(R.layout.activity_village);
        VillageRepository.getInstance().getVillageData().observe(this, this);
        setupViewPager();
    }


    private void setupViewPager() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        queueFragment = new QueueFragment(villageId);
        adapter.add(homeFragment);
        adapter.add(queueFragment);
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

    @Override
    public void onChanged(VillageGameBatch villageGameBatch) {
        homeFragment.update(villageGameBatch.get(villageId));
    }
}