package com.recipe.fe.recipe.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Fady on 01-May-18.
 */

public class Ingredient implements Parcelable{
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_MEASURE = "measure";
    private static final String KEY_INGREDIENT = "ingredient";
    private int quantity;
    private String measure, ingredient;

    public Ingredient() {
    }

    public Ingredient fromJson(JSONObject jsonObject){
        if(jsonObject.has(KEY_QUANTITY)){
            this.quantity = jsonObject.optInt(KEY_QUANTITY);
        }
        if(jsonObject.has(KEY_MEASURE)){
            this.measure = jsonObject.optString(KEY_MEASURE);
        }
        if(jsonObject.has(KEY_INGREDIENT)){
            this.ingredient = jsonObject.optString(KEY_INGREDIENT);
        }
        return this;
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Ingredient>(){
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public Ingredient(Parcel parcel){
        this.quantity = parcel.readInt();
        this.measure = parcel.readString();
        this.ingredient = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
