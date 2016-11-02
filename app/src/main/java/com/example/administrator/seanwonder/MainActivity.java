package com.example.administrator.seanwonder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.seanwonder.common.BaseFragmentActivity;
import com.example.administrator.seanwonder.fragment.HomeFragment;
import com.example.administrator.seanwonder.fragment.MeFragment;
import com.example.administrator.seanwonder.fragment.MessageFragment;
import com.example.administrator.seanwonder.view.transformer.FlipHorizontalTransformer;

import java.util.ArrayList;

public class MainActivity extends BaseFragmentActivity {
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initView();
    }

    private void findView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void initView() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MeFragment());
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(currentIndex);
        viewPager.setPageTransformer(true, new FlipHorizontalTransformer());
    }

    class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> fragments;

        public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        private class My implements ViewPager.OnPageChangeListener {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
        super.onClick(view);
    }
}
