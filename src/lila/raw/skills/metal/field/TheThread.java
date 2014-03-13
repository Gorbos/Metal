package lila.raw.skills.metal.field;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceView;

@SuppressLint("WrongCall")
public class TheThread extends Thread {
	static final long FPS = 10;
	private MySurfaceView tg;	
	private boolean running = false;
	
	
	public TheThread(MySurfaceView tg){
		this.tg = tg;
	}
	
	public void setRunning(boolean run) {
        running = run;
  }

  @Override
  public void run() {
	  long ticksPS = 1000 / FPS;
      long startTime;
      long sleepTime;
      while (running) {
          Canvas c = null;
          startTime = System.currentTimeMillis();
          try {
                 c = tg.getHolder().lockCanvas();
                 synchronized (tg.getHolder()) {
                        tg.onDraw(c);
                 }
          } finally {
                 if (c != null) {
                        tg.getHolder().unlockCanvasAndPost(c);
                 }
          }
          sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
          try {
                 if (sleepTime > 0)
                        sleep(sleepTime);
                 else
                        sleep(10);
          } catch (Exception e) {}
   }
  }	

}
