package icss.android.network.http;

import icss.android.network.auxiliary.XMLRequest;
import icss.android.network.withparams.XmlWithParamsRequest;

import java.util.Map;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class XmlHttpExecutor extends AbstractHttpExecutor{
	Map<String, String>map;
	public XmlHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, String tag) {
		super(context, url, method, listener, errorListener, tag);
		// TODO Auto-generated constructor stub
	}
	public XmlHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, String tag,String dialogText,String dialogTitle) {
		super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
		// TODO Auto-generated constructor stub

	}
	public XmlHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, String tag,Map<String, String>map) {
		super(context, url, method, listener, errorListener, tag);
		// TODO Auto-generated constructor stub
		this.map=map;
	}
	public XmlHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, String tag,String dialogText,String dialogTitle,Map<String, String>map) {
		super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
		// TODO Auto-generated constructor stub
		this.map=map;
	}
	@Override
	public Request buildSpecificRequest(Listener listener,
			ErrorListener errorListener) {
		// TODO Auto-generated method stub
		if(method==1)
			return new XmlWithParamsRequest(method, url, listener, errorListener, map);
		else
			return new XMLRequest(url, listener, errorListener);
	}

}
