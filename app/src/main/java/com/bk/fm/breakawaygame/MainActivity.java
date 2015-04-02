package com.bk.fm.breakawaygame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.SurfaceView;


public class MainActivity extends ActionBarActivity {

//---------------------------------------------------
//
//		Fields
//
//---------------------------------------------------
	private int score;

	private SurfaceView drawingSurface;


//---------------------------------------------------
//
//		onCreate
//
//---------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeFields();

	} //End onCreate

	@Override
	protected void onPause() {

	}

	@Override
	public void onDestroy() {

	}

//---------------------------------------------------
//
//		Logistical Methods
//
//---------------------------------------------------



//---------------------------------------------------
//
//		GUI Methods
//
//---------------------------------------------------
	public void initializeFields() {
		score = 0;

		//drawingSurface = (SurfaceView) findViewById(R.id.drawingSurface);


	} //End public void initializeFields()

} //End Class
