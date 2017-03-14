package icss.android.network.withparams;

import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import icss.android.network.auxiliary.XMLRequest;

public class XmlWithParamsRequest extends XMLRequest{
	private Map<String, String>map;
	public XmlWithParamsRequest(int method, String url,
			Listener<XmlPullParser> listener, ErrorListener errorListener,Map<String, String>map) {
		super(method, url, listener, errorListener);
		this.map=map;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return map;
	}
}
