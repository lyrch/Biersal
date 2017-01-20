package com.lyrch.librebrew;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
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
    private String[] ingredients;
    private Map<String, Integer> layouts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);
        Context context = getApplicationContext();
        Resources resources = context.getResources();
        ingredients = resources.getStringArray(R.array.ingredient_types);
        layouts = loadLayoutMap();

        setAdapter();
    }

    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        String ingredient=ingredients[position].toString();
        //Intent ingredientIntent = new Intent(IngredientChooserActivity.this, IngredientActivity.class);
        //Need to refactor loadLayoutMap and ingredient_layout_map.xml to store Activity class names for the ingredients instead of layout names
        Intent ingredientIntent = new Intent((IngredientChooserActivity.this, Class.forName(layouts.get(ingredients[position].toString()))));
        Bundle ingredientBundle = new Bundle();

        ingredientBundle.putInt("IngredientLayout", layouts.get(ingredient));
        ingredientIntent.putExtras(ingredientBundle);
        IngredientChooserActivity.this.startActivity(ingredientIntent);
    }

    private Map<String, Integer> loadLayoutMap() {
        Map<String,Integer> ingredientLayouts = new HashMap<String,Integer>();

        Resources resources = getApplicationContext().getResources();

        XmlResourceParser parser = resources.getXml(R.xml.ingredient_layout_map);
        try {
            int node = parser.getEventType();
            String key   = null;
            int layoutId = -1;

            while(node != XmlPullParser.END_DOCUMENT) {

                if(node == XmlPullParser.START_TAG) {
                    if (parser.getAttributeValue(null, "key") != null) {
                        key = resources.getString(resources.getIdentifier(parser.getAttributeValue(null, "key"), "string", getPackageName()));
                        layoutId = resources.getIdentifier(parser.getAttributeValue(null, "value"), "string", getPackageName());
                    }
                    System.out.println(key);
                    System.out.println(layoutId);
                } else if(node == XmlPullParser.END_TAG) {
                    ingredientLayouts.put(key, layoutId);
                    key   = null;
                    layoutId = -1;

                }

                node = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ingredientLayouts;
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