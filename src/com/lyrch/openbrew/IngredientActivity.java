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

        /*
         * provide local instances of the mLocalAdapter and the mLocalContext
         */

        ArrayAdapter<CharSequence> mLocalAdapter;
        Activity mLocalContext;

        /**
         *  Constructor
         *  @param c - The activity that displays the Spinner.
         *  @param ad - The Adapter view that
         *    controls the Spinner.
         *  Instantiate a new listener object.
         */
        public myOnItemSelectedListener(Activity c, ArrayAdapter<CharSequence> ad) {

          this.mLocalContext = c;
          this.mLocalAdapter = ad;

        }

        /**
         * When the user selects an item in the spinner, this method is invoked by the callback
         * chain. Android calls the item selected listener for the spinner, which invokes the
         * onItemSelected method.
         *
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *  android.widget.AdapterView, android.view.View, int, long)
         * @param parent - the AdapterView for this listener
         * @param v - the View for this listener
         * @param pos - the 0-based position of the selection in the mLocalAdapter
         * @param row - the 0-based row number of the selection in the View
         */
        public void onItemSelected(AdapterView<?> parent, View v, int pos, long row) {

            IngredientActivity.this.mPos = pos;
            IngredientActivity.this.mSelection = parent.getItemAtPosition(pos).toString();
        }

        /**
         * The definition of OnItemSelectedListener requires an override
         * of onNothingSelected(), even though this implementation does not use it.
         * @param parent - The View for this Listener
         */
        public void onNothingSelected(AdapterView<?> parent) {

            // do nothing

        }
    }
}
