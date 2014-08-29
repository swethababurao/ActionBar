package com.example.actionbar;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	public static class ViewHolder {
		TextView name;
	}

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ArrayList<Dummy> aList = new ArrayList<Dummy>();

	public ListAdapter(Context context, ArrayList<Dummy> pList) {
		mContext = context;
		aList = pList;
		mLayoutInflater = LayoutInflater.from(this.mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return aList.size();
	}

	@Override
	public Dummy getItem(int position) {
		// TODO Auto-generated method stub
		return aList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		ViewHolder holder = null;
		if (v == null) {
			v = mLayoutInflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.name = (TextView) v.findViewById(R.id.name);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}

		Dummy item = getItem(position);
		holder.name.setText(item.getName());

		return v;
	}

}
