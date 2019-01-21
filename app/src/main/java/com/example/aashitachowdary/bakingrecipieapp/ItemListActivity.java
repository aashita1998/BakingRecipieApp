package com.example.aashitachowdary.bakingrecipieapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Ingredient;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Step;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {
    private boolean mTwoPane;
    ArrayList<Step> stepArrayList;
    ArrayList<Ingredient> ingredientArrayList;
    RecyclerView recyclerView1, recyclerView;
    String ingre_to_Widget;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        name=getIntent().getStringExtra("name");
        stepArrayList = new ArrayList<>();
        ingredientArrayList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        stepArrayList = bundle.getParcelableArrayList("steps");
        ingredientArrayList = bundle.getParcelableArrayList("ingredients");

        recyclerView = findViewById(R.id.step_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        StepAdapter stepAdapter = new StepAdapter(this, stepArrayList);
        recyclerView.setAdapter(stepAdapter);


        recyclerView1 = findViewById(R.id.ing_rec);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        IngredientsAdapter ingredentAdapter = new IngredientsAdapter(this, ingredientArrayList);
        recyclerView1.setAdapter(ingredentAdapter);

        if (findViewById(R.id.item_detail_container) != null) {

            mTwoPane = true;
        }
        recyclerView = findViewById(R.id.step_rec);

        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }
    public  void AddToWidget(MenuItem item)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("file",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        StringBuilder builder=new StringBuilder();

        for (int i=0;i<ingredientArrayList.size();i++)
        {
            String in=ingredientArrayList.get(i).getIngredient();
            String m=ingredientArrayList.get(i).getMeasure();
            String q=ingredientArrayList.get(i).getQuantity();

            builder.append((i+1)+"."+in+"\t"+q+"\t"+m+"\n");
        }

        editor.putString("appwidget","\t\t\t\t\t"+name+"\n\n"+builder.toString());
        editor.apply();

        Intent intent= new Intent(this,NewAppWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int id[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(),NewAppWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,id);
        sendBroadcast(intent);
        Toast.makeText(this, "Ingredients Added To widget", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, stepArrayList, mTwoPane));
    }
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final ArrayList<Step> mValues;
        private final boolean mTwoPane;
        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      ArrayList<Step> items
                , boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
            holder.mContentView.setText(mValues.get(position).getShortDescription());
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString("link", mValues.get(pos).getVideoURL());
                    arguments.putString("thumbnailurl",mValues.get(pos).getThumbnailURL());
                    arguments.putString("fullDesc", mValues.get(pos).getDescription());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {

                    Intent intent = new Intent(mParentActivity, ItemDetailActivity.class);
                    intent.putExtra("link", mValues.get(pos).getVideoURL());
                    intent.putExtra("thumbnailurl",mValues.get(pos).getThumbnailURL());
                    intent.putExtra("fullDesc", mValues.get(pos).getDescription());
                    intent.putExtra("position", pos);
                    intent.putParcelableArrayListExtra("steplist", mValues);
                    mParentActivity.startActivity(intent);
                }
            }
        }
    }
}