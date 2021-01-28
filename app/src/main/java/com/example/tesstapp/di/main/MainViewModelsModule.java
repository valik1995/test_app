package com.example.tesstapp.di.main;

import androidx.lifecycle.ViewModel;

import com.example.tesstapp.di.ViewModelKey;
import com.example.tesstapp.ui.main.addUser.AddUserViewModel;
import com.example.tesstapp.ui.main.userList.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddUserViewModel.class)
    public abstract ViewModel bindAddUserViewModel(AddUserViewModel viewModel);
}
