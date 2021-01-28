package com.example.tesstapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.tesstapp.viewModel.ViewModelsProvirderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelsProvirderFactory modelsProvirdersFactory);
}
