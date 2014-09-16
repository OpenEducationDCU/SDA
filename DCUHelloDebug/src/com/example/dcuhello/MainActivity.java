package com.example.dcuhello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.helloWorldDebug.R;

public class MainActivity extends Activity {

private static final String TAG = "HelloWorldTag";

protected void onCreate(Bundle savedInstanceState)
{
	
	super.onCreate(savedInstanceState);
	Log.d(TAG, "About to Changed Text");
	
	TextView textView = (TextView) this.findViewById(R.id.textView1);
	textView.setText("CHANGING");
	
	setContentView(R.layout.activity_main);
	
	Log.d(TAG, "Changed Text");
	
	
	
	//Test adding
	/*int val1 = 0;
	int val2 = 0;
	int ans = 0;
	
	val1 = 2;
	val2 = 7;
	ans = val1 + val1;
	
	Log.d(TAG, "Answer is " + ans);*/
	
	
}
}