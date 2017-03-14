package icss.android.network.http;

import android.app.Activity;
import android.os.Bundle;

public class NetworkActivity extends Activity{
	protected VolleyHttp voleyhttp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		voleyhttp=new VolleyHttp(this);
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		voleyhttp.stop();
	}
}
