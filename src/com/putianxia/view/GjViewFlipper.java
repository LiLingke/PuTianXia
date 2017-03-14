package com.putianxia.view;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class GjViewFlipper extends ViewFlipper{
	 private OnDisplayChagnedListener mListener;
	 TouchEvents TouchEventss;
	public GjViewFlipper(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void setOnDisplayChagnedListener(OnDisplayChagnedListener listener) {
        if (mListener != listener) {
            this.mListener = listener;
        }
    }
	@Override
	public void showNext() {
		// TODO Auto-generated method stub
		super.showNext();
		if(mListener != null){
            mListener.OnDisplayChildChanging(this, super.getDisplayedChild());
        }
	}
	public GjViewFlipper(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }
	 public interface OnDisplayChagnedListener {//手动显示后面的孩子。
	        void OnDisplayChildChanging(ViewFlipper view, int index);//接口获得ID
	    }
	 @Override
	public void showPrevious() {//手动显示前面的孩子。
		// TODO Auto-generated method stub
		super.showPrevious();
		if(mListener != null){
            mListener.OnDisplayChildChanging(this, super.getDisplayedChild());
            
        }
	}
	 @Override  
	    public boolean dispatchTouchEvent(MotionEvent ev) {  
	      getParent().requestDisallowInterceptTouchEvent(true);  
	          super.dispatchTouchEvent(ev);  
	          TouchEventss.commOnTouchEvent(ev);  //进行子View手势的相应操作  
	          return true;  
	    }  
	public  interface TouchEvents {
		void commOnTouchEvent(MotionEvent ev);
	 }
	public void setTouchEvents(TouchEvents l){
		this.TouchEventss=l;
	}
//	@Override
//	public void setOnTouchListener(OnTouchListener l) {
//		// TODO Auto-generated method stub
//		super.setOnTouchListener(l);
//	}
	
}
