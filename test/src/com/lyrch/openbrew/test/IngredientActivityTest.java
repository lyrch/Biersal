package com.lyrch.openbrew.test;

import com.lyrch.openbrew.IngredientActivity;
import com.lyrch.openbrew.RecipeActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class IngredientActivityTest extends ActivityInstrumentationTestCase2<IngredientActivity> {

	public static final int INGREDIENT_TYPE_COUNT = 8;
	public static final int TEST_POSITION = 3;
	public static final int INITIAL_POSITION = 0;
	
	private IngredientActivity mActivity;
	private Spinner mSpinner;
	private SpinnerAdapter mIngredientTypes;
	private String mSelection;
	private int mPos;
	
	public IngredientActivityTest() {
		super(IngredientActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
        System.out.println("Setting up.");
	    mActivity = getActivity();
	    
	    mSpinner = (Spinner) mActivity.findViewById(com.lyrch.openbrew.R.id.ingredient_type_spinner);
	    mIngredientTypes = mSpinner.getAdapter();
	}
	
	public void testPreConditions() {
		System.out.println(mSpinner.toString());
		assertTrue(mSpinner.getOnItemSelectedListener() != null);
		assertTrue(mIngredientTypes != null);
		assertEquals(mIngredientTypes.getCount(), INGREDIENT_TYPE_COUNT);
	}

	public void testIngredientSelectionUI() {
		mActivity.runOnUiThread(
		  new Runnable() {
			public void run() {
			  mSpinner.requestFocus();
			  mSpinner.setSelection(INITIAL_POSITION);
			}
		  }
		);

		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	    for (int i = 1; i <= TEST_POSITION; i++) {
	      this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
	    }
	    this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);

	    mPos = mSpinner.getSelectedItemPosition();
	    mSelection = (String)mSpinner.getItemAtPosition(mPos);

	    Resources res = mActivity.getResources();
	    String[] ingredients = res.getStringArray(com.lyrch.openbrew.R.array.ingredient_types);
	    assertEquals(ingredients[TEST_POSITION],mSelection);
	}
}
