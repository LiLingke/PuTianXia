package icss.android.network.linstener;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by echen5 on 5/13/2016.
 */
public class IcssErrorResponseListenerWrapper implements Response.ErrorListener {

    private Response.ErrorListener originalListener;

    private HttpExecutorInterface executor;

    public IcssErrorResponseListenerWrapper(HttpExecutorInterface executor, Response.ErrorListener originalListener) {
        this.executor = executor;
        this.originalListener = originalListener;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        this.originalListener.onErrorResponse(error);

        this.executor.onFinish();
    }
}
