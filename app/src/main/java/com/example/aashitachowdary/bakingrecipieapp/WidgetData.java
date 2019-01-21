package com.example.aashitachowdary.bakingrecipieapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class WidgetData  implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    Intent intent;

    public WidgetData(Context context, Intent intent){

        this.context=context;
        this.intent=intent;
        }
    @Override
    public void onCreate(){}
    @Override
    public void onDataSetChanged() { }
    @Override
    public void onDestroy() { }
    @Override
    public int getCount() { return 0; }
    @Override
    public RemoteViews getViewAt(int i) {
        return null;
    }
    @Override
    public RemoteViews getLoadingView() {
        return null;
    }
    @Override
    public int getViewTypeCount() {
        return 0;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
}
