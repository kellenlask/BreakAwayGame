package com.bk.fm.breakawaygame;

import android.content.Context;
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


	}

//-----------------------------------------------------------------------
//
//		SurfaceView Methods
//
//-----------------------------------------------------------------------
	// called when surface is first created
	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		if (!dialogIsDisplayed)
		{
			cannonThread = new CannonThread(holder); // create thread
			cannonThread.setRunning(true); // start game running
			cannonThread.start(); // start the game loop thread
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
		cannonThread.setRunning(false); // terminate cannonThread

		while (retry)
		{
			try
			{
				cannonThread.join(); // wait for cannonThread to finish
				retry = false;
			}
			catch (InterruptedException e)
			{
				System.out.println("(KELLEN) Thread Interrupted.")
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
			fireCannonball(e); // fire the cannonball toward the touch point
		}

		return true;
	} // end method onTouchEvent
}
