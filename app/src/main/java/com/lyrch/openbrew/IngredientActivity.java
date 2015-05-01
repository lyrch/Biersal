package com.lyrch.openbrew;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class IngredientActivity extends ListActivity {
    private String[] ingredients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);

        Context context = getApplicationContext();
        Resources resources = context.getResources();
        ingredients = resources.getStringArray(R.array.ingredient_types);

        ArrayAdapter<String> ingredientsAdapter = new ArrayAdapter<String>(this, R.layout.ingredient_list_layout, ingredients);

        setListAdapter(ingredientsAdapter);
    }

}