package tw.im.ncue.imking;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Challenge extends ActionBarActivity implements OnClickListener {
	Storage DATA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_challenge);

		DATA = new Storage(this);

		findViewById(R.id.R_back_button).setOnClickListener(this);
		findViewById(R.id.B_L_L1).setOnClickListener(this);
		findViewById(R.id.B_L_L2).setOnClickListener(this);
		findViewById(R.id.B_L_L3).setOnClickListener(this);
		findViewById(R.id.B_L_L4).setOnClickListener(this);
		findViewById(R.id.B_L_L5).setOnClickListener(this);
		findViewById(R.id.B_L_L6).setOnClickListener(this);

		TextView myCredit = (TextView) findViewById(R.id.C_credit);
		myCredit.setText(DATA.getCredit());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.challenge, menu);
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
		switch (v.getId()) {
		case R.id.R_back_button:
			MediaPlayer.create(this, R.raw.click_button).start();
			finish();
			break;
		case R.id.B_L_L1:
			i = new Intent(Challenge.this, Room.class);
			i.putExtra("LEVEL", "1"); // 单
			i.putExtra("QUESTION", "5"); // 肈ヘ计
			i.putExtra("TIME", "6"); // –肈丁
			i.putExtra("SCORE", "10"); // 肈眔だ
			MediaPlayer.create(this, R.raw.click_room).start();

			startActivity(i);
			break;
		case R.id.B_L_L2:
			i = new Intent(Challenge.this, Room.class);
			i.putExtra("LEVEL", "2"); // 单
			i.putExtra("QUESTION", "5"); // 肈ヘ计
			i.putExtra("TIME", "5"); // –肈丁
			i.putExtra("SCORE", "15"); // 肈眔だ
			MediaPlayer.create(this, R.raw.click_room).start();

			startActivity(i);
			break;
		case R.id.B_L_L3:
			i = new Intent(Challenge.this, Room.class);
			i.putExtra("LEVEL", "3"); // 单
			i.putExtra("QUESTION", "7"); // 肈ヘ计
			i.putExtra("TIME", "6"); // –肈丁
			i.putExtra("SCORE", "30"); // 肈眔だ
			MediaPlayer.create(this, R.raw.click_room).start();

			startActivity(i);
			break;
		case R.id.B_L_L4:
			i = new Intent(Challenge.this, Room.class);
			i.putExtra("LEVEL", "4"); // 单
			i.putExtra("QUESTION", "8"); // 肈ヘ计
			i.putExtra("TIME", "3"); // –肈丁
			i.putExtra("SCORE", "35"); // 肈眔だ
			MediaPlayer.create(this, R.raw.click_room).start();

			startActivity(i);
			break;
		case R.id.B_L_L5:
			i = new Intent(Challenge.this, Room.class);
			i.putExtra("LEVEL", "5"); // 单
			i.putExtra("QUESTION", "6"); // 肈ヘ计
			i.putExtra("TIME", "5"); // –肈丁
			i.putExtra("SCORE", "40"); // 肈眔だ
			MediaPlayer.create(this, R.raw.click_room).start();

			startActivity(i);
			break;
		case R.id.B_L_L6:
			i = new Intent(Challenge.this, Room.class);
			i.putExtra("LEVEL", "6"); // 单
			i.putExtra("QUESTION", "10"); // 肈ヘ计
			i.putExtra("TIME", "6"); // –肈丁
			i.putExtra("SCORE", "50"); // 肈眔だ
			MediaPlayer.create(this, R.raw.click_room).start();

			startActivity(i);
			break;
		}

	}
}
