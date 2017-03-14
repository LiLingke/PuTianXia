package icss.android.network.auxiliary;

import android.content.Context;
import icss.android.network.http.VolleyHttp;

public class ProgressVolleyHttp extends VolleyHttp{
	private String dialogMsg = "Loading";
    private boolean showDialog = false;
    public ProgressVolleyHttp(Context context, boolean showDialog, String dialogMsg) {
        super(context);
        this.showDialog = showDialog;
        this.dialogMsg = dialogMsg;
        
    }
   
}
