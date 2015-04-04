package com.bk.fm.breakawaygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kellen on 4/2/2015.
 */
public class BreakAway extends SurfaceView implements SurfaceHolder.Callback {
//-----------------------------------------------------------------------
//
//		Fields
//
//-----------------------------------------------------------------------
	//Screen Dimensions
	private int screenWidth;
	private int screenHeight;

	//UI
	private boolean dialogIsDisplayed;
	private BreakAwayThread BAThread;
	private Paint backgroundPaint;
	private SoundPool soundPool;
	private SparseIntArray soundMap;

	//Game State
	private int currentHighScore;
	private int score;
	private double totalElapsedTime;
	private double seconds;

	//Objects
	private Activity activity;
	private Context context;
	private Ball ball;
	private Paddle paddle;

//-----------------------------------------------------------------------
//
//		Constructors
//
//-----------------------------------------------------------------------
	public BreakAway(Context context, AttributeSet attrs){
	//Sort out the Activity stuff
		super(context, attrs);
		this.context = context;
		activity = (Activity) context;

	// register SurfaceHolder.Callback listener
		getHolder().addCallback(this);

	//Handle Sound
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundMap = new SparseIntArray(1);
		soundMap.put(0, soundPool.load(context, R.raw.bounce, 1));

	//Set background paint
		backgroundPaint = new Paint();
		backgroundPaint.setColor(Color.WHITE);

	//Start Game
		newGame();

	}

//-----------------------------------------------------------------------
//
//		Logistical Methods
//
//-----------------------------------------------------------------------
	public void newGame() {
		initializeObjects();
		seconds = System.currentTimeMillis()/1000;
		score = 0;

	}

	private void updatePositions(double elapsedTime) {
		if (!dialogIsDisplayed) {
			double interval = elapsedTime / 10000.0;

			if(!ball.isValid() && !dialogIsDisplayed) {
				showGameOverDialog();

			} else if(ball.isTouching(paddle) != 0) {
				score++;
				ball.bounce(ball.isTouching(paddle));
				soundPool.play(soundMap.get(0), 1, 1, 1, 0, 1f);

			} else {
				ball.move();
			}

			if(System.currentTimeMillis()/1000 - seconds >= 20) {
				paddle.decrease();
				ball.speedUp();

				seconds = System.currentTimeMillis()/1000;

			}
		}

	}

	private void drawGameElements(Canvas canvas) {
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

		ball.draw(canvas);
		paddle.draw(canvas);
	}


	public void initializeObjects() {
		getHighScore();
		screenWidth = super.getWidth();
		screenHeight = super.getHeight();

		ball = new Ball(screenWidth, screenHeight);
		paddle = new Paddle(screenWidth, screenHeight);

	}

	public int getHighScore() {
		SharedPreferences sp = context.getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
		currentHighScore = sp.getInt("highScore", -1);

		return currentHighScore;
	}

	public void setHighScore() {
		SharedPreferences sp = context.getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("highScore", score);
		editor.commit();
	}

//-----------------------------------------------------------------------
//
//		SurfaceView Methods
//
//-----------------------------------------------------------------------
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		screenWidth = w;
		screenHeight = h;

		newGame();
	}

	// called when surface is first created
	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		if (!dialogIsDisplayed)
		{
			BreakAwayThread BAThread = new BreakAwayThread(holder); // create thread
			BAThread.setRunning(true); // start game running
			BAThread.start(); // start the game loop thread
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		screenWidth = width;
		screenHeight = height;
	}

	// called when the surface is destroyed
	@Override
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// ensure that thread terminates properly
		boolean retry = true;
		BAThread.setRunning(false); // terminate cannonThread

		while (retry)
		{
			try
			{
				BAThread.join(); // wait for thread to finish
				retry = false;
			}
			catch (InterruptedException e)
			{
				System.out.println("(KELLEN) Thread Interrupted.");
			}
		}
	} // end method surfaceDestroyed

	// called when the user touches the screen in this Activity
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		// get int representing the type of action which caused this event
		int action = e.getAction();

		// the user user touched the screen or dragged along the screen
		if (action == MotionEvent.ACTION_MOVE) {
			paddle.update(e);
		}

		return true;
	} // end method onTouchEvent

//-----------------------------------------------------------------------
//
//		Thread Subclass
//
//-----------------------------------------------------------------------

	// Thread subclass to control the game loop
	private class BreakAwayThread extends Thread {
		private SurfaceHolder surfaceHolder; // for manipulating canvas
		private boolean threadIsRunning = true; // running by default

		// initializes the surface holder
		public BreakAwayThread(SurfaceHolder holder)
		{
			surfaceHolder = holder;
			setName("BreakAwayThread");
		}

		// changes running state
		public void setRunning(boolean running)
		{
			threadIsRunning = running;
		}

		// controls the game loop
		@Override
		public void run()
		{
			Canvas canvas = null; // used for drawing
			long previousFrameTime = System.currentTimeMillis();

			while (threadIsRunning)
			{
				try
				{
					// get Canvas for exclusive drawing from this thread
					canvas = surfaceHolder.lockCanvas(null);

					// lock the surfaceHolder for drawing
					synchronized(surfaceHolder)
					{
						long currentTime = System.currentTimeMillis();
						double elapsedTimeMS = currentTime - previousFrameTime;

						totalElapsedTime += elapsedTimeMS / 1000.0;

						updatePositions(elapsedTimeMS); // update game state
						drawGameElements(canvas); // draw using the canvas
						previousFrameTime = currentTime; // update previous time
					}
				}
				finally
				{
					// display canvas's contents on the CannonView
					// and enable other threads to use the Canvas
					if (canvas != null)
						surfaceHolder.unlockCanvasAndPost(canvas);
				}
			} // end while
		} // end method run
	} //End Hidden Class

//-----------------------------------------------------------------------
//
//		Game Over Dialog Subclass
//
//-----------------------------------------------------------------------

	private void showGameOverDialog()
	{
		if(currentHighScore < score) {
			setHighScore();
		}

		// DialogFragment to display quiz stats and start new quiz
		final DialogFragment gameResult = new DialogFragment() {
			// create an AlertDialog and return it
			@Override
			public Dialog onCreateDialog(Bundle bundle) {
				// create dialog displaying String resource for messageId
				AlertDialog.Builder builder
						= new AlertDialog.Builder(getActivity());
				builder.setTitle("Game Over");

				// display number of shots fired and total time elapsed
				builder.setMessage("Score: " + score);
				builder.setPositiveButton("New Game",
						new DialogInterface.OnClickListener() {
							// called when "Reset Game" Button is pressed
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialogIsDisplayed = false;
								newGame(); // set up and start a new game
							}
						} // end anonymous inner class
				); // end call to setPositiveButton

				builder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//BAThread.destroy();

						Intent i = new Intent(context, MainActivity.class);
						startActivity(i);
					}
				});

				return builder.create(); // return the AlertDialog
			} // end method onCreateDialog
		}; // end DialogFragment anonymous inner class

		// in GUI thread, use FragmentManager to display the DialogFragment
		activity.runOnUiThread(
				new Runnable() {
					public void run() {
						dialogIsDisplayed = true;
						//TODO: BAThread.setRunning(false);
						gameResult.show(activity.getFragmentManager(), "results");
					}
				} // end Runnable
		); // end call to runOnUiThread

	} // end method showGameOverDialog

} //End Class
