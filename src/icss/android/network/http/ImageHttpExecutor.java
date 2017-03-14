package icss.android.network.http;

import icss.android.network.linstener.HttpExecutorInterface;
import icss.android.network.linstener.IcssImageListenerWrapper;
import icss.android.network.utils.VolleyImageBitmapCache;
import icss.android.network.utils.VolleyRequestQueueUtils;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;


/**
 * Created by echen5 on 5/13/2016.
 */
public class ImageHttpExecutor implements HttpExecutorInterface {

    protected ImageView imageView;

    protected String url;

    protected int defaultImageResId;

    protected int errorImageResId;

    protected int maxHeight;

    protected int maxWidth;


    public ImageHttpExecutor(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
    }

    public ImageHttpExecutor(String url, ImageView imageView, int errorImageResId, int defaultImageResId) {
        this.url = url;
        this.imageView = imageView;
        this.errorImageResId = errorImageResId;
        this.defaultImageResId = defaultImageResId;
    }

    public ImageHttpExecutor(String url, ImageView imageView, int defaultImageResId, int errorImageResId, int maxHeight, int maxWidth) {
        this.url = url;
        this.imageView = imageView;
        this.defaultImageResId = defaultImageResId;
        this.errorImageResId = errorImageResId;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    @Override
    public void execute() {

        onStart();
        ImageLoader imageLoader = new ImageLoader(VolleyRequestQueueUtils.getRequestQueue(), VolleyImageBitmapCache.getInstance(10));
        ImageLoader.ImageListener imageListener = wrappListener(imageLoader.getImageListener(imageView,defaultImageResId, errorImageResId));
        imageLoader.get(url,imageListener);
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onStart() {

    }

    private ImageLoader.ImageListener wrappListener(ImageLoader.ImageListener listener) {
        return new IcssImageListenerWrapper(this, listener);
    }

}
