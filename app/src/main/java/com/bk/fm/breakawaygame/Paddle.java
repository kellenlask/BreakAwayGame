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
	protected int lineLength;

	//Relative Values
	protected int Ycoord;
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
		lineLength = screenWidth * 1 / 3;

		leftSide = new Point(0, Ycoord);
		rightSide = new Point(lineLength, Ycoord);

		paint = new Paint();
		paint.setStrokeWidth(screenWidth * 1 / 36);
		paint.setColor(Color.BLUE);
	}



//-----------------------------------------------------------------------
//
//		Accessors
//
//-----------------------------------------------------------------------

	public void draw(Canvas canvas) {
		canvas.drawLine(leftSide.x, leftSide.y, rightSide.x, rightSide.y, paint);

	}

	public int getLineLength() {
		lineLength = rightSide.x - leftSide.x;
		return lineLength;
	}

	public int getStrokeWidth() {
		return screenWidth * 2 / 36;
	}

//-----------------------------------------------------------------------
//
//		Mutators
//
//-----------------------------------------------------------------------

	public void setCenter(Point p) {
		int lineWidth = getLineLength() / 2;

		if(p.x - lineWidth < 0) {
			leftSide.x = 0;
			rightSide.x = 2 * lineWidth;

		} else if(p.x + lineWidth >= screenWidth) {
			rightSide.x = screenWidth;
			leftSide.x = screenWidth - 2 * lineWidth;

		} else {
			leftSide.x = p.x - lineWidth;
			rightSide.x = p.x + lineWidth;
		}


	}

	public void decrease() {
		if(getLineLength() > (screenWidth * 1 / 6)) {
			leftSide.x += 5;
		}
	}

	public void update(MotionEvent event) {
		Point touchPoint = new Point((int) event.getX(), (int) event.getY());

		setCenter(touchPoint);
	}
}
