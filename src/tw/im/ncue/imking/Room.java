package tw.im.ncue.imking;

import java.util.Random;

import android.R.bool;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Room extends ActionBarActivity implements OnClickListener {
	Storage DATA;
	ItemDAO DB;
	TextView gameTime, R_question, R_myScore, R_proScore;
	Button R_ans1, R_ans2, R_ans3, R_ans4;
	Button R_myAns1, R_myAns2, R_myAns3, R_myAns4, R_proAns1, R_proAns2,
			R_proAns3, R_proAns4;
	int TIME; // Storage current time here
	int countQuestion = 0;
	int TOTAL_QUESTION;
	int TOTAL_TIME;
	int TOTAL_SCORE;
	private MyCount mc;

	final Context context = this;

	String Level;

	QuestionItem myItem; // Save The Question in this object

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room);

		findViewById(R.id.R_back_button).setOnClickListener(this);

		TextView myName = (TextView) findViewById(R.id.R_name);
		TextView professorName = (TextView) findViewById(R.id.R_professor);
		gameTime = (TextView) findViewById(R.id.R_time);
		R_question = (TextView) findViewById(R.id.R_question);
		R_myScore = (TextView) findViewById(R.id.R_myScore);
		R_proScore = (TextView) findViewById(R.id.R_proScore);
		R_ans1 = (Button) findViewById(R.id.R_ans1);
		R_ans2 = (Button) findViewById(R.id.R_ans2);
		R_ans3 = (Button) findViewById(R.id.R_ans3);
		R_ans4 = (Button) findViewById(R.id.R_ans4);
		R_myAns1 = (Button) findViewById(R.id.R_myAns1);
		R_myAns2 = (Button) findViewById(R.id.R_myAns2);
		R_myAns3 = (Button) findViewById(R.id.R_myAns3);
		R_myAns4 = (Button) findViewById(R.id.R_myAns4);
		R_proAns1 = (Button) findViewById(R.id.R_proAns1);
		R_proAns2 = (Button) findViewById(R.id.R_proAns2);
		R_proAns3 = (Button) findViewById(R.id.R_proAns3);
		R_proAns4 = (Button) findViewById(R.id.R_proAns4);

		R_ans1.setOnClickListener(this);
		R_ans2.setOnClickListener(this);
		R_ans3.setOnClickListener(this);
		R_ans4.setOnClickListener(this);

		// GET LEVEL DATA
		Intent it = getIntent();
		Level = it.getStringExtra("LEVEL");
		TOTAL_QUESTION = Integer.parseInt(it.getStringExtra("QUESTION"));
		TOTAL_TIME = Integer.parseInt(it.getStringExtra("TIME"));
		TOTAL_SCORE = Integer.parseInt(it.getStringExtra("SCORE"));

		DATA = new Storage(this);
		DB = new ItemDAO(getApplicationContext());

		if (DB.getCount() == 0) {
			DB.initialize();
		}

		myName.setText(DATA.getName());
		professorName.setText(DATA.getProfessor(Integer.parseInt(Level)));

		// Log.v("Tony", Integer.toString(DB.getCount()));

		mc = new MyCount(TOTAL_TIME * 1000, 1000);
		mc.start();

	}

	class MyCount extends CountDownTimer {

		public MyCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
			timerStart();
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// TODO Auto-generated method stub
			gameTime.setText(millisUntilFinished / 1000 + "");
			TIME = (int) (millisUntilFinished / 1000);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			gameTime.setText("0");
			if (timerStart()) {
				this.cancel();
				this.start();
			} else {
				MediaPlayer.create(context, R.raw.bgm_end).start();
				this.cancel();
				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.my_dialog);
				dialog.setTitle("IM King");

				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.DI_text);
				text.setText(compareScoure());

				Button dialogButton = (Button) dialog.findViewById(R.id.DI_btn);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						finish();
						if (Integer.parseInt(R_myScore.getText().toString()) > Integer
								.parseInt(R_proScore.getText().toString())) {
							DATA.addWin();
							DATA.addCredit(TOTAL_SCORE);
						} else if (Integer.parseInt(R_myScore.getText()
								.toString()) < Integer.parseInt(R_proScore
								.getText().toString())) {
							DATA.addLose();
							DATA.reduceCredit(TOTAL_SCORE);
						}
					}
				});

				dialog.show();
			}

		}
	}

	public String compareScoure() {
		if (Integer.parseInt(R_myScore.getText().toString()) > Integer
				.parseInt(R_proScore.getText().toString())) {
			return "勝利!!";
		} else if (Integer.parseInt(R_myScore.getText().toString()) < Integer
				.parseInt(R_proScore.getText().toString())) {
			return "失敗!!";
		} else {
			return "平手!!";
		}

	}

	public boolean timerStart() {

		if (countQuestion == TOTAL_QUESTION) {
			return false;
		} else {

			R_ans1.setEnabled(true);
			R_ans2.setEnabled(true);
			R_ans3.setEnabled(true);
			R_ans4.setEnabled(true);

			R_ans1.setBackgroundResource(R.drawable.my_button);
			R_ans2.setBackgroundResource(R.drawable.my_button);
			R_ans3.setBackgroundResource(R.drawable.my_button);
			R_ans4.setBackgroundResource(R.drawable.my_button);

			R_myAns1.setVisibility(View.INVISIBLE);
			R_myAns2.setVisibility(View.INVISIBLE);
			R_myAns3.setVisibility(View.INVISIBLE);
			R_myAns4.setVisibility(View.INVISIBLE);
			R_proAns1.setVisibility(View.INVISIBLE);
			R_proAns2.setVisibility(View.INVISIBLE);
			R_proAns3.setVisibility(View.INVISIBLE);
			R_proAns4.setVisibility(View.INVISIBLE);

			Random r = new Random();
			int myRandom = r.nextInt(DB.getCount()) + 1;

			countQuestion++;
			myItem = DB.getData(myRandom);
			setQuestion();
			return true;
		}

	}

	public void setQuestion() {
		R_question.setText(myItem.question);
		R_ans1.setText(myItem.option1);
		R_ans2.setText(myItem.option2);
		R_ans3.setText(myItem.option3);
		R_ans4.setText(myItem.option4);
	}

	public void userAnsClick(int v) {
		String rightColor = "#88a4ec";
		String wrongColor = "#f48c8c";
		if (v == R.id.R_ans1 && myItem.answer.equals("A")) {
			R_ans1.setBackgroundColor(Color.parseColor(rightColor));
			R_myAns1.setText("O");
			R_myAns1.setVisibility(View.VISIBLE);
			addMyScore();
		} else if (v == R.id.R_ans2 && myItem.answer.equals("B")) {
			R_ans2.setBackgroundColor(Color.parseColor(rightColor));
			R_myAns2.setText("O");
			R_myAns2.setVisibility(View.VISIBLE);
			addMyScore();
		} else if (v == R.id.R_ans3 && myItem.answer.equals("C")) {
			R_ans3.setBackgroundColor(Color.parseColor(rightColor));
			R_myAns3.setText("O");
			R_myAns3.setVisibility(View.VISIBLE);
			addMyScore();
		} else if (v == R.id.R_ans4 && myItem.answer.equals("D")) {
			R_ans1.setBackgroundColor(Color.parseColor(rightColor));
			R_myAns4.setText("O");
			R_myAns4.setVisibility(View.VISIBLE);
			addMyScore();
		} else {
			if (v == R.id.R_ans1) {
				R_ans1.setBackgroundColor(Color.parseColor(wrongColor));
				R_myAns1.setText("X");
				R_myAns1.setVisibility(View.VISIBLE);
			} else if (v == R.id.R_ans2) {
				R_ans2.setBackgroundColor(Color.parseColor(wrongColor));
				R_myAns2.setText("X");
				R_myAns2.setVisibility(View.VISIBLE);
			} else if (v == R.id.R_ans3) {
				R_ans3.setBackgroundColor(Color.parseColor(wrongColor));
				R_myAns3.setText("X");
				R_myAns3.setVisibility(View.VISIBLE);
			} else if (v == R.id.R_ans4) {
				R_ans4.setBackgroundColor(Color.parseColor(wrongColor));
				R_myAns4.setText("X");
				R_myAns4.setVisibility(View.VISIBLE);
			}
		}
		proAnsver();
		// Then set button enable to flase
		R_ans1.setEnabled(false);
		R_ans2.setEnabled(false);
		R_ans3.setEnabled(false);
		R_ans4.setEnabled(false);

	}

	public void proAnsver() {
		Random r = new Random();
		int myRandom = r.nextInt(4) + 1;
		if (myItem.answer.equals("A") && myRandom == 1) {
			R_proAns1.setText("O");
			R_proAns1.setVisibility(View.VISIBLE);
			addProScore();
		} else if (myItem.answer.equals("B") && myRandom == 2) {
			R_proAns2.setText("O");
			R_proAns2.setVisibility(View.VISIBLE);
			addProScore();
		} else if (myItem.answer.equals("C") && myRandom == 3) {
			R_proAns3.setText("O");
			R_proAns3.setVisibility(View.VISIBLE);
			addProScore();
		} else if (myItem.answer.equals("D") && myRandom == 4) {
			R_proAns4.setText("O");
			R_proAns4.setVisibility(View.VISIBLE);
			addProScore();
		} else {
			MediaPlayer.create(this, R.raw.click_wrong).start();
			switch (myRandom) {
			case 1:
				R_proAns1.setText("X");
				R_proAns1.setVisibility(View.VISIBLE);
				break;
			case 2:
				R_proAns2.setText("X");
				R_proAns2.setVisibility(View.VISIBLE);
				break;
			case 3:
				R_proAns3.setText("X");
				R_proAns3.setVisibility(View.VISIBLE);
				break;
			case 4:
				R_proAns4.setText("X");
				R_proAns4.setVisibility(View.VISIBLE);
				break;
			}
		}
	}

	public void addMyScore() {
		int temp = 0;
		temp = Integer.parseInt(R_myScore.getText().toString()) + TIME * 10;
		R_myScore.setText(String.valueOf(temp));
		MediaPlayer.create(this, R.raw.click_right).start();
	}

	public void addProScore() {
		int temp = 0;
		temp = Integer.parseInt(R_proScore.getText().toString()) + TIME * 10;
		R_proScore.setText(String.valueOf(temp));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.room, menu);
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
			MediaPlayer.create(this, R.raw.click_button).start();
			finish();
			break;
		case R.id.R_ans1:
			userAnsClick(v.getId());
			break;
		case R.id.R_ans2:
			userAnsClick(v.getId());
			break;
		case R.id.R_ans3:
			userAnsClick(v.getId());
			break;
		case R.id.R_ans4:
			userAnsClick(v.getId());
			break;
		}

	}
}
