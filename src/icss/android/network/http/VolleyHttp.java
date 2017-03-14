package icss.android.network.http;
import icss.android.network.linstener.GsonLinstener;
import icss.android.network.linstener.JsonLinstener;
import icss.android.network.linstener.TextLinstener;
import icss.android.network.linstener.XmlLinstener;
import icss.android.network.utils.VolleyRequestQueueUtils;
import java.util.Map;
import org.json.JSONObject;
import com.android.volley.Request.Method;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.ImageView;

public class VolleyHttp{
	private static String TAG_stop="gj_http";
	private static  String TAG="";
	private static Context context;
	public VolleyHttp(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		TAG=getRunningActivityName(context);
	}
	private String getRunningActivityName(Context context){          
        ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);  
        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();  
        return runningActivity;                 
	}
	public void HttpGetString(String url,TextLinstener listener){
		StringHttpExecutor<String> StringHttp=new StringHttpExecutor<String>(context, url, Method.GET, listener.getListener(), listener.getErrorListener(), TAG);
		StringHttp.execute();
	}
	public void HttpGetString(String url,TextLinstener listener,String tag,String dialogText,String dialogTitle){
		StringHttpExecutor<String> StringHttp=new StringHttpExecutor<String>(context, url, Method.GET, listener.getListener(), listener.getErrorListener(), tag, dialogText, dialogTitle);
		StringHttp.execute();
	}
	public void HttpGetString(String url,TextLinstener listener,String dialogText,String dialogTitle){
		StringHttpExecutor<String> StringHttp=new StringHttpExecutor<String>(context, url, Method.GET, listener.getListener(), listener.getErrorListener(), TAG, dialogText, dialogTitle);
		StringHttp.execute();
	}
	public void HttppostString(String url,TextLinstener listener,final Map<String, String>map){
		StringHttpExecutor<String> StringHttp=new StringHttpExecutor<String>(context, url, Method.POST, listener.getListener(), listener.getErrorListener(), TAG,map);
		StringHttp.execute();
	}
	public void HttppostString(String url,TextLinstener listener,final Map<String, String>map,String dialogTitle,String dialogText){
		StringHttpExecutor<String> StringHttp=new StringHttpExecutor<String>(context, url, Method.POST,listener.getListener(), listener.getErrorListener(), TAG, dialogText, dialogTitle, map);
		StringHttp.execute();
	}
	public void HttppostString(String url,TextLinstener listener,final Map<String, String>map,String tag,String dialogText,String dialogTitle){
		StringHttpExecutor<String> StringHttp=new StringHttpExecutor<String>(context, url, Method.POST,listener.getListener(), listener.getErrorListener(), tag, dialogText, dialogTitle, map);
		StringHttp.execute();
	}
	public void HttpGetJsonObject(String url,JsonLinstener linstener,String dialogText,String dialogTitle){
		JsonHttpExecutor<JSONObject> jsonhttp=new JsonHttpExecutor<JSONObject>(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), TAG, dialogText, dialogTitle);
		jsonhttp.execute();
		}
	public void HttpGetJsonObject(String url,JsonLinstener linstener,String dialogText,String dialogTitle,String tag){
		JsonHttpExecutor<JSONObject> jsonhttp=new JsonHttpExecutor<JSONObject>(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), tag, dialogText, dialogTitle);
		jsonhttp.execute();
		}
	public void HttpGetJsonObject(String url,JsonLinstener linstener){
		JsonHttpExecutor<JSONObject> jsonhttp=new JsonHttpExecutor<JSONObject>(context, Method.GET, TAG, url, linstener.getErrorListener(), linstener.getListener());
		jsonhttp.execute();

		}
	public void HttpPostJsonObject(String url,JsonLinstener linstener,String dialogText,String dialogTitle,final Map<String, String> map,String tag){
		JsonHttpExecutor<JSONObject> jsonhttp=new JsonHttpExecutor<JSONObject>(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), tag, dialogText, dialogTitle,map);
		jsonhttp.execute();
		}
	public void HttpPostJsonObject(String url,JsonLinstener linstener,String dialogText,String dialogTitle,final Map<String, String> map){
		JsonHttpExecutor<JSONObject> jsonhttp=new JsonHttpExecutor<JSONObject>(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), TAG, dialogText, dialogTitle,map);
		jsonhttp.execute();
		}
	public void HttpPostJsonObject(String url,JsonLinstener linstener,final Map<String, String> map){
		JsonHttpExecutor<JSONObject> jsonhttp=new JsonHttpExecutor<JSONObject>(context, Method.POST, TAG, url, linstener.getErrorListener(), linstener.getListener(),map);
		jsonhttp.execute();
	}
	public void HttpGetXml(String url,XmlLinstener linstener){
		XmlHttpExecutor XmlHttp=new XmlHttpExecutor(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), TAG);
		XmlHttp.execute();
	}
	public 	void HttpGetXml(String url,XmlLinstener linstener,String tag,String dialogText,String dialogTitle){
		XmlHttpExecutor XmlHttp=new XmlHttpExecutor(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), tag, dialogText, dialogTitle);
		XmlHttp.execute();
	}
	public void HttpGetXml(String url,XmlLinstener linstener,String dialogText,String dialogTitle){
		XmlHttpExecutor XmlHttp=new XmlHttpExecutor(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), TAG, dialogText, dialogTitle);
		XmlHttp.execute();
	}
	public 	void HttpPostXml(String url,XmlLinstener linstener,final Map<String, String>map,String tag,String dialogText,String dialogTitle){
		XmlHttpExecutor XmlHttp=new XmlHttpExecutor(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), tag, dialogText, dialogTitle,map);
		XmlHttp.execute();
	}
	public 	void HttpPostXml(String url,XmlLinstener linstener,final Map<String, String>map,String dialogText,String dialogTitle){
		XmlHttpExecutor XmlHttp=new XmlHttpExecutor(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), TAG, dialogText, dialogTitle,map);
		XmlHttp.execute();
	}
	public void HttpPostXml(String url,XmlLinstener linstener,final Map<String, String>map){
		XmlHttpExecutor XmlHttp=new XmlHttpExecutor(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), TAG,map);
		XmlHttp.execute();
	}
	public void HttpImage(ImageView imageView, String url,int defaultImageResId, int errorImageResId,int maxWidth,int maxHeight){
		ImageHttpExecutor ImageHttp=new ImageHttpExecutor(url, imageView, defaultImageResId, errorImageResId, maxHeight, maxWidth);
		ImageHttp.execute();

	}
	
	public void HttpImage(ImageView imageView, String url,int defaultImageResId, int errorImageResId){
		ImageHttpExecutor ImageHttp=new ImageHttpExecutor(url, imageView, defaultImageResId, errorImageResId);
		ImageHttp.execute();
	}
	 
		public void stop(){
			VolleyRequestQueueUtils.getRequestQueue().cancelAll(TAG);
		};
		public void stop(String tag){
			VolleyRequestQueueUtils.getRequestQueue().cancelAll(tag);
		};
	
		static public class Gson_http<T> {
			
			void GosnjsonGet(String url,Class<T> cls,GsonLinstener<T> linstener){
				GsonHttpExecutor<T> GsonHttp=new GsonHttpExecutor<T>(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), TAG, cls);
				GsonHttp.execute();
			}
			void GosnjsonGet(String url,Class<T> cls,GsonLinstener<T> linstener,String dialogText,String dialogTitle,String tag){
				GsonHttpExecutor<T> GsonHttp=new GsonHttpExecutor<T>(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), tag,dialogText,dialogTitle, cls);
				GsonHttp.execute();
			}
			void GosnjsonGet(String url,Class<T> cls,GsonLinstener<T> linstener,String dialogText,String dialogTitle){
				GsonHttpExecutor<T> GsonHttp=new GsonHttpExecutor<T>(context, url, Method.GET, linstener.getListener(), linstener.getErrorListener(), TAG,dialogText,dialogTitle, cls);
				GsonHttp.execute();
			}
			void GosnjsonPost(String url,Class<T> cls,GsonLinstener<T> linstener,String dialogText,String dialogTitle,Map<String, String> map){
				
				GsonHttpExecutor<T> GsonHttp=new GsonHttpExecutor<T>(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), TAG,dialogText,dialogTitle, cls,map);
				GsonHttp.execute();
			
			} 
			void GosnjsonPost(String url,Class<T> cls,GsonLinstener<T> linstener,String dialogText,String dialogTitle,String tag,Map<String, String> map){	
				GsonHttpExecutor<T> GsonHttp=new GsonHttpExecutor<T>(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), tag,dialogText,dialogTitle, cls,map);
				GsonHttp.execute();
			} 
			void GosnjsonPost(String url,Class<T> cls,GsonLinstener<T> linstener,Map<String, String> map){	
				GsonHttpExecutor<T> GsonHttp=new GsonHttpExecutor<T>(context, url, Method.POST, linstener.getListener(), linstener.getErrorListener(), TAG, cls,map);
				GsonHttp.execute();
			} 
		}
		public static Context getContext() {
			return context;
		}
}
