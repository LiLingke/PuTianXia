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
			// �ж��ϴ��Ƿ��¼�ɹ���
			// �ɹ����Զ���¼��������ʾ��¼ҳ��
			view = inflater.inflate(R.layout.main_fragment3, container, false);
			findview();
			if (lostlogi) {
				my_login_register.setVisibility(View.GONE);
				if (marker == 0) {
					my_fragment_shouyi.setVisibility(View.GONE);
					my_fragment_textview7.setVisibility(View.GONE);
					my_fragment_textview1.setText("�ѹ�ע��Դ");
					my_fragment_textview2.setText("��������Դ");
					my_fragment_textview3.setText("����˷�Դ");
					my_fragment_textview4.setText("�ѷ�����Դ");
					my_fragment_textview5.setText("�����з�Դ");
					my_fragment_textview6.setText("�ѽ��׷�Դ");
				} else if (marker == 1) {
					my_fragment_textview1.setText("���Ƽ�����");
					my_fragment_textview2.setText("�ѹ�ע����");
					my_fragment_textview3.setText("����������");
					my_fragment_textview4.setText("���������");
					my_fragment_textview5.setText("�ѷ�������");
					my_fragment_textview6.setText("�����з���");
					my_fragment_textview7.setText("�ѽ��׷�Դ");
					my_fragment_shouyi.setVisibility(View.GONE);
				}

				else {
					my_fragment_shouyi.setVisibility(View.VISIBLE);
					my_fragment_textview1.setText("���Ƽ�����");
					my_fragment_textview2.setText("�ѹ�ע����");
					my_fragment_textview3.setText("����������/��Դ");
					my_fragment_textview4.setText("���������/��Դ");
					my_fragment_textview5.setText("�ѷ�������/��Դ");
					my_fragment_textview6.setText("�����з���");
					my_fragment_textview7.setText("�ѽ��׷�Դ");
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
				setIntent(intent, "�ѹ�ע��Դ", "");
			else if (marker == 1)
				setIntent(intent, "���Ƽ���Դ", "");
			else if (marker == 2)
				setIntent(intent, "���Ƽ���Դ", "");

			break;
		case R.id.my_fragment_textview2:

			if (marker == 0)
				setIntent(intent, "��������Դ", "");
			else if (marker == 1)
				setIntent(intent, "�ѹ�ע��Դ", "");
			else if (marker == 2)
				setIntent(intent, "�ѹ�ע��Դ", "");

			break;
		case R.id.my_fragment_textview3:

			if (marker == 0)
				setIntent(intent, "����˷�Դ", "");
			else if (marker == 1)
				setIntent(intent, "����������", "");
			else if (marker == 2)
				setIntent(intent, "����������/��Դ", "");

			break;
		case R.id.my_fragment_textview4:
			if (marker == 0)
				setIntent(intent, "�ѷ�����Դ", "");
			else if (marker == 1)
				setIntent(intent, "���������", "");
			else if (marker == 2)
				setIntent(intent, "���������/��Դ", "");
			break;
		case R.id.my_fragment_textview5:
			if (marker == 0)
				setIntent(intent, "�����з�Դ", "");
			else if (marker == 1)
				setIntent(intent, "�ѷ�������", "");
			else if (marker == 2)
				setIntent(intent, "�ѷ�������/��Դ", "");
			break;
		case R.id.my_fragment_textview6:
			if (marker == 0)
				setIntent(intent, "�ѽ��׷�Դ", "");
			else if (marker == 1)
				setIntent(intent, "�����з���", "");
			else if (marker == 2)
				setIntent(intent, "�����з���", "");
			break;
		case R.id.my_fragment_textview7:
			if (marker == 1)
				setIntent(intent, "�ѽ��׷���", "");
			else if (marker == 2)
				setIntent(intent, "�ѽ��׷���", "");
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

	// ��Fragment����ӵ�Activity��ص����������ֻ����1�Σ�
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = this.getActivity();

	}

	// ����Fragment�ᱻ�ص�����only1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	// ��Fragment����Activity������ɺ����ã�
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	// ����Fragment
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	// �ָ�Fragmentʱ�ص���������onStart��������ʱ��Ȼ����
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	// ��ͣFragment
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	// ֹͣFragment
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	// ����Fragment��������View����ǵ��ã�
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	// ����Fragment�ᱻ�ص�
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	// Fragment��Activityɾ��ʱ��ص�,only1
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
	}

	private void showShare() {
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle("����");
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl("http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText("���Ƿ����ı�");
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		// oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl("http://sharesdk.cn");
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("���ǲ��������ı�");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite(getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl("http://sharesdk.cn");

		// ��������GUI
		oks.show(context);
	}
}
