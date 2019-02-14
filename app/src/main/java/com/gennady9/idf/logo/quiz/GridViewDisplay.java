package com.gennady9.idf.logo.quiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

@SuppressLint("NewApi")
public class GridViewDisplay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_display);
        
        

        Integer[] ChoosenArray = null;
		
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        try {myDbHelper.openDataBase();}catch(SQLException sqle){throw sqle;}
        String Type = getIntent().getStringExtra("type");
     //	ChoosenArray = myDbHelper.getImgIdArray(this, Type);
        myDbHelper.close();
        
        final Integer[] FinalArray = ChoosenArray;
        GridView gridView = (GridView) findViewById(R.id.gridview);
            
            // Instance of ImageAdapter Class
            final ImageAdapter imgAdapter = new ImageAdapter(this,Type,ChoosenArray);
            gridView.setAdapter(imgAdapter);
            
            gridView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                	
                	
                	Intent intent = new Intent(GridViewDisplay.this,ImageDisplay.class);
                    intent.putExtra("imgId", FinalArray[position]); // change to id
                    startActivity(intent);
                    
                }
            });
	}
	
	@Override
	protected void onResume()
	{
	   super.onResume();
	   this.onCreate(null); // CHECK FOR EFFICIENTY
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_grid_display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}