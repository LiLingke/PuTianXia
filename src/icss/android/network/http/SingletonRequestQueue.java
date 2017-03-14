package icss.android.network.http;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by echen5 on 5/11/2016.
 */
public class SingletonRequestQueue {

    public static RequestQueue INSTANCE = null;
    private SingletonRequestQueue() {

    }
/*
 * synchronized当它用来修饰一个方法或者一个代码块的时候，能够保证在同一时刻最多只有一个线程执行该段代码
 * */
    public synchronized static RequestQueue getSingletonRequestQueue(Application context) {

        if (INSTANCE == null) {
            INSTANCE = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }
    public synchronized static RequestQueue getSingletonRequestQueue(Context context) {
        if (context != null) {
            INSTANCE = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }
    public synchronized static RequestQueue getSingletonRequestQueue(Application context, HttpStack httpStack) {

        if (INSTANCE == null) {
            INSTANCE = Volley.newRequestQueue(context, httpStack);
        }
        return INSTANCE;
    }
}

