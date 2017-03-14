package icss.android.network.withparams;



import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class StringWithParamsRequest extends StringRequest{
	Map<java.lang.String, java.lang.String> map;
	public StringWithParamsRequest(int method, String url,
			Response.Listener<String> listener, Response.ErrorListener errorListener,Map<java.lang.String, java.lang.String> map) {
		super(method, url, listener, errorListener);
		 this.map = map;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		// TODO Auto-generated method stub
		return map;
	}
}
