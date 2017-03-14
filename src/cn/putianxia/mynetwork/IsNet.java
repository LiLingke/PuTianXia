package cn.putianxia.mynetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
/*
 * ÍøÂçÅÐ¶Ï¹¤¾ß
 */
public class IsNet {
	private Context ctx;

	public IsNet(Context ctx) {
		this.ctx = ctx;
	}

	public boolean IsConnect() {
		ConnectivityManager manager = (ConnectivityManager) ctx
				.getSystemService(ctx.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		State stata = null;
		if (info != null) {
			stata = info.getState();
			if (stata == State.CONNECTED)
				return true;
		}
		info = null;
		info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		stata = null;
		if (info != null) {
			stata = info.getState();
			if (stata == State.CONNECTED) {
				return true;
			}
		}
		return false;
	}
}
