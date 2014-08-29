package com.example.actionbar;

import java.util.ArrayList;
import java.util.List;

import com.example.actionbar.adapter.PageAdapter;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	ListView listView;
	Fragment fragment, fragment1, fragment2;
	public static ArrayList<Dummy> mList;
	List<Fragment> mFragmentList;
	FragmentManager fragmentManager = getSupportFragmentManager();
	public static PageAdapter mPageAdapter;
	ViewPager mPager;

	private int mNotificationId = 100;
	private NotificationManager mNotificationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		createNotification();
		fragment = new PopulateList();
		fragment1 = new PopulateListCat20();

		mFragmentList = getFragments();
		mPageAdapter = new PageAdapter(fragmentManager, mFragmentList);
		mPager = (ViewPager) findViewById(R.id.viewpager);
		mPager.setAdapter(mPageAdapter);

		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// When swiping between pages, select the
				// corresponding tab.
				getActionBar().setSelectedNavigationItem(position);
			}
		});

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		ActionBar.Tab mListTabCat19 = actionBar
				.newTab()
				.setText(getString(R.string.ui_tabname_category19))
				.setTabListener(
						new MyTabsListener(fragment, getApplicationContext()));
		ActionBar.Tab mListTabCat20 = actionBar
				.newTab()
				.setText(getString(R.string.ui_tabname_category20))
				.setTabListener(
						new MyTabsListener(fragment1, getApplicationContext()));

		actionBar.addTab(mListTabCat19);
		actionBar.addTab(mListTabCat20);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// menu.add(Menu.NONE, 1, Menu.NONE,
		// "Action Bar").setIcon(R.drawable.ic_action_help).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		MenuInflater inflater = getMenuInflater();
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.main, menu);
		// return super.onCreateOptionsMenu(menu);
		return true;
	}

	public void callFragmentTwo(ArrayList<Dummy> pList, int position) {
		Bundle arg = new Bundle();
		String website;
		String address;
		website = pList.get(position).getWebsite();
		Log.i("website", website);

		address = pList.get(position).getAddress();
		Log.i("address", address);

		arg.putString("website", website);
		arg.putString("address", address);

		fragment2 = new FragmentTwo();
		fragment2.setArguments(arg);
		// fragment.initialize();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_container, fragment2);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}

	private List<Fragment> getFragments() {
		List<Fragment> aFragmentList = new ArrayList<Fragment>();
		aFragmentList.add(fragment);
		aFragmentList.add(fragment1);

		return aFragmentList;

	}

	public class MyTabsListener extends Fragment implements
			ActionBar.TabListener {
		public Fragment fragment;
		public Context context;

		public MyTabsListener(Fragment fragment, Context context) {
			this.fragment = fragment;
			this.context = context;

		}

		public void onTabReselected(Tab tab,
				android.app.FragmentTransaction fragment) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "Reselected!", Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			Log.i("Inside onTabSelected", "Tab selected");

			// ft.add(mPageAdapter.getItem(tab.getPosition()), "");
			// // fragmentTransaction.addToBackStack(null);
			// ft.commit();
			mPager.setCurrentItem(tab.getPosition());

		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

	}

	public void createNotification() {

		NotificationCompat.Builder aBuilder = new NotificationCompat.Builder(
				this).setContentTitle("alarm").setContentText("Action Bar")
				.setSmallIcon(R.drawable.alarmnotification);

		Intent aIntent = new Intent(this, NotificationReceiveActivity.class);
		TaskStackBuilder aStackBuilder = TaskStackBuilder.create(this);
		aStackBuilder.addParentStack(NotificationReceiveActivity.class);
		aStackBuilder.addNextIntent(aIntent);

		PendingIntent aPendingIntent = aStackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		aBuilder.setContentIntent(aPendingIntent);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(mNotificationId, aBuilder.build());
	}

	public void cancelNotification() {
		mNotificationManager.cancel(mNotificationId);
	}

}
