package icss.android.network.http;

import icss.android.network.linstener.HttpExecutorInterface;
import icss.android.network.linstener.IcssErrorResponseListenerWrapper;
import icss.android.network.linstener.IcssResponseListenerWrapper;
import icss.android.network.utils.VolleyRequestQueueUtils;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

/**
 * Created by echen5 on 5/13/2016.
 */
public abstract class AbstractHttpExecutor<T> implements HttpExecutorInterface {
    protected Context context;

    protected String url;

    protected int method;

    protected String tag;

    private Response.Listener<T> listener;

    private Response.ErrorListener errorListener;

    private ProgressDialog dialog;

    private String dialogText;

    private String dialogTitle = "提示";

    private boolean showDialog=false;

    public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	//do not show dialog
    public AbstractHttpExecutor(Context context, String url, int method, Response.Listener<T> listener, Response.ErrorListener errorListener, String tag) {
        
    	this.context = context;
        this.errorListener = errorListener;
        this.listener = listener;
        this.method = method;
        this.tag = tag;
        this.url = url;
    }

    //show dialog
    public AbstractHttpExecutor(Context context, String url, int method, Response.Listener<T> listener, Response.ErrorListener errorListener, String tag, String dialogText, String dialogTitle) {
        this.showDialog = true;
        this.context = context;
        this.dialogText = dialogText;
        this.dialogTitle = dialogTitle;
        this.errorListener = errorListener;
        this.listener = listener;
        this.method = method;
        this.tag = tag;
        this.url = url;
    }

    public abstract Request<T> buildSpecificRequest(Response.Listener<T> listener, Response.ErrorListener errorListener);


    private Request<T> buildRequest() {
        return buildSpecificRequest(wrappListener(this, this.listener), wrappErrorListener(this, this.errorListener));
    }

    private Response.Listener<T> wrappListener(HttpExecutorInterface executor, Response.Listener<T> listener) {
        return new IcssResponseListenerWrapper<T>(executor, listener);
    }

    private Response.ErrorListener wrappErrorListener(HttpExecutorInterface executor, Response.ErrorListener errorListener) {
        return new IcssErrorResponseListenerWrapper(executor, errorListener);
    }

    @Override
    public void execute() {
        onStart();
        VolleyRequestQueueUtils.add(buildRequest(),tag);
    }

    @Override
    public void onStart() {
        if (showDialog) {
            dialog = ProgressDialog.show(context, dialogTitle, dialogText);
        }
    }

    @Override
    public void onFinish() {
        if (showDialog && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
