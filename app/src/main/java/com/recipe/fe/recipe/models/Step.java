package com.recipe.fe.recipe.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Fady on 01-May-18.
 */

public class Step implements Parcelable{

    private static final String KEY_ID = "id";
    private static final String KEY_SHORT_DESCRIPTION = "shortDescription";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_VIDEO_URL = "videoURL";
    private static final String KEY_THUMBNAIL_URL = "thumbnailURL";

    private int id;
    private String shortDescription, description,videoURL, thumbnailURL;

    public Step() {
    }

    public Step fromJson(JSONObject jsonObject){
        if(jsonObject.has(KEY_ID)){
            this.id = jsonObject.optInt(KEY_ID);
        }
        if(jsonObject.has(KEY_SHORT_DESCRIPTION)){
            this.shortDescription = jsonObject.optString(KEY_SHORT_DESCRIPTION);
        }
        if(jsonObject.has(KEY_DESCRIPTION)){
            this.description = jsonObject.optString(KEY_DESCRIPTION);
        }
        if(jsonObject.has(KEY_VIDEO_URL)){
            this.videoURL = jsonObject.optString(KEY_VIDEO_URL);
        }
        if(jsonObject.has(KEY_THUMBNAIL_URL)){
            this.thumbnailURL = jsonObject.optString(KEY_THUMBNAIL_URL);
        }
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Step>(){
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public Step(Parcel parcel){
        this.id = parcel.readInt();
        this.shortDescription = parcel.readString();
        this.description = parcel.readString();
        this.videoURL = parcel.readString();
        this.thumbnailURL = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }


}
