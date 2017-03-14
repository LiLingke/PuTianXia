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
	//����һ��DownBitmap����
	public static synchronized DownBitmap getInstance(){
		if (my == null) {
			my = new DownBitmap();
		}
		return my;
	}
	//��ȡ����ͼƬ����ʱ���ص���
	public  InputStream getInputStream (String Burl){
		//ʹ��get����ķ�ʽ��ȡͼƬ��Դ
		HttpGet httpGet = new HttpGet(Burl);
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 5*1000);
		HttpConnectionParams.setSoTimeout(httpParams, 30*1000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		try {
			HttpResponse result = httpClient.execute(httpGet);
			if (result.getStatusLine().getStatusCode() == 200) {
				return result.getEntity().getContent();  //��ȡ���������ص�������
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}

