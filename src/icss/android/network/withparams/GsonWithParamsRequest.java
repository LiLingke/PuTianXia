package icss.android.network.withparams;

import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import icss.android.network.auxiliary.GsonRequest;

public class GsonWithParamsRequest<T> extends GsonRequest<T>{
	private Map<String, String>map;
	public GsonWithParamsRequest(int method, String url, Class<T> clazz,
			Listener<T> listener, ErrorListener errorListener,Map<String, String>map) {
		super(method, url, clazz, listener, errorListener);
		this.map=map;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return map;
	}
}
