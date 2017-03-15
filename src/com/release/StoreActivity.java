package com.release;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.putianxia.R;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.release.SpinerPopWindow.SelectedCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.widget.AdapterView.OnItemClickListener;

public class StoreActivity extends Activity {

	private Context context;
	// 下拉框对象
	private SpinerPopWindow<String> mySpinerPopWindow1, mySpinerPopWindow2, mySpinerPopWindow3, mySpinerPopWindow4,
			mySpinerPopWindow5, mySpinerPopWindow6, mySpinerPopWindow7, mySpinerPopWindow8, mySpinerPopWindow9,
			mySpinerPopWindow10;
	// 适配下拉框内容的list
	private List<String> list1, list2, list3, list4, list5, list6, list7, list8, list9, list10;

	// 包含下拉框的TextView
	private TextView pyarea2, pyactualarea2, pyrent2, pystaltype2, pycondition2, pyfloor2, pyfloor3, pybusiness2,
			pyaddress2, pyaddress3;
	private GridView gridView;
	private MyGridAdapter gridAdapter;
	private ImageButton backButton;
	private Button btreset, btconfirm;
	private static final int REQUEST_PICK = 0;
	// 存放所有选择的照片
	private ArrayList<String> allSelectedPicture = new ArrayList<String>();
	// 存放从选择界面选择的照片
	private ArrayList<String> selectedPicture = new ArrayList<String>();

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		context = this;
		// 默认activity启动时输入法是不开启的
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.threadPriority(Thread.NORM_PRIORITY - 2)// 设置当前线程的优先级
				.denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())// 使用MD5对UIL进行加密命名
				.discCacheSize(100 * 1024 * 1024)// 50 Mb sd卡(本地)缓存的最大值
				.discCacheFileCount(300)// 可以缓存的文件数量
				.tasksProcessingOrder(QueueProcessingType.LIFO)// 后进先出
				.build();
		// 初始化操作
		ImageLoader.getInstance().init(config);

		backButton = (ImageButton) findViewById(R.id.store_back);
		backButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});

		initData();
		createSpinerPopWindow();
		initView();
	}

	// 创建SpinerPopWindow对象
	private void createSpinerPopWindow() {
		mySpinerPopWindow1 = new SpinerPopWindow<String>(this, list1);
		mySpinerPopWindow2 = new SpinerPopWindow<String>(this, list2);
		mySpinerPopWindow3 = new SpinerPopWindow<String>(this, list3);
		mySpinerPopWindow4 = new SpinerPopWindow<String>(this, list4);
		mySpinerPopWindow5 = new SpinerPopWindow<String>(this, list5);
		mySpinerPopWindow6 = new SpinerPopWindow<String>(this, list6);
		mySpinerPopWindow7 = new SpinerPopWindow<String>(this, list7);
		mySpinerPopWindow8 = new SpinerPopWindow<String>(this, list8);
		mySpinerPopWindow9 = new SpinerPopWindow<String>(this, list9);
		mySpinerPopWindow10 = new SpinerPopWindow<String>(this, list10);
	}

	// 实例化各个Textview并设置监听
	private void initView() {
		gridView = (GridView) findViewById(R.id.sgridview);
		// pyedit = (TextView) findViewById(R.id.pyedit);
		pyarea2 = (TextView) findViewById(R.id.pyarea2);
		pyactualarea2 = (TextView) findViewById(R.id.pyactualarea2);
		pyrent2 = (TextView) findViewById(R.id.pyrent2);
		pystaltype2 = (TextView) findViewById(R.id.pystaltype2);
		pycondition2 = (TextView) findViewById(R.id.pycondition2);
		pyfloor2 = (TextView) findViewById(R.id.pyfloor2);
		pyfloor3 = (TextView) findViewById(R.id.pyfloor3);
		pybusiness2 = (TextView) findViewById(R.id.pybusiness2);
		pyaddress2 = (TextView) findViewById(R.id.pyaddress2);
		pyaddress3 = (TextView) findViewById(R.id.pyaddress3);
		btreset = (Button) findViewById(R.id.btreset);
		btconfirm = (Button) findViewById(R.id.btconfirm);
		// backButton = (Button) findViewById(R.id.store_back);
		// pylabel1 = (TextView) findViewById(R.id.pylabel1);
		// pydescribe2 = (TextView) findViewById(R.id.pydescribe2);

		gridAdapter = new MyGridAdapter();
		gridView.setAdapter(gridAdapter);

		btreset.setOnClickListener(clickListener);
		btconfirm.setOnClickListener(clickListener);
		pyarea2.setOnClickListener(clickListener);
		pyactualarea2.setOnClickListener(clickListener);
		pyrent2.setOnClickListener(clickListener);
		pystaltype2.setOnClickListener(clickListener);
		pycondition2.setOnClickListener(clickListener);
		pyfloor2.setOnClickListener(clickListener);
		pyfloor3.setOnClickListener(clickListener);
		pybusiness2.setOnClickListener(clickListener);
		pyaddress2.setOnClickListener(clickListener);
		pyaddress3.setOnClickListener(clickListener);
	}

	// 初始化数据
	private void initData() {
		String[] name1 = getResources().getStringArray(R.array.area);
		String[] name2 = getResources().getStringArray(R.array.actualarea);
		String[] name3 = getResources().getStringArray(R.array.rent);
		String[] name4 = getResources().getStringArray(R.array.staltype);
		String[] name5 = getResources().getStringArray(R.array.condition);
		String[] name6 = getResources().getStringArray(R.array.floor1);
		String[] name7 = getResources().getStringArray(R.array.floor2);
		String[] name8 = getResources().getStringArray(R.array.business);
		String[] name9 = getResources().getStringArray(R.array.address1);
		String[] name10 = getResources().getStringArray(R.array.address2);
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		list3 = new ArrayList<String>();
		list4 = new ArrayList<String>();
		list5 = new ArrayList<String>();
		list6 = new ArrayList<String>();
		list7 = new ArrayList<String>();
		list8 = new ArrayList<String>();
		list9 = new ArrayList<String>();
		list10 = new ArrayList<String>();
		for (int i = 0; i < name1.length; i++) {
			list1.add(name1[i]);
		}
		for (int i = 0; i < name2.length; i++) {
			list2.add(name2[i]);
		}
		for (int i = 0; i < name3.length; i++) {
			list3.add(name3[i]);
		}
		for (int i = 0; i < name4.length; i++) {
			list4.add(name4[i]);
		}
		for (int i = 0; i < name5.length; i++) {
			list5.add(name5[i]);
		}
		for (int i = 0; i < name6.length; i++) {
			list6.add(name6[i]);
		}
		for (int i = 0; i < name7.length; i++) {
			list7.add(name7[i]);
		}
		for (int i = 0; i < name8.length; i++) {
			list8.add(name8[i]);
		}
		for (int i = 0; i < name9.length; i++) {
			list9.add(name9[i]);
		}
		for (int i = 0; i < name10.length; i++) {
			list10.add(name10[i]);
		}
	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btreset:
				break;
			case R.id.btconfirm:
				break;
			case R.id.pyarea2:
				mySpinerPopWindow1.setWidth(pyarea2.getWidth());
				mySpinerPopWindow1.showAsDropDown(pyarea2);
				mySpinerPopWindow1.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow1.dismiss();
						pyarea2.setText(value);
					}
				});
				break;
			case R.id.pyactualarea2:
				mySpinerPopWindow2.setWidth(pyactualarea2.getWidth());
				mySpinerPopWindow2.showAsDropDown(pyactualarea2);
				mySpinerPopWindow2.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow2.dismiss();
						pyactualarea2.setText(value);
					}
				});
				break;
			case R.id.pyrent2:
				mySpinerPopWindow3.setWidth(pyrent2.getWidth());
				mySpinerPopWindow3.showAsDropDown(pyrent2);
				mySpinerPopWindow3.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow3.dismiss();
						pyrent2.setText(value);
					}
				});
				break;
			case R.id.pystaltype2:
				mySpinerPopWindow4.setWidth(pystaltype2.getWidth());
				mySpinerPopWindow4.showAsDropDown(pystaltype2);
				mySpinerPopWindow4.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow4.dismiss();
						pystaltype2.setText(value);
					}
				});
				break;
			case R.id.pycondition2:
				mySpinerPopWindow5.setWidth(pycondition2.getWidth());
				mySpinerPopWindow5.showAsDropDown(pycondition2);
				mySpinerPopWindow5.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow5.dismiss();
						pycondition2.setText(value);
					}
				});
				break;
			case R.id.pyfloor2:
				mySpinerPopWindow6.setWidth(pyfloor2.getWidth());
				mySpinerPopWindow6.showAsDropDown(pyfloor2);
				mySpinerPopWindow6.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow6.dismiss();
						pyfloor2.setText(value);
					}
				});
				break;
			case R.id.pyfloor3:
				mySpinerPopWindow7.setWidth(pyfloor3.getWidth());
				mySpinerPopWindow7.showAsDropDown(pyfloor3);
				mySpinerPopWindow7.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow7.dismiss();
						pyfloor3.setText(value);
					}
				});
				break;
			case R.id.pybusiness2:
				mySpinerPopWindow8.setWidth(pybusiness2.getWidth());
				mySpinerPopWindow8.showAsDropDown(pybusiness2);
				mySpinerPopWindow8.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow8.dismiss();
						pybusiness2.setText(value);
					}
				});
				break;
			case R.id.pyaddress2:
				mySpinerPopWindow9.setWidth(pyaddress2.getWidth());
				mySpinerPopWindow9.showAsDropDown(pyaddress2);
				mySpinerPopWindow9.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow9.dismiss();
						pyaddress2.setText(value);
					}
				});
				break;
			case R.id.pyaddress3:
				mySpinerPopWindow10.setWidth(pyaddress3.getWidth());
				mySpinerPopWindow10.showAsDropDown(pyaddress3);
				mySpinerPopWindow10.setSelectedCallback(new SelectedCallback<String>() {
					@Override
					public void onSelect(String value) {
						mySpinerPopWindow10.dismiss();
						pyaddress3.setText(value);
					}
				});
				break;
			default:
				break;
			}
		}
	};
    /**
     * gridView的适配器
     * @author Administrator
     *
     */
	class MyGridAdapter extends BaseAdapter {

		public LayoutInflater layoutInflater = LayoutInflater.from(context);

		public int getCount() {
			return allSelectedPicture.size() + 1;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int positon) {
			return positon;
		}

		@Override
		public View getView(final int position, View contextView, ViewGroup parent) {
			ViewHolder holder = null;
			if (contextView == null) {
				holder = new ViewHolder();
				contextView = layoutInflater.inflate(R.layout.childgrid_item, null);
				holder.image = (ImageView) contextView.findViewById(R.id.child_iv);
				holder.btn = (Button) contextView.findViewById(R.id.child_delete);
				holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
				contextView.setTag(holder);
			} else {
				holder = (ViewHolder) contextView.getTag();
			}
			if (position == allSelectedPicture.size()) {
				holder.image
						.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_addpic));
				holder.btn.setVisibility(View.GONE);

				holder.image.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						selectClick();
					}
				});
				if (position == 3) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				ImageLoader.getInstance().displayImage("file://" + allSelectedPicture.get(position), holder.image);

				holder.btn.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// 点击后移除图片
						allSelectedPicture.remove(position);
                        
						// 更新UI
						gridView.setAdapter(gridAdapter);
					}
				});
			}
			return contextView;
		}

		class ViewHolder {
			public ImageView image;
			public Button btn;
		}
	}
    /**
     * 跳转到选择图片页面
     */
	private void selectClick() {

		Intent intent = new Intent();
		intent.setClass(StoreActivity.this, SelectPictureActivity.class);

		Bundle bundle = new Bundle();
		bundle.putStringArrayList("allSelectedPicture", allSelectedPicture);
		intent.putExtras(bundle);

		if (allSelectedPicture.size() < 3) {
			startActivityForResult(intent, REQUEST_PICK);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			selectedPicture = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
			for (String str : selectedPicture) {
				if (!allSelectedPicture.contains(str)) {
					allSelectedPicture.add(str);

					gridView.setAdapter(gridAdapter);
				}
			}
		}
	}

	/**
	 * 上传json数据
	 */
	private void postJson() {
	    //申明给服务端传递一个json串
		 MediaType JSON=MediaType.parse("application/json; charset=utf-8");
		 JsonObject json1 = new JsonObject();
		 String json = json1.toString();
	    //创建一个OkHttpClient对象
	    OkHttpClient okHttpClient = new OkHttpClient();
	    //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
	    //json为String类型的json数据
	    RequestBody requestBody = RequestBody.create(JSON, json);
	    //创建一个请求对象
	    Request request = new Request.Builder()
	            .url("http://192.168.0.102:8080/TestProject/JsonServlet")
	            .post(requestBody)
	            .build();
	    //发送请求获取响应
	    try {
	    Response response=okHttpClient.newCall(request).execute();
	        //判断请求是否成功
	        if(response.isSuccessful()){
	            //打印服务端返回结果
	            Log.i("TAG",response.body().string());

	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	/**
	 * 上传一张或多张图片
	 */
	private void upload() {
		try {
			URL url = new URL("");
			// 参数类型
			MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
			// 创建OkHttpClient实例
			OkHttpClient client = new OkHttpClient();

			MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

			for (int i = 0; i < selectedPicture.size(); i++) {
				File f = new File(selectedPicture.get(i));
				if (f != null) {
					builder.addFormDataPart("img", f.getName(),
							RequestBody.create(MEDIA_TYPE_PNG, new File(selectedPicture.get(i))));// RequestBody.create(MEDIA_TYPE_PNG,
																					// f)
				}
			}
			/*
			 * //遍历map中所有参数到builder for (String key : map.keySet()) {
			 * builder.addFormDataPart(key, map.get(key)); }
			 * 
			 * 
			 * //遍历paths中所有图片绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key for
			 * (String path : paths) { builder.addFormDataPart("upload", null,
			 * RequestBody.create(MEDIA_TYPE_PNG, new File(path))); }
			 */

			// 构建请求体
			RequestBody requestBody = builder.build();

			// 构建请求
			Request request = new Request.Builder().url(url)// 地址
					.post(requestBody)// 添加请求体
					.build();

			// 发送异步请求，同步会报错，Android4.0以后禁止在主线程中进行耗时操作
			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Call arg0, IOException arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onResponse(Call arg0, Response arg1) throws IOException {
					// TODO Auto-generated method stub

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
