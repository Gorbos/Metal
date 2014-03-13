package lila.raw.skills.metal.field;

import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SetMe {
	
	public static void SetTransparent(SurfaceView leSV){
		leSV.setZOrderOnTop(true);    // necessary
		SurfaceHolder sfhTrackHolder = leSV.getHolder();
		sfhTrackHolder.setFormat(PixelFormat.TRANSPARENT);
	}

}
