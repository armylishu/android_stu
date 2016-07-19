package com.example.stu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	private Button to_groupwidget;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}


	private void initView() {
		setContentView(R.layout.activity_main);
		to_groupwidget = (Button) findViewById(R.id.to_groupwidget);
	}

	private void initData() {
		to_groupwidget.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.to_groupwidget:
			Intent intent = new Intent(this, WidgetActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}


}
