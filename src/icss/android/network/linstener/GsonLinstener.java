package icss.android.network.linstener;

import icss.android.network.http.VolleyHttp;

import org.xmlpull.v1.XmlPullParser;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public abstract class GsonLinstener<T> {
	private Response.ErrorListener ErrorListener;
	private Response.Listener<T> Listener;
	public GsonLinstener() {
		// TODO Auto-generated constructor stub
		ErrorListener=new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
		
				onerrorResponse(arg0);
			}
		};
		Listener=new Response.Listener<T>() {

			@Override
			 public void onResponse(T arg0) {
				// TODO Auto-generated method stub
		
				onresponse(arg0);
			}
		};
	
	}
	public abstract void onerrorResponse(VolleyError error);
	public abstract void onresponse(T entity);
	public Response.ErrorListener getErrorListener() {
		return ErrorListener;
	}
	public Response.Listener<T> getListener() {
		return Listener;
	}
}
