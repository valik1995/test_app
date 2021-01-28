package com.example.tesstapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "forname")
    private String forname;
    @ColumnInfo(name = "middle_name")
    private String middle_name;
    @ColumnInfo(name = "phone_number")
    private String phone_number;
    @ColumnInfo(name = "age")
    private int age;

    public User(String name, String forname, String middle_name, String phone_number, int age) {
        this.name = name;
        this.forname = forname;
        this.middle_name = middle_name;
        this.phone_number = phone_number;
        this.age = age;
    }

    public User() {
    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        forname = in.readString();
        middle_name = in.readString();
        phone_number = in.readString();
        age = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(forname);
        parcel.writeString(middle_name);
        parcel.writeString(phone_number);
        parcel.writeInt(age);
    }
}
