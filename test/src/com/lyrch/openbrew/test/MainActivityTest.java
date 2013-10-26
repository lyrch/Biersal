package com.lyrch.openbrew.test;

import com.lyrch.openbrew.MainActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	MainActivity mActivity;
	
	
	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		                           MainActivity.class);
		startActivity(intent, null, null);
		mActivity = getActivity();
	}
	
	public void tesNewRecipeButtonIsCorrect() {
		Button       mNewRecipe;
		mNewRecipe = (Button) mActivity.findViewById(com.lyrch.openbrew.R.id.newRecipeButton);
		assertNotNull("Missing the New Recipe button", mNewRecipe);
		assertEquals("Wrong text", "New Recipe", mNewRecipe.getText());
	}
	
	public void testNewRecipeIntent() {
		Button       mNewRecipe;
		mNewRecipe = (Button) mActivity.findViewById(com.lyrch.openbrew.R.id.newRecipeButton);
		mNewRecipe.performClick();
		
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull(triggeredIntent);
	}
}
