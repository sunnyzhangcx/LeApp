package com.example.star.leapp.homepage.progress;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.star.leapp.MainActivity;
import com.example.star.leapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProgressMainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragment;
    private String[] titles = {"知识点进度","案例进度"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_main);
        mTabLayout = findViewById(R.id.progress_tablayout);
        mViewPager = findViewById(R.id.progress_viewpager);

        mFragment = new ArrayList<>();
        mFragment.add(new ProgressTopic_Fragment());
        mFragment.add(new ProgressExample_Fragment());

        ViewpagerAdapter ViewpagerAdapter=new ViewpagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(ViewpagerAdapter);
        //mViewPager.setCurrentItem(2);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int i = 0;i < mTabLayout.getTabCount(); i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(ViewpagerAdapter.getTabView(i));
        }

    }

    private class ViewpagerAdapter extends FragmentPagerAdapter {
        private Context context;
        public ViewpagerAdapter(FragmentManager fm, Context context){
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }

        public View getTabView(int position){
            View v = LayoutInflater.from(context).inflate(R.layout.layout_tab_textview,null);
            TextView textView = v.findViewById(R.id.tv_title);
            textView.setText(titles[position]);
            return v;
        }
    }
}

