package lila.raw.skills.metal.field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameMap extends MySurfaceView{
	
	private Paint paint = new Paint();	
	private int mBGFarMoveX = 0;
	private int mBGNearMoveX = 0;
	private Bitmap bg;
	
	private int screenWidth = 0;
	private int screenHeight = 0;
	private TheThread tt;
	
	public GameMap(Context context, int bgID){
		super(context);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		SetMe.SetTransparent(this);
		bg = scaleImage(bgID);
	}
	
	public GameMap(Context context, int bgID, TheThread theThread){
		super(context);
		tt = theThread;
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		SetMe.SetTransparent(this);
		bg = scaleImage(bgID);
		this.getHolder().addCallback(holdy());
		
	}
	
	@Override
    public void onDraw(Canvas canvas) {
	   paint = new Paint();
	   paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
	   canvas.drawPaint(paint);
	   doDrawRunning(canvas);
	   paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
       
    }
	
	private void doDrawRunning(Canvas canvas) {
	    // decrement the far background
	    mBGFarMoveX = mBGFarMoveX - 10;
	    // decrement the near background
	    mBGNearMoveX = mBGNearMoveX - 4;
	    // calculate the wrap factor for matching image draw
	    int newFarX = bg.getWidth() - (-mBGFarMoveX);
	    // if we have scrolled all the way, reset to start
	    if (newFarX <= 0) {
	     mBGFarMoveX = 0;
	     // only need one draw
	     canvas.drawBitmap(bg, mBGFarMoveX, 0, null);
	     //canvas.drawBitmap(bg, newFarX, 0, null);
	    } else {
	     // need to draw original and wrap
	     canvas.drawBitmap(bg, mBGFarMoveX, 0, null);
	     canvas.drawBitmap(bg, newFarX, 0, null);
	    }
	}
	
	private Bitmap scaleImage(int resource){
	   Bitmap bp = BitmapFactory.decodeResource(getResources(), resource);
	   Bitmap bg = Bitmap.createScaledBitmap(bp, screenWidth, screenHeight, false);
	   return bg;
    }
//	
////	holder = leHolder = new SurfaceHolder.Callback() {
	
	private SurfaceHolder.Callback holdy(){
		
		
		
		return new SurfaceHolder.Callback() {
			@Override
	           public void surfaceDestroyed(SurfaceHolder holder) {
	           	boolean retry = true;
	               tt.setRunning(false);
	               while (retry) {
	                      try {
	                            tt.join();
	                            retry = false;
	                      } catch (InterruptedException e) {
	                      }
	               }
	           }	

	           @Override
	           public void surfaceCreated(SurfaceHolder holder) {
	           
	           	tt.setRunning(true);
	               tt.start();
	           }

	           @Override
	           public void surfaceChanged(SurfaceHolder holder, int format,
	                         int width, int height) {
	           }
	    };
	}

}
