package com.recipe.fe.recipe.utils;

import android.content.Context;
import android.util.Log;

import com.recipe.fe.recipe.models.Recipe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Fady on 01-May-18.
 */

public class JsonUtils {

    private static String loadJSONFromAsset(Context context ) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("recipelist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e(JsonUtils.class.getSimpleName(), e.getMessage());
        }
        return json;
    }


    public static ArrayList<Recipe> getRecipes(Context context){
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset(context));
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                Recipe recipe = new Recipe().fromJson(jsonObject);
                if (recipe != null)
                    recipes.add(recipe);
            }
        } catch (JSONException e) {
            Log.e(JsonUtils.class.getSimpleName(), e.getMessage());
        }
        return recipes;
    }
}
