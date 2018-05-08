package com.recipe.fe.recipe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.recipe.fe.recipe.utils.JsonUtils;
import com.recipe.fe.recipe.R;
import com.recipe.fe.recipe.adapters.RecipesRecyclerViewAdapter;
import com.recipe.fe.recipe.interfaces.RecyclerClickListener;
import com.recipe.fe.recipe.models.Recipe;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements RecyclerClickListener<Recipe> {
    private static final String SAVED_RECIPES = "savelist";
    private RecyclerView recipesRecyclerView;
    private GridLayoutManager recipesLayoutManager;
    private RecipesRecyclerViewAdapter recipeRecyclerViewAdapter;
    private AppCompatTextView tvPlaceholder;
    private ArrayList<Recipe> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvPlaceholder = (AppCompatTextView) findViewById(R.id.tv_placeholder);
        recipesRecyclerView = (RecyclerView) findViewById(R.id.rv_recipes);
        if (savedInstanceState != null) {
            restoreView(savedInstanceState);
        }else {
            recipes = JsonUtils.getRecipes(this);
            initRecyclerView();
        }
    }

    private int getScreenWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return width;
    }

    private void initRecyclerView() {
        if (recipes != null && ! recipes.isEmpty()) {
            tvPlaceholder.setVisibility(View.GONE);
            recipesRecyclerView.setVisibility(View.VISIBLE);
            int spn = getScreenWidth()/270;
            recipesLayoutManager = new GridLayoutManager(this, spn);
            recipesRecyclerView.setLayoutManager(recipesLayoutManager);
            recipeRecyclerViewAdapter = new RecipesRecyclerViewAdapter(this);
            recipesRecyclerView.setAdapter(recipeRecyclerViewAdapter);
            recipeRecyclerViewAdapter.update(recipes);
        }else
        {
            tvPlaceholder.setVisibility(View.VISIBLE);
            recipesRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this,RecipeDetailsActivity.class);
        intent.putExtra(RecipeDetailsActivity.SEND_RECIPE,recipe);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(SAVED_RECIPES, recipes);
        super.onSaveInstanceState(outState);
    }
    private void restoreView(Bundle savedInstanceState) {
        recipes = savedInstanceState.getParcelableArrayList(SAVED_RECIPES);
        if ( recipes == null){
            recipes = JsonUtils.getRecipes(this);
        }
        initRecyclerView();
    }
}
