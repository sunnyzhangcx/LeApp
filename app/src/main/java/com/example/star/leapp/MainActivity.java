package com.example.star.leapp;

import android.content.Context;

import android.content.Intent;
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

import com.example.star.leapp.Application.LeappApplication;
import com.example.star.leapp.fragment.ExamineLibrary_Fragment;
import com.example.star.leapp.fragment.ExampleLibrary_Fragment;
import com.example.star.leapp.fragment.HomePage_Fragment;
import com.example.star.leapp.fragment.Question_Fragment;
import com.example.star.leapp.fragment.TopicCataLog_Fragment;


import java.util.ArrayList;
import java.util.List;

import data4mooc.Data4Mooc;
import data4mooc.MoocWriter;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> mFragment;
    private String[] titles = {"知识目录","案例库","首页","测验题库","常见问题"};
    private int images[] = {R.drawable.icon_catalog,R.drawable.icon_example,R.drawable.icon_home,R.drawable.icon_test,R.drawable.icon_faq};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String fileName = "Readme.zxz";
        LeappApplication.getQueryMoocDataList(getApplicationContext(), fileName);
        setContentView(R.layout.activity_main);
        TabLayout mTabLayout = findViewById(R.id.main_tablayout);
        ViewPager mViewPager = findViewById(R.id.main_viewpager);

        mFragment =new ArrayList<>();
        mFragment.add(new TopicCataLog_Fragment());
        mFragment.add(new ExampleLibrary_Fragment());
        mFragment.add(new HomePage_Fragment());
        mFragment.add(new ExamineLibrary_Fragment());
        mFragment.add(new Question_Fragment());

        ViewpagerAdapter ViewpagerAdapter=new ViewpagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(ViewpagerAdapter);
        //mViewPager.setCurrentItem(2);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int i = 0; i < mTabLayout.getTabCount(); i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(ViewpagerAdapter.getTabView(i));
            }
        }

        Intent intent = getIntent();
        int fragmentFlag = intent.getIntExtra("fragmentFlag",2);
        mViewPager.setCurrentItem(fragmentFlag);


        /**
         * 添加案例库测试数据，后期可以去掉
         */
       //MoocWriter.writeMoocData(true);

    }


    private class ViewpagerAdapter extends FragmentPagerAdapter{
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
            View v = LayoutInflater.from(context).inflate(R.layout.layout_tab_custom,null);
            TextView textView = v.findViewById(R.id.tv_title);
            ImageView imageView = v.findViewById(R.id.iv_icon);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);
            return v;
        }
    }

}
