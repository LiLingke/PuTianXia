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

public class HouseActivity extends Activity {
	
	private SpinerPopWindow< String> HSpinerPopWindow1,HSpinerPopWindow2,HSpinerPopWindow3,
																		HSpinerPopWindow4,HSpinerPopWindow5,HSpinerPopWindow6,
																		HSpinerPopWindow7,HSpinerPopWindow8,HSpinerPopWindow9,
																		HSpinerPopWindow10;
	
	private List<String> hlist1,hlist2,hlist3,hlist4,hlist5,hlist6,hlist7,hlist8,hlist9,hlist10;
	
	private TextView housearea2,housetype2,houseprice2,housedecorate2,housetime2,
	                                 housefloor2,housefloor3,housecommunity2,houseaddress2,houseaddress3;
	private ImageButton backButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house);
		backButton = (ImageButton) findViewById(R.id.house_back);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
		initData();
		createSpinerPopWindow();
		initView();

	}
	
	//创建SpinerPopWindow对象
		private void createSpinerPopWindow() {
			HSpinerPopWindow1 = new SpinerPopWindow<String>(this, hlist1);
			HSpinerPopWindow2 = new SpinerPopWindow<String>(this, hlist2);
			HSpinerPopWindow3 = new SpinerPopWindow<String>(this, hlist3);
			HSpinerPopWindow4 = new SpinerPopWindow<String>(this, hlist4);
			HSpinerPopWindow5 = new SpinerPopWindow<String>(this,hlist5);
			HSpinerPopWindow6 = new SpinerPopWindow<String>(this, hlist6);
			HSpinerPopWindow7 = new SpinerPopWindow<String>(this, hlist7);
			HSpinerPopWindow8 = new SpinerPopWindow<String>(this, hlist8);
			HSpinerPopWindow9 = new SpinerPopWindow<String>(this, hlist9);
			HSpinerPopWindow10 = new SpinerPopWindow<String>(this, hlist10);
		}
	     
		//实例化各个Textview并设置监听
		private void initView() {
		
			//pyedit = (TextView) findViewById(R.id.pyedit);
			housearea2 = (TextView) findViewById(R.id.housearea2);
			housetype2 = (TextView) findViewById(R.id.housetype2);
			houseprice2 = (TextView) findViewById(R.id.houseprice2);
			housedecorate2 = (TextView) findViewById(R.id.housedecorate2);
			housetime2 = (TextView) findViewById(R.id.housetime2);
			housefloor2 = (TextView) findViewById(R.id.housefloor2);
			housefloor3 = (TextView) findViewById(R.id.housefloor3);
			housecommunity2 = (TextView) findViewById(R.id.housecommunity2);
			houseaddress2 = (TextView) findViewById(R.id.houseaddress2);
			houseaddress3 = (TextView) findViewById(R.id.houseaddress3);
		//	pylabel1 = (TextView) findViewById(R.id.pylabel1);
		//	pydescribe2 = (TextView) findViewById(R.id.pydescribe2);
			housearea2.setOnClickListener(clickListener);
			housetype2.setOnClickListener(clickListener);
			houseprice2.setOnClickListener(clickListener);
			housedecorate2.setOnClickListener(clickListener);
			housetime2.setOnClickListener(clickListener);
			housefloor2.setOnClickListener(clickListener);
			housefloor3.setOnClickListener(clickListener);
			housecommunity2.setOnClickListener(clickListener);
			houseaddress2.setOnClickListener(clickListener);
			houseaddress3.setOnClickListener(clickListener);
		}

	private void initData() {
		String[] name1 = getResources().getStringArray(R.array.area);
		String[] name2 = getResources().getStringArray(R.array.actualarea);
		String[] name3= getResources().getStringArray(R.array.rent);
		String[] name4= getResources().getStringArray(R.array.staltype);
		String[] name5 = getResources().getStringArray(R.array.condition);
		String[] name6 = getResources().getStringArray(R.array.floor1);
		String[] name7 = getResources().getStringArray(R.array.floor2);
		String[] name8 = getResources().getStringArray(R.array.business);
		String[] name9 = getResources().getStringArray(R.array.address1);
		String[] name10 = getResources().getStringArray(R.array.address2);
		hlist1 = new ArrayList<String>();
		hlist2 = new ArrayList<String>();
		hlist3 = new ArrayList<String>();
		hlist4 = new ArrayList<String>();
		hlist5 = new ArrayList<String>();
		hlist6 = new ArrayList<String>();
		hlist7 = new ArrayList<String>();
		hlist8 = new ArrayList<String>();
		hlist9 = new ArrayList<String>();
		hlist10 = new ArrayList<String>();
		for(int i = 0; i < name1.length; i++){
			hlist1.add(name1[i]);
		}
		for(int i = 0; i < name2.length; i++){
			hlist2.add(name2[i]);
		}
		for(int i = 0; i < name3.length; i++){
			hlist3.add(name3[i]);
		}
		for(int i = 0; i < name4.length; i++){
			hlist4.add(name4[i]);
		}
		for(int i = 0; i < name5.length; i++){
			hlist5.add(name5[i]);
		}
		for(int i = 0; i < name6.length; i++){
			hlist6.add(name6[i]);
		}
		for(int i = 0; i < name7.length; i++){
			hlist7.add(name7[i]);
		}
		for(int i = 0; i < name8.length; i++){
			hlist8.add(name8[i]);
		}
		for(int i = 0; i < name9.length; i++){
			hlist9.add(name9[i]);
		}
		for(int i = 0; i < name10.length; i++){
			hlist10.add(name10[i]);
		}
	}
	
private OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.housearea2:
				HSpinerPopWindow1.setWidth(housearea2.getWidth());
				HSpinerPopWindow1.showAsDropDown(housearea2);
				HSpinerPopWindow1.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow1.dismiss();
						housearea2.setText(value);
					}
				});
				break;
		
			case R.id.housetype2:
				HSpinerPopWindow2.setWidth(housetype2.getWidth());
				HSpinerPopWindow2.showAsDropDown(housetype2);
			HSpinerPopWindow2.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow2.dismiss();
						housetype2.setText(value);
					}
				});
				break;
			case R.id.houseprice2:
				HSpinerPopWindow3.setWidth(houseprice2.getWidth());
				HSpinerPopWindow3.showAsDropDown(houseprice2);
				HSpinerPopWindow3.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow3.dismiss();
						houseprice2.setText(value);
					}
				});
				break;
			case R.id.housedecorate2:
				HSpinerPopWindow4.setWidth(housedecorate2.getWidth());
				HSpinerPopWindow4.showAsDropDown(housedecorate2);
				HSpinerPopWindow4.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
					HSpinerPopWindow4.dismiss();
					housedecorate2.setText(value);
					}
				});
				break;
			case R.id.housetime2:
				HSpinerPopWindow5.setWidth(housetime2.getWidth());
				HSpinerPopWindow5.showAsDropDown(housetime2);
				HSpinerPopWindow5.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow5.dismiss();
						housetime2.setText(value);
					}
				});
				break;
			case R.id.housefloor2:
				HSpinerPopWindow6.setWidth(housefloor2.getWidth());
				HSpinerPopWindow6.showAsDropDown(housefloor2);
				HSpinerPopWindow6.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow6.dismiss();
						housefloor2.setText(value);
					}
				});
				break;
			case R.id.housefloor3:
				HSpinerPopWindow7.setWidth(housefloor3.getWidth());
				HSpinerPopWindow7.showAsDropDown(housefloor3);
				HSpinerPopWindow7.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow7.dismiss();
						housefloor3.setText(value);
					}
				});
				break;
			case R.id.housecommunity2:
				HSpinerPopWindow8.setWidth(housecommunity2.getWidth());
				HSpinerPopWindow8.showAsDropDown(housecommunity2);
				HSpinerPopWindow8.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow8.dismiss();
						housecommunity2.setText(value);
					}
				});
				break;
			case R.id.houseaddress2:
				HSpinerPopWindow9.setWidth(houseaddress2.getWidth());
				HSpinerPopWindow9.showAsDropDown(houseaddress2);
				HSpinerPopWindow9.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow9.dismiss();
						houseaddress2.setText(value);
					}
				});
				break;
			case R.id.houseaddress3:
				HSpinerPopWindow10.setWidth(houseaddress3.getWidth());
				HSpinerPopWindow10.showAsDropDown(houseaddress3);
				HSpinerPopWindow10.setSelectedCallback(new SelectedCallback<String>(){
					@Override
					public void onSelect(String value) {
						HSpinerPopWindow10.dismiss();
						houseaddress3.setText(value);
					}
				});
				break;
			default:
				break;
			}
		}
	};

}
