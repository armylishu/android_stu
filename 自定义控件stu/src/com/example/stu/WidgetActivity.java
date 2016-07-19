package com.example.stu;

import com.example.util.AnimationUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class WidgetActivity extends Activity implements OnClickListener{
	private RelativeLayout rel_level1,rel_level2,rel_level3;
	private ImageView iv_home,iv_menu;
	private boolean level1_flag = true;
	private boolean level2_flag,level3_flag = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initListener();
	}


	private void initView() {
		setContentView(R.layout.activity_widget);
		rel_level1 = (RelativeLayout) findViewById(R.id.level1);
		rel_level2 = (RelativeLayout) findViewById(R.id.level2);
		rel_level3 = (RelativeLayout) findViewById(R.id.level3);
		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_menu = (ImageView) findViewById(R.id.iv_menu);
		
	}
	private void initListener() {
		iv_home.setOnClickListener(this);
		iv_menu.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.widget, menu);
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
		switch (v.getId()) {
		//点击level1,level2旋转
		case R.id.iv_home:
			if (level2_flag) {
				AnimationUtil.closeMenu(rel_level2);
			}else {
				AnimationUtil.openMenu(rel_level2);
			}
			level2_flag = !level2_flag;
			break;
		//点击level2,level3旋转
		case R.id.iv_menu:
			if (level3_flag) {
				AnimationUtil.closeMenu(rel_level3);
			}else {
				AnimationUtil.openMenu(rel_level3);
			}
			level3_flag = !level3_flag;
			break;
//		case R.id.level3:
//			if (level3_flag) {
//				AnimationUtil.closeMenu(rel_level3);
//			}else {
//				AnimationUtil.openMenu(rel_level3);
//			}
//			level3_flag = !level3_flag;
//			break;
		}
	}
}
