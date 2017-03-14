package icss.android.network.linstener;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by echen5 on 5/13/2016.
 */
public class IcssImageListenerWrapper implements ImageLoader.ImageListener {

    private ImageLoader.ImageListener imageListener;

    private HttpExecutorInterface executor;

    public IcssImageListenerWrapper(HttpExecutorInterface executor, ImageLoader.ImageListener imageListener) {
        this.executor = executor;
        this.imageListener = imageListener;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        this.imageListener.onResponse(response, isImmediate);
        this.executor.onFinish();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        this.imageListener.onErrorResponse(error);
        this.executor.onFinish();
    }
}
