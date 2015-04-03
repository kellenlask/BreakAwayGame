package com.bk.fm.breakawaygame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
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

	//GUI
	private boolean dialogIsDisplayed;
	private BreakAwayThread BAThread;

	//Game State
	private double totalElapsedTime;

	//Objects
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

	// register SurfaceHolder.Callback listener
		getHolder().addCallback(this);

	//Setup initial game state
		initializeObjects();

	}

//-----------------------------------------------------------------------
//
//		Logistical Methods
//
//-----------------------------------------------------------------------
	private void updatePositions(double elapsedTime) {

	}

	private void drawGameElements(Canvas canvas) {
		drawBall();
		drawPaddle();
	}

	public void drawBall() {

	}

	public void drawPaddle() {

	}

	public void initializeObjects() {
		ball = new Ball();
		paddle = new Paddle();


	}

//-----------------------------------------------------------------------
//
//		SurfaceView Methods
//
//-----------------------------------------------------------------------
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);




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
				BAThread.join(); // wait for cannonThread to finish
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
		if (action == MotionEvent.ACTION_DOWN ||
				action == MotionEvent.ACTION_MOVE)
		{
			//fireCannonball(e); // fire the cannonball toward the touch point
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
} //End Class
