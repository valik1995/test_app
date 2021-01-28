package com.example.tesstapp.di;

import com.example.tesstapp.di.main.MainFragmentBuildersModule;
import com.example.tesstapp.di.main.MainModule;
import com.example.tesstapp.di.main.MainScope;
import com.example.tesstapp.di.main.MainViewModelsModule;
import com.example.tesstapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainViewModelsModule.class, MainModule.class, MainFragmentBuildersModule.class}
    )
    abstract MainActivity contributeMainActivity();


}
