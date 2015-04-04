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
	protected boolean goingLeft;
	protected Paint paint;

	//Dimensions
	protected int screenHeight;
	protected int screenWidth;
	protected int radius;


//-------------------------------------------------------
//
//		Constructors
//
//-------------------------------------------------------
	public Ball(int screenWidth, int screenHeight) {
		setGoingUp(false);
		paint = new Paint();

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;

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

	public boolean isGoingLeft() {
		return goingLeft;
	}

	public Paint getPaint() {
		return paint;
	}

	public void draw(Canvas canvas) {
		canvas.drawCircle(position.y, position.x, radius, paint);

	}

	public boolean isValid() {
		return position.y < screenHeight;
	}

	public boolean isTouchingWall() {
		return 0 < position.x + radius
				&& screenWidth > position.x + radius
				&& 0 < position.y + radius;
	}

	public boolean isTouchingPaddle(Paddle p) {
		Point a = new Point(p.getLeftSide().x, p.getLeftSide().y - p.getLineWidth() / 2);
		Point c = new Point(p.getRightSide().x, p.getLeftSide().y + p.getLineWidth() / 2);

		if(position.y + radius < a.y || position.y - radius > c.y) {
			return false;

		} else {
			return position.x + radius > a.x && position.x - radius < c.x;

		}
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

	public void setGoingLeft(boolean goingLeft) {
		this.goingLeft = goingLeft;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

} //End Class
