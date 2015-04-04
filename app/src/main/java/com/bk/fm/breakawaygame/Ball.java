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
	protected Point position;
	protected int radius;
	protected double xVel;
	protected double yVel;
	protected int speed;

	protected int screenHeight;
	protected int screenWidth;
	protected Paint paint;

//-------------------------------------------------------
//
//		Constructors
//
//-------------------------------------------------------
	public Ball(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;

		radius = width / 36;
		position = new Point(width / 2, height / 6);
		xVel = 2; yVel = 1;
		speed = 3;

		paint = new Paint();

	} //End Constructor

//-------------------------------------------------------
//
//		Accessors
//
//-------------------------------------------------------

	public void draw(Canvas canvas) {
		canvas.drawCircle(position.x, position.y, radius, paint);

	} //End public void draw(Canvas)

	public boolean isValid() {
		return position.y < screenHeight;

	} //End public boolean isValid()

	public int isTouching(Paddle paddle) {
		if(position.y - radius < 0) { //Top
			return 1;
		}

		if(position.x + radius > screenWidth) { //Right
			return 2;
		}

		if(position.x - radius < 0) { //Left
			return 4;
		}

		if(position.x >= paddle.leftSide.x && position.x <= paddle.rightSide.x
				&& position.y + radius >= paddle.Ycoord - paddle.getStrokeWidth() / 2
				&& !(position.y - radius > paddle.Ycoord + paddle.getStrokeWidth() / 2) ) { //Paddle
			return 3;
		}

		return 0;

	} //End public int isTouching(Paddle)

//-------------------------------------------------------
//
//		Mutators
//
//-------------------------------------------------------	

	public void bounce(int wall) {
		switch(wall) {
			case 0:
				break;

			case 1:
				yVel *= -1;
				break;

			case 2:
				xVel *= -1;
				break;

			case 3:
				yVel *= -1;
				break;

			case 4:
				xVel *= -1;
				break;
		} //End switch

		move();
	}

	public void move() {
		int x = (int) (position.x + xVel * speed);
		int y = (int) (position.y + yVel * speed);

		position.set(x, y);
	}

	public void speedUp() {
		speed++;
	}

} //End Class
	
	
	

