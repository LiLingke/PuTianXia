package icss.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

abstract public class IcssBaseAdapter<T> extends BaseAdapter{
	protected List<T> list;
	protected ViewHolder viewholder;
	protected Context context ;
	protected int layoutId;
	public IcssBaseAdapter(Context context ,List<T> list,int layoutId) {
		// TODO Auto-generated constructor stub
		this.list=list;
		this.context=context;
		this.layoutId=layoutId;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	public void updataView(int posi, ListView listView,Iupdata updata) {  
		//单条更新
		int visibleFirstPosi = listView.getFirstVisiblePosition();  //得到第一个可显示控件的位罿
        int visibleLastPosi = listView.getLastVisiblePosition();  //得到朿后一个可见的控件的位罿
        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {  
            View view = listView.getChildAt(posi - visibleFirstPosi);  //在可见位置时
            updata.updateOne(view);
        }
	}
	public interface Iupdata {
		void updateOne(View view);
	}
}
