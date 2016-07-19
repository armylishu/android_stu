package com.example.util;

import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimationUtil {
	public static void closeMenu(RelativeLayout relativeLayout){
		RotateAnimation rotateAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, 
				RotateAnimation.RELATIVE_TO_SELF, 1);
		rotateAnimation.setDuration(200);
		
		rotateAnimation.setFillAfter(true);
		relativeLayout.startAnimation(rotateAnimation);
	}

	public static void openMenu(RelativeLayout relativeLayout) {
		RotateAnimation rotateAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, 
				RotateAnimation.RELATIVE_TO_SELF, 1);
		rotateAnimation.setDuration(200);
		
		rotateAnimation.setFillAfter(true);
		relativeLayout.startAnimation(rotateAnimation);
	}	
}
