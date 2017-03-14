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
 * 控制图片的加载类
 * 
 * 列表在滑动过程时,没有图片会进行下载,并保存到sdcard与
 * imageCaches 当中去,使用软引用进行封装，如果内存不够时
 * 我们的imageCaches 当中的Bitmap对象会被清理掉,图片被释放掉
 * 再次需要加载的时候，先从1级缓存当中获取，如果没有的话，去
 * 本地获取，本地也获取不到的话，去网络下载。
 * 一级缓存作用：对于listview当中刚刚滑动过的item显示的图片进行保存
 * 二级缓存作用：对于listview当中很久前查看的图片或已经被释放掉图片
 * 进行保存
 * */
public class LoadImg {
	//下载图片最大并行线程数
		private static final int Max = 5;
		//图片的一级缓存,保存在我们程序内部
		//如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，
		//就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存
		private Map<String,SoftReference<Bitmap>> imageCaches = null;

		//查看本地缓存工具类
		private FileUtiles fileUtiles;
		//android 提供给我们的一个线程池,使用方便
		private ExecutorService threadPools = null;

		//初始化上面的相关的变量
		public LoadImg(Context ctx){
			imageCaches = new HashMap<String, SoftReference<Bitmap>>();
			fileUtiles = new FileUtiles(ctx);
		}
		
		//加载图片入口
		public Bitmap loadImage(final ImageView imageView,final String imageUrl, 
				final ImageDownloadCallBack imageDownloadCallBack){
			//imageUrl 由于其唯一型，把他作为我们map当中的key
			//图片名称
			final String filename = imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.length());
			//保存图片到本地的地址
			String filepath = fileUtiles.getAbsolutePath()+"/"+filename;
			//查找一级缓存是否有这张图片,如果map中有这个key就返回true
			if (imageCaches.containsKey(imageUrl)) {
				//找到对应图片软引用的封装
				SoftReference<Bitmap> soft = imageCaches.get(imageUrl);
				//从软引用中获取图片
				Bitmap bit = soft.get();
				if (bit != null) {
					return bit;
				}
			}
			//从二级缓存中获取图片
			if (fileUtiles.isBitmap(filename)) {
				Bitmap bit = BitmapFactory.decodeFile(filepath);
				//在二级缓存中获取到时直接放到一级缓存中
				imageCaches.put(imageUrl, new SoftReference<Bitmap>(bit));
				return bit;
			}
			//一级缓存，二级缓存都没有就直接从网络加载
			if (imageUrl != null && !imageUrl.endsWith("")) {
				if (threadPools == null) {
					//实例化我么的线程池
					threadPools = Executors.newFixedThreadPool(Max);
				}
				//下载回图片回调Handler
				final Handler hand = new Handler(){
					public void handleMessage(Message msg){
						//如果图片下载成功并且回调对象不为空时
						if (msg.what == 111 && imageDownloadCallBack != null) {
							Bitmap bit = (Bitmap) msg.obj;
							//调用回调自定义适配器的接口方法传递数据
							imageDownloadCallBack.onImageDownload(imageView, bit);
						}
						super.handleMessage(msg);
					}
				};
				
				//下载图片的线程
				Thread thread = new Thread(){
					public void run(){
						//网络下载时的字节流
						InputStream inputStream = DownBitmap
								.getInstance()
								.getInputStream(imageUrl);
						//把图片压缩为原来的一半
						BitmapFactory.Options op = new BitmapFactory.Options();
						op.inSampleSize = 2;
						Bitmap bit = BitmapFactory.decodeStream(inputStream,null,op);
						if (bit != null) {
							//添加到一级缓存中去
							imageCaches.put(imageUrl, new SoftReference<Bitmap>(bit));
							//添加到二级缓存中去
							fileUtiles.saveBitmap(filename, bit);
							//传递给Handler
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
		
		//通过回调机制设置图片是的接口
		public interface ImageDownloadCallBack{
			//IamgeView是你想要设定的imageView，Bitmap是想要设定的图片
			void onImageDownload(ImageView imageView,Bitmap bitmap);
		}
		
}
