package com.bk.fm.breakawaygame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

/**
 * Created by Kellen on 4/2/2015.
 */
public class Paddle {
//-----------------------------------------------------------------------
//
//		Fields
//
//-----------------------------------------------------------------------
	protected Point leftSide;
	protected Point rightSide;
	protected Paint paint;

	//Relative Values
	protected int Ycoord;
	protected int lineWidth;
	protected int screenWidth;

//-----------------------------------------------------------------------
//
//		Constructor
//
//-----------------------------------------------------------------------
	public Paddle(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;

		//Set Relative Values
		Ycoord = screenHeight * 7 / 8;


		leftSide = new Point(screenWidth / 3, Ycoord);
		rightSide = new Point(screenWidth * 2 / 3, Ycoord);
		lineWidth = screenHeight / 16;

		paint = new Paint();
		paint.setStrokeWidth(lineWidth);
		paint.setColor(Color.BLUE);
	}



//-----------------------------------------------------------------------
//
//		Accessors
//
//-----------------------------------------------------------------------
	public Point getLeftSide() {
		return leftSide;
	}

	public Point getRightSide() {
		return rightSide;
	}

	public void draw(Canvas canvas) {
		canvas.drawLine(leftSide.x, leftSide.y, rightSide.x, rightSide.y, paint);

	}

	public int getLineWidth() {
		return lineWidth;
	}

	public Point getCenter() {
		return new Point(rightSide.x - leftSide.x, rightSide.y);
	}

//-----------------------------------------------------------------------
//
//		Mutators
//
//-----------------------------------------------------------------------
	public void moveLeft() {
		int amount = screenWidth / 32;

		if(leftSide.x - amount > 0) {
			leftSide.x -= amount;
			rightSide.x -= amount;
		}
	}

	public void moveRight() {
		int amount = screenWidth / 32;

		if(rightSide.x + amount < screenWidth) {
			leftSide.x += amount;
			rightSide.x += amount;
		}
	}

	public void decrease() {
		if(getLineWidth() > (screenWidth * 1 / 6)) {
			leftSide.x -= 5;
			rightSide.x -= 5;
		}
	}

	public void update(MotionEvent event) {
		Point touchPoint = new Point((int) event.getX(), (int) event.getY());
		Point center = getCenter();

		if(touchPoint.x < center.x) {
			moveLeft();
		} else if(touchPoint.x > center.x) {
			moveRight();
		}
	}
}
