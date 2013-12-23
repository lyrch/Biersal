package com.lyrch.openbrew;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RecipeActivity extends Activity {

	TextView mAddIngredientButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_layout);
		
		mAddIngredientButton = (TextView) findViewById(R.id.add_ingredient_button);
		mAddIngredientButton.setOnClickListener(newIngredientListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe, menu);
		return true;
	}

	private OnClickListener newIngredientListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("I'm listenting!");
                Intent newRecipeIntent = new Intent(RecipeActivity.this, IngredientActivity.class);
                RecipeActivity.this.startActivity(newRecipeIntent);
        }
	};

}
