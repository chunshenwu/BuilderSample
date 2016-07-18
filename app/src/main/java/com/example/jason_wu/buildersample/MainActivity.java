package com.example.jason_wu.buildersample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jason_wu.buildersample.ui.fragment.FormatTextFragment;
import com.example.jason_wu.buildersample.ui.fragment.SpannableTextFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    //View
    private TextView mTextView;
    private ViewPager mViewPager;
    private FragmentManager mFragmentManager;
    //Presenter
    private MyFragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
        updateUI();
    }

    private void initView() {
        mTextView = (TextView)findViewById(R.id.tv);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mFragmentManager = getSupportFragmentManager();
    }

    private void initViewPager() {
        mAdapter = new MyFragmentPagerAdapter(mFragmentManager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTextView.setText(mAdapter.getItem(position).getClass().getSimpleName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateUI() {
        int position = mViewPager.getCurrentItem();
        mTextView.setText(mAdapter.getItem(position).getClass().getSimpleName());
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
        mFragmentList.add(new SpannableTextFragment());
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

