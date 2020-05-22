package com.example.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todolist.DashBoards.DashBoard;
import com.example.todolist.DataBase.AppDataBase;
import com.example.todolist.Fragments.High_Fragment;
import com.example.todolist.Fragments.Low_Fragment;
import com.example.todolist.Fragments.Medium_Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{
    AppDataBase appDataBase;
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentPagerAdapter fragmentPagerAdapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //build DataBase
        appDataBase = Room.databaseBuilder(getApplication(), AppDataBase.class,"room2").build();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        fab = findViewById(R.id.fab);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            public Fragment[] fragments = new Fragment[]
                    {
                            new High_Fragment(),
                            new Medium_Fragment(),
                            new Low_Fragment()
                    };
            public String[] fragmentNames = new String[]
                    {
                            "High",
                            "Medium",
                            "Low"
                    };

            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentNames[position];
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DashBoard.class);
                intent.putExtra("key",1);
                startActivity(intent);
            }
        });
    }
}
