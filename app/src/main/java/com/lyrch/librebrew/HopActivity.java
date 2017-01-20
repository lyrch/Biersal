package com.lyrch.librebrew;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import com.lyrch.librebrew.R;


public class HopActivity extends Activity {


    // Create ingredient object that creates callbacks for the view objects for each ingredient

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bundle ingredientBundle = this.getIntent().getExtras();
       //if (null != ingredientBundle)
        setContentView(R.layout.hop_layout);
    }
}
