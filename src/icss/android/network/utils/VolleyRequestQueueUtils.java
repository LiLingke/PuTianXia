package icss.android.network.utils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;





import icss.android.network.http.SingletonRequestQueue;
import icss.android.network.http.VolleyHttp;
import icss.android.network.icss.ICSSApplication;

import java.util.List;

/**
 * Created by echen5 on 5/11/2016.
 */
public class VolleyRequestQueueUtils {

    public static RequestQueue getRequestQueue() {
        return ICSSApplication.getINSTANCE() != null ? ICSSApplication.getINSTANCE().getRequestQueue() : SingletonRequestQueue.getSingletonRequestQueue(VolleyHttp.getContext());
    }

    public static <T extends Request> void add(T request) {
        RequestQueue requestQueue = getRequestQueue();
        requestQueue.add(request);
    }

    public static <T extends Request> void add(T request, String tag) {
        RequestQueue requestQueue = getRequestQueue();
        request.setTag(tag);
        requestQueue.add(request);
    }

    public static void cancelRequestForActivity(final List<String> tags) {
        RequestQueue requestQueue = getRequestQueue();
        requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                if (request.getTag() != null && (request.getTag() instanceof String)) {
                    return tags.contains((String)request.getTag());
                }
                return false;
            }
        });
    }
}

