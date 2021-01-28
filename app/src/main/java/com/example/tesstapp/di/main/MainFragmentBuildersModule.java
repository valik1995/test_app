package com.example.tesstapp.di.main;

import com.example.tesstapp.ui.main.addUser.AddUserFragment;
import com.example.tesstapp.ui.main.userList.ListUserFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ListUserFragment contributeListUserFragment();

    @ContributesAndroidInjector
    abstract AddUserFragment contributeAddUserFragment();
}
