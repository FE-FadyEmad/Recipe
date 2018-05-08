package com.recipe.fe.recipe.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Fady on 01-May-18.
 */

public class Recipe implements Parcelable{
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_STEPS = "steps";

    private int id;
    private String name, image;
    private ArrayList <Ingredient> ingredients;
    private ArrayList <Step> steps;

    public Recipe() {
    }

    public Recipe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Recipe fromJson(JSONObject jsonObject){
        if(jsonObject.has(KEY_ID)){
            this.id = jsonObject.optInt(KEY_ID);
        }
        if(jsonObject.has(KEY_NAME)){
            this.name = jsonObject.optString(KEY_NAME);
        }
        if(jsonObject.has(KEY_IMAGE)){
            this.image = jsonObject.optString(KEY_IMAGE);
        }
        if(jsonObject.has(KEY_INGREDIENTS)){
            JSONArray ingrJsonArray = jsonObject.optJSONArray(KEY_INGREDIENTS);
            ingredients = new ArrayList<>();
            for (int i = 0; i < ingrJsonArray.length(); i++){
                JSONObject ing = ingrJsonArray.optJSONObject(i);
                Ingredient ingredient = new Ingredient().fromJson(ing);
                if (ingredient != null)
                    ingredients.add(ingredient);
            }
        }
        if(jsonObject.has(KEY_STEPS)){
            JSONArray stepJsonArray = jsonObject.optJSONArray(KEY_STEPS);
            steps = new ArrayList<>();
            for (int i = 0; i < stepJsonArray.length(); i++){
                JSONObject stepJsonObject = stepJsonArray.optJSONObject(i);
                Step step = new Step().fromJson(stepJsonObject);
                if (step != null)
                    steps.add(step);
            }
        }
        return this;
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Recipe>(){
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe(Parcel parcel){
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.image = parcel.readString();
        this.steps = parcel.createTypedArrayList(Step.CREATOR);
        this.ingredients = parcel.createTypedArrayList(Ingredient.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeTypedList(steps);
        dest.writeTypedList(ingredients);
    }
    

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
}
