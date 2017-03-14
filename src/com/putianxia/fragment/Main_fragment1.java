package com.putianxia.fragment;

import icss.android.adapter.IcssAdapter;

import java.util.ArrayList;

import java.util.List;

import com.droid.Activity01;
import com.example.putianxia.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.putianxia.bin.MainData;
import com.putianxia.bin.XiangQing;
import com.putianxia.view.GjViewFlipper;
import com.putianxia.view.GjViewFlipper.OnDisplayChagnedListener;
import com.putianxia.view.GjViewFlipper.TouchEvents;
import com.release.BuildingActivity;
import com.release.HouseActivity;
import com.release.NeedActivity;
import com.release.StoreActivity;
import com.release.detailsActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ImageView.ScaleType;

public class Main_fragment1 extends Fragment implements OnClickListener {
	private Activity context;
	Button btstore, bthouse, btbuilding, btneed;
	TextView city;
	float touchDownX = 0, touchUpX = 0;
	private View view;
	private LinearLayout pointLayout;
	GjViewFlipper viewFlipper;
	int sizeimage = 0, butt, heights = 0;
	LinearLayout weizhi;
	EditText sousuo;
	ListView fragment1_listview1;
	IcssAdapter<MainData> adapter;
	boolean marker_scloow = true;
	private ImageView[] imgsary;// ViewFlipper使用
	List<MainData> list;
	ScrollView scrollview;
	boolean height = true;
	List<Integer> listint = new ArrayList<Integer>();
	ArrayList<String> banner_list = new ArrayList<String>();
	ArrayList<String> banner_url = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null == view) {
			view = inflater.inflate(R.layout.main_fragment1, container, false);
			viewFlipper = (GjViewFlipper) view.findViewById(R.id.flipper);
			pointLayout = (LinearLayout) view.findViewById(R.id.point_layout);
			weizhi = (LinearLayout) view.findViewById(R.id.weizhi);
			fragment1_listview1 = (ListView) view.findViewById(R.id.fragment1_listview1);

			sousuo = (EditText) view.findViewById(R.id.sousuo);
			scrollview = (ScrollView) view.findViewById(R.id.scrollview);
			city = (TextView) view.findViewById(R.id.weizhi_city);
			btstore = (Button) view.findViewById(R.id.btstore);
			bthouse = (Button) view.findViewById(R.id.bthouse);
			btbuilding = (Button) view.findViewById(R.id.btbuilding);
			btneed = (Button) view.findViewById(R.id.btneed);

			weizhi.setOnClickListener(this);
			btstore.setOnClickListener(this);
			bthouse.setOnClickListener(this);
			btbuilding.setOnClickListener(this);
			btneed.setOnClickListener(this);

			setListadapter();

			banner_list.add("http://h.hiphotos.baidu.com/zhidao/pic/item/a71ea8d3fd1f413460f58caf241f95cad0c85e25.jpg");
			banner_list.add("http://d.hiphotos.baidu.com/zhidao/pic/item/9345d688d43f87941f476dfad71b0ef41ad53aad.jpg");
			banner_list.add("http://a.hiphotos.baidu.com/zhidao/pic/item/b21bb051f81986180eda09684bed2e738ad4e6b9.jpg");
			banner_list.add("http://a.hiphotos.baidu.com/zhidao/pic/item/e61190ef76c6a7ef76584ecafcfaaf51f2de66e4.jpg");

			setyuand();

			if (marker_scloow) {
				scrollview.smoothScrollTo(0, 0);
				marker_scloow = false;
			}
			if (getUserVisibleHint()) {
				// scrollview.smoothScrollTo(0, scrollview_y);
			}
		} else {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	private void setListadapter() {
		// TODO Auto-generated method stub
		List<String> liststring = new ArrayList<String>();
		liststring.add("近地铁");
		liststring.add("购物方便");
		liststring.add("金融中心");
		List<XiangQing> lists1 = new ArrayList<XiangQing>();
		lists1.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房",
				"一室一厅", "1500/月", "南山区", liststring));
		lists1.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房",
				"一室一厅", "1500/月", "南山区", liststring));
		lists1.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房",
				"一室一厅", "1500/月", "南山区", liststring));
		lists1.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房",
				"一室一厅", "1500/月", "南山区", liststring));
		lists1.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房",
				"一室一厅", "1500/月", "南山区", liststring));
		lists1.add(new XiangQing("http://img4.imgtn.bdimg.com/it/u=62705028,1604552902&fm=21&gp=0.jpg", "柠溪花园租房",
				"一室一厅", "1500/月", "南山区", liststring));
		list = new ArrayList<MainData>();
		list.add(new MainData("热门房源", lists1));
		list.add(new MainData("热门铺源", lists1));
		list.add(new MainData("热门需求", lists1));
		adapter = new IcssAdapter<MainData>(context, list, R.layout.adapter_listview) {

			@Override
			public void getview(int position) {
				// TODO Auto-generated method stub

				ListView listview = viewholder.getView(R.id.adapter_listview);
				IcssAdapter<XiangQing> adapter_XQ = new IcssAdapter<XiangQing>(this.context,
						this.list.get(position).getList(), R.layout.xiangqing) {

					@Override
					public void getview(int position) {
						// TODO Auto-generated method stub
						viewholder.setText(R.id.xiangqing_name, list.get(position).getName())
								.setText(R.id.xiangqing_weizhi, list.get(position).getWeizhi())
								.setText(R.id.xiangqing_left, list.get(position).getLeftdecp())
								.setText(R.id.xiangqing_right, list.get(position).getRightdecp());
						ImageView imageview = viewholder.getView(R.id.xiangqing_image);
						ImageLoader.getInstance().displayImage(list.get(position).getImageurl(), imageview);
					}
				};

				listview.setAdapter(adapter_XQ);
				fixListViewHeights(listview);
				// boolean maks=true;
				// if(listint.size()>0)
				// {
				// for(int i=0;i<listint.size();i++)
				// {
				//
				// int j=listint.get(i);
				// if(position==j){
				// maks=false;
				// break;
				// }
				// }
				// if(maks)
				// { listint.add(position);
				// fixListViewHeights(listview);
				// maks=true;
				// }
				// }
				// else
				// {
				// listint.add(position);
				// fixListViewHeights(listview);
				// }
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// startActivityForResult(new Intent(context,
						// detailsActivity.class), 1);
						Intent intent = new Intent();
						intent.setClass(context, detailsActivity.class);

						startActivity(intent);

					}
				});
				viewholder.setText(R.id.adapter_title, list.get(position).getTitle()).setText(R.id.adapter_gengduo,
						"更多>>", new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub

							}
						});

			}
		};
		fragment1_listview1.setAdapter(adapter);
		fixListViewHeight(fragment1_listview1);
	}

	public void fixListViewHeight(ListView listView) {
		// 如果没有设置数据适配器，则ListView没有子项，返回。
		ListAdapter listAdapter = listView.getAdapter();
		int totalHeight = 0;
		if (listAdapter == null) {
			return;
		}
		int i = 0;
		for (int index = 0, len = listAdapter.getCount(); i < len; i++) {
			View listViewItem = listAdapter.getView(index, null, listView);
			// 计算子项View 的宽高
			listViewItem.measure(0, 0);
			// 计算所有子项的高度和

			totalHeight += listViewItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		// listView.getDividerHeight()获取子项间分隔符的高度
		// params.height设置ListView完全显示需要的高度
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		listView.setLayoutParams(params);
		height = false;
	}

	public void fixListViewHeights(ListView listView) {
		// 如果没有设置数据适配器，则ListView没有子项，返回。
		ListAdapter listAdapter = listView.getAdapter();
		int totalHeight = 0;
		if (listAdapter == null) {
			return;
		}
		int i = 0;
		for (int index = 0, len = listAdapter.getCount(); i < len; i++) {
			View listViewItem = listAdapter.getView(index, null, listView);
			// 计算子项View 的宽高
			listViewItem.measure(0, 0);
			// 计算所有子项的高度和
			totalHeight += listViewItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		// listView.getDividerHeight()获取子项间分隔符的高度
		// params.height设置ListView完全显示需要的高度
		// int j=(listView.getDividerHeight() * (listAdapter.getCount() - 1));
		params.height = totalHeight + Math.abs((listView.getDividerHeight() * (listAdapter.getCount() - 1)));
		heights = params.height / 3 * 2;
		if (height)
			listView.setLayoutParams(params);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.weizhi:
			startActivityForResult(new Intent(context, Activity01.class), 1);
			break;
		case R.id.btstore:
			startActivityForResult(new Intent(context, StoreActivity.class), 1);
			break;
		case R.id.bthouse:
			startActivityForResult(new Intent(context, HouseActivity.class), 1);
			break;
		case R.id.btbuilding:
			startActivityForResult(new Intent(context, BuildingActivity.class), 1);
			break;
		case R.id.btneed:
			startActivityForResult(new Intent(context, NeedActivity.class), 1);
			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			city.setText(data.getStringExtra("city"));
			// 开启网络重新刷新数据

		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		if (isVisibleToUser && isVisible()) {
			// 加载数据
			// setyuand();
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	void setyuand() {
		if (banner_list.size() > 0) {
			if (viewFlipper.getChildCount() > 0) {
				viewFlipper.removeAllViews();
			}
			for (int i = 0; i < banner_list.size(); i++) {
				final ImageView iv = new ImageView(getActivity());
				iv.setScaleType(ScaleType.FIT_XY);
				String picUrl = banner_list.get(i);

				iv.setTag(picUrl);
				// Http_image(iv, banner_list.get(i));//加载图片
				ImageLoader.getInstance().displayImage(banner_list.get(i), iv);
				viewFlipper.addView(iv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}
			if (banner_list.size() > 1)
				viewFlipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
			viewFlipper.setFlipInterval(4000);// 图片切换时间
			Animation lInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha
																									// 0.1
																									// ->
		   																						   // 1.0）
			Animation lOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha
																									// 1.0
																									// ->
																									// 0.1）
			viewFlipper.setInAnimation(lInAnim);
			viewFlipper.setOutAnimation(lOutAnim);
			viewFlipper.setOnDisplayChagnedListener(new OnDisplayChagnedListener() {

				@Override
				public void OnDisplayChildChanging(ViewFlipper view, int index) {
					// TODO Auto-generated method stub
					butt = viewFlipper.getDisplayedChild() % imgsary.length;

					for (int i = 0; i < banner_list.size(); i++)
						if (butt != i) {
							imgsary[i].setEnabled(false);
						} else
							imgsary[i].setEnabled(true);
				}
			});

			viewFlipper.setTouchEvents(new TouchEvents() {

				@Override
				public void commOnTouchEvent(MotionEvent event) {
					// TODO Auto-generated method stub
					// GjViewFlipper viewFlipper = viewFlipper;
					viewFlipper.stopFlipping();

					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						// 取得左右滑动时手指按下的X坐标
						touchDownX = event.getX();
						return;
					} else if (event.getAction() == MotionEvent.ACTION_UP) {
						// 取得左右滑动时手指松开的X坐标
						touchUpX = event.getX();
						// 从左往右，看前一个View
						if (touchUpX - touchDownX > 50) {
							// 设置View切换的动画
							Animation rInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_in);
							Animation rOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_right_out);
							viewFlipper.setInAnimation(rInAnim);
							viewFlipper.setOutAnimation(rOutAnim);
							// 显示前一个View
							viewFlipper.showPrevious();
							// 从右往左，看后一个View
						} else if (touchDownX - touchUpX > 50) {
							// 设置View切换的动画
							Animation lInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in);
							Animation lOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out);
							viewFlipper.setInAnimation(lInAnim);
							viewFlipper.setOutAnimation(lOutAnim);
							// 显示下一个View
							viewFlipper.showNext();
						} else if (touchDownX - touchUpX <= 50 && touchDownX - touchUpX >= -50) {
							viewFlipper.setInAnimation(null);
							viewFlipper.setOutAnimation(null);
							int current = 0;
							// Intent intent =new Intent(getActivity(),
							// Webview_activity.class);
							// intent.putExtra("title", "轮播图链接");
							// intent.putExtra("url", banner_url.get(butt));
							// startActivity(intent);

						}

						Animation lInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_in);
						Animation lOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.push_left_out);
						viewFlipper.setInAnimation(lInAnim);
						viewFlipper.setOutAnimation(lOutAnim);
						viewFlipper.startFlipping();
						return;
					}
					// return false;
				}
			});
			if (!viewFlipper.isFlipping())
				viewFlipper.startFlipping();
			viewFlipper.invalidate();
		}
		sizeimage = banner_list.size();
		imgsary = new ImageView[sizeimage];
		if (pointLayout.getChildCount() > 0) {
			pointLayout.removeAllViews();// 判断当前布局里面释放有view
		}
		if (sizeimage > 0) {
			for (int i = 0; i < sizeimage; i++) {
				ImageView point = new ImageView(getActivity());
				point.setImageResource(R.drawable.insoft_point_bg);
				point.setPadding(5, 5, 5, 5);
				point.setLayoutParams(new LayoutParams(25, 25));
				pointLayout.addView(point);
			}
			for (int i = 0; i < sizeimage; i++) {
				imgsary[i] = (ImageView) pointLayout.getChildAt(i);
				imgsary[i].setEnabled(false);
				imgsary[i].setTag(i);
			}
			imgsary[0].setEnabled(true);
		}
	}

	// 当Fragment被添加到Activity会回调这个方法，只调用1次；
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = this.getActivity();

	}

	// 创建Fragment会被回调；，only1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	// 当Fragment所在Activity启动完成后会调用；
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	// 启动Fragment
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	// 恢复Fragment时回调；，并且onStart（）调用时必然调用
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	// 暂停Fragment
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	// 停止Fragment
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	// 销毁Fragment所包含的View组件是调用；
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	// 销毁Fragment会被回调
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	// Fragment从Activity删除时会回调,only1
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

}
