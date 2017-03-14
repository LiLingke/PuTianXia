package icss.android.network.linstener;

import icss.android.network.http.VolleyHttp;

import org.xmlpull.v1.XmlPullParser;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public abstract class XmlLinstener {
	private Response.ErrorListener ErrorListener;
	private Response.Listener<XmlPullParser> Listener;
	public XmlLinstener() {
		// TODO Auto-generated constructor stub
	
		ErrorListener=new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
	
				onerrorResponse(arg0);
			}
		};
		Listener=new Response.Listener<XmlPullParser>() {

			@Override
			public void onResponse(XmlPullParser arg0) {
				// TODO Auto-generated method stub
		
				onresponse(arg0);
			}
		};
	
	}
	public abstract void onerrorResponse(VolleyError error);
	public abstract void onresponse(XmlPullParser xml);
	public Response.ErrorListener getErrorListener() {
		return ErrorListener;
	}
	public Response.Listener<XmlPullParser> getListener() {
		return Listener;
	}
}
