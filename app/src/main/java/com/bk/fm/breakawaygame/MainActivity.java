package com.bk.fm.breakawaygame;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

//---------------------------------------------------
//
//		Fields
//
//---------------------------------------------------



//---------------------------------------------------
//
//		onCreate
//
//---------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

	} //End public void initializeFields()

} //End Class
