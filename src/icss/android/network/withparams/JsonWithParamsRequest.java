package icss.android.network.withparams;

import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class JsonWithParamsRequest extends JsonObjectRequest{
	private Map<String, String>map;
	public JsonWithParamsRequest(int method, String url,
			JSONObject jsonRequest, Listener<JSONObject> listener,
			ErrorListener errorListener,Map<String, String>map) {
		super(method, url, jsonRequest, listener, errorListener);
		this.map=map;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return map;
	}
}
