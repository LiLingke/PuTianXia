package cn.putianxia.mynetwork;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.os.Handler;
import android.os.Message;

public class HttpGetThread implements Runnable{

	private Handler handler;
	private String url;
	private MyGet myGet = new MyGet();
	
	public HttpGetThread(Handler handler,String endParamerse){
		this.handler = handler;
		url = endParamerse;
	}
	public void run() {
		//获取我们回调主ui的message
		Message message  = handler.obtainMessage();
		try {
			String result = myGet.doGet(url);
			message.what = 200;
			message.obj = result;
		} catch (ClientProtocolException e) {
			message.what = 404;
		}catch (IOException e) {
			message.what  = 100;
		}
		//向主ui发送消息传送数据
		handler.sendMessage(message);
	}
	

}
