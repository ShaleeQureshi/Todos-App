package com.example.todoapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.todoapp.Home.HomeFragment;
import com.example.todoapp.AddTodo.*;
import com.example.todoapp.ViewAll.ViewAllFragment;
/*
Author: Shahrukh Qureshi
Date: May 10, 2020
Description: This is the main class which builds the application
*/

public class MainActivity extends AppCompatActivity {

    Fragment myFragment;
    Class fragmentClass;

    private DrawerLayout drawerLayout;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //Toolbar from XML
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout); //Layout from XML

        NavigationView navigationView = findViewById(R.id.nav_view); //Navigation bar

        //Way to merge the uses of DrawerLayout and ActionBar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle); //Adding a listener to the layout
        toggle.syncState(); //Syncing the icon from the drawer
        setupDrawerContnt(navigationView);

    }

    public void selectDrawer (MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.nav_todo:
                fragmentClass = TodoFragment.class;
                break;
            case R.id.nav_viewAll:
                fragmentClass = ViewAllFragment.class;
                break;
            case R.id.nav_home:
                fragmentClass = HomeFragment.class;
                break;
        }
        try{
            myFragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, myFragment).commit();
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();


    }
    private void setupDrawerContnt (NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawer(menuItem);
                return true;
            }
        });
    }



}
