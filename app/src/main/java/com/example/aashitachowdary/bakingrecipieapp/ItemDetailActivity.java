package com.example.aashitachowdary.bakingrecipieapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Step;
import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    String desc, video,thumbnailUrl;
    ArrayList<Step> stepAlist;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
       Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       toolbar.setTitleTextColor(0xFFFFFFFF);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        stepAlist=new ArrayList<>();
        desc=getIntent().getStringExtra("fullDesc");
        video=getIntent().getStringExtra("link");
        thumbnailUrl=getIntent().getStringExtra("thumbnailurl");
        //Toast.makeText(this, thumbnailUrl, Toast.LENGTH_SHORT).show();
        stepAlist=getIntent().getParcelableArrayListExtra("steplist");
        if (savedInstanceState != null) {
            video=savedInstanceState.getString("link");
            desc=savedInstanceState.getString("fullDesc");
            thumbnailUrl=savedInstanceState.getString("thumbnailurl");
        }
        else {
            Bundle arguments = new Bundle();
            arguments.putString("link", video);
            arguments.putString("fullDesc", desc);
            arguments.putString("thumbnailurl",thumbnailUrl);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
           finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
