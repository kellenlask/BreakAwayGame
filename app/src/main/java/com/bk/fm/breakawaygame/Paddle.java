package com.bk.fm.breakawaygame;

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
	private Point leftSide;
	private Point rightSide;
	protected Paint paint;

//-----------------------------------------------------------------------
//
//		Constructor
//
//-----------------------------------------------------------------------
	public Paddle() {
		paint = new Paint();


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
