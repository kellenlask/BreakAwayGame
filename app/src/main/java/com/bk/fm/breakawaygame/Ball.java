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
	public Ball(int w, int h) {
		yVelocity = 1;
		xVelocity = -1;

		paint = new Paint();

		this.screenHeight = h;
		this.screenWidth = w;

		//Set relative values
		acceleration = w * 3 / 2;
		radius = w / 36;

		//Randomly generate a good starting position
		/*Random r = new Random();
		if(w != 0) {
			position = new Point(r.nextInt(w - 200) + 50, 50);
		} else {
			position = new Point(r.nextInt(700) + 20, 50);
		}*/

		position = new Point(500, 500);

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
		return position.y < screenHeight && position.y > 0 && position.x > 0 && position.x < screenWidth;
	}

	public boolean isTouchingWall() {
		return (1 > position.x - radius) || (screenWidth - 1 < position.x + radius) || (1 > position.y - radius);
	}

	public boolean isTouchingPaddle(Paddle p) {
		Point a = new Point(p.getLeftSide().x, p.getLeftSide().y - p.getLineWidth() / 2);
		Point c = new Point(p.getRightSide().x, p.getLeftSide().y + p.getLineWidth() / 2);

		if(position.y + radius < a.y) {
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

	public void speedUp() {
		xVelocity++;
		yVelocity++;
	}

	public void bounce(double interval) {
		if(position.y <= 0) { //Top
			position.y = radius + 1;
			yVelocity *= -1;

		} else if(position.x <= 0) { //Left
			position.x = radius + 1;
			xVelocity *= -1;

		} else if(position.x >= screenWidth) { //Right
			position.x = screenWidth - radius;
			xVelocity *= -1;

		} else { //Paddle
			yVelocity *= -1;
		}

		update(interval);
	}

	public void update(double interval) {
		position.x += xVelocity;
		position.y += yVelocity;
	}

} //End Class
