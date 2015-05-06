package com.lyrch.librebrew.test;

import com.lyrch.librebrew.RecipeActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

public class RecipeActivityTest extends ActivityUnitTestCase<RecipeActivity> {

	RecipeActivity mActivity;

	public RecipeActivityTest() {
		super(RecipeActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		                           RecipeActivity.class);
		startActivity(intent, null, null);
		mActivity = getActivity();
	}

	protected void tearDown() throws Exception {
	}

	public void testRecipeNameLayout() {
		String nameTitleText = (String) mActivity.getString(com.lyrch.librebrew.R.string.recipe_name_title);
		TextView nameTitle = (TextView) mActivity.findViewById(com.lyrch.librebrew.R.id.recipe_name_title_text);
		assertEquals( "Wrong Text", nameTitleText, nameTitle.getText());
	}
	
	public void testIngredientAddButton() {
		String addButtonText = (String) mActivity.getString(com.lyrch.librebrew.R.string.add_ingredient);
		TextView addButton = (TextView) mActivity.findViewById(com.lyrch.librebrew.R.id.add_ingredient_button);
		assertEquals( "Wrong Text", addButtonText, addButton.getText());
	}
	
	public void testAddIngredientIntent() {	
		TextView       mNewIngrdient;
		mNewIngrdient = (TextView) mActivity.findViewById(com.lyrch.librebrew.R.id.add_ingredient_button);
		mNewIngrdient.performClick();
		
		Intent triggeredIntent = getStartedActivityIntent();
		assertNotNull(triggeredIntent);		
	}
}
