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
		//����listview�ı���
		mListView.setBackgroundResource(color.darker_gray);
		//����listview�Ĵ�ֱ������
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
			 * PopupWindowĬ���ǲ���ȡ����ģ����Ҫ��ȡ����Ҫ������һ�д�������
			 */
			mPopupWindow.setFocusable(true);
			/**
			 * PopupWindow���Ҫ���õ���ⲿ����ʹPopupWindow��ʧҪ���������д��루һ�����ã�
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
			//��ȡ��Item������PopupWindowӦ����ʧ
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
				 * ������Ļ��View�Ỻ�浽convertView�У����convertViewΪ�ձ�ʾ������û��Ҫ����Ļ��ʾ��View����ʱ����Ҫ��ʾ��View����
				 */
				mView = View.inflate(getApplication(), R.layout.adapter_listview, null);
				
				/**
				 * ��ȡListView��item��heightΪ0
				 */
//				Log.e("TAG", mView.getHeight()+"");
//				Log.e("TAGTAG", mView.getMeasuredHeight()+"");
				itemHolder = new ItemHolder();
				itemHolder.mTextView = (TextView) mView.findViewById(R.id.tv_show);
				//ERROR:
				//��һ�д���setText������ListView��ʾ�����ݾ����ڳ�ʼ��Ļ��ʾ��Item����
//				mTextView.setText(mArrayList.get(position));
				itemHolder.iv_delete = (ImageView) mView.findViewById(R.id.iv_delete);
				mView.setTag(itemHolder);
				
			}else {
				//���convertView��Ϊ�գ���ʾ��Ҫ��ʾ��View�ڻ����д��ڣ���convertView����mView����
				mView = convertView;
				itemHolder = (ItemHolder) mView.getTag();
			}
//			��ȡListView��Item�ĸ߶�
			//��ȡListView��item��heightΪ0�Ľ����������һ�д���
			mView.measure(0, 0);
			final int listViewItemHeight = mView.getMeasuredHeight();
//			Log.e("*********", listViewItemHeight+"");
			itemHolder.mTextView.setText(mArrayList.get(position));
			itemHolder.iv_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//ɾ��ѡ�е�item
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
