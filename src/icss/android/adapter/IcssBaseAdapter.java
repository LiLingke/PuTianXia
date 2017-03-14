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
		//��������
		int visibleFirstPosi = listView.getFirstVisiblePosition();  //�õ���һ������ʾ�ؼ���λ�Z
        int visibleLastPosi = listView.getLastVisiblePosition();  //�õ��c��һ���ɼ��Ŀؼ���λ�Z
        if (posi >= visibleFirstPosi && posi <= visibleLastPosi) {  
            View view = listView.getChildAt(posi - visibleFirstPosi);  //�ڿɼ�λ��ʱ
            updata.updateOne(view);
        }
	}
	public interface Iupdata {
		void updateOne(View view);
	}
}
