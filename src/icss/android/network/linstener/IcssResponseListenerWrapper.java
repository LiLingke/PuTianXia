package icss.android.network.linstener;

import com.android.volley.Response;

/**
 * Created by echen5 on 5/13/2016.
 */
public class IcssResponseListenerWrapper<T> implements Response.Listener<T> {

    private Response.Listener<T> originalListener;

    private HttpExecutorInterface executor;

    public IcssResponseListenerWrapper(HttpExecutorInterface executor, Response.Listener<T> originalListener) {
        this.executor = executor;
        this.originalListener = originalListener;
    }


    @Override
    public void onResponse(T response) {

        this.originalListener.onResponse(response);

        this.executor.onFinish();
    }
}
