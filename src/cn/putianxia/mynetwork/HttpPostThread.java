package cn.putianxia.mynetwork;

import android.os.Handler;
import android.os.Message;


public class HttpPostThread implements Runnable{

	private Handler hand;
	private String url;
	private String mycode;
	private String value;
	private MyPost myPost = new MyPost();
	public HttpPostThread(Handler hand,String endParamerse,String mycode,String value){
		this.hand = hand;
		//拼接访问服务器完整的地址
		url = endParamerse;
		this.mycode = mycode;
		this.value = value;
	}

	@Override
	public void run() {
		//获取我们回调主ui的message
		Message msg = hand.obtainMessage();
		String result = myPost.doPost(url, mycode, value);
		msg.what = 200;
		msg.obj = result;
		//给主ui发送消息传递数据
		hand.sendMessage(msg);

	}

}
