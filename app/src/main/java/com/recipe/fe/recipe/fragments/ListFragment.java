package com.recipe.fe.recipe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.recipe.fe.recipe.R;
import com.recipe.fe.recipe.adapters.DetailsRecyclerViewAdapter;
import com.recipe.fe.recipe.interfaces.RecyclerClickListener;
import com.recipe.fe.recipe.models.Recipe;
import com.recipe.fe.recipe.models.Step;


public class ListFragment extends Fragment  {

    Recipe mRecipe;
    RecyclerClickListener<Step> stepRecyclerClickListener;
    public ListFragment() {
    }

    public void setArguments(Recipe mRecipe,RecyclerClickListener<Step> listener) {
        this.mRecipe = mRecipe;
        this.stepRecyclerClickListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new DetailsRecyclerViewAdapter(mRecipe.getSteps(), stepRecyclerClickListener));
        return view;
    }

}
