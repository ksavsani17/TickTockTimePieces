package com.example.ticktocktimepieces.models;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("category")
    private String category;

    public String getCategory() {
        return category;
    }
}
