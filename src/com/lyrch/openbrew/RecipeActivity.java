package com.lyrch.openbrew;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeActivity extends Activity {
	
	Button saveRecipeButton;
	EditText name;
	EditText hops;
	EditText malt;
	EditText grain;
	
	private OnClickListener saveRecipeListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//TODO: Save the Recipe to a database
			//Possible have a Recipe Object with a save method
			saveDataToFile();
			Context context = getApplicationContext();
			CharSequence text = "Recipe Saved!";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
			Toast.makeText(context, name.getText().toString(), duration).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		
		saveRecipeButton = (Button) findViewById(R.id.saveButton);
		saveRecipeButton.setOnClickListener(saveRecipeListener);
		
		
		
	}
	
	private void saveDataToFile() {
		
		name = (EditText) findViewById(R.id.recipeNameText);
		hops = (EditText) findViewById(R.id.hopsText);
		malt = (EditText) findViewById(R.id.maltText);
		grain = (EditText) findViewById(R.id.grainText);
		
		String nameString = name.getText().toString();
		String hopsString = hops.getText().toString();
		String maltString = malt.getText().toString();
		String grainString = grain.getText().toString();
		
		String FILENAME = nameString+".txt";

		FileOutputStream fos;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(nameString.getBytes());
			fos.write(hopsString.getBytes());
			fos.write(maltString.getBytes());
			fos.write(grainString.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_brew_main, menu);
		return true;
	}

}
