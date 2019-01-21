package com.example.aashitachowdary.bakingrecipieapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Receipe;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Step;
import java.io.Serializable;
import java.util.List;

class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    Context context;
    List<Receipe> recipieModelList;
    List<Step> stepList;
    public MainAdapter(MainActivity mainActivity, List<Receipe> recipieModelList, List<Step> stepList) {
    this.context=mainActivity;
    this.recipieModelList=recipieModelList;
    this.stepList=stepList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.recipienames,viewGroup,false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.textView.setText(recipieModelList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return recipieModelList.size();
    }

    public class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public MainHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.recipiename);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Intent intent=new Intent(context,ItemListActivity.class);
            intent.putParcelableArrayListExtra("steps",recipieModelList.get(position).getSteps());
            intent.putExtra("name",recipieModelList.get(position).getName());
            intent.putParcelableArrayListExtra("ingredients",recipieModelList.get(position).getIngredients());
            intent.putExtra("position",position);
            intent.putExtra("steplist", (Serializable) stepList);
            context.startActivity(intent);
        }
    }
}
