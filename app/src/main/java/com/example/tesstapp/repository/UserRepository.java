package com.example.tesstapp.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tesstapp.model.User;
import com.example.tesstapp.persistence.UserDao;
import com.example.tesstapp.persistence.UserDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

    private static final String TAG = "UserRepository";

    private UserDao userDao;
    //private LiveData<List<User>> allUsers;
    private Flowable<List<User>> allUsers;
    private UserDatabase userDatabase;
    private static UserRepository instance;
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();



    public static UserRepository getInstance(Application application){
        if(instance == null){
            instance = new UserRepository(application);
        }
        return instance;
    }

//    public void insert(User user){
//        //new InsertAsyncTask(mWordDatabase.getWordDao()).execute(word);
//
//    }


    public UserRepository(Application application){
        userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.getUserDao();
        //allUsers = userDao.getUsers();
        allUsers = userDao.getAllUsers();
    }

    //--------------RX Java

    //Get all Genre
    public Flowable<List<User>> getAllUsers(){
        return userDao.getAllUsers();

    }

    //Get Loading State
    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }


    //Insert User
    public void insertUser(final User user){
        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                userDao.insertUsers(user);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }
                });
    }

    //Delete all Users
    public void deleteAllUsers(){

        isLoading.setValue(true);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                userDao.deleteAllUsers();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: Called");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Called");
                        deleteAllUsers();
                        isLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: Called"+e.getMessage());
                    }
                });
    }


//    //Search for s.th.
//    public void searchForSomthing(final String somthing, final String criterion){
//        isLoading.setValue(true);
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                if(criterion.equals("name")){
//                    userDao.searchForNames(somthing);
//                }else if(criterion.equals("forname")){
//                    userDao.searchForNames(somthing);
//                }else if(criterion.equals("middlename")){
//                    userDao.searchForNames(somthing);
//                }else if(criterion.equals("phone_number")){
//                    userDao.searchForNames(somthing);
//                }else if(criterion.equals("age")){
//                    userDao.searchForNames(somthing);
//                }
//
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "onSubscribe: Called");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete: Called");
//                        isLoading.setValue(false);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "onError: "+e.getMessage());
//                    }
//                });
//    }


    public Flowable<List<User>> search(String some, String criterian){
        isLoading.setValue(true);
        Flowable<List<User>> list = null;
        if(criterian.equals("name")){
            list = userDatabase.getUserDao().searchForNames(some);
        }else if(criterian.equals("forname")){
            list = userDatabase.getUserDao().searchForFornames(some);
        }else if(criterian.equals("middlename")){
            list = userDatabase.getUserDao().searchForMiddlenames(some);
        }else if(criterian.equals("phone_number")){
            list = userDatabase.getUserDao().searchForPhonenumbers(some);
        }else if(criterian.equals("age")){
            list = userDatabase.getUserDao().searchForAges(some);
        }
        isLoading.setValue(false);
        return list;
    }


}
