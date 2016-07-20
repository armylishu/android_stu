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
	private boolean level2_flag = true,level3_flag = true;
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
		//在布局上setlistener无效果？
		//有效果，只是布局上的其他组件会覆盖一定的区域，必须点区域外才有效果
//		rel_level1.setOnClickListener(this);
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
			int startOffset = 0;
			if (level2_flag) {
				//如果第三层View在
				if (level3_flag) {
					//第三层View消失
					AnimationUtil.closeMenu(rel_level3, startOffset);
					level3_flag = !level3_flag;
					startOffset = 200;
					//200ms后第二层View消失
//					AnimationUtil.closeMenu(rel_level2,startOffset);
				}
//				不管第三层在不在，最后也是执行第二层消失
					AnimationUtil.closeMenu(rel_level2, startOffset);
				
				
			}else {//如果第二层View不在，则必定第三层也不在，不能出现在第三层View在而第二层View不在的情况
				//如果第三层View也不在
//				if (!level3_flag) {
					AnimationUtil.openMenu(rel_level2,startOffset);
					
					startOffset = 200;
					AnimationUtil.openMenu(rel_level3, startOffset);
					level3_flag = !level3_flag;
//				}
				
			}
			level2_flag = !level2_flag;
			break;
		//点击level2,level3旋转
		case R.id.iv_menu:
			if (level3_flag) {
				AnimationUtil.closeMenu(rel_level3,0);
			}else {
				AnimationUtil.openMenu(rel_level3,0);
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
