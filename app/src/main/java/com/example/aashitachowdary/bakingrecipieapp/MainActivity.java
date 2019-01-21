package com.example.aashitachowdary.bakingrecipieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Receipe;
import com.example.aashitachowdary.bakingrecipieapp.BakingRecipie.Step;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    String ImageUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    List<Receipe>  recipieModelList ;
    List<Step> stepList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rec);
        recipieModelList = new ArrayList<>();
        stepList=new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        items();
        }
    public void items() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ImageUrl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                recipieModelList = Arrays.asList(gson.fromJson(response, Receipe[].class));
                MainAdapter myAdapter = new MainAdapter(MainActivity.this, recipieModelList,stepList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(myAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
