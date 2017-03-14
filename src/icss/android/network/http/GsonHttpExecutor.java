package icss.android.network.http;

import java.util.Map;

import icss.android.network.auxiliary.GsonRequest;
import icss.android.network.withparams.GsonWithParamsRequest;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class GsonHttpExecutor<T> extends AbstractHttpExecutor{
	Class<T> cls;
	Map<String, String>map;
	public GsonHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, String tag,Class<T> cls) {
		super(context, url, method, listener, errorListener, tag);
		// TODO Auto-generated constructor stub
		this.cls=cls;
	}
	public GsonHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, String tag,Class<T> cls,Map<String, String>map) {
		super(context, url, method, listener, errorListener, tag);
		// TODO Auto-generated constructor stub
		this.cls=cls;
		this.map=map;
	}
	public GsonHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, 
			String tag,String dialogText,String dialogTitle,Class<T> cls,Map<String, String>map) {
		super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
		// TODO Auto-generated constructor stub
		this.cls=cls;
		this.map=map;
	}
	public GsonHttpExecutor(Context context, String url, int method,
			Listener listener, ErrorListener errorListener, 
			String tag,String dialogText,String dialogTitle,Class<T> cls) {
		super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
		// TODO Auto-generated constructor stub
		this.cls=cls;
	}
	@Override
	public Request buildSpecificRequest(Listener listener,
			ErrorListener errorListener) {
		// TODO Auto-generated method stub
		if(method==1)
			return new GsonWithParamsRequest<T>(method, url, cls, listener, errorListener, map);
		else
			return new GsonRequest<T>(  
			        url, cls,listener, errorListener);
	}

}
