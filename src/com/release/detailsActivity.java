package com.release;

import icss.android.adapter.IcssAdapter;

import java.util.List;

import com.example.putianxia.R;
import com.example.putianxia.R.id;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.putianxia.bin.MainData;
import com.putianxia.view.GjViewFlipper;
import com.putianxia.view.GjViewFlipper.OnDisplayChagnedListener;
import com.putianxia.view.GjViewFlipper.TouchEvents;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewFlipper;

public class detailsActivity extends Activity implements OnClickListener{
	private Activity context;
	Button dmaijia, dfuwu, dpingjia, dtuijian;
	TextView city;
	float touchDownX = 0,touchUpX=0;
	private View view;
	private  LinearLayout pointLayout;
	GjViewFlipper mFlipper;
	 int  sizeimage=0,butt,heights=0;
	 LinearLayout weizhi;
	 boolean marker_scloow=true;
	private ImageView[] imgsary;//ViewFlipper使用
	private int [] images;
	ScrollView scrollview;
	boolean height=true;
	
	private TextView dtitle,dxiangqing_name,dxiangqing_right,dxiangqing_left,
	                                 dprice,darea,dfloor,dactualarea,dstyle,dfanwei,ddizhi,dbiaoqian,djieshao;
	private ImageView dxiangqing_image;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		initView();
		initDate();
		setyuand();
	}
	private void initDate(){
		images = new int[]{R.drawable.house1,R.drawable.house2,R.drawable.house3,
				                             R.drawable.house4,R.drawable.house5,R.drawable.house6};
	}
	
	private void initView() {
		  mFlipper = (GjViewFlipper) findViewById(R.id.dflipper);
		  pointLayout = (LinearLayout) findViewById(R.id.dpoint_layout);
		  scrollview = (ScrollView) findViewById(R.id.dscrollview);
		  
		  dtitle = (TextView) findViewById(R.id.dtitle);
		  
		  dxiangqing_name = (TextView) findViewById(R.id.dxiangqing_name);
		  dxiangqing_right = (TextView) findViewById(R.id.dxiangqing_right);
		  dxiangqing_left = (TextView) findViewById(R.id.dxiangqing_left);
		  
		  dprice = (TextView) findViewById(R.id.dprice);
		  darea = (TextView) findViewById(R.id.darea);
		  dfloor = (TextView) findViewById(R.id.dfloor);
		  dactualarea = (TextView) findViewById(R.id.dactualarea);
		  dstyle = (TextView) findViewById(R.id.dstyle);
		  dfanwei = (TextView) findViewById(R.id.dfanwei);
		  ddizhi = (TextView) findViewById(R.id.ddizhi);
		  dbiaoqian = (TextView) findViewById(R.id.dbiaoqian);
		  djieshao = (TextView) findViewById(R.id.djieshao);
		  
		  dmaijia = (Button) findViewById(R.id.dmaijia);
		  dfuwu = (Button) findViewById(R.id.dfuwu);
		  dpingjia = (Button) findViewById(R.id.dpingjia);
		  dtuijian = (Button) findViewById(R.id.dtiujian);
		  
		  dmaijia.setOnClickListener(this);
		  dfuwu.setOnClickListener(this);
		  dpingjia.setOnClickListener(this);
		  dtuijian.setOnClickListener(this);
	}
	
	void setyuand(){
    	if(images.length>0)
    	{
    		if (mFlipper.getChildCount() > 0) {
    			mFlipper.removeAllViews();
    		}
    		for (int i = 0; i <images.length; i++) {
				final ImageView iv = new ImageView(this);
				iv.setScaleType(ScaleType.FIT_XY);
				
				iv.setImageResource(images[i]);
			//	Http_image(iv, banner_list.get(i));//加载图片
				mFlipper.addView(iv, new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}
    		if(images.length>1)
    			mFlipper.setAutoStart(true);  // 设置自动播放功能（点击事件，前自动播放）
    		mFlipper.setFlipInterval(4000);//图片切换时间
    		Animation lInAnim = AnimationUtils.loadAnimation(this,
    				R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha
    										// 0.1 -> 1.0）
    		Animation lOutAnim = AnimationUtils.loadAnimation(
    				this, R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha
    																// 1.0 -> 0.1）
    		mFlipper.setInAnimation(lInAnim);
    		mFlipper.setOutAnimation(lOutAnim);
    		mFlipper.setOnDisplayChagnedListener(new OnDisplayChagnedListener() {
				
				@Override
				public void OnDisplayChildChanging(ViewFlipper view, int index) {
					// TODO Auto-generated method stub
					butt=mFlipper.getDisplayedChild() % imgsary.length;
					
					for(int i=0;i<images.length;i++)
					if(butt!=i){
						imgsary[i].setEnabled(false);
					}
					else
						imgsary[i].setEnabled(true);
				}

			});
    				
    		mFlipper.setTouchEvents(new TouchEvents() {
				
				@Override
				public void commOnTouchEvent(MotionEvent event) {
					// TODO Auto-generated method stub
				//	GjmFlipper mFlipper = mFlipper;
    				mFlipper.stopFlipping();
    				
    				if (event.getAction() == MotionEvent.ACTION_DOWN) {
    					// 取得左右滑动时手指按下的X坐标
    					touchDownX = event.getX();
    					return ;
    				} else if (event.getAction() == MotionEvent.ACTION_UP) {
    					// 取得左右滑动时手指松开的X坐标
    					touchUpX = event.getX();
    					// 从左往右，看前一个View
    					if (touchUpX - touchDownX > 50) {
    						// 设置View切换的动画
    						Animation rInAnim = AnimationUtils.loadAnimation(
    								getApplicationContext(), R.anim.push_right_in);
    						Animation rOutAnim = AnimationUtils.loadAnimation(
    								getApplicationContext(), R.anim.push_right_out);
    						mFlipper.setInAnimation(rInAnim);
    						mFlipper.setOutAnimation(rOutAnim);
    						// 显示下一个View
    						mFlipper.showPrevious();
    						// 从右往左，看后一个View
    					} else if (touchDownX - touchUpX > 50) {
    						// 设置View切换的动画
    						Animation lInAnim = AnimationUtils.loadAnimation(
    								getApplicationContext(), R.anim.push_left_in);
    						Animation lOutAnim = AnimationUtils.loadAnimation(
    								getApplicationContext(), R.anim.push_left_out);
    						mFlipper.setInAnimation(lInAnim);
    						mFlipper.setOutAnimation(lOutAnim);
    						// 显示前一个View
    						mFlipper.showNext();
    					} else if (touchDownX - touchUpX <= 50
    							&& touchDownX - touchUpX >= -50) {
    						mFlipper.setInAnimation(null);
    						mFlipper.setOutAnimation(null);
    						int current = 0;
//    						Intent intent =new Intent(getActivity(), Webview_activity.class);
//    						intent.putExtra("title", "轮播图链接");
//    						intent.putExtra("url", banner_url.get(butt));
//    						startActivity(intent);
    						
    					}
    				
    					Animation lInAnim = AnimationUtils.loadAnimation(
    							getApplicationContext(), R.anim.push_left_in);
    					Animation lOutAnim = AnimationUtils.loadAnimation(
    							getApplicationContext(), R.anim.push_left_out);
    					mFlipper.setInAnimation(lInAnim);
    					mFlipper.setOutAnimation(lOutAnim);
    					mFlipper.startFlipping();
    					return ;
    				}
    			//	return false;
				}
			});
    		if (!mFlipper.isFlipping())
    			mFlipper.startFlipping();
    		mFlipper.invalidate();		  	
    	}
    	sizeimage=images.length;
    	imgsary = new ImageView[sizeimage];
		if (pointLayout.getChildCount() > 0) {
			pointLayout.removeAllViews();//判断当前布局里面释放有view
		}
		if (sizeimage > 0) {
			for (int i = 0; i < sizeimage; i++) {
				ImageView point = new ImageView(getApplicationContext());
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

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.dmaijia:
			showNormalDialog();
			break;

		default:
			break;
		}
		
	}
	
	private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
		 new AlertDialog.Builder(detailsActivity.this)
		 .setTitle("系统提示")//设置对话框标题  
	     .setMessage("确定要联系卖家吗？ ")//设置显示的内容  
	     .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮  
	         public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件  
	        		String number ="13106898411";  
	                //用intent启动拨打电话  
	                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+number));  
	                startActivity(intent);  
	  
	         }  
	  
	     }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮  
	         public void onClick(DialogInterface dialog, int which) {//响应事件  
	             Log.i("alertdialog"," 请保存数据！");  
	  
	         }  
	  
	     }).show();
	}
}

