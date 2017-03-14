package icss.android.network.http;

import icss.android.network.withparams.JsonWithParamsRequest;

import java.util.Map;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by echen5 on 5/13/2016.
 */
public class JsonHttpExecutor<JSONObject> extends AbstractHttpExecutor {
	Map<String, String>map;
    //private String requestBody;

    public JsonHttpExecutor(Context context, int method, String tag, String url, Response.ErrorListener errorListener, Response.Listener listener) {
        super(context, url, method, listener, errorListener, tag);
    //    this.requestBody = requestBody;
    }
    public JsonHttpExecutor(Context context, int method, String tag, String url, Response.ErrorListener errorListener, Response.Listener listener,Map<String, String>map) {
        super(context, url, method, listener, errorListener, tag);
        this.map=map;
    //    this.requestBody = requestBody;
    }
    public JsonHttpExecutor(Context context, String url, int method, Response.Listener listener, Response.ErrorListener errorListener, String tag, String dialogText, String dialogTitle) {
        super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
    //    this.requestBody = requestBody;Map<String, String>map;
    }
    public JsonHttpExecutor(Context context, String url, int method, Response.Listener listener, Response.ErrorListener errorListener, String tag, String dialogText, String dialogTitle,Map<String, String>map) {
        super(context, url, method, listener, errorListener, tag, dialogText, dialogTitle);
        this.map=map;
    }

    @Override
    public Request buildSpecificRequest(Response.Listener listener, Response.ErrorListener errorListener) {
    	if(method==1)
        return new JsonWithParamsRequest(method,url, null, listener, errorListener,map);
    	else
    	return new JsonObjectRequest(method,url, null, listener, errorListener);
    }
}
