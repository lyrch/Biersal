package com.lyrch.openbrew;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
import android.util.Xml;

public class RecipeActivity extends Activity {

	Button saveRecipeButton;
	EditText name;
	EditText hops;
	EditText malt;
	EditText grain;
	EditText directions;
    String   nameString;
    String   hopsString;
    String   maltString;
    String   grainString;
    String   directionsString;

	private OnClickListener saveRecipeListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			saveRecipe();
			Context context = getApplicationContext();
			CharSequence text = "Recipe Saved!";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
		}
	};
	
	private void connectLayout(){
		saveRecipeButton = (Button) findViewById(R.id.saveButton);
		saveRecipeButton.setOnClickListener(saveRecipeListener);

		name =       (EditText) findViewById(R.id.recipeNameText);
		hops =       (EditText) findViewById(R.id.hopsText);
		malt =       (EditText) findViewById(R.id.maltText);
		grain =      (EditText) findViewById(R.id.grainText);
		directions = (EditText) findViewById(R.id.directionsText);
	}
	
	private void parseIngredient(String nodeName, String nodeValue){
		System.out.println("Name: "+nodeName);
		System.out.println("Value: "+nodeValue);
//		String nodeName = recipe.getName();
//		String nodeValue = recipe.next().getText();
		if ("name" == nodeName){
			nameString = nodeValue;
		}
		else if ("hops" == nodeName){
			hopsString = nodeValue;
		}
		else if ("malt" == nodeName){
			maltString = nodeValue;
		}
		else if ("grain" == nodeName){
			grainString = nodeValue;
		}
		else if ("directions" == nodeName){
			directionsString = nodeValue;
		}
	}
	
	private void setRecipe(){
		name.setText(nameString);
		hops.setText(hopsString);
		malt.setText(maltString);
		grain.setText(grainString);
		directions.setText(directionsString);
	}
	
	private void loadRecipe(String fileName){
		FileInputStream fis;
		InputStreamReader fileReader;
		XmlPullParser recipe = Xml.newPullParser();
		try{
			fis = new FileInputStream(getFilesDir()+"/"+fileName);
			fileReader = new InputStreamReader(fis);
			
			recipe.setInput(fileReader);
			int eventType = recipe.getEventType();
		    boolean done = false;
		    String nodeName = "";
		    String nodeValue = "";
		    while (eventType != XmlPullParser.END_DOCUMENT && !done){
		        switch (eventType){
		        	case XmlPullParser.PROCESSING_INSTRUCTION:
		        		break;
		            case XmlPullParser.START_DOCUMENT:
		                break;
		            case XmlPullParser.START_TAG:
		            	nodeName = recipe.getName();
		            	break;
		            case XmlPullParser.TEXT:
		            	nodeValue = recipe.getText();
		            	parseIngredient(nodeName, nodeValue);
		            	break;
		            case XmlPullParser.END_TAG:
		            	break;
		            case XmlPullParser.END_DOCUMENT:
		            	break;
		            default:
		            	break;
		        }
		        eventType = recipe.next();
		    }
		    setRecipe();
		} catch (FileNotFoundException e) {
		    // TODO
		} catch (IOException e) {
		    // TODO
		} catch (Exception e){
		    // TODO
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipe_layout);
		
		connectLayout();
		
		Bundle recipeBundle = this.getIntent().getExtras();
		if (null != recipeBundle)
			loadRecipe(recipeBundle.getString("RecipeFile"));

		
	}

	private void saveRecipe() {

		nameString =       name.getText().toString();
		hopsString =       hops.getText().toString();
		maltString =       malt.getText().toString();
		grainString =      grain.getText().toString();
		directionsString = directions.getText().toString();

		String filename = getFilesDir()+"/"+nameString+".xml";

		FileOutputStream fos;
        XmlSerializer recipe = Xml.newSerializer();
		try {
            fos = new FileOutputStream(filename);
            writeRecipeXML(recipe, fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    private void writeRecipeXML(XmlSerializer recipeSerializer, FileOutputStream output) {
        try {
            recipeSerializer.setOutput(output, "UTF-8");
            recipeSerializer.startDocument(null, Boolean.valueOf(true));
            recipeSerializer.startTag(null, "recipe");
            addRecipeNode("name", nameString, recipeSerializer);
            addRecipeNode("hops", hopsString, recipeSerializer);
            addRecipeNode("malt", maltString, recipeSerializer);
            addRecipeNode("grain", grainString, recipeSerializer);
            addRecipeNode("directions", directionsString, recipeSerializer);
            recipeSerializer.endTag(null, "recipe");
            recipeSerializer.endDocument();
            recipeSerializer.flush();

        }catch( Exception e) {
            //Log.e("Exception","Exception occured in writing");
            e.printStackTrace();
        }
    }
    
    private void addRecipeNode(String tagName, String content, XmlSerializer serializer) throws IllegalArgumentException, IllegalStateException, IOException {
    	serializer.startTag(null, tagName);
        serializer.text(content);
        serializer.endTag(null, tagName);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.open_brew_main, menu);
		return true;
	}
}
