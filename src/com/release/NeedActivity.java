package com.release;

import java.util.ArrayList;
import java.util.List;

import com.example.putianxia.R;
import com.release.SpinerPopWindow.SelectedCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class NeedActivity extends Activity {
	//下拉框对象
		private SpinerPopWindow<String> NSpinerPopWindow1, NSpinerPopWindow2,
		                    NSpinerPopWindow3,NSpinerPopWindow4;
		
		private List<String>nlist1,nlist2,nlist3,nlist4;
		
		private TextView needarea2,needtype2,needaddress2,needaddress3;
		private ImageButton backButton;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_need);
		
		backButton = (ImageButton) findViewById(R.id.need_back);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
		
		initData();
		createSpinerPopWindow();
		initView();
	}
	private void initView() {
		needarea2 = (TextView) findViewById(R.id.needarea2);
		needtype2 = (TextView) findViewById(R.id.needtype2);
		needaddress2 = (TextView) findViewById(R.id.needaddress2);
		needaddress3 = (TextView) findViewById(R.id.needaddress3);
	
		needarea2.setOnClickListener(clickListener);
		needtype2.setOnClickListener(clickListener);
		needaddress2.setOnClickListener(clickListener);
		needaddress3.setOnClickListener(clickListener);
	}
	private void createSpinerPopWindow() {
		NSpinerPopWindow1 = new SpinerPopWindow<String>(this, nlist1);
		NSpinerPopWindow2 = new SpinerPopWindow<String>(this, nlist2);
		NSpinerPopWindow3 = new SpinerPopWindow<String>(this, nlist3);
		NSpinerPopWindow4 = new SpinerPopWindow<String>(this, nlist4);
		
	}
	private void initData() {
		String[] name1 = getResources().getStringArray(R.array.area);
		String[] name2 = getResources().getStringArray(R.array.actualarea);
		String[] name3= getResources().getStringArray(R.array.rent);
		String[] name4= getResources().getStringArray(R.array.staltype);
	
		nlist1 = new ArrayList<String>();
		nlist2 = new ArrayList<String>();
		nlist3 = new ArrayList<String>();
		nlist4 = new ArrayList<String>();
	
		for(int i = 0; i < name1.length; i++){
			nlist1.add(name1[i]);
		}
		for(int i = 0; i < name2.length; i++){
			nlist2.add(name2[i]);
		}
		for(int i = 0; i < name3.length; i++){
			nlist3.add(name3[i]);
		}
		for(int i = 0; i < name4.length; i++){
			nlist4.add(name4[i]);
		}
	}
	
	private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.needarea2:
				NSpinerPopWindow1.setWidth(needarea2.getWidth());
				NSpinerPopWindow1.showAsDropDown(needarea2);
				NSpinerPopWindow1.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					NSpinerPopWindow1.dismiss();
					needarea2.setText(value);
					}
				});
				break;
			case R.id.needtype2:
				NSpinerPopWindow2.setWidth(needtype2.getWidth());
				NSpinerPopWindow2.showAsDropDown(needtype2);
				NSpinerPopWindow2.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					NSpinerPopWindow2.dismiss();
					needtype2.setText(value);
					}
				});
				break;
			case R.id.needaddress2:
				NSpinerPopWindow3.setWidth(needaddress2.getWidth());
				NSpinerPopWindow3.showAsDropDown(needaddress2);
				NSpinerPopWindow3.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					NSpinerPopWindow3.dismiss();
					needaddress2.setText(value);
					}
				});
				break;
			case R.id.needaddress3:
				NSpinerPopWindow4.setWidth(needaddress3.getWidth());
				NSpinerPopWindow4.showAsDropDown(needaddress3);
				NSpinerPopWindow4.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						NSpinerPopWindow4.dismiss();
						needaddress3.setText(value);
					}
				});
				break;
			default:
				break;
			}
		}
	};

}
