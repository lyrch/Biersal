package com.lyrch.openbrew;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RecipeActivity extends Activity {
	
	private Button saveRecipeButton;
	private OnClickListener saveRecipeListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//TODO: Save the Recipe to a database
			//Possible have a Recipe Object with a save method
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		
		saveRecipeButton = (Button) findViewById(R.id.saveButton);
		saveRecipeButton.setOnClickListener(saveRecipeListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_brew_main, menu);
		return true;
	}

}
