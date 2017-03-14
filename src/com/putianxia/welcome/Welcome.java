package com.putianxia.welcome;

import com.example.putianxia.R;
import com.putianxia.activity.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import cn.sharesdk.framework.authorize.e;

public class Welcome extends Activity {
	private static final int TIME = 3000;  
    private static final int GO_HOME = 1000;  
    private static final int GO_GUIDE = 1001;  
    private boolean isFirstIn = false;  
    
    private Handler handler = new Handler(){  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            switch (msg.what){  
                case GO_HOME:  
                    goHome();  
                    break;  
                case GO_GUIDE:  
                    goGuide();  
                    break;  
            }  
        }
    }; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcom);
		
		init();
		
	}

	  private void init(){  
	        //ִ�гɹ��Ļ����Ὣ���ݱ�����homeTest.xml�ļ���  
	        SharedPreferences preferences = getSharedPreferences("homeTest",MODE_PRIVATE);  
	        //��һ��������key���ڶ���������Ĭ��ֵ  
	        isFirstIn = preferences.getBoolean("isFirstIn",true);  
	        if (!isFirstIn){  
	            handler.sendEmptyMessageDelayed(GO_HOME,TIME);  
	        }  
	        else {  
	            handler.sendEmptyMessageDelayed(GO_GUIDE,TIME);  
	            //����edit()������ȡEditor����  
	            SharedPreferences.Editor editor = preferences.edit();  
	            editor.putBoolean("isFirstIn",false);  
	  
	            editor.commit();  
	        }  
	    }  


	private void goGuide() {
		Intent intent = new Intent(this,ViewFlipperActivity.class);
		startActivity(intent);
		finish();
		
	}

	private void goHome() {
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
		
	}  
}
