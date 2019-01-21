package com.example.aashitachowdary.bakingrecipieapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Step;
import java.util.ArrayList;

class StepAdapter  extends RecyclerView.Adapter<StepAdapter.StepHolder> {
    Context context;
    ArrayList<Step> stepArrayList;
    public StepAdapter(ItemListActivity itemListActivity, ArrayList<Step> stepList) {
        this.context=itemListActivity;
        this.stepArrayList=stepList;
        }

    @NonNull
    @Override
    public StepHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_list_content,viewGroup,false);
        return new StepHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepHolder stepHolder, int i) {
     stepHolder.textView.setText(stepArrayList.get(i).getId());
     stepHolder.textView1.setText(stepArrayList.get(i).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return stepArrayList.size();
    }

    public class StepHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView1;
        public StepHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.id_text);
            textView1=itemView.findViewById(R.id.content);
        }
    }
}
