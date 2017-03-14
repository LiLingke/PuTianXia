package icss.android.network.http;

import android.content.Context;
import icss.android.network.withparams.StringWithParamsRequest;
import java.util.Map;
import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.toolbox.StringRequest;

public class StringHttpExecutor<String> extends AbstractHttpExecutor {
	Map<java.lang.String, java.lang.String> map;
	public StringHttpExecutor(Context context, java.lang.String url,
			int method, Response.Listener listener, Response.ErrorListener errorListener,
			java.lang.String tag) {
		super(context, url, method, listener, errorListener, tag);
		// TODO Auto-generated constructor stub
	}
	public StringHttpExecutor(Context context, java.lang.String url,
			int method,Response.Listener listener, Response.ErrorListener errorListener,
			java.lang.String tag, java.lang.String dialogText, java.lang.String dialogTitle) {
		super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
		// TODO Auto-generated constructor stub
	}
	public StringHttpExecutor(Context context, java.lang.String url,
			int method, Response.Listener listener, Response.ErrorListener errorListener,
			java.lang.String tag,Map<java.lang.String, java.lang.String> map) {	
		// TODO Auto-generated constructor stub
		super(context, url, method, listener, errorListener, tag);
		this.map=map;

	}
	public StringHttpExecutor(Context context, java.lang.String url,
			int method, Response.Listener listener, Response.ErrorListener errorListener,
			java.lang.String tag, java.lang.String dialogText, java.lang.String dialogTitle,Map<java.lang.String, java.lang.String> map) {
		super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
		this.map=map;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Request buildSpecificRequest(Response.Listener listener, Response.ErrorListener errorListener) {
		// TODO Auto-generated method stub
		if(method==1)
	        return new StringWithParamsRequest(method, url,listener,errorListener,map);
	    else
	    	return new StringRequest(url,  
	    			listener,errorListener); 
	}

}
