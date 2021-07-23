package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class Home extends AppCompatActivity {
    
    
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter=new VPAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragmentnews(),"News");
        vpAdapter.addFragment(new fragmentnotice(),"Notice");
        vpAdapter.addFragment(new fragmentschedule(),"Schedule");
        viewPager.setAdapter(vpAdapter);
        

    }
}