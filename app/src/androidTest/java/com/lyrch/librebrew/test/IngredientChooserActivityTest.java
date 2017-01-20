package com.lyrch.librebrew.test;

import com.lyrch.librebrew.IngredientChooserActivity;

import android.test.ActivityUnitTestCase;

import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class IngredientChooserActivityTest extends ActivityUnitTestCase<IngredientChooserActivity> {

	public static final int INGREDIENT_TYPE_COUNT = 8;
	public static final int TEST_POSITION = 3;
	public static final int INITIAL_POSITION = 0;
	
	private IngredientChooserActivity mActivity;
	private Spinner mSpinner;
	private SpinnerAdapter mIngredientTypes;
	private String mSelection;
	private int mPos;
	
	public IngredientChooserActivityTest() {
		super(IngredientChooserActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
        System.out.println("Setting up.");
	    mActivity = getActivity();
	}
	
	public void testPreConditions() {

	}

//	public void testIngredientSelection() {
//        TextView mNewIngrdient;
//        mNewIngrdient = (TextView) mActivity.findViewById(R.id.TextView01);
//        mNewIngrdient.performClick();
//
//        Intent triggeredIntent = getStartedActivityIntent();
//        System.out.println(triggeredIntent);
//        assertNotNull(triggeredIntent);
//	}
}
