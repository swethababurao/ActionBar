package com.example.actionbar.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentPagerAdapter {

	private List<Fragment> mFragments;

	public PageAdapter(FragmentManager fm, List<Fragment> iFragments) {
		super(fm);
		mFragments = iFragments;
	}

	@Override
	public Fragment getItem(int iPosition) {
		// TODO Auto-generated method stub
		Log.i("PageAdapter", "Inside getItem");
		return this.mFragments.get(iPosition);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub'
		return this.mFragments.size();
	}

}
