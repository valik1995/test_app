package com.example.tesstapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tesstapp.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Insert
    void insertUsers(User... users);

    @Query("DELETE FROM user")
    void deleteAllUsers();

    //-----RX java
    @Query("SELECT * FROM user WHERE name LIKE '%' || :name || '%' ")
    Flowable<List<User>> searchForNames(String name);

    @Query("SELECT * FROM user WHERE forname LIKE '%' || :forname || '%' ")
    Flowable<List<User>> searchForFornames(String forname);

    @Query("SELECT * FROM user WHERE middle_name LIKE '%' || :middle_name || '%' ")
    Flowable<List<User>> searchForMiddlenames(String middle_name);

    @Query("SELECT * FROM user WHERE phone_number LIKE '%' || :phone_number || '%' ")
    Flowable<List<User>> searchForPhonenumbers(String phone_number);

    @Query("SELECT * FROM user WHERE age LIKE '%' || :age || '%' ")
    Flowable<List<User>> searchForAges(String age);

    //Get all Genre from table
    @Query("SELECT * FROM user")
    Flowable<List<User>> getAllUsers();


    //    @Query("SELECT * FROM user")
//    LiveData<List<User>> getUsers();
//
//    @Query("SELECT * FROM user WHERE name LIKE '%' || :name || '%' ")
//    LiveData<List<User>> searchForName(String name);
//
//    @Query("SELECT * FROM user WHERE forname LIKE '%' || :forname || '%' ")
//    LiveData<List<User>> searchForForname(String forname);
//
//    @Query("SELECT * FROM user WHERE middle_name LIKE '%' || :middle_name || '%' ")
//    LiveData<List<User>> searchForMiddlename(String middle_name);
//
//    @Query("SELECT * FROM user WHERE phone_number LIKE '%' || :phone_number || '%' ")
//    LiveData<List<User>> searchForPhonenumber(String phone_number);
//
//    @Query("SELECT * FROM user WHERE age LIKE '%' || :age || '%' ")
//    LiveData<List<User>> searchForAge(String age);



}
