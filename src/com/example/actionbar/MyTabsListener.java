package com.example.actionbar;

import com.example.actionbar.adapter.PageAdapter;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;

import android.content.Context;

import android.widget.Toast;

public class MyTabsListener extends Fragment implements ActionBar.TabListener {
	public Fragment fragment;
	public Context context;
	PageAdapter mPageAdapter;

	public MyTabsListener(Fragment fragment, Context context) {
		this.fragment = fragment;
		this.context = context;

	}

	// public void onTabSelected(Tab tab, FragmentTransaction ft) {
	// // TODO Auto-generated method stub
	//
	// if (fragment == null) {
	// // fragment= Fragment.instantiate(context,
	// // fragment.getClass().getName());
	// Log.i("Inside If", "Inside If");
	// ft.add(R.id.fragment_container, fragment);
	// ft.commit();
	// Toast.makeText(context, "Selected!", Toast.LENGTH_SHORT).show();
	// } else {
	// Log.i("Inside Else", "Else");
	//
	// ft.add(R.id.fragment_container, fragment);
	// ft.commit();
	// }
	//
	// }
	//
	// public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	// // TODO Auto-generated method stub
	// if (fragment != null) {
	// Toast.makeText(context, "Unselected!", Toast.LENGTH_SHORT).show();
	// ft.detach(fragment);
	// }
	// }

	public void onTabReselected(Tab tab,
			android.app.FragmentTransaction fragment) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "Reselected!", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// mPageAdapter.getItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}