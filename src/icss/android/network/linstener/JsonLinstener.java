package icss.android.network.linstener;

import icss.android.network.http.VolleyHttp;

import org.json.JSONObject;

import android.app.ProgressDialog;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public abstract class JsonLinstener {
	private Response.ErrorListener ErrorListener;
	private Response.Listener<JSONObject> Listener;
	//private Response.Listener<T> Listener_gson;
	public JsonLinstener() {
		// TODO Auto-generated constructor stub
		ErrorListener=new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
//				if(VolleyHttp.bool_dialog)
//					VolleyHttp.dialog.dismiss();
				onerrorResponse(arg0);
			}
		};
		Listener=new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject arg0) {
				// TODO Auto-generated method stub
//				if(VolleyHttp.bool_dialog)
//					VolleyHttp.dialog.dismiss();
				onresponse(arg0);
			}
		};
	
	}
	
	
	
	
	public abstract void onerrorResponse(VolleyError arg0);
	public abstract void onresponse(JSONObject jsonobject);
	public Response.ErrorListener getErrorListener() {
		return ErrorListener;
	}
	public Response.Listener<JSONObject> getListener() {
		return Listener;
	}
	
}
