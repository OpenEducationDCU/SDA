

package course.examples.networking.volley;

import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;





public class NetworkVolleyActivity extends ListActivity
{
    private TextView mTextView;

    private static final String USER_NAME = "YOUR_NAME";
    private static final String s_URL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username="
            + USER_NAME;
    private static final String LONGITUDE_TAG = "lng";
    private static final String LATITUDE_TAG = "lat";
    private static final String MAGNITUDE_TAG = "magnitude";
    private static final String EARTHQUAKE_TAG = "earthquakes";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sendRequest();


    }


    public void sendRequest()
    {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        if (networkInfo != null && networkInfo.isConnected())
        {
            StringRequest stringRequest = new StringRequest(s_URL,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            List result =parseResponse(response);
                            setListAdapter(new ArrayAdapter<String>(
                                    NetworkVolleyActivity.this,
                                    R.layout.list_item, result));
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(NetworkVolleyActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            requestQueue.add(stringRequest);
        } else
        {
            Toast.makeText(NetworkVolleyActivity.this, "Add Error Message: Network down", Toast.LENGTH_LONG).show();
        }

    }

    public List<String> parseResponse(String jsonString)

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
                        + earthquake.get(MAGNITUDE_TAG) + ","
                        + LATITUDE_TAG + ":"
                        + earthquake.getString(LATITUDE_TAG) + ","
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


