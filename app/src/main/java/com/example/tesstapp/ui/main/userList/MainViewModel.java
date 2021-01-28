package com.example.tesstapp.ui.main.userList;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.tesstapp.model.User;
import com.example.tesstapp.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MainViewModel extends /*ViewModel*/AndroidViewModel {

    private static final String TAG = "MainViewModel";
    private UserRepository repository;

    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        Log.d(TAG, "MainViewModel: viewModel is ready");
    }


    //Get all Users
    public Flowable<List<User>> getAllUsers(){
        return repository.getAllUsers();
    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return repository.getIsLoading();
    }

    //Delete All Users
    public void deleteAllUsers()
    {
        repository.deleteAllUsers();
    }

    public Flowable<List<User>> search(String some, String criterian){
        return repository.search(some, criterian);
    }

}
