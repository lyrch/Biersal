package com.lyrch.openbrew;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IngredientActivity extends Activity {

    protected int mPos;
    protected String mSelection;
    protected ArrayAdapter<CharSequence> mIngredientTypeAdapter;

    private Spinner mIngredientSpinner;
    private static Map<String, Pair<Integer, Integer>> mIngredientLayouts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_layout);
        loadIngredients();

        mIngredientSpinner = (Spinner) findViewById(R.id.ingredient_type_spinner);
        this.mIngredientTypeAdapter = ArrayAdapter.createFromResource(this, R.array.ingredient_types, android.R.layout.simple_spinner_item);
        this.mIngredientTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIngredientSpinner.setAdapter(mIngredientTypeAdapter);
        OnItemSelectedListener spinnerListener = new ingredientSelectedListener(this, this.mIngredientTypeAdapter);
        mIngredientSpinner.setOnItemSelectedListener(spinnerListener);
    }

    private void loadIngredients() {
        Map<String, Pair<Integer, Integer>> tempMap = new HashMap<String, Pair<Integer, Integer>>();
        tempMap.put(getString(R.string.adjunct), Pair.create(R.id.adjunctLayout, R.layout.adjunct_layout));
        tempMap.put(getString(R.string.dehyrated_yeast), Pair.create(R.id.dehydratedYeastLayout, R.layout.dehydrated_yeast_layout));
        tempMap.put(getString(R.string.grain), Pair.create(R.id.grainLayout, R.layout.grain_layout));
        tempMap.put(getString(R.string.hops), Pair.create(R.id.hopLayout, R.layout.hop_layout));
        tempMap.put(getString(R.string.liquid_malt), Pair.create(R.id.liquidMaltLayout, R.layout.liquid_malt_layout));
        tempMap.put(getString(R.string.liquid_yeast), Pair.create(R.id.liquidYeastLayout, R.layout.liquid_yeast_layout));
        tempMap.put(getString(R.string.powdered_malt), Pair.create(R.id.powderedMaltLayout, R.layout.powdered_malt_layout));
        tempMap.put(getString(R.string.water), Pair.create(R.id.waterLayout, R.layout.water_layout));

        mIngredientLayouts = Collections.unmodifiableMap(tempMap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recipe, menu);
        return true;
    }

    public class ingredientSelectedListener implements OnItemSelectedListener {

        ArrayAdapter<CharSequence> mLocalAdapter;
        Activity mLocalContext;

        public ingredientSelectedListener(Activity context, ArrayAdapter<CharSequence> adapter) {

            this.mLocalContext = context;
            this.mLocalAdapter = adapter;

        }

        public void onItemSelected(AdapterView<?> parent, View v, int pos, long row) {

            IngredientActivity.this.mPos = pos;
            IngredientActivity.this.mSelection = parent.getItemAtPosition(pos).toString();


            if (!mSelection.equals("")) {
                Pair layoutSelection = mIngredientLayouts.get(mSelection);
                ViewStub ingredientStub = (ViewStub) findViewById(R.id.ingredientStub);
                ingredientStub.setInflatedId((Integer) layoutSelection.first);
                ingredientStub.setLayoutResource((Integer) layoutSelection.second);
                ingredientStub.inflate();
            }

        }

        public void onNothingSelected(AdapterView<?> parent) {

            // do nothing

        }
    }
}
