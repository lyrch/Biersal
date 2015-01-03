package com.lyrch.openbrew;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class IngredientActivity extends Activity {

	protected int mPos;
    protected String mSelection;
	private Spinner mIngredientSpinner;
	protected ArrayAdapter<CharSequence> mIngredientTypeAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ingredient_layout);
		
		mIngredientSpinner = (Spinner) findViewById(R.id.ingredient_type_spinner);
		this.mIngredientTypeAdapter = ArrayAdapter.createFromResource(this, R.array.ingredient_types, android.R.layout.simple_spinner_item);
		this.mIngredientTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mIngredientSpinner.setAdapter(mIngredientTypeAdapter);
		OnItemSelectedListener spinnerListener = new myOnItemSelectedListener(this, this.mIngredientTypeAdapter);
	    mIngredientSpinner.setOnItemSelectedListener(spinnerListener);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recipe, menu);
		return true;
	}
	
	public class myOnItemSelectedListener implements OnItemSelectedListener {

        ArrayAdapter<CharSequence> mLocalAdapter;
        Activity mLocalContext;

        public myOnItemSelectedListener(Activity c, ArrayAdapter<CharSequence> ad) {

          this.mLocalContext = c;
          this.mLocalAdapter = ad;

        }

        public void onItemSelected(AdapterView<?> parent, View v, int pos, long row) {

            IngredientActivity.this.mPos = pos;
            IngredientActivity.this.mSelection = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {

            // do nothing

        }
    }
}
