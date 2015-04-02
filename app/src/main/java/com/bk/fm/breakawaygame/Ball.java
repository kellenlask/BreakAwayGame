package com.bk.fm.breakawaygame;

import android.graphics.Point;

/**
 * Created by Kellen on 4/3/2015.
 */
public class Ball {
//-------------------------------------------------------
//
//		Fields
//
//-------------------------------------------------------
	protected final int ACCELERATION = 3;
	protected int speed;
	protected Point position;
	protected boolean goingUp;


//-------------------------------------------------------
//
//		Constructors
//
//-------------------------------------------------------
	public Ball() {
		setGoingUp(false);

		//Randomly generate a good starting position

	}

//-------------------------------------------------------
//
//		Accessors
//
//-------------------------------------------------------

	public int getACCELERATION() {
		return ACCELERATION;
	}

	public int getSpeed() {
		return speed;
	}

	public Point getPosition() {
		return position;
	}

	public boolean isGoingUp() {
		return goingUp;
	}


//-------------------------------------------------------
//
//		Mutators
//
//-------------------------------------------------------
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}


} //End Class
