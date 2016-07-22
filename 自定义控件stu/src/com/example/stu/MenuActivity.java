package com.example.stu;

import java.security.PublicKey;
import java.util.ArrayList;









import android.R.color;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnClickListener {
	private EditText mEditText;
	private ImageView mImageView;
	private ArrayList<String> mArrayList;
	private ListView mListView;
	private int mPopupWindowHeight = 300;
	private PopupWindow mPopupWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
		initListener();
	}





	private void initView() {
		setContentView(R.layout.activity_menu);
		mEditText = (EditText) findViewById(R.id.et_show);
		mImageView = (ImageView) findViewById(R.id.iv_down);
	}

	private void initData() {
		mArrayList = new ArrayList<String>();
		for(int i = 0; i<=15;i++){
			mArrayList.add(90000 + i +"");
		}
		mListView = new ListView(this);
		//设置listview的背景
		mListView.setBackgroundResource(color.darker_gray);
		//消除listview的垂直滚动条
		mListView.setVerticalScrollBarEnabled(false);
		MyAdapter adapter = new MyAdapter();
		mListView.setAdapter(adapter);
		
	}

	private void initListener() {
		mImageView.setOnClickListener(this);
		mListView.setOnItemClickListener(new ListViewItemListener());
	}
	@Override
	public void onClick(View v) {
		if(mPopupWindow == null){
			mPopupWindow = new PopupWindow(mListView, mEditText.getWidth(), mPopupWindowHeight);
			/**
			 * PopupWindow默认是不获取焦点的，如果要获取焦点要进行下一行代码设置
			 */
			mPopupWindow.setFocusable(true);
			/**
			 * PopupWindow如果要设置点击外部区域使PopupWindow消失要设置下两行代码（一起设置）
			 */
			mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//			mPopupWindow.setOutsideTouchable(true);
			
		}

		mPopupWindow.showAsDropDown(mEditText, 0, 5);
		
	}
	class ListViewItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mEditText.setText(mArrayList.get(position));
			//获取到Item的内容PopupWindow应该消失
			mPopupWindow.dismiss();
		}
		
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mArrayList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View mView = null;
			ItemHolder itemHolder = null;
			
//			Log.e("TAG", position+"");
			if (convertView == null) {
				/**
				 * 划出屏幕的View会缓存到convertView中，如果convertView为空表示缓存中没有要在屏幕显示的View，此时创建要显示的View对象
				 */
				mView = View.inflate(getApplication(), R.layout.adapter_listview, null);
				
				/**
				 * 获取ListView中item的height为0
				 */
//				Log.e("TAG", mView.getHeight()+"");
//				Log.e("TAGTAG", mView.getMeasuredHeight()+"");
				itemHolder = new ItemHolder();
				itemHolder.mTextView = (TextView) mView.findViewById(R.id.tv_show);
				//ERROR:
				//下一行代码setText将导致ListView显示的内容局限于初始屏幕显示的Item内容
//				mTextView.setText(mArrayList.get(position));
				itemHolder.iv_delete = (ImageView) mView.findViewById(R.id.iv_delete);
				mView.setTag(itemHolder);
				
			}else {
				//如果convertView不为空，表示将要显示的View在缓存中存在，将convertView赋给mView即可
				mView = convertView;
				itemHolder = (ItemHolder) mView.getTag();
			}
//			获取ListView的Item的高度
			//获取ListView中item的height为0的解决方法如下一行代码
			mView.measure(0, 0);
			final int listViewItemHeight = mView.getMeasuredHeight();
//			Log.e("*********", listViewItemHeight+"");
			itemHolder.mTextView.setText(mArrayList.get(position));
			itemHolder.iv_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//删除选中的item
//					Log.e("TAG", mArrayList.get(position));
					mArrayList.remove(position);
					notifyDataSetChanged();
					
					int listViewHeight = mArrayList.size() * listViewItemHeight;
//					Log.e("TAG#", ""+listViewItemHeight);
//					Log.e("TAG", listViewHeight+"");
					mPopupWindow.update(mEditText.getWidth(), listViewHeight > mPopupWindowHeight?mPopupWindowHeight:listViewHeight);
					if(listViewHeight == 0){
						mPopupWindow.dismiss();
						mImageView.setVisibility(View.GONE);
					}
				}
			});
			return mView;
		}
		
	}
	class ItemHolder{
		TextView mTextView;
		ImageView iv_delete;
	}
}
