package com.bk.fm.breakawaygame;

import android.graphics.Canvas;
import android.graphics.Paint;
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
	protected int acceleration;
	protected int speed;
	protected Point position;
	protected boolean goingUp;
	protected Paint paint;

	//Dimensions
	protected int radius;


//-------------------------------------------------------
//
//		Constructors
//
//-------------------------------------------------------
	public Ball(int screenWidth, int screenHeight) {
		setGoingUp(false);
		paint = new Paint();

		//Set relative values
		acceleration = screenWidth * 3 / 2;
		radius = screenWidth / 36;

		//Randomly generate a good starting position

	}

//-------------------------------------------------------
//
//		Accessors
//
//-------------------------------------------------------

	public int getACCELERATION() {
		return acceleration;
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

	public void draw(Canvas canvas) {
		canvas.drawCircle(position.y, position.x, radius, paint);

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
