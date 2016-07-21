package com.example.stu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button to_groupwidget,to_downmenu;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}


	private void initView() {
		setContentView(R.layout.activity_main);
		to_groupwidget = (Button) findViewById(R.id.to_groupwidget);
		to_downmenu = (Button)findViewById(R.id.to_downmenu);
	}
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		Log.e("TAG", keyCode+"");
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_HOME:
//			Log.e("TAG", "Home 键被按下");
//			break;
//
//		case KeyEvent.KEYCODE_MENU:
//			Log.e("TAG", "Menu 键被按下");
//			break;
//		}
//		return super.onKeyDown(keyCode, event);
//	}

	private void initData() {
		to_groupwidget.setOnClickListener(this);
		to_downmenu.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.to_groupwidget:
			intent = new Intent(this, WidgetActivity.class);
			startActivity(intent);
			break;

		case R.id.to_downmenu:
			intent = new Intent(this, MenuActivity.class);
			startActivity(intent);
			break;
		}
	}


}
