package course.examples.networking.xml;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


	public class NetworkingXMLActivity extends ListActivity
	{
		private TextView mTextView;

		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			new HttpGetTask().execute();
		}
		// Uses AsyncTask to create a task away from the main UI thread. This task takes a
		// URL string and uses it to create an HttpUrlConnection. Once the connection
		// has been established, the AsyncTask downloads the contents of the webpage as
		// an InputStream. Finally, the InputStream is converted into a string, which is
		// displayed in the UI by the AsyncTask's onPostExecute method.

		private class HttpGetTask extends AsyncTask<Void, Void, List>
		{

			private static final String TAG = "HttpGetTask";

			// Get your own user name at http://www.geonames.org/login
			private static final String USER_NAME = "YOUR_NAME";
			private static final String URL = "http://api.geonames.org/earthquakes?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
					+ USER_NAME;



			private static final String EARTHQUAKE_TAG = "earthquakes";
			private static final String MAGNITUDE_TAG = "magnitude";
			private static final String LONGITUDE_TAG = "lng";
			private static final String LATITUDE_TAG = "lat";
			private String mLat, mLng, mMag;
			private boolean mIsParsingLat, mIsParsingLng, mIsParsingMag;
			private final List<String> mResults = new ArrayList<String>();
			private  final String NS = null;

			@Override


			protected List doInBackground(Void... params)
			{
				String data = "";
				HttpURLConnection httpUrlConnection = null;
				InputStream in = null;
				List result = null;

				try
				{
					httpUrlConnection = (HttpURLConnection) new URL(URL)
							.openConnection();
					httpUrlConnection.setReadTimeout(10000 /* milliseconds */);
					httpUrlConnection.setConnectTimeout(15000 /* milliseconds */);
					httpUrlConnection.setRequestMethod("GET");
					httpUrlConnection.setDoInput(true);
					// Starts the query
					httpUrlConnection.connect();
					int response = httpUrlConnection.getResponseCode();


					in = httpUrlConnection.getInputStream();
					//convert stream into a string

					//  data = readStream(in);
					try
					{
						in = httpUrlConnection.getInputStream();
						XmlPullParser parser = Xml.newPullParser();
						parser.setInput(in, null);
						result = getDataFromXML(parser);
					} catch (Exception e)
					{
						e.printStackTrace();
						in.close();
					}


				} catch (Exception e)
				{
					e.printStackTrace();
				}
				//	now we have the stream, we need to parse it

				return result;
			}


			protected void onPostExecute(List result)
			{
				if (result != null)
				{
					setListAdapter(new ArrayAdapter<String>(
							NetworkingXMLActivity.this,
							R.layout.list_item, result));
				}

			}


			public List<String> getDataFromXML(XmlPullParser xpp)
					throws IOException
			{

				try
				{
// Get the first Parser event and start iterating over the XML document
					int eventType = xpp.getEventType();

					while (eventType != XmlPullParser.END_DOCUMENT) {

						if (eventType == XmlPullParser.START_TAG) {
							startTag(xpp.getName());
						} else if (eventType == XmlPullParser.END_TAG) {
							endTag(xpp.getName());
						} else if (eventType == XmlPullParser.TEXT) {
							text(xpp.getText());
						}
						eventType = xpp.next();
					}
					return mResults;
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				}
				return null;
			}



			public void startTag(String localName)
			{
				if (localName.equals(LATITUDE_TAG))
				{
					mIsParsingLat = true;
				} else if (localName.equals(LONGITUDE_TAG))
				{
					mIsParsingLng = true;
				} else if (localName.equals(MAGNITUDE_TAG))
				{
					mIsParsingMag = true;
				}
			}

			public void text(String text)
			{
				if (mIsParsingLat)
				{
					mLat = text.trim();
				} else if (mIsParsingLng)
				{
					mLng = text.trim();
				} else if (mIsParsingMag)
				{
					mMag = text.trim();
				}
			}

			public void endTag(String localName)
			{
				if (localName.equals(LATITUDE_TAG))
				{
					mIsParsingLat = false;
				} else if (localName.equals(LONGITUDE_TAG))
				{
					mIsParsingLng = false;
				} else if (localName.equals(MAGNITUDE_TAG))
				{
					mIsParsingMag = false;
				} else if (localName.equals("earthquake"))
				{
					mResults.add(MAGNITUDE_TAG + ":" + mMag + "," + LATITUDE_TAG + ":"
							+ mLat + "," + LONGITUDE_TAG + ":" + mLng);
					mLat = null;
					mLng = null;
					mMag = null;
				}
			}
		}
	}

