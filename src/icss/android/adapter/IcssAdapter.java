package icss.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class IcssAdapter<T> extends IcssBaseAdapter<T>{
	public IcssAdapter(Context context ,List<T> list,int layoutId) {
		super(context,list,layoutId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View view, ViewGroup convertView) {
		// TODO Auto-generated method stub
		viewholder=ViewHolder.get(context, view, convertView, layoutId, position);
		getview(position);
		return viewholder.getmConvertView();
	}
	public abstract void getview(int position);
}
