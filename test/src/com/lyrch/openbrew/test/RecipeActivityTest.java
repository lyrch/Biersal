package com.lyrch.openbrew.test;

import com.lyrch.openbrew.RecipeActivity;

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
		String nameText = (String) mActivity.getString(com.lyrch.openbrew.R.string.title);
		TextView nameTitle = (TextView) mActivity.findViewById(com.lyrch.openbrew.R.id.recipeNameTitle);
		assertEquals( "Wrong Text", nameText, nameTitle.getText());
	}

	

}
