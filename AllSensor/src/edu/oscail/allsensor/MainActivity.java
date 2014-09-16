package edu.oscail.allsensor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends ListActivity {
	private SensorManager mSensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		Iterator<Sensor> iter = deviceSensors.iterator();
		ArrayList<String> sensorList = new ArrayList<String>();
	
		
		while (iter.hasNext())
		{
			 Sensor s = (Sensor)iter.next();
			 sensorList.add(s.getName());
			
		}
			

		String[] listString = sensorList.toArray( new String[sensorList.size()] );
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				listString));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
				
				

	}
}
