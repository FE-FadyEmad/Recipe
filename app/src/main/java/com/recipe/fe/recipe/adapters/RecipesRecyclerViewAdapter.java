package com.recipe.fe.recipe.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.recipe.fe.recipe.R;
import com.recipe.fe.recipe.interfaces.RecyclerClickListener;
import com.recipe.fe.recipe.models.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fady on 16-Mar-18.
 */

public class RecipesRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Recipe> recipes;
    private RecyclerClickListener<Recipe> recyclerClickListener;

    public RecipesRecyclerViewAdapter(RecyclerClickListener<Recipe> recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
        recipes = new ArrayList<>();
    }

    public void update(ArrayList<Recipe> newRecipes){
        recipes = newRecipes;
        notifyDataSetChanged();
    }

    public void addExtraItems(ArrayList<Recipe> newMovies){
        recipes.addAll(newMovies);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (recipes != null ){
            return recipes.size();
        }
        return 0;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item_layout,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RecipeViewHolder) holder).bind(recipes.get(position));
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatTextView titleTv;
        private ImageView posterIv;
        private Recipe mRecipe;

        private RecipeViewHolder(View itemView) {
            super(itemView);
            titleTv = (AppCompatTextView) itemView.findViewById(R.id.tv_title);
            posterIv = (ImageView ) itemView.findViewById(R.id.iv_poster);
            itemView.setOnClickListener(this);
        }
        private void bind(Recipe recipe){
            mRecipe = recipe;
            titleTv.setText(mRecipe.getName());
            if (mRecipe.getImage() != null && !mRecipe.getImage().isEmpty())
                Picasso.with(itemView.getContext()).load(mRecipe.getImage()).into(posterIv);
            else
                posterIv.setImageResource(R.color.grey);
        }

        @Override
        public void onClick(View v) {
            recyclerClickListener.onClick(mRecipe);
        }
    }
}
