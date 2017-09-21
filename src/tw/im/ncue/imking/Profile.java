package tw.im.ncue.imking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends ActionBarActivity implements OnClickListener {
	Storage DATA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		findViewById(R.id.R_back_button).setOnClickListener(this);
		findViewById(R.id.P_name).setOnClickListener(this);

		DATA = new Storage(this);

		TextView myName = (TextView) findViewById(R.id.P_name);
		myName.setText(DATA.getName());

		TextView myScore = (TextView) findViewById(R.id.P_score);
		myScore.setText("TOTAL CREDIT：" + DATA.getCredit());

		TextView myWin = (TextView) findViewById(R.id.P_win);
		myWin.setText(DATA.getWin());

		TextView myLose = (TextView) findViewById(R.id.P_lose);
		myLose.setText(DATA.getLose());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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

		switch (v.getId()) {
		case R.id.R_back_button: // Back to main activity
			MediaPlayer mediaPlayer;
			mediaPlayer = MediaPlayer.create(this, R.raw.click_button);
			mediaPlayer.start();
			finish();
			break;
		case R.id.P_name: // Change name setting
			LayoutInflater inflater = LayoutInflater.from(Profile.this);
			final View v1 = inflater.inflate(R.layout.edit_diagram, null);

			new AlertDialog.Builder(Profile.this)
					.setTitle("請輸入你的名字")
					.setView(v1)
					.setPositiveButton("確定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {

									EditText editText = (EditText) (v1
											.findViewById(R.id.edittext));
									Toast.makeText(
											getApplicationContext(),
											"你叫做"
													+ editText.getText()
															.toString(),
											Toast.LENGTH_SHORT).show();
									TextView myName = (TextView) findViewById(R.id.P_name);
									myName.setText(editText.getText()
											.toString());
									DATA.setName(editText.getText().toString());
								}
							}).show();

			break;
		}
	}
}
