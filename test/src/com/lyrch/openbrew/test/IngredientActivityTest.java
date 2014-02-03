package com.lyrch.openbrew.test;

import com.lyrch.openbrew.IngredientActivity;
import com.lyrch.openbrew.RecipeActivity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class IngredientActivityTest extends ActivityInstrumentationTestCase2<IngredientActivity> {

	public static final int INGREDIENT_TYPE_COUNT = 8;
	
	private IngredientActivity mActivity;
	private Spinner mSpinner;
	private SpinnerAdapter mIngredientTypes;
	
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
}
