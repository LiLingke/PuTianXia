package com.putianxia.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.example.putianxia.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.putianxia.bin.XiangQing;
import com.putianxia.view.XListView;
import com.putianxia.view.XListView.IXListViewListener;

import icss.android.adapter.IcssAdapter;
import icss.android.network.http.NetworkActivity;
import icss.android.network.http.VolleyHttp;
import icss.android.network.linstener.JsonLinstener;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListviewActivity extends NetworkActivity implements IXListViewListener{
	ImageButton back;
	TextView title;
	Handler handler=new Handler();
	String url;
	XListView listview;
	IcssAdapter<XiangQing> adapter;
	List<XiangQing> list=new ArrayList<XiangQing>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.tongyong_listview);
		back=(ImageButton) findViewById(R.id.back);
		
		listview=(XListView) findViewById(R.id.listview_ty);
		title=(TextView) findViewById(R.id.title);
		List<String>liststring=new ArrayList<String>();
		liststring.add("近地铁");
		liststring.add("购物方便");
		liststring.add("金融中心");
		list.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房", "一室一厅", "1500/月", "南山区", liststring));
		list.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房", "一室一厅", "1500/月", "南山区", liststring));
		list.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房", "一室一厅", "1500/月", "南山区", liststring));
		list.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房", "一室一厅", "1500/月", "南山区", liststring));
		list.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房", "一室一厅", "1500/月", "南山区", liststring));
		list.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房", "一室一厅", "1500/月", "南山区", liststring));
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		Intent intent=getIntent();
		title.setText(intent.getStringExtra("title"));
		url=intent.getStringExtra("url");
		//setHttp();
		setadapter();
	}
	
	private void setHttp() {
		// TODO Auto-generated method stub
		Map<String, String>map=new HashMap<String, String>();
//		map.put(arg0, arg1);
		voleyhttp.HttpPostJsonObject(url, new JsonLinstener() {
			
			@Override
			public void onresponse(JSONObject jsonobject) {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						setadapter();
					}
				});
			}
			
			@Override
			public void onerrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				
			}
		}, map);
	}
	void setadapter(){
		if(adapter==null){
		adapter=new IcssAdapter<XiangQing>(this,list,R.layout.xiangqing) {
			
			@Override
			public void getview(int position) {
				// TODO Auto-generated method stub
				viewholder.setText(R.id.xiangqing_name, list.get(position).getName())
				.setText(R.id.xiangqing_weizhi, list.get(position).getWeizhi())
				.setText(R.id.xiangqing_left, list.get(position).getLeftdecp())
				.setText(R.id.xiangqing_right, list.get(position).getRightdecp());
				ImageView imageview=viewholder.getView(R.id.xiangqing_image);
				ImageLoader.getInstance().displayImage(list.get(position).getImageurl(), imageview);
			}
		};
		listview.setAdapter(adapter);
		listview.setXListViewListener(this);
		}
		else
		{
			adapter.notifyDataSetChanged();
		}
	}
	@Override
	public void onRefresh(XListView list) {
		// TODO Auto-generated method stub
		list.setRefreshTime("刚刚");//下拉刷新
		seton();
	}
	@Override
	public void onLoadMore(XListView list) {
		// TODO Auto-generated method stub
		list.setRefreshTime("刚刚");//下拉刷新
		seton();
	}
	void seton(){
		new CountDownTimer(3 * 1000, 1000) {
			@Override
			public void onFinish() {
				// done
				listview.stopLoadMore();
				listview.stopRefresh();
				
			}

			@Override
			public void onTick(long arg0) {
				// 每1000毫秒回调的方法
				
			}
		}.start();
	}
}
