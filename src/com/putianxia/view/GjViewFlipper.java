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
	 public interface OnDisplayChagnedListener {//�ֶ���ʾ����ĺ��ӡ�
	        void OnDisplayChildChanging(ViewFlipper view, int index);//�ӿڻ��ID
	    }
	 @Override
	public void showPrevious() {//�ֶ���ʾǰ��ĺ��ӡ�
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
	          TouchEventss.commOnTouchEvent(ev);  //������View���Ƶ���Ӧ����  
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
