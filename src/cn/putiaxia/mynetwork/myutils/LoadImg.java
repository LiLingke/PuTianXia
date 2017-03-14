package cn.putiaxia.mynetwork.myutils;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.putianxia.mynetwork.DownBitmap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

/**
 * ����ͼƬ�ļ�����
 * 
 * �б��ڻ�������ʱ,û��ͼƬ���������,�����浽sdcard��
 * imageCaches ����ȥ,ʹ�������ý��з�װ������ڴ治��ʱ
 * ���ǵ�imageCaches ���е�Bitmap����ᱻ�����,ͼƬ���ͷŵ�
 * �ٴ���Ҫ���ص�ʱ���ȴ�1�����浱�л�ȡ�����û�еĻ���ȥ
 * ���ػ�ȡ������Ҳ��ȡ�����Ļ���ȥ�������ء�
 * һ���������ã�����listview���иոջ�������item��ʾ��ͼƬ���б���
 * �����������ã�����listview���кܾ�ǰ�鿴��ͼƬ���Ѿ����ͷŵ�ͼƬ
 * ���б���
 * */
public class LoadImg {
	//����ͼƬ������߳���
		private static final int Max = 5;
		//ͼƬ��һ������,���������ǳ����ڲ�
		//���һ������ֻ���������ã����ڴ�ռ��㹻�������������Ͳ��������������ڴ�ռ䲻���ˣ�
		//�ͻ������Щ������ڴ档ֻҪ����������û�л��������ö���Ϳ��Ա�����ʹ�á������ÿ�����ʵ���ڴ����еĸ��ٻ���
		private Map<String,SoftReference<Bitmap>> imageCaches = null;

		//�鿴���ػ��湤����
		private FileUtiles fileUtiles;
		//android �ṩ�����ǵ�һ���̳߳�,ʹ�÷���
		private ExecutorService threadPools = null;

		//��ʼ���������صı���
		public LoadImg(Context ctx){
			imageCaches = new HashMap<String, SoftReference<Bitmap>>();
			fileUtiles = new FileUtiles(ctx);
		}
		
		//����ͼƬ���
		public Bitmap loadImage(final ImageView imageView,final String imageUrl, 
				final ImageDownloadCallBack imageDownloadCallBack){
			//imageUrl ������Ψһ�ͣ�������Ϊ����map���е�key
			//ͼƬ����
			final String filename = imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.length());
			//����ͼƬ�����صĵ�ַ
			String filepath = fileUtiles.getAbsolutePath()+"/"+filename;
			//����һ�������Ƿ�������ͼƬ,���map�������key�ͷ���true
			if (imageCaches.containsKey(imageUrl)) {
				//�ҵ���ӦͼƬ�����õķ�װ
				SoftReference<Bitmap> soft = imageCaches.get(imageUrl);
				//���������л�ȡͼƬ
				Bitmap bit = soft.get();
				if (bit != null) {
					return bit;
				}
			}
			//�Ӷ��������л�ȡͼƬ
			if (fileUtiles.isBitmap(filename)) {
				Bitmap bit = BitmapFactory.decodeFile(filepath);
				//�ڶ��������л�ȡ��ʱֱ�ӷŵ�һ��������
				imageCaches.put(imageUrl, new SoftReference<Bitmap>(bit));
				return bit;
			}
			//һ�����棬�������涼û�о�ֱ�Ӵ��������
			if (imageUrl != null && !imageUrl.endsWith("")) {
				if (threadPools == null) {
					//ʵ������ô���̳߳�
					threadPools = Executors.newFixedThreadPool(Max);
				}
				//���ػ�ͼƬ�ص�Handler
				final Handler hand = new Handler(){
					public void handleMessage(Message msg){
						//���ͼƬ���سɹ����һص�����Ϊ��ʱ
						if (msg.what == 111 && imageDownloadCallBack != null) {
							Bitmap bit = (Bitmap) msg.obj;
							//���ûص��Զ����������Ľӿڷ�����������
							imageDownloadCallBack.onImageDownload(imageView, bit);
						}
						super.handleMessage(msg);
					}
				};
				
				//����ͼƬ���߳�
				Thread thread = new Thread(){
					public void run(){
						//��������ʱ���ֽ���
						InputStream inputStream = DownBitmap
								.getInstance()
								.getInputStream(imageUrl);
						//��ͼƬѹ��Ϊԭ����һ��
						BitmapFactory.Options op = new BitmapFactory.Options();
						op.inSampleSize = 2;
						Bitmap bit = BitmapFactory.decodeStream(inputStream,null,op);
						if (bit != null) {
							//��ӵ�һ��������ȥ
							imageCaches.put(imageUrl, new SoftReference<Bitmap>(bit));
							//��ӵ�����������ȥ
							fileUtiles.saveBitmap(filename, bit);
							//���ݸ�Handler
							Message msg = hand.obtainMessage();
							msg.what = 111;
							msg.obj = bit;
							hand.sendMessage(msg);
						}
					}
				};
				threadPools.execute(thread);
			}
			return null;
		}
		
		//ͨ���ص���������ͼƬ�ǵĽӿ�
		public interface ImageDownloadCallBack{
			//IamgeView������Ҫ�趨��imageView��Bitmap����Ҫ�趨��ͼƬ
			void onImageDownload(ImageView imageView,Bitmap bitmap);
		}
		
}
