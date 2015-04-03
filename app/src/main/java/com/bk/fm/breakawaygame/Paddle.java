package com.bk.fm.breakawaygame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

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

//-----------------------------------------------------------------------
//
//		Constructor
//
//-----------------------------------------------------------------------
	public Paddle(int screenWidth, int screenHeight) {
		//Set Relative Values
		Ycoord = screenHeight * 7 / 8;


		leftSide = new Point(screenWidth / 3, Ycoord);
		rightSide = new Point(screenWidth * 2 / 3, Ycoord);
		lineWidth = screenHeight / 16;

		paint = new Paint();
		paint.setStrokeWidth(lineWidth);
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

//-----------------------------------------------------------------------
//
//		Mutators
//
//-----------------------------------------------------------------------
	public void setLeftSide(Point leftSide) {
		this.leftSide = leftSide;
	}

	public void setRightSide(Point rightSide) {
		this.rightSide = rightSide;
	}



}
