package com.example.util;

import android.R.integer;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationUtil {
	/*
	 * 
	 */
	public static void closeMenu(RelativeLayout relativeLayout,int startOffset){
		RotateAnimation rotateAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, 
				RotateAnimation.RELATIVE_TO_SELF, 1);
		rotateAnimation.setDuration(200);
		//设置旋转开始偏移时间
		rotateAnimation.setStartOffset(startOffset);
		rotateAnimation.setFillAfter(true);
		relativeLayout.startAnimation(rotateAnimation);
		//使View消失 后不接受点击
		for(int i = 0; i<relativeLayout.getChildCount(); i++){
			relativeLayout.getChildAt(i).setEnabled(false);
		}
	}

	public static void openMenu(RelativeLayout relativeLayout,int startOffset) {
		for(int i = 0; i<relativeLayout.getChildCount(); i++){
			relativeLayout.getChildAt(i).setEnabled(true);
		}
		RotateAnimation rotateAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, 
				RotateAnimation.RELATIVE_TO_SELF, 1);
		rotateAnimation.setDuration(200);
		rotateAnimation.setStartOffset(startOffset);
		rotateAnimation.setFillAfter(true);
		relativeLayout.startAnimation(rotateAnimation);
	}	
}
