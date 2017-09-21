package tw.im.ncue.imking;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;

public class Storage extends ActionBarActivity {
	Context context;

	public Storage(Context context) {
		this.context = context;
	}

	public String getName() {
		// Ū���]�w�ɸ�T�A�ݱa�JContext�B�]�w�ɦW�١B���Key�B�w�]�ȡA�w�]�Ȭ��Y�S�����]�w�ɫh�^�Ǧ��w�]��r
		String GET = getConfig(context, "Config", "MyName", "Null");
		return GET;
	}

	public void setName(String name) {
		// �g�J��Ʀܳ]�w�ɡA�ݱa�JContext�B�]�w�ɦW�١B���Key�B���e
		String SET = name;
		setConfig(context, "Config", "MyName", SET);

	}

	public String getCredit() {
		String GET = getConfig(context, "Config", "MyCredit", "0");
		return GET;
	}

	public void addCredit(Integer credit) {
		Integer SET = Integer.parseInt(getConfig(context, "Config", "MyCredit",
				"0")) + credit;
		setConfig(context, "Config", "MyCredit", SET.toString());
	}

	public void reduceCredit(Integer credit) {
		Integer SET;
		if (Integer.parseInt(getConfig(context, "Config", "MyCredit", "0"))
				- credit > 0) {
			SET = Integer
					.parseInt(getConfig(context, "Config", "MyCredit", "0"))
					- credit;
		} else {
			SET = 0;
		}
		setConfig(context, "Config", "MyCredit", SET.toString());
	}

	public String getWin() {
		String GET = getConfig(context, "Config", "MyWin", "0");
		return GET;
	}

	public void addWin() {
		Integer SET = Integer.parseInt(getConfig(context, "Config", "MyWin",
				"0")) + 1;
		setConfig(context, "Config", "MyWin", SET.toString());
	}

	public String getLose() {
		String GET = getConfig(context, "Config", "MyLose", "0");
		return GET;
	}

	public void addLose() {
		Integer SET = Integer.parseInt(getConfig(context, "Config", "MyLose",
				"0")) + 1;
		setConfig(context, "Config", "MyLose", SET.toString());
	}

	public String getProfessor(int number) {
		String myString = "";
		switch (number) {
		case 1:
			myString = "�p�«�";
			break;
		case 2:
			myString = "���S";
			break;
		case 3:
			myString = "��shine";
			break;
		case 4:
			myString = "inWei";
			break;
		case 5:
			myString = "�˩[Chiang";
			break;
		case 6:
			myString = "SlideMountain";
			break;
		}
		return myString;
	}

	// �]�w���x�s
	public static void setConfig(Context context, String name, String key,
			String value) {
		SharedPreferences settings = context.getSharedPreferences(name, 0);
		SharedPreferences.Editor PE = settings.edit();
		PE.putString(key, value);
		PE.commit();
	}

	// �]�w��Ū��
	public static String getConfig(Context context, String name, String key,
			String def) {
		SharedPreferences settings = context.getSharedPreferences(name, 0);
		return settings.getString(key, def);
	}

}
