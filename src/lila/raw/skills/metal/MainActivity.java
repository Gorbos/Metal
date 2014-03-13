package lila.raw.skills.metal;

import lila.raw.skills.metal.field.GameMap;
import lila.raw.skills.metal.field.TheThread;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceView;

public class MainActivity extends Activity {
	
	GameMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setGame(){
		//this.setContentView(new GameMap(getApplicationContext(), R.drawable.bricks,new TheThread(GameMap)));
	} 

}
