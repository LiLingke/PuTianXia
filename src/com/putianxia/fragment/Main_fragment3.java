package com.putianxia.fragment;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.example.putianxia.R;
import com.putianxia.activity.ListviewActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main_fragment3 extends Fragment implements OnClickListener {
	private Activity context;
	private View view;
	Boolean lostlogi = true;
	int marker = 2;
	private LinearLayout my_login_register;
	private TextView my_register, my_login;
	private TextView my_xiugai, my_name, my_type, my_renzheng;
	private ImageView image;
	private TextView my_fragment_shouyi, my_fragment_textview1, my_fragment_textview2, my_fragment_textview3,
			my_fragment_textview4, my_fragment_textview5, my_fragment_textview6, my_fragment_textview7;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (null == view) {
			// 判断上次是否登录成功过
			// 成功则自动登录，否则显示登录页面
			view = inflater.inflate(R.layout.main_fragment3, container, false);
			findview();
			if (lostlogi) {
				my_login_register.setVisibility(View.GONE);
				if (marker == 0) {
					my_fragment_shouyi.setVisibility(View.GONE);
					my_fragment_textview7.setVisibility(View.GONE);
					my_fragment_textview1.setText("已关注房源");
					my_fragment_textview2.setText("待发布房源");
					my_fragment_textview3.setText("待审核房源");
					my_fragment_textview4.setText("已发布房源");
					my_fragment_textview5.setText("交易中房源");
					my_fragment_textview6.setText("已交易房源");
				} else if (marker == 1) {
					my_fragment_textview1.setText("被推荐房产");
					my_fragment_textview2.setText("已关注房产");
					my_fragment_textview3.setText("待发布需求");
					my_fragment_textview4.setText("待审核需求");
					my_fragment_textview5.setText("已发布需求");
					my_fragment_textview6.setText("交易中房产");
					my_fragment_textview7.setText("已交易房源");
					my_fragment_shouyi.setVisibility(View.GONE);
				}

				else {
					my_fragment_shouyi.setVisibility(View.VISIBLE);
					my_fragment_textview1.setText("已推荐房产");
					my_fragment_textview2.setText("已关注房产");
					my_fragment_textview3.setText("待发布需求/房源");
					my_fragment_textview4.setText("待审核需求/房源");
					my_fragment_textview5.setText("已发布需求/房源");
					my_fragment_textview6.setText("交易中房产");
					my_fragment_textview7.setText("已交易房源");
				}
			} else
				my_login_register.setVisibility(View.VISIBLE);
			if (getUserVisibleHint()) {

			}
		} else {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	void findview() {
		my_register = (TextView) view.findViewById(R.id.my_register);
		my_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		my_login = (TextView) view.findViewById(R.id.my_login);
		my_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		my_login_register = (LinearLayout) view.findViewById(R.id.my_login_register);

		my_name = (TextView) view.findViewById(R.id.my_textview);
		my_type = (TextView) view.findViewById(R.id.my_type);
		my_renzheng = (TextView) view.findViewById(R.id.my_renzheng);
		my_renzheng.setOnClickListener(this);
		my_xiugai = (TextView) view.findViewById(R.id.my_xiugai);
		my_xiugai.setOnClickListener(this);
		image = (ImageView) view.findViewById(R.id.imageView1);
		WindowManager wm = context.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		LayoutParams para;
		para = image.getLayoutParams();
		para.height = width / 3;
		para.width = width / 3;
		image.setLayoutParams(para);
		my_fragment_shouyi = (TextView) view.findViewById(R.id.my_fragment_shouyi);
		my_fragment_textview1 = (TextView) view.findViewById(R.id.my_fragment_textview1);
		my_fragment_textview2 = (TextView) view.findViewById(R.id.my_fragment_textview2);
		my_fragment_textview3 = (TextView) view.findViewById(R.id.my_fragment_textview3);
		my_fragment_textview4 = (TextView) view.findViewById(R.id.my_fragment_textview4);
		my_fragment_textview5 = (TextView) view.findViewById(R.id.my_fragment_textview5);
		my_fragment_textview6 = (TextView) view.findViewById(R.id.my_fragment_textview6);
		my_fragment_textview7 = (TextView) view.findViewById(R.id.my_fragment_textview7);
		my_fragment_textview1.setOnClickListener(this);
		my_fragment_textview2.setOnClickListener(this);
		my_fragment_textview3.setOnClickListener(this);
		my_fragment_textview4.setOnClickListener(this);
		my_fragment_textview5.setOnClickListener(this);
		my_fragment_textview6.setOnClickListener(this);
		my_fragment_textview7.setOnClickListener(this);
		my_fragment_shouyi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showShare();
			}
		});

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context, ListviewActivity.class);

		switch (arg0.getId()) {
		case R.id.my_fragment_textview1:

			if (marker == 0)
				setIntent(intent, "已关注房源", "");
			else if (marker == 1)
				setIntent(intent, "被推荐房源", "");
			else if (marker == 2)
				setIntent(intent, "已推荐房源", "");

			break;
		case R.id.my_fragment_textview2:

			if (marker == 0)
				setIntent(intent, "待发布房源", "");
			else if (marker == 1)
				setIntent(intent, "已关注房源", "");
			else if (marker == 2)
				setIntent(intent, "已关注房源", "");

			break;
		case R.id.my_fragment_textview3:

			if (marker == 0)
				setIntent(intent, "待审核房源", "");
			else if (marker == 1)
				setIntent(intent, "待发布需求", "");
			else if (marker == 2)
				setIntent(intent, "待发布需求/房源", "");

			break;
		case R.id.my_fragment_textview4:
			if (marker == 0)
				setIntent(intent, "已发布房源", "");
			else if (marker == 1)
				setIntent(intent, "待审核需求", "");
			else if (marker == 2)
				setIntent(intent, "待审核需求/房源", "");
			break;
		case R.id.my_fragment_textview5:
			if (marker == 0)
				setIntent(intent, "交易中房源", "");
			else if (marker == 1)
				setIntent(intent, "已发布需求", "");
			else if (marker == 2)
				setIntent(intent, "已发布需求/房源", "");
			break;
		case R.id.my_fragment_textview6:
			if (marker == 0)
				setIntent(intent, "已交易房源", "");
			else if (marker == 1)
				setIntent(intent, "交易中房产", "");
			else if (marker == 2)
				setIntent(intent, "交易中房产", "");
			break;
		case R.id.my_fragment_textview7:
			if (marker == 1)
				setIntent(intent, "已交易房产", "");
			else if (marker == 2)
				setIntent(intent, "已交易房产", "");
			break;

		default:
			break;
		}
		startActivity(intent);
	}

	private void setIntent(Intent intent, String title, String url) {
		// TODO Auto-generated method stub

		intent.putExtra("title", title);
		intent.putExtra("url", url);

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		if (isVisibleToUser && isVisible()) {
			if (lostlogi)
				my_login_register.setVisibility(View.GONE);
			else
				my_login_register.setVisibility(View.VISIBLE);
		}
		super.setUserVisibleHint(isVisibleToUser);
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

	private void showShare() {
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("敬哥");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(context);
	}
}
