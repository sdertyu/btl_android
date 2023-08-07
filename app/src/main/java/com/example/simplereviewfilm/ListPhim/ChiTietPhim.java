package com.example.simplereviewfilm.ListPhim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.simplereviewfilm.ListPhim.CTPFragment;
import com.example.simplereviewfilm.ListPhim.TLViewPagerAdapter;
import com.example.simplereviewfilm.ListPhim.userFilm;
import com.example.simplereviewfilm.R;
import com.google.android.material.tabs.TabLayout;

public class ChiTietPhim extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.vp_CTphim);

        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        userFilm user = (userFilm) bundle.getSerializable("userFilm");


        TLViewPagerAdapter adapter = new TLViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.setData(user);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}