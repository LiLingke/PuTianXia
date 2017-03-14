package com.putianxia.activity;

import java.util.ArrayList;

import com.example.putianxia.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.putianxia.adapter.MainPagerAdapter;
import com.putianxia.fragment.Main_fragment1;
import com.putianxia.fragment.Main_fragment2;
import com.putianxia.fragment.Main_fragment3;
import com.putianxia.view.NoScrollViewPager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private ArrayList<Fragment> fragmentList;
	public static boolean login = true;
	Main_fragment1 fragment1;
	Main_fragment2 fragment2;
	Main_fragment3 fragment3;
	MainPagerAdapter adapter;
	NoScrollViewPager pager;
	// 底部菜单3个Linearlayout
	private LinearLayout ll_home;
	private LinearLayout ll_address;
	private LinearLayout ll_my;
	// 底部菜单3个ImageView
	private ImageView iv_home;
	private ImageView iv_address;
	private ImageView iv_my;
	// 底部菜单3个TextView
	private TextView tv_home;
	private TextView tv_address;
	private TextView tv_my;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// setContentView(R.layout.xiangqing);
		pager = (NoScrollViewPager) findViewById(R.id.main_Pager);
		initView();
		initEvent();
		inagelogle();
		setFragment();

	}

	private void initView() {
		ll_home = (LinearLayout) findViewById(R.id.ll_home);
		ll_address = (LinearLayout) findViewById(R.id.ll_address);
		ll_my = (LinearLayout) findViewById(R.id.ll_my);

		iv_home = (ImageView) findViewById(R.id.iv_home);
		iv_address = (ImageView) findViewById(R.id.iv_address);
		iv_my = (ImageView) findViewById(R.id.iv_my);

		tv_home = (TextView) findViewById(R.id.tv_home);
		tv_address = (TextView) findViewById(R.id.tv_address);
		tv_my = (TextView) findViewById(R.id.tv_my);
	}

	private void initEvent() {
		ll_home.setOnClickListener(this);
		ll_address.setOnClickListener(this);
		ll_my.setOnClickListener(this);
	}

	private void setFragment() {// ViewPager+Fragment
		// TODO Auto-generated method stub
		fragmentList = new ArrayList<Fragment>();
		fragment1 = new Main_fragment1();
		fragment2 = new Main_fragment2();
		fragment3 = new Main_fragment3();
		// fragment4=new Main_fragment4();
		fragmentList.add(fragment1);
		fragmentList.add(fragment2);
		fragmentList.add(fragment3);

		adapter = new MainPagerAdapter(getSupportFragmentManager(),
				fragmentList);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	/*
	 * Pager
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		// private int one = offset *2 +bmpW;//两个相邻页面的偏移量

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			// if(arg0==2)
			// if(arg1==1)
			// fragment3.main_pager3_scrollview.scrollTo(0,
			// fragment3.scrollview_y);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			// String say;
			// say="";
		}

		@Override
		public void onPageSelected(int arg0) { // 监听
			// TODO Auto-generated method stub
			if (arg0 == 0) {
				restartBotton();
				iv_home.setImageResource(R.drawable.tab_weixin_pressed);
	            tv_home.setTextColor(0xff1B940A);

			} else if (arg0 == 1) {
				restartBotton();
				iv_address.setImageResource(R.drawable.tab_address_pressed);
	            tv_address.setTextColor(0xff1B940A);

			} else if (arg0 == 2) {
				restartBotton();
				iv_my.setImageResource(R.drawable.tab_settings_pressed);
	            tv_my.setTextColor(0xff1B940A);

			}
		/*	Toast.makeText(MainActivity.this, "您选择了第" + arg0 + "个页卡",
					Toast.LENGTH_SHORT).show();*/
		}
	}

	void inagelogle() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// 设置图片下载期间显示的图
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存�???
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.resetViewBeforeLoading(true)
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图�???
				.build(); // 创建配置过得DisplayImageOption对象

		// 创建默认的ImageLoader配置参数
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCacheExtraOptions(480, 800)
				.threadPoolSize(3)
				// 线程池内加载的数
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// You can pass your own memory cache
				// implementation/你可以过自己的内存缓存实
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 将保存的时的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100) // 缓存的文件数
				.defaultDisplayImageOptions(options)
				.imageDownloader(
						new BaseImageDownloader(getApplicationContext(),
								5 * 1000, 30 * 1000)) // connectTimeout (5 s),
														// readTimeout (30
														// s)超时时间
				.writeDebugLogs() // Remove for release app
				.build();// 始构
		ImageLoader.getInstance().init(configuration);
	}

	// @Override
	// protected void onActivityResult(int arg0, int arg1, Intent arg2) {
	// // TODO Auto-generated method stub
	// super.onActivityResult(arg0, arg1, arg2);
	// }

	@Override
	public void onClick(View v) {
		restartBotton();

		switch (v.getId()) {
		case R.id.ll_home:
			//0xff00ff00
			pager.setCurrentItem(0);
			iv_home.setImageResource(R.drawable.tab_weixin_pressed);
            tv_home.setTextColor(0xff1B940A);
			break;
			
		case R.id.ll_address:
			pager.setCurrentItem(1);
			iv_address.setImageResource(R.drawable.tab_address_pressed);
            tv_address.setTextColor(0xff1B940A);
			break;
			
		case R.id.ll_my:
			pager.setCurrentItem(2);
			iv_my.setImageResource(R.drawable.tab_settings_pressed);
            tv_my.setTextColor(0xff1B940A);
			break;

		default:
			break;
		}

	}

	private void restartBotton() {
		// ImageView置为灰色
		iv_home.setImageResource(R.drawable.tab_weixin_normal);
		iv_address.setImageResource(R.drawable.tab_address_normal);
		iv_my.setImageResource(R.drawable.tab_settings_normal);
		// TextView置为灰色
		tv_home.setTextColor(0xff888888);
		tv_address.setTextColor(0xff888888);
		tv_my.setTextColor(0xff888888);
	}
}
