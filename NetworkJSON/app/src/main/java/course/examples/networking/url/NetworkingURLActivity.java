package course.examples.networking.url;

/* created by ckirwan */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NetworkingURLActivity extends Activity
{
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);


        final Button loadButton = (Button) findViewById(R.id.button1);
        loadButton.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected())
                {
                    // fetch data
                    startActivity(new Intent(NetworkingURLActivity.this,
                            NetworkJsonActivity.class));
                } else
                {
                    // display error
                }

            }
        });
    }


}