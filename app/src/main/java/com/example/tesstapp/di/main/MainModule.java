package com.example.tesstapp.di.main;

import com.example.tesstapp.adapters.RecyclerAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @MainScope
    @Provides
    static RecyclerAdapter provideAdapter(){
        return  new RecyclerAdapter();
    }

}
