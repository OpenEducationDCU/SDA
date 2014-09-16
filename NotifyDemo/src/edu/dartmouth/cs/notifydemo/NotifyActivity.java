package edu.dartmouth.cs.notifydemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotifyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button buttonStartService = (Button)findViewById(R.id.startservice);
        Button buttonStopService = (Button)findViewById(R.id.stopservice);
        
        buttonStartService.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NotifyActivity.this, NotifyService.class);
				NotifyActivity.this.startService(intent);
			}});
        
        buttonStopService.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(NotifyService.ACTION);
				intent.putExtra(NotifyService.STOP_SERVICE_BROADCAST_KEY, NotifyService.RQS_STOP_SERVICE);
				sendBroadcast(intent);
			}});
        
    }
}