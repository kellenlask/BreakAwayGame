package com.bk.fm.breakawaygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

//---------------------------------------------------
//
//		Fields
//
//---------------------------------------------------
	private int score;

	private Button playButton;


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
		addActionHandler();

	} //End onCreate

//---------------------------------------------------
//
//		Logistical Methods
//
//---------------------------------------------------
	public void addActionHandler() {
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), BreakActivity.class);
				startActivity(i);
			}
		});
	}


//---------------------------------------------------
//
//		GUI Methods
//
//---------------------------------------------------
	public void initializeFields() {
		score = 0;

		playButton = (Button) findViewById(R.id.playButton);

		//drawingSurface = (SurfaceView) findViewById(R.id.drawingSurface);


	} //End public void initializeFields()

} //End Class
