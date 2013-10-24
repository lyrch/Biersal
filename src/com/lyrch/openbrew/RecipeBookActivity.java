package com.lyrch.openbrew;
import java.io.File;
import java.io.IOException;

import com.lyrch.openbrew.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class RecipeBookActivity extends ListActivity {

	private ListView recipeListView;
    String[] recipes = { "nothing here!"};
	
	private void findRecipes() {
		File filesDir = new File(getFilesDir().toString());
		recipes = filesDir.list();
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_book_layout);
		findRecipes();
		
		setViewAdapter();
	}
	
	protected void onListItemClick(ListView listView, View view, int position, long id) {
		super.onListItemClick(listView, view, position, id);
		String fileName=recipes[position].toString();
		System.out.println(fileName);
		Intent recipeIntent = new Intent(RecipeBookActivity.this, RecipeActivity.class);
		Bundle recipeBundle = new Bundle();
		recipeBundle.putString("RecipeFile", fileName);
		recipeIntent.putExtras(recipeBundle);
		RecipeBookActivity.this.startActivity(recipeIntent);
	}

	private void setViewAdapter() {
		ListAdapter adapter = new BaseAdapter() {
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
		};
		setListAdapter(adapter);
	}

}
