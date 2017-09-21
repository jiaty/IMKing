package tw.im.ncue.imking;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

public class MusicService extends Service {

	static MediaPlayer player;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		player = new MediaPlayer();
	}

	@Override
	public void onStart(Intent intent, int startId) { // 完成onCreate()後啟動
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				int op = bundle.getInt("op");
				switch (op) {
				case 1: // play
					Random r = new Random();
					int myRandom = r.nextInt(5) + 1;
					switch (myRandom) {
					case 1:
						player = MediaPlayer.create(this, R.raw.bgm01);
						break;
					case 2:
						player = MediaPlayer.create(this, R.raw.bgm02);
						break;
					case 3:
						player = MediaPlayer.create(this, R.raw.bgm03);
						break;
					case 4:
						player = MediaPlayer.create(this, R.raw.bgm04);
						break;
					case 5:
						player = MediaPlayer.create(this, R.raw.bgm05);
						break;
					}

					player.start();
					player.setLooping(true);
					break;
				case 2: // replay
					player.start();
					break;
				case 3: // pause
					player.pause();
					break;
				case 4: // stop
					if (player != null) {
						player.stop();
					}
					break;
				}
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (player != null) {
			player.stop();
			player.release();
		}

	}

}