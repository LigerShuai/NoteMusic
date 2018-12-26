package com.liger.note.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Liger
 * @date 2018/12/25 00:57
 */
public class Music implements Parcelable{

    private long id;
    private String name;
    private String displayName;
    private String singer;
    private String album;
    private String path;
    private String imgUrl;
    private long size;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.displayName);
        dest.writeString(this.singer);
        dest.writeString(this.album);
        dest.writeString(this.path);
        dest.writeString(this.imgUrl);
        dest.writeLong(this.size);
    }

    public Music() {
    }

    protected Music(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.displayName = in.readString();
        this.singer = in.readString();
        this.album = in.readString();
        this.path = in.readString();
        this.imgUrl = in.readString();
        this.size = in.readLong();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel source) {
            return new Music(source);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}
