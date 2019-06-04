package com.example.murilomarcineiro.stalker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Path implements Parcelable {

    private String path;
    private long id;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    protected Path(Parcel in) {
        path = in.readString();
        id = in.readLong();
    }

    public static final Creator<Path> CREATOR = new Creator<Path>() {
        @Override
        public Path createFromParcel(Parcel in) {
            return new Path(in);
        }

        @Override
        public Path[] newArray(int size) {
            return new Path[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeLong(id);
    }
}
