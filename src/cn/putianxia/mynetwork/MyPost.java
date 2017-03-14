package cn.putianxia.mynetwork;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class MyPost {
	/*
	 * post���󹤾���
	 */
	public String doPost(String url, String mycode, String value) {
		String result = null;
		HttpResponse httpResponse = null;
		HttpPost httpPost = new HttpPost(url);
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				30000);//���ó�ʱ
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 10000);//���ӳ�ʱ
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		// Json�ַ���ƴ��
		nameValuePairs.add(new BasicNameValuePair("mycode", mycode));
		nameValuePairs.add(new BasicNameValuePair("value", value));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
			} else {
				result = null;
			}
		} catch (UnsupportedEncodingException e) {
			result = null;
		} catch (ClientProtocolException e) {
			result = null;
		} catch (IOException e) {
			result = null;
		}
		return result;
	}
}
