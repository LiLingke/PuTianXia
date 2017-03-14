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

	// ����ViewPager��view��ͼ
	private ViewPager viewPager;
	private int[] imageId = { R.drawable.welcome_houes1, R.drawable.welcome_houes2, R.drawable.welcome_houes4,
			R.drawable.welcome_houes5 };
	private List<View> viewList;
	// private List<ImageUrlBean> imageList;
	private View lastView;
	// ����СԲ��
	private ImageView[] points;// ���СԲȦ����
	private int currentIndex = 0;// ��ǰҳ��,Ĭ����ҳ
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
		// ��ǰҳ����ѡ��ʱ,positionΪ��ǰҳ��
		points[position].setEnabled(false);// ���ɵ��
		points[currentIndex].setEnabled(true);// �ָ�֮ǰҳ��״̬
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

	// �����Ǽ���mipmap��ͼƬ�ķ�ʽ����ʼ��ViewPager����ͼƬ��Դ
	private void initViewPage() {
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		viewList = new ArrayList<View>();
		// ��Ӷ�Ӧ��view���뼯�ϣ�����Դ��
		for (int i = 0; i < imageId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);// ����������ʽ
			imageView.setImageResource(imageId[i]);
			viewList.add(imageView);
		}
		// ����viewpager��������������Դ
		ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewList);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(this);
	}

	// ��ʼ���±��
	private void initPoints() {
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
		points = new ImageView[4];
		// ��ʼ�������е�СԲ��ImageView�ؼ�
		for (int i = 0; i < points.length; i++) {
			points[i] = (ImageView) linearLayout.getChildAt(i);// ����LinearLayout�µ�����ImageView�ӽڵ�
			points[i].setEnabled(true);// ���õ�ǰ״̬Ϊ���������ɵ㣬��ɫ��
			points[i].setOnClickListener(this);// ���õ������
			// ��������һ����ʶ�����Ա���СԲ��ʱ��ת��Ӧҳ��
			points[i].setTag(i);// ��ʶ����Բ��˳��һ��
		}
		currentIndex = 0;
		points[currentIndex].setEnabled(false);// ������ҳΪ��ǰҳ(���ɵ㣬��ɫ)
	}

	class ViewPagerAdapter extends PagerAdapter {
		private List list;

		public ViewPagerAdapter(List list) {
			this.list = list;
		}

		// ��ȡҪ�����Ŀؼ��������������������Ի����Ĺ����Ϊ������ô�����Ӧ����չʾ�Ĺ��ͼƬ��ImageView����
		@Override
		public int getCount() {
			return list.size();
		}

		// ���ж���ʾ���Ƿ���ͬһ��ͼƬ���������ǽ�����������ȽϷ��ؼ���
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;// �ٷ�demo�����Ľ���д��
		}

		// ��Ҫ��ʾ��ͼƬ���Խ��л����ʱ�򣬻�����������������ʾͼƬ�ĳ�ʼ�������ǽ�Ҫ��ʾ��ImageView���뵽ViewGroup�У�Ȼ����Ϊ����ֵ���ؼ���
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView((View) list.get(position));
			return list.get(position);
		}

		// PagerAdapterֻ��������Ҫ��ʾ��ͼƬ�����������ͼƬ�����˻���ķ�Χ���ͻ���������������ͼƬ����
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) list.get(position));
		}
	}

}
