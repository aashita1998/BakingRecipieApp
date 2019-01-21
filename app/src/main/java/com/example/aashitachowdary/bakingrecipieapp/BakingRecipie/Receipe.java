
package com.example.aashitachowdary.bakingrecipieapp.BakingRecipie;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class Receipe implements Parcelable
{
    @SerializedName("id")
    @Expose
    private Integer id;
    public Receipe(Integer id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step> steps, Integer servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredient> ingredients = null;
    @SerializedName("steps")
    @Expose
    private ArrayList<Step> steps = null;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;
    protected Receipe(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            servings = null;
        } else {
            servings = in.readInt();
        }
        image = in.readString();
    }
    public static final Creator<Receipe> CREATOR = new Creator<Receipe>() {
        @Override
        public Receipe createFromParcel(Parcel in) {
            return new Receipe(in);
        }
        @Override
        public Receipe[] newArray(int size) {
            return new Receipe[size];
        }
    };

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
    public Integer getServings() {
        return servings;
    }
    public void setServings(Integer servings) {
        this.servings = servings;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(name);
        if (servings == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(servings);
        }
        parcel.writeString(image);
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList("stepslist",steps);
        bundle.putParcelableArrayList("ingredients",ingredients);
        parcel.writeBundle(bundle);
    }
}
