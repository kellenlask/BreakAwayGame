package com.bk.fm.breakawaygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Kellen on 4/2/2015.
 */
public class BreakAway extends SurfaceView implements SurfaceHolder {
//-----------------------------------------------------------------------
//
//		Fields
//
//-----------------------------------------------------------------------
	private Context context;

//-----------------------------------------------------------------------
//
//		Constructors
//
//-----------------------------------------------------------------------
	public BreakAway(Context context, AttributeSet attrs){
	//Sort out the Activity stuff
		super(context, attrs);
		this.context = context;


	}

//-----------------------------------------------------------------------
//
//		SurfaceView Methods
//
//-----------------------------------------------------------------------
	@Override
	public void addCallback(Callback callback) {

	}

	@Override
	public void removeCallback(Callback callback) {

	}

	@Override
	public boolean isCreating() {
		return false;
	}

	@Override
	public void setType(int type) {

	}

	@Override
	public void setFixedSize(int width, int height) {

	}

	@Override
	public void setSizeFromLayout() {

	}

	@Override
	public void setFormat(int format) {

	}

	@Override
	public Canvas lockCanvas() {
		return null;
	}

	@Override
	public Canvas lockCanvas(Rect dirty) {
		return null;
	}

	@Override
	public void unlockCanvasAndPost(Canvas canvas) {

	}

	@Override
	public Rect getSurfaceFrame() {
		return null;
	}

	@Override
	public Surface getSurface() {
		return null;
	}
}
