package sensor.oscail.edu.allsensorfrag;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivityFragment extends ListFragment {
    private SensorManager mSensorManager;


    public MainActivityFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Iterator<Sensor> iter = deviceSensors.iterator();
        ArrayList<String> sensorList = new ArrayList<String>();


        while (iter.hasNext()) {
            Sensor s = (Sensor) iter.next();
            sensorList.add(s.getName());

        }
        String[] listString = sensorList.toArray(new String[sensorList.size()]);

        setListAdapter(new ArrayAdapter<String>(inflater.getContext(), R.layout.fraglist_main,
                listString));

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void onListItemClick(ListView l, View v, int position, long id) {


        Toast.makeText(getActivity().getApplicationContext(),
                ((TextView) v).getText(), Toast.LENGTH_SHORT).show();


    }

}




