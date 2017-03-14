package com.putianxia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RelativeLayouts extends RelativeLayout{
//	Context context;
	public RelativeLayouts(Context context, AttributeSet attrs) {
		
		super(context, attrs);
	//	this.context=context;
		// TODO Auto-generated constructor stub
	}
	public RelativeLayouts(Context context) {
		super(context);
	//	this.context=context;
	}
	public RelativeLayouts(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	//	this.context=context;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
				getDefaultSize(0, heightMeasureSpec));
		int childWidthSize = getMeasuredWidth();
		int childHightSize = (int) (getMeasuredWidth() / 2.5);//����߶�
		widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize,
				MeasureSpec.EXACTLY);   //MeasureSpec.EXACTLY������ͼϣ������ͼ�Ĵ�СӦ����specSize��ָ���ġ�
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHightSize,
				MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
