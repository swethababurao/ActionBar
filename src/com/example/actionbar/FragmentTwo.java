package com.example.actionbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actionbar.R;

public class FragmentTwo extends Fragment {

	String fAddress;
	String fWebsite;
	TextView website;
	TextView address;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			fAddress = getArguments().getString("address");
			fWebsite = getArguments().getString("website");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// MenuInflater inflater = getMenuInflater();

		View view = inflater.inflate(R.layout.list_details, container, false);
		website = (TextView) view.findViewById(R.id.website);
		address = (TextView) view.findViewById(R.id.address);

		website.setText(fWebsite);
		address.setText(fAddress);

		return view;

	}

}
