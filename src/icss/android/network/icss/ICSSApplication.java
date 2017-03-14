package icss.android.network.icss;

import icss.android.network.http.SingletonRequestQueue;

import com.android.volley.RequestQueue;

import android.app.Application;

public class ICSSApplication extends Application{
    public static ICSSApplication INSTANCE = null;
    private RequestQueue requestQueue;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		INSTANCE = this;
		requestQueue = SingletonRequestQueue.getSingletonRequestQueue(this);
		//持有静态变量的引用

	}
	public static ICSSApplication getINSTANCE() {
		return INSTANCE;
	}
	public RequestQueue getRequestQueue() {
		return requestQueue;
	}
}
