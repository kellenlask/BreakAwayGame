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
	protected Paint paint;
	protected int xVelocity;
	protected int yVelocity;

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
		yVelocity = 3;
		xVelocity = 3;

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

	public int getAcceleration() {
		return acceleration;
	}

	public int getSpeed() {
		return speed;
	}

	public Point getPosition() {
		return position;
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public int getYVelocity() {
		return yVelocity;
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

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public void setXVelocity(int velocity) {
		xVelocity = velocity;
	}

	public void setYVelocity(int velocity) {
		yVelocity = velocity;
	}

	public void bounce() {
		if(position.y <= 0) { //Top
			yVelocity *= -1;
		} else if(position.x <= 0) { //Left
			xVelocity *= -1;
		} else if(position.x >= screenWidth) { //Right
			xVelocity *= -1;
		} else { //Paddle
			yVelocity *= -1;
		}

		update();
	}

	public void update() {

	}

} //End Class
