package com.recipe.fe.recipe.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.recipe.fe.recipe.R;
import com.recipe.fe.recipe.interfaces.RecyclerClickListener;
import com.recipe.fe.recipe.models.Step;

import java.util.List;

public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.DetailsViewHolder> {

    private final List<Step> mStepsList;
    private final RecyclerClickListener<Step> mListener;

    public DetailsRecyclerViewAdapter(List<Step> items, RecyclerClickListener<Step> listener) {
        mStepsList = items;
        mListener = listener;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_details, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailsViewHolder holder, int position) {
        ((DetailsViewHolder) holder).bind(mStepsList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mStepsList != null)
          return mStepsList.size();
        else
            return 0;
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mIdView;
        TextView mContentView;
        Step mStep;

        private DetailsViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            view.setOnClickListener(this);
        }
        private void bind(Step step){
            this.mStep = step;
            mIdView.setText(step.getShortDescription());
            mContentView.setText(step.getVideoURL());
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(mStep);
        }
    }
}
