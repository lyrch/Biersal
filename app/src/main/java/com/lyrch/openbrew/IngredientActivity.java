package com.lyrch.openbrew;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


public class IngredientActivity extends ListActivity {
    private String[] ingredients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);

        Context context = getApplicationContext();
        Resources resources = context.getResources();
        ingredients = resources.getStringArray(R.array.ingredient_types);

        setAdapter();
    }

    private void setAdapter() {
        ListAdapter adapter = new BaseAdapter() {
            public int getCount() {
                return ingredients.length;
            }

            public Object getItem(int position) {
                return ingredients[position];
            }

            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater ingredientInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = ingredientInflater.inflate(R.layout.list_row, null);
                TextView ingredientTextView = (TextView) view.findViewById(R.id.TextView01);
                ingredientTextView.setText(ingredients[position]);
                ingredientTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
                ingredientTextView.setGravity(Gravity.CENTER);
                ingredientTextView.setTextIsSelectable(false);
                return view;
            }
        };
        setListAdapter(adapter);
    }

}