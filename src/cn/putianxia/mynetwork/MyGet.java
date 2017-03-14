package cn.putianxia.mynetwork;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class MyGet {
	/*
	 * Get请求工具类
	 */
	public String doGet(String url)throws ClientProtocolException,IOException{
		String result = null;//我们网络交互的返回值
		HttpGet myget = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().getIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 5*1000);
		httpClient.getParams().getIntParameter(HttpConnectionParams.SO_TIMEOUT, 30*1000);
		
		HttpResponse httpResponse = httpClient.execute(myget);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
		}
		return result;
	}
}
