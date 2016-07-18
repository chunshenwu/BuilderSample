package com.example.jason_wu.buildersample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.jason_wu.buildersample.ui.fragment.FormatTextFragment;
import com.example.jason_wu.buildersample.ui.fragment.SpannableFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mFragmentManager = getSupportFragmentManager();
    }

    private void initViewPager() {
        mViewPager.setAdapter(new MyFragmentPagerAdapter(mFragmentManager));
    }
}

class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private ArrayList<Fragment> mFragmentList;
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList();
        initFragmentList();
    }

    private void initFragmentList() {
        mFragmentList.add(new FormatTextFragment());
        mFragmentList.add(new SpannableFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

