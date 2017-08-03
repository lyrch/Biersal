package com.lyrch.librebrew;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;


public class IngredientChooserActivity extends ListActivity {
    private static final String TAG = "IngredientChooser";
    private String[] ingredients;
    private Map<String, String> activities;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        ingredients = resources.getStringArray(R.array.ingredient_types);
        activities = loadActivityMap();

        setAdapter();
    }

    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        String ingredient=ingredients[position].toString();
        String ingredientKlass = activities.get(ingredient);
        Class klass = null;
        try {
             klass = Class.forName("com.lyrch.librebrew." + ingredientKlass);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Failed to find class for " + ingredientKlass);
            e.printStackTrace();
            //perhaps create an alert dialog to tell people what happened
        }
        Intent ingredientIntent = new Intent(IngredientChooserActivity.this, klass);
        Bundle ingredientBundle = new Bundle();

        IngredientChooserActivity.this.startActivity(ingredientIntent);
    }

    private Map<String, String> loadActivityMap() {
        Map<String, String> ingredientKlasses = new HashMap<String,String>();

        Resources resources = getApplicationContext().getResources();

        XmlResourceParser parser = resources.getXml(R.xml.ingredient_map);
        try {
            int node = parser.getEventType();
            String key         = null;
            String layoutKlass = null;

            while(node != XmlPullParser.END_DOCUMENT) {

                if(node == XmlPullParser.START_TAG) {
                    if (parser.getAttributeValue(null, "key") != null) {
                        key = resources.getString(resources.getIdentifier(parser.getAttributeValue(null, "key"), "string", getPackageName()));
                        layoutKlass = parser.getAttributeValue(null, "klass");
                    }

                } else if(node == XmlPullParser.END_TAG) {
                    ingredientKlasses.put(key, layoutKlass);
                    key   = null;
                    layoutKlass = null;
                    Log.d(TAG, "Add " + key + " = " + layoutKlass);
                }

                node = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredientKlasses;
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
