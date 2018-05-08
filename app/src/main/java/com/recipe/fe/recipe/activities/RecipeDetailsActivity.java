package com.recipe.fe.recipe.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.recipe.fe.recipe.R;
import com.recipe.fe.recipe.fragments.ListFragment;
import com.recipe.fe.recipe.fragments.StepFragment;
import com.recipe.fe.recipe.interfaces.RecyclerClickListener;
import com.recipe.fe.recipe.models.Recipe;
import com.recipe.fe.recipe.models.Step;

public class RecipeDetailsActivity extends AppCompatActivity implements RecyclerClickListener<Step> {
    public static final String SEND_RECIPE = "seletedRecipe";
    Recipe mRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        mRecipe = getIntent().getExtras().getParcelable(SEND_RECIPE);

        ListFragment mListFragment = new ListFragment();
        mListFragment.setArguments(mRecipe, this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,mListFragment)
                .commit();

    }

    @Override
    public void onClick(Step step) {
        StepFragment stepFragment = new StepFragment();
        stepFragment.setStep(step);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,stepFragment)
                .commit();
    }

}
