package com.example.murilomarcineiro.stalker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Parcelable, Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private  String job;
    private String birthday;
    private String phone;
    private String description;
    private ArrayList<String> paths;

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person(long id, String lastName, String job, int age, String phone, String birthday, String description, ArrayList<String> photos, String firstName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.age = age;
        this.phone = phone;
        this.description = description;
        this.birthday = birthday;
        this.paths = photos;
    }

    protected Person(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        job = in.readString();
        age = in.readInt();
        phone = in.readString();
        birthday = in.readString();
        description = in.readString();
        paths = new ArrayList<>();
        in.readList(paths,String.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(job);
        dest.writeInt(age);
        dest.writeString(phone);
        dest.writeString(birthday);
        dest.writeString(description);
        dest.writeList(paths);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}