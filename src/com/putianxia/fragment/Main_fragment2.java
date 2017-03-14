package com.putianxia.fragment;

import com.example.putianxia.R;
import com.putianxia.activity.MainActivity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Main_fragment2 extends Fragment implements OnClickListener {
	private Activity context;
	private View view,popupView1,popupView2;
	
	private TextView fabu_xuqiu,fabu_ziyuan,fbpy,fblp,fbfy,pyxq,fyxq;;
	PopupWindow popupwindow1,popupwindow2;
	OnClickListener onclick1=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			popupwindow1.dismiss();
		}
	};
	OnClickListener onclick2=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			popupwindow2.dismiss();
		}
	};
	OnClickListener onclick=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.pupop_fbpy:
				Toast.makeText(context, "������Դ", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pupop_fbfy:
				Toast.makeText(context, "������Դ", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pupop_fblp:
				Toast.makeText(context, "����¥��", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pupop_fyxq:
				Toast.makeText(context, "��Դ����", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pupop_pyxq:
				Toast.makeText(context, "��Դ����", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 if (null == view)
		 {
			view=inflater.inflate(R.layout.main_fragment2, container, false );
			fabu_xuqiu=(TextView) view.findViewById(R.id.fabu_xuqiu);
			fabu_ziyuan=(TextView) view.findViewById(R.id.fabu_ziyuan);
			fabu_xuqiu.setOnClickListener(this);
			fabu_ziyuan.setOnClickListener(this);
			popupView1=inflater.inflate(R.layout.pupop_fabuziyuan, null);
			fbpy=(TextView) popupView1.findViewById(R.id.pupop_fbpy);
			fbfy=(TextView) popupView1.findViewById(R.id.pupop_fbfy);
			fblp=(TextView) popupView1.findViewById(R.id.pupop_fblp);
			fbpy.setOnClickListener(onclick);
			fbfy.setOnClickListener(onclick);
			fblp.setOnClickListener(onclick);
			popupView1.findViewById(R.id.pupop_lin).setOnClickListener(onclick1);
//			pop1view2.setOnClickListener(onclick1);
			popupView2=inflater.inflate(R.layout.fabu_xuqiu, null);
			pyxq=(TextView) popupView2.findViewById(R.id.pupop_pyxq);
			fyxq=(TextView) popupView2.findViewById(R.id.pupop_fyxq);
			fyxq.setOnClickListener(onclick);
			pyxq.setOnClickListener(onclick);
			popupView2.findViewById(R.id.fbxq_lin).setOnClickListener(onclick2);
	
			 if (getUserVisibleHint())
			 {	
		//	       setAdapyer();
			 }
		 }
		  else
		 {	 
			 ViewGroup parent = (ViewGroup) view.getParent();
	         if (parent != null)
	         {
	             parent.removeView(view);
	         }
		 }
		return view;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//�жϵ�¼��
		//�ж������֤
		switch (arg0.getId()) {
		case R.id.fabu_xuqiu:
			if(MainActivity.login)
				setXuqiu();
			
			break;
		case R.id.fabu_ziyuan:
			if(MainActivity.login)
				setZiyuan();
			break;
		default:
			break;
		}
	}
	void setZiyuan(){
		popupwindow1 = new PopupWindow(popupView1,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT, true);
			popupwindow1.setBackgroundDrawable(new BitmapDrawable());
			popupwindow1.setOutsideTouchable(true);
			popupwindow1.setTouchable(true);
			popupwindow1.setAnimationStyle(R.style.AnimBottom);
			popupwindow1.showAtLocation( view.findViewById(R.id.fragment2_lin), Gravity.CENTER,
					0, 0);
		//	popupwindow1.showAsDropDown(view.findViewById(R.id.fragment2_view));
	}
	void setXuqiu(){
		popupwindow2 = new PopupWindow(popupView2,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, true);
		popupwindow2.setBackgroundDrawable(new BitmapDrawable());
		popupwindow2.setOutsideTouchable(true);
		popupwindow2.setTouchable(true);
		popupwindow2.setAnimationStyle(R.style.AnimBottom);

		popupwindow2.showAtLocation( view.findViewById(R.id.fragment2_lin), Gravity.CENTER,
				0, 0);
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) { 
		// TODO Auto-generated method stub
		 if (isVisibleToUser && isVisible())
	        {
			 //��������
	       //    setAdapyer();
	        }
		super.setUserVisibleHint(isVisibleToUser);
	}
	
	
	
	//��Fragment����ӵ�Activity��ص����������ֻ����1�Σ�
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context=this.getActivity();

	}
	//����Fragment�ᱻ�ص�����only1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	//��Fragment����Activity������ɺ����ã�
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	//����Fragment
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	//�ָ�Fragmentʱ�ص���������onStart��������ʱ��Ȼ����
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	//��ͣFragment
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	//ֹͣFragment
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	//����Fragment��������View����ǵ��ã�
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	//����Fragment�ᱻ�ص�
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	//Fragment��Activityɾ��ʱ��ص�,only1
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}
}
