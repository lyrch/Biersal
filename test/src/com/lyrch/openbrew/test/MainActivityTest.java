package com.lyrch.openbrew.test;

import com.lyrch.openbrew.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

	MainActivity mActivity;
	Button       mNewRecipe;
	
	public MainActivityTest() {
		super("com.lyrch.openbrew", MainActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);
		mActivity = getActivity();
		mNewRecipe = (Button) mActivity.findViewById(com.lyrch.openbrew.R.id.newRecipeButton);
	}
}
