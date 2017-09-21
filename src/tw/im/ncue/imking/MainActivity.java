package tw.im.ncue.imking;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.B_Profile).setOnClickListener(this);
		findViewById(R.id.B_Challenge).setOnClickListener(this);
		findViewById(R.id.B_Library).setOnClickListener(this);

		Intent music = new Intent();
		music.setClass(this, MusicService.class);
		startService(music);
		intent = new Intent(this, MusicService.class);
		playAction(1);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		playAction(4);
	}

	private void playAction(int op) {
		Bundle bundle = new Bundle();
		bundle.putInt("op", op);
		intent.putExtras(bundle);
		MainActivity.this.startService(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = null;
		MediaPlayer mediaPlayer;
		switch (v.getId()) {
		case R.id.B_Profile:
			// Start your app main activity
			mediaPlayer = MediaPlayer.create(this, R.raw.click_button);
			mediaPlayer.start();
			i = new Intent(MainActivity.this, Profile.class);
			break;
		case R.id.B_Challenge:
			mediaPlayer = MediaPlayer.create(this, R.raw.click_button);
			mediaPlayer.start();
			i = new Intent(MainActivity.this, Challenge.class);
			break;
		case R.id.B_Library:
			mediaPlayer = MediaPlayer.create(this, R.raw.click_button);
			mediaPlayer.start();
			break;
		}
		startActivity(i);

	}

}
