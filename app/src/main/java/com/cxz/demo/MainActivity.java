package com.cxz.demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cxz.demo.adapter.ViewPagerAdapter;
import com.cxz.demo.fragment.FirstFragment;
import com.cxz.demo.fragment.SecondFragment;
import com.cxz.demo.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setViewPager();

        //设置ViewPager的数据
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多Tab
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//tab均分，适合少的Tab

    }

    private void setViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment newFragment = new FirstFragment();
        Bundle data = new Bundle();
        data.putInt("id", 0);
        newFragment.setArguments(data);
        adapter.addFragment(newFragment, "First");

        newFragment = new SecondFragment();
        data = new Bundle();
        data.putInt("id", 1);
        newFragment.setArguments(data);
        adapter.addFragment(newFragment, "Second");

        newFragment = new ThirdFragment();
        data = new Bundle();
        data.putInt("id", 2);
        newFragment.setArguments(data);
        adapter.addFragment(newFragment, "Third");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
