package com.lyrch.openbrew;
import java.io.File;
import java.io.IOException;

import com.lyrch.openbrew.R;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class RecipeBookActivity extends Activity {

	private ListView recipeListView;
    String[] recipes = { "nothing here!"};
	
	private void findRecipes() {
		File filesDir = new File(getFilesDir().toString());
		recipes = filesDir.list();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_book_layout);
		recipeListView = (ListView) findViewById(R.id.recipeListView);
		findRecipes();
		
		setViewAdapter();
	}

	private void setViewAdapter() {
		recipeListView.setAdapter(new BaseAdapter() {
			public int getCount() {
				return recipes.length;
			}
			
			public Object getItem(int position) {
				return recipes[position];
			}
			
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.list_row, null);
				TextView textView = (TextView) view.findViewById(R.id.TextView01);
				textView.setText(recipes[position]);
				return view;
			}
		});
	}

}
