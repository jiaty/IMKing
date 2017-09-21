package tw.im.ncue.imking;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Welcome extends ActionBarActivity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		ImageView img = (ImageView) findViewById(R.id.W_img2);

		// 動畫路徑設定(left,right,up,down)
		Animation am = new TranslateAnimation(150.0f, 0.0f, 50.0f, 0.0f);

		// 動畫開始到結束的時間，3秒
		am.setDuration(3000);

		// 動畫重覆次數 (-1表示一直重覆，0表示不重覆執行，所以只會執行一次)
		am.setRepeatCount(0);

		// 將動畫寫入ImageView
		img.setAnimation(am);
		// 開始動畫
		am.startNow();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(Welcome.this, MainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
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
}
