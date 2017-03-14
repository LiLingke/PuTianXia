package cn.putianxia.mynetwork;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


public class DownBitmap {
	private DownBitmap(){}
	private static DownBitmap my = null;
	//锁定一个DownBitmap对象
	public static synchronized DownBitmap getInstance(){
		if (my == null) {
			my = new DownBitmap();
		}
		return my;
	}
	//获取网络图片下载时返回的流
	public  InputStream getInputStream (String Burl){
		//使用get请求的方式获取图片资源
		HttpGet httpGet = new HttpGet(Burl);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 5*1000);
		HttpConnectionParams.setSoTimeout(httpParams, 30*1000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		try {
			HttpResponse result = httpClient.execute(httpGet);
			if (result.getStatusLine().getStatusCode() == 200) {
				return result.getEntity().getContent();  //获取服务器返回的输入流
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

