package com.example.tesstapp.ui.main.addUser;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tesstapp.model.User;
import com.example.tesstapp.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AddUserViewModel extends AndroidViewModel {

    private static final String TAG = "AddUserViewModel";
    private UserRepository repository;


    @Inject
    public AddUserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        Log.d(TAG, "MainViewModel: viewModel is ready");
    }

    //Insert User
    public void insert(User user){
        repository.insertUser(user);
    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return repository.getIsLoading();
    }
}
