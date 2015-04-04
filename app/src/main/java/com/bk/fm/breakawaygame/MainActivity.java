package com.bk.fm.breakawaygame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

//---------------------------------------------------
//
//		Fields
//
//---------------------------------------------------
	private int score;

	private Button playButton;
	private TextView highScore;

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
		SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
		int currentHighScore = sp.getInt("highScore", 0);

		highScore = (TextView) findViewById(R.id.highScore);
		playButton = (Button) findViewById(R.id.playButton);

		highScore.setText(highScore.getText().toString() + " " + currentHighScore);

	} //End public void initializeFields()

} //End Class
