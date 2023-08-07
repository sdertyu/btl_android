package com.example.simplereviewfilm.ListPhim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.simplereviewfilm.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_navi);
        viewPager = findViewById(R.id.viewPager);
        setUpViewPager();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_home:
//                        Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.action_favorite:
//                        Toast.makeText(MainActivity.this,"Favorite",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.action_setting:
//                        Toast.makeText(MainActivity.this,"Setting",Toast.LENGTH_SHORT).show();
//                        break;
//                }

                if(item.getItemId() == R.id.action_home)
                {
                    viewPager.setCurrentItem(0);
//                    HomeFragment f = (HomeFragment) viewPager.getAdapter().instantiateItem(viewPager,0);
//                    f.loadData();
                }

                if(item.getItemId() == R.id.action_search)
                {
                    viewPager.setCurrentItem(1);
                }

                if(item.getItemId() == R.id.action_account)
                {
                    viewPager.setCurrentItem(2);
                    accountDaDN acc = (accountDaDN) viewPager.getAdapter().instantiateItem(viewPager,2);
                    acc.setData();
                }
                return true;
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Bundle bundle = getIntent().getExtras();
//        userTK tk = (userTK) bundle.getSerializable("userTK");
//        if(tk.getSdt().compareTo("") != 0)
//        {
//            ViewPagerAdapter vpg = new ViewPagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//            vpg.getItem(2);
//        }
//
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        Bundle bundle = getIntent().getExtras();
//        userTK user = (userTK) bundle.getSerializable("userTK");
//        viewPagerAdapter.setData(user);
//        viewPager.setAdapter(viewPagerAdapter);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        Toast.makeText(this,"sfdf",Toast.LENGTH_SHORT).show();
//        if (requestCode == 1) {
////            if(data!=null && data.hasExtra("userTK"))
////            {
////            Log.e("f",String.valueOf(requestCode));
////                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//////                Bundle bundle = data.getExtras();
////                userTK user = (userTK) data.getSerializableExtra("userTK");
////                Toast.makeText(this,user.getSdt() + "123",Toast.LENGTH_SHORT).show();
////                viewPagerAdapter.setData(user);
////                viewPager.setAdapter(viewPagerAdapter);
////            }
//            Toast.makeText(this,data.getStringExtra("huhu"),Toast.LENGTH_SHORT).show();
//        }
//    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_search).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_account).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}