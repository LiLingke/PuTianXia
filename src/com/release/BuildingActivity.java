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

public class BuildingActivity extends Activity {
	//下拉框对象
	private SpinerPopWindow<String> BSpinerPopWindow1, BSpinerPopWindow2,
	                    BSpinerPopWindow3,BSpinerPopWindow4,BSpinerPopWindow5,
	                    BSpinerPopWindow6;
	
	private List<String>blist1,blist2,blist3,blist4,blist5,blist6;
	
	private TextView lpjiage2,lpkptime2,lpjftime2,lptype2,lpaddress2,lpaddress3;
	private ImageButton backButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_building);
		
		backButton = (ImageButton) findViewById(R.id.building_back);
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
		lpjiage2 = (TextView) findViewById(R.id.lpjiage2);
		lpkptime2 = (TextView) findViewById(R.id.lpkptime2);
		lpjftime2 = (TextView) findViewById(R.id.lpjftime2);
		lptype2 = (TextView) findViewById(R.id.lptype2);
		lpaddress2 = (TextView) findViewById(R.id.lpaddress2);
		lpaddress3 = (TextView) findViewById(R.id.lpaddress3);
		
		lpjiage2.setOnClickListener(clickListener);
		lpkptime2.setOnClickListener(clickListener);
		lpjftime2.setOnClickListener(clickListener);
		lptype2.setOnClickListener(clickListener);
		lpaddress2.setOnClickListener(clickListener);
		lpaddress3.setOnClickListener(clickListener);
	}
	private void createSpinerPopWindow() {
		BSpinerPopWindow1 = new SpinerPopWindow<String>(this, blist1);
		BSpinerPopWindow2 = new SpinerPopWindow<String>(this, blist2);
		BSpinerPopWindow3 = new SpinerPopWindow<String>(this, blist3);
		BSpinerPopWindow4 = new SpinerPopWindow<String>(this, blist4);
		BSpinerPopWindow5 = new SpinerPopWindow<String>(this, blist5);
		BSpinerPopWindow6 = new SpinerPopWindow<String>(this, blist6);
		
	}
	private void initData() {
		String[] name1 = getResources().getStringArray(R.array.area);
		String[] name2 = getResources().getStringArray(R.array.actualarea);
		String[] name3= getResources().getStringArray(R.array.rent);
		String[] name4= getResources().getStringArray(R.array.staltype);
		String[] name5 = getResources().getStringArray(R.array.condition);
		String[] name6 = getResources().getStringArray(R.array.floor1);
	
		blist1 = new ArrayList<String>();
		blist2 = new ArrayList<String>();
		blist3 = new ArrayList<String>();
		blist4 = new ArrayList<String>();
		blist5 = new ArrayList<String>();
		blist6 = new ArrayList<String>();
	
		for(int i = 0; i < name1.length; i++){
			blist1.add(name1[i]);
		}
		for(int i = 0; i < name2.length; i++){
			blist2.add(name2[i]);
		}
		for(int i = 0; i < name3.length; i++){
			blist3.add(name3[i]);
		}
		for(int i = 0; i < name4.length; i++){
			blist4.add(name4[i]);
		}
		for(int i = 0; i < name5.length; i++){
			blist5.add(name5[i]);
		}
		for(int i = 0; i < name6.length; i++){
			blist6.add(name6[i]);
		}
	}
	
	private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.lpjiage2:
				BSpinerPopWindow1.setWidth(lpjiage2.getWidth());
				BSpinerPopWindow1.showAsDropDown(lpjiage2);
				BSpinerPopWindow1.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					BSpinerPopWindow1.dismiss();
					lpjiage2.setText(value);
					}
				});
				break;
			case R.id.lpkptime2:
				BSpinerPopWindow2.setWidth(lpkptime2.getWidth());
				BSpinerPopWindow2.showAsDropDown(lpkptime2);
				BSpinerPopWindow2.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					BSpinerPopWindow2.dismiss();
					lpkptime2.setText(value);
					}
				});
				break;
			case R.id.lpjftime2:
				BSpinerPopWindow3.setWidth(lpjftime2.getWidth());
				BSpinerPopWindow3.showAsDropDown(lpjftime2);
				BSpinerPopWindow3.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					BSpinerPopWindow3.dismiss();
					lpjftime2.setText(value);
					}
				});
				break;
			case R.id.lptype2:
				BSpinerPopWindow4.setWidth(lptype2.getWidth());
				BSpinerPopWindow4.showAsDropDown(lptype2);
				BSpinerPopWindow4.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						BSpinerPopWindow4.dismiss();
						lptype2.setText(value);
					}
				});
				break;
			case R.id.lpaddress2:
				BSpinerPopWindow5.setWidth(lpaddress2.getWidth());
				BSpinerPopWindow5.showAsDropDown(lpaddress2);
				BSpinerPopWindow5.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						BSpinerPopWindow5.dismiss();
						lpaddress2.setText(value);
					}
				});
				break;
			case R.id.lpaddress3:
				BSpinerPopWindow6.setWidth(lpaddress3.getWidth());
				BSpinerPopWindow6.showAsDropDown(lpaddress3);
				BSpinerPopWindow6.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						BSpinerPopWindow6.dismiss();
						lpaddress3.setText(value);
					}
				});
				break;
			default:
				break;
			}
		}
	};

}
