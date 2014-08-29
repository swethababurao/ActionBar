package com.example.actionbar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PopulateListCat20 extends Fragment {

	ArrayList<Dummy> qList;
	ListView listView;
	ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.list_view_cat20, container, false);

		listView = (ListView) view.findViewById(R.id.listcat20);

		qList = new ArrayList<Dummy>();
		new ReadRetaleJSONFeedTask()
				.execute("http://mynewapp.aws.af.cm/index.php/api/store/storeByCategory?mobile_user_id=141&set=10&city_id=28&latitude=12.9792&longitude=77.6450&category_id=19&page_no=1");
		return view;
	}

	private class ReadRetaleJSONFeedTask extends
			AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			return readJSONFeed(urls[0]);
		}

		@Override
		protected void onPostExecute(String result) {

			try {
				JSONObject jsonObj = new JSONObject(result);

				JSONArray jsonStoreArray = jsonObj.getJSONArray("stores");

				for (int i = 0; i < jsonStoreArray.length(); ++i) {

					JSONObject jsonStoreObject = jsonStoreArray
							.getJSONObject(i);
					JSONArray jsonCategoryArray = jsonStoreObject
							.getJSONArray("cats");
					for (int j = 0; j < jsonCategoryArray.length(); ++j) {
						JSONObject jsonCatObj = jsonCategoryArray
								.getJSONObject(j);
						String cat_id = jsonCatObj.getString("cat_id");
						Log.i("cat_id", cat_id);
						if (cat_id.equals("23")) {
							Log.i("Inside if", "Inside category if");
							String name = jsonStoreObject.getString("name");
							Log.i("name", name);
							String website = jsonStoreObject
									.getString("website");
							Log.i("website", website);
							String address = jsonStoreObject
									.getString("display_address");
							Log.i("address", address);
							Dummy obj = new Dummy(name, website, address);
							qList.add(obj);
						}

						Log.i("outside if", "outside category if");
					}
				}

			} catch (Exception e) {
				Log.d("ReadRetaleJSONFeedTask", e.getLocalizedMessage());
			}

			ListAdapter adapter = new ListAdapter(getActivity(), qList);
			listView.setAdapter(adapter);

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					// ArrayList<Dummy> xList;
					// xList = pList;
					((MainActivity) getActivity()).callFragmentTwo(qList,
							position);
				}

			});
		}

		public String readJSONFeed(String URL) {
			StringBuilder stringBuilder = new StringBuilder();
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(URL);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream inputStream = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(inputStream));
					String line;
					while ((line = reader.readLine()) != null) {
						stringBuilder.append(line);
					}
					inputStream.close();
				} else {
					Log.d("JSON", "Failed to download file");
				}
			} catch (Exception e) {
				Log.d("readJSONFeed", e.getLocalizedMessage());
			}
			// Rate.setText(stringBuilder.toString());
			return stringBuilder.toString();
		}

	}

}
