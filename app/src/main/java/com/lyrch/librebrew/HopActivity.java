package com.lyrch.librebrew;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class HopActivity extends Activity {


    // Create ingredient object that creates callbacks for the view objects for each ingredient

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bundle ingredientBundle = this.getIntent().getExtras();
        //if (null != ingredientBundle)
        //load selected ingredient
        setContentView(R.layout.hop_layout);
        Button saveButton = (Button) findViewById(R.id.hop_save);
        saveButton.setOnClickListener(saveListener);

        Button cancelButton = (Button) findViewById(R.id.hop_cancel);
        cancelButton.setOnClickListener(cancelListener);
    }

    private OnClickListener saveListener = new OnClickListener() {
        public void onClick(View v) {
            //Return the HopIngredient object and return to the receipe
        }
    };

    private OnClickListener cancelListener = new OnClickListener() {
        public void onClick(View v) {
            //Return to the receipe without an ingredient
        }
    };

    //Need to load hops into an array
}
