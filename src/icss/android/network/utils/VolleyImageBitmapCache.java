package icss.android.network.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by echen5 on 5/13/2016.
 */
public class VolleyImageBitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> mCache;

    private static int DEFAULT_CACHE_SIZE = 10;

    private static VolleyImageBitmapCache INSTANCE = null;

    private VolleyImageBitmapCache(int cacheSize) {
        init(cacheSize);
    }

    public synchronized static ImageLoader.ImageCache getInstance(int cacheSize) {
        if (INSTANCE == null) {
            INSTANCE = new VolleyImageBitmapCache(cacheSize);
        }
        return INSTANCE;
    }

    private void init(int cacheSize) {
        int maxSize = (cacheSize > 0 ? cacheSize : DEFAULT_CACHE_SIZE) * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }


    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
