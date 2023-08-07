package com.example.simplereviewfilm.ListPhim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TLViewPagerAdapter extends FragmentStatePagerAdapter {

    userFilm user;

    public void setData(userFilm user){
        this.user = user;
    }
    public TLViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("nhan",user);
        CTPFragment ctp = new CTPFragment();
        CMTFragment cmt = new CMTFragment();
        cmt.setArguments(bundle);
        ctp.setArguments(bundle);
        switch (position) {
            case 0:
                return ctp;
            case 1:
                return cmt;
            default:
                return ctp;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "THÔNG TIN";
            case 1:
                return "BÌNH LUẬN";
            default:
                return "THÔNG TIN";
        }
    }
}
