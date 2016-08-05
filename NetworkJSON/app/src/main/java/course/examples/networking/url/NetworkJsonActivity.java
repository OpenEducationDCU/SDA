

package course.examples.networking.url;


import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* created by ckirwan */

public class NetworkJsonActivity extends ListActivity
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
        private static final String USER_NAME = "ckirwan";
        private static final String URL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
                + USER_NAME;
        private static final String LONGITUDE_TAG = "lng";
        private static final String LATITUDE_TAG = "lat";
        private static final String MAGNITUDE_TAG = "magnitude";
        private static final String EARTHQUAKE_TAG = "earthquakes";

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

                data = readStream(in);
                result = getDataFromJson(data);
                //	now we have the stream, we need to parse it


            } catch (MalformedURLException exception)
            {
                Log.e(TAG, "MalformedURLException");
            } catch (IOException exception)
            {
                Log.e(TAG, "IOException");
            } finally
            {
                if (null != httpUrlConnection)
                    httpUrlConnection.disconnect();
                if (in != null)
                {
                    try

                    {
                        in.close();
                    } catch (Exception e)
                    {

                    }
                }
            }
            return result;
        }


        protected void onPostExecute(List result)
        {

            setListAdapter(new ArrayAdapter<String>(
                    NetworkJsonActivity.this,
                    R.layout.list_item, result));

        }

        private String readStream(InputStream in)
        {
            BufferedReader reader = null;
            StringBuffer data = new StringBuffer("");
            try
            {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    data.append(line);
                }
            } catch (IOException e)
            {
                //Log.e(TAG, "IOException");
            } finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return data.toString();
        }


        public List<String> getDataFromJson(String jsonString)
                throws IOException
        {

            List<String> result = new ArrayList<String>();


            try
            {

                // Get top-level JSON Object - a Map
                JSONObject responseObject = (JSONObject) new JSONTokener(
                        jsonString).nextValue();

                // Extract value of "earthquakes" key -- a List
                JSONArray earthquakes = responseObject
                        .getJSONArray(EARTHQUAKE_TAG);

                // Iterate over earthquakes list
                for (int idx = 0; idx < earthquakes.length(); idx++)
                {

                    // Get single earthquake data - a Map
                    JSONObject earthquake = (JSONObject) earthquakes.get(idx);

                    // Summarize earthquake data as a string and add it to
                    // result
                    result.add(MAGNITUDE_TAG + ":"
                            + earthquake.get(MAGNITUDE_TAG) + ", "
                            + LATITUDE_TAG + ":"
                            + earthquake.getString(LATITUDE_TAG) + ", "
                            + LONGITUDE_TAG + ":"
                            + earthquake.get(LONGITUDE_TAG));
                }
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
            return result;
        }
    }
}

