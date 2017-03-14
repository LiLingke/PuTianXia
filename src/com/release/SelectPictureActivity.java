package com.release;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.example.putianxia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.putianxia.bin.ImageFloder;
import com.putianxia.bin.ImageItem;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SelectPictureActivity extends Activity {
	// ���ѡ��ͼƬ�ĸ���
	private static int MAX_NUM = 3;
	private static final int TAKE_PICTURE = 520;

	public static final String INTENT_MAX_NUM = "intent_max_num";
	public static final String INTENT_SELECTED_PICTURE = "intent_selected_picture";

	private Context context;
	private GridView gridview;
	private PictureAdapter adapter;

	/**
	 * ��ʱ�ĸ����࣬���ڷ�ֹͬһ���ļ��еĶ��ɨ��
	 */
	private HashMap<String, Integer> tmpDir = new HashMap<String, Integer>();
	private ArrayList<ImageFloder> mDirPaths = new ArrayList<ImageFloder>();

	/**
	 * ��������ͼƬ������ͼƬ����ʾͼƬ�ľ���ִ���࣬������������ķ���displayImage(...)��loadImage(...)��
	 */
	private ImageLoader loader;

	/**
	 * ����ָ��ÿһ��Imageloader��������ͼƬ��״̬���հס����ش����������أ���ʾ��Ӧ��ͼƬ��
	 * �Ƿ񽫻�����ص������ϣ���������ͼƬ������ô���Ĵ���
	 */
	private DisplayImageOptions options;

	private ContentResolver mContentResolver;

	// ѡ��ť����ɰ�ť
	private Button btn_select, btn_ok;

	// ͼƬ�ļ����б�
	private ListView listview;

	private FolderAdapter folderAdapter;

	private ImageFloder imageAll, currentImageFolder;

	// ��ѡ���ͼƬ
	private ArrayList<String> selectedPicture = new ArrayList<String>();

	private String cameraPath = null;

	private int isExisted;

	private ArrayList<String> existedPictureList = new ArrayList<String>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_picture);
		
		MAX_NUM = getIntent().getIntExtra(INTENT_MAX_NUM, 3);
		Bundle bundle = getIntent().getExtras();
		existedPictureList = bundle.getStringArrayList("allSelectedPicture");
		isExisted = existedPictureList.size();

		context = this;
		mContentResolver = getContentResolver();
		loader = ImageLoader.getInstance();

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)// ����ͼƬ�������ڼ���ʾ��ͼƬ

				.showImageForEmptyUri(R.drawable.ic_launcher)// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ

				.showImageOnFail(R.drawable.ic_launcher)// ����ͼƬ����/��������д���ʱ����ʾ��ͼƬ

				.cacheInMemory(true)// �������ص�ͼƬ�Ƿ񻺴����ڴ���

				.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����

				.considerExifParams(true)// ����EXIF��JPEGͼ���ʽ

				.imageScaleType(ImageScaleType.EXACTLY)// ͼ����ȫ��������С��Ŀ���С
				// Bitmap.Config.RGB_565
				.bitmapConfig(Bitmap.Config.RGB_565)// ����ͼƬ�Ľ�������

				.build();

		initView();

	}

	/**
	 * ���"����ͼƬ"��ť�¼�
	 * 
	 * @param v
	 */
	public void select(View v) {
		if (listview.getVisibility() == View.VISIBLE) {
			hideListAnimation();
		} else {
			listview.setVisibility(View.VISIBLE);
			showListAnimation();
			folderAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * �����ɰ�ť
	 */
	public void ok(View v) {
		Intent data = new Intent();
		data.putExtra(INTENT_SELECTED_PICTURE, selectedPicture);
		setResult(RESULT_OK, data);
		this.finish();
	}

	private void initView() {
		imageAll = new ImageFloder();
		imageAll.setDir(getResources().getString(R.string.dir_all_pictures));
		currentImageFolder = imageAll;
		mDirPaths.add(imageAll);

		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok.setText(getResources().getString(R.string.complete) + isExisted
				+ "/" + MAX_NUM);

		btn_select = (Button) findViewById(R.id.btn_select);

		gridview = (GridView) findViewById(R.id.gridview);
		adapter = new PictureAdapter();
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					goCamare();
				}
			}
		});

		listview = (ListView) findViewById(R.id.listview);
		folderAdapter = new FolderAdapter();
		listview.setAdapter(folderAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentImageFolder = mDirPaths.get(position);
				hideListAnimation();
				adapter.notifyDataSetChanged();
				btn_select.setText(currentImageFolder.getName());
			}
		});

		getThumbnail();
	}

	/*
	 * ʹ�������
	 */
	public void goCamare() {
		if (isExisted + selectedPicture.size() + 1 > MAX_NUM) {
			Toast.makeText(
					context,
					getResources().getString(R.string.chose_at_most) + MAX_NUM
							+ getResources().getString(R.string.sheet),
					Toast.LENGTH_SHORT).show();
			return;
		}

		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri imageUri = getOutputMediaFileUri();
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	/**
	 * ��������ʱ��ȡ�����Uri
	 */
	protected Uri getOutputMediaFileUri() {
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Night");
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator
				+ "IMG_" + timeStamp + ".jpg");
		cameraPath = mediaFile.getAbsolutePath();
		return Uri.fromFile(mediaFile);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && cameraPath != null) {

			selectedPicture.add(cameraPath);

			Intent data2 = new Intent();
			data2.putExtra(INTENT_SELECTED_PICTURE, selectedPicture);

			setResult(RESULT_OK, data2);
			this.finish();
		}
	}

	public void back(View v) {
		onBackPressed();
	}

	/**
	 * ��ȡ����ͼ
	 */
	 private void getThumbnail() {
		 if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED))
			{
				Toast.makeText(this, "�����ⲿ�洢", Toast.LENGTH_SHORT).show();
				return;
			}
	        Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
	                new String[] { MediaStore.Images.ImageColumns.DATA }, "", null,
	                MediaStore.MediaColumns.DATE_ADDED + " DESC");

	        if (mCursor.moveToFirst()) {
	            int _date = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
	            do {

	                // ��ȡͼƬ��·��
	                String path = mCursor.getString(_date);
	                imageAll.images.add(new ImageItem(path));

	                // ��ȡ��ͼƬ�ĸ�·����
	                File parentFile = new File(path).getParentFile();
	                if (parentFile == null) {
	                    continue;
	                }

	                ImageFloder imageFloder = null;
	                String dirPath = parentFile.getAbsolutePath();

	                if (!tmpDir.containsKey(dirPath)) {
	                    // ��ʼ��imageFloder
	                    imageFloder = new ImageFloder();
	                    imageFloder.setDir(dirPath);
	                    imageFloder.setFirstImagePath(path);
	                    mDirPaths.add(imageFloder);
	                    tmpDir.put(dirPath, mDirPaths.indexOf(imageFloder));
	                } else {
	                    imageFloder = mDirPaths.get(tmpDir.get(dirPath));
	                }

	                imageFloder.images.add(new ImageItem(path));
	            } while (mCursor.moveToNext());
	        }

	        mCursor.close();
	        tmpDir = null;
	    }


	/**
	 * ����ListView���ƶ��Ķ���Ч��
	 */
	public void showListAnimation() {
		TranslateAnimation ta = new TranslateAnimation(1, 0f, 1, 0f, 1, 1f, 1,
				0f);
		ta.setDuration(200);
		listview.startAnimation(ta);
	}

	/**
	 * ����ListView���ƶ��Ķ���Ч��
	 */
	public void hideListAnimation() {
		TranslateAnimation ta = new TranslateAnimation(1, 0f, 1, 0f, 1, 0f, 1,
				1f);
		ta.setDuration(200);
		listview.startAnimation(ta);
		ta.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				listview.setVisibility(View.INVISIBLE);
			}
		});
	}

	class PictureAdapter extends BaseAdapter {
		public int getCount() {
			return currentImageFolder.images.size() + 1;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// �Զ����һ������������convertview
			ViewHolder holder = null;

			if (convertView == null) {
				convertView = View.inflate(context, R.layout.grid_item_picture,
						null);
				holder = new ViewHolder();
				holder.iv = (ImageView) convertView.findViewById(R.id.iv);
				holder.checkBox = (Button) convertView.findViewById(R.id.check);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == 0) {// ����ͼƬ
				holder.iv
						.setImageResource(R.drawable.pickphotos_to_camera_normal);
				holder.checkBox.setVisibility(View.INVISIBLE);
			} else {
				position = position - 1;
				holder.checkBox.setVisibility(View.VISIBLE);

				final ImageItem item = currentImageFolder.images.get(position);

				// ��ʾͼƬ
				loader.displayImage("file://" + item.path, holder.iv, options);

				// �Ƿ�ѡ��
				boolean isSelected = (selectedPicture.contains(item.path) || existedPictureList
						.contains(item.path));
				holder.checkBox.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						// ��ѡ�е�ͼƬ���������õ����ޣ����ټ���
						if (!v.isSelected()
								&& isExisted + selectedPicture.size() + 1 > MAX_NUM) {
							Toast.makeText(
									context,
									getResources().getString(
											R.string.chose_at_most)
											+ MAX_NUM
											+ getResources().getString(
													R.string.sheet),
									Toast.LENGTH_SHORT).show();
							return;
						}

						// ����ѡ���Ƴ�
						if (selectedPicture.contains(item.path)
								|| existedPictureList.contains(item.path)) {
							selectedPicture.remove(item.path);
						} else {

							// ��������
							selectedPicture.add(item.path);
						}

						btn_ok.setEnabled(selectedPicture.size() > 0);
						btn_ok.setText(getResources().getString(
								R.string.complete)
								+ (selectedPicture.size() + isExisted)
								+ "/"
								+ MAX_NUM);

						v.setSelected(selectedPicture.contains(item.path)
								|| existedPictureList.contains(item.path));
					}
				});

				holder.checkBox.setSelected(isSelected);
			}

			return convertView;

		}

		class ViewHolder {
			ImageView iv;
			Button checkBox;
		}

	}

	class FolderAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mDirPaths.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			FolderViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.list_dri_item,
						null);
				holder = new FolderViewHolder();
				holder.id_dir_item_image = (ImageView) convertView
						.findViewById(R.id.id_dir_item_image);
				holder.id_dir_item_name = (TextView) convertView
						.findViewById(R.id.id_dir_item_name);
				holder.id_dir_item_count = (TextView) convertView
						.findViewById(R.id.id_dir_item_count);
				holder.choose = (ImageView) convertView
						.findViewById(R.id.choose);
				convertView.setTag(holder);
			} else {
				holder = (FolderViewHolder) convertView.getTag();
			}
			ImageFloder item = mDirPaths.get(position);
			loader.displayImage("file://" + item.getFirstImagePath(),
					holder.id_dir_item_image, options);
			holder.id_dir_item_count.setText(item.images.size()
					+ getResources().getString(R.string.sheet));
			holder.id_dir_item_name.setText(item.getName());

			holder.choose
					.setVisibility(currentImageFolder == item ? View.VISIBLE
							: View.GONE);
			return convertView;
		}

		class FolderViewHolder {
			ImageView id_dir_item_image;
			ImageView choose;
			TextView id_dir_item_name;
			TextView id_dir_item_count;
		}
	}
}
