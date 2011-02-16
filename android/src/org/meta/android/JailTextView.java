package org.meta.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class JailTextView extends TextView {

	public JailTextView(Context context) {
		super(context);
	}

	public JailTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public JailTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//canvas.getMatrix().invert(canvas.getMatrix());
		super.onDraw(canvas);
		final int num_of_bars=10;
		int div=canvas.getWidth()/num_of_bars;
		Paint paint=new Paint();
		
		paint.setColor(Color.RED);
		paint.setStrokeWidth(6);
		for(int i=0;i<num_of_bars;i++) {
			int pos=i*div;
			canvas.drawLine(pos, 0, pos, canvas.getHeight(), paint);
		}
	}
}
