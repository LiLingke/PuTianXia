package icss.android.network.linstener;
import icss.android.network.http.VolleyHttp;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public abstract class TextLinstener {
	private Response.ErrorListener ErrorListener;
	private Response.Listener<String> Listener;
	public TextLinstener() {
		// TODO Auto-generated constructor stub
	
		ErrorListener=new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub

				onerrorResponse(arg0);
			}
		};
		Listener=new Response.Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
	
				onresponse(arg0);
			}
		};
	
	}
	public abstract void onerrorResponse(VolleyError error);
	public abstract void onresponse(String text);
	public Response.ErrorListener getErrorListener() {
		return ErrorListener;
	}
	public Response.Listener<String> getListener() {
		return Listener;
	}
}
