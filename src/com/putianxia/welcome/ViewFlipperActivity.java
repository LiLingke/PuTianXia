package com.putianxia.welcome;

import java.util.ArrayList;
import java.util.List;

import com.example.putianxia.R;
import com.putianxia.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ViewFlipperActivity extends Activity implements OnPageChangeListener, OnClickListener {

	// 设置ViewPager和view视图
	private ViewPager viewPager;
	private int[] imageId = { R.drawable.welcome_houes1, R.drawable.welcome_houes2, R.drawable.welcome_houes4,
			R.drawable.welcome_houes5 };
	private List<View> viewList;
	// private List<ImageUrlBean> imageList;
	private View lastView;
	// 设置小圆点
	private ImageView[] points;// 存放小圆圈数组
	private int currentIndex = 0;// 当前页面,默认首页
	private Button startbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager);
		initView();
		initViewPage();
		initPoints();

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		// 当前页卡被选择时,position为当前页数
		points[position].setEnabled(false);// 不可点击
		points[currentIndex].setEnabled(true);// 恢复之前页面状态
		currentIndex = position;
		if (startbutton.getVisibility() == View.INVISIBLE && position == 3) {
			startbutton.setVisibility(View.VISIBLE);
		} else {
			startbutton.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// viewPager.setCurrentItem((int) v.getTag());
		switch (v.getId()) {
		case R.id.startbutton:
			Intent intent = new Intent(ViewFlipperActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		startbutton = (Button) findViewById(R.id.startbutton);
		startbutton.setOnClickListener(this);
	}

	// 这里是加载mipmap中图片的方式，初始化ViewPager及其图片资源
	private void initViewPage() {
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewList = new ArrayList<View>();
		// 添加对应的view进入集合（数据源）
		for (int i = 0; i < imageId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);// 设置缩放样式
			imageView.setImageResource(imageId[i]);
			viewList.add(imageView);
		}
		// 设置viewpager的适配器和数据源
		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewList);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(this);
	}

	// 初始化下标点
	private void initPoints() {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
		points = new ImageView[4];
		// 初始化布局中的小圆点ImageView控件
		for (int i = 0; i < points.length; i++) {
			points[i] = (ImageView) linearLayout.getChildAt(i);// 遍历LinearLayout下的所有ImageView子节点
			points[i].setEnabled(true);// 设置当前状态为允许点击（可点，灰色）
			points[i].setOnClickListener(this);// 设置点击监听
			// 额外设置一个标识符，以便点击小圆点时跳转对应页面
			points[i].setTag(i);// 标识符与圆点顺序一致
		}
		currentIndex = 0;
		points[currentIndex].setEnabled(false);// 设置首页为当前页(不可点，黑色)
	}

	class ViewPagerAdapter extends PagerAdapter {
		private List list;

		public ViewPagerAdapter(List list) {
			this.list = list;
		}

		// 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
		@Override
		public int getCount() {
			return list.size();
		}

		// 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;// 官方demo给出的建议写法
		}

		// 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView((View) list.get(position));
			return list.get(position);
		}

		// PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) list.get(position));
		}
	}

}
