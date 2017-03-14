package icss.android.adapter;

import java.util.ArrayList;

import com.example.putianxia.R;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class MyGridAdapter extends BaseAdapter{
	public Context context;
	 //存放所有选择的照片
	private ArrayList<String> allSelectedPicture = new ArrayList<String>();
    //存放从选择界面选择的照片
    private ArrayList<String> selectedPicture = new ArrayList<String>();
	public MyGridAdapter(Context context, ArrayList<String>allSelectedPicture,ArrayList<String>selectedPicture){
		this.context = context;
		this.allSelectedPicture = allSelectedPicture;
		this.selectedPicture = selectedPicture;
	}

	public LayoutInflater layoutInflater = LayoutInflater.from(context);
	public int getCount() {
		return allSelectedPicture.size()+1;
	}

	@Override
	public Object getItem(int positon) {
		return positon;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.childgrid_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.child_iv);
			holder.btn = (Button) convertView.findViewById(R.id.child_delete);
			holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		return null;
	}
	
	  public class ViewHolder {
	        public ImageView image;
	        public Button btn ;
	    }

}
